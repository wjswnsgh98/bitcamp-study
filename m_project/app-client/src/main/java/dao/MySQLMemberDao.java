package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import m_project.dao.MemberDao;
import m_project.vo.Member;

public class MySQLMemberDao implements MemberDao{
  Connection con;

  public MySQLMemberDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Member member) {
    try (Statement stmt = con.createStatement()) {

      stmt.executeUpdate(String.format(
          "insert into myapp_member(booktitle,author,name,p_num,gender)"
              + " values('%s','%s','%s','%s',%c')",
              member.getB_title(),
              member.getAuthor(),
              member.getName(),
              member.getP_num(),
              member.getGender()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Member> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select book_no, booktitle, author, name, p_num, gender"
                + " from myapp_member order by name asc")) {

      List<Member> list = new ArrayList<>();

      while (rs.next()) {
        Member m = new Member();
        m.setBook_no(rs.getInt("book_no"));
        m.setB_title(rs.getString("booktitle"));
        m.setAuthor(rs.getString("author"));
        m.setName(rs.getString("name"));
        m.setP_num(rs.getString("p_num"));
        m.setGender(rs.getString("gender").charAt(0));

        list.add(m);
      }

      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Member findBy(int no) {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select book_no, booktitle, author, name, p_num, gender"
                + " from myapp_member order by name asc")) {

      if (rs.next()) {
        Member m = new Member();
        m.setBook_no(rs.getInt("book_no"));
        m.setB_title(rs.getString("booktitle"));
        m.setAuthor(rs.getString("author"));
        m.setName(rs.getString("name"));
        m.setP_num(rs.getString("p_num"));
        m.setGender(rs.getString("gender").charAt(0));
        return m;
      }

      return null;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Member member) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format(
          "update myapp_member set"
              + " booktitle='%s',"
              + " author='%s',"
              + " name='%s',"
              + " p_num='%s',"
              + " gender='%c'"
              + " where member_no=%d",
              member.getB_title(),
              member.getAuthor(),
              member.getName(),
              member.getP_num(),
              member.getGender(),
              member.getBook_no()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try (Statement stmt = con.createStatement()) {

      return stmt.executeUpdate(String.format(
          "delete from myapp_member where book_no=%d",
          no));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}