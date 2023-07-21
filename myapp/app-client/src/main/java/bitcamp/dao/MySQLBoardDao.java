package bitcamp.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bitcamp.myapp.dao.BoardDao;
import bitcamp.myapp.vo.Board;

public class MySQLBoardDao implements BoardDao{
  Connection con;

  public MySQLBoardDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Board board) {
    try(Statement stmt = con.createStatement()){
      stmt.executeUpdate(String.format(
          "insert into myapp_board(title,content,writer,password) values('%s','%s','%s','%s')",
          board.getTitle(),
          board.getContent(),
          board.getWriter(),
          board.getPassword()));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public List<Board> list() {
    try(Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select board_no, title, content, writer category from myapp_board order by name asc")) {

      List<Board> list = new ArrayList<>();
      while(rs.next()) {
        Board b = new Board();
        b.setNo(rs.getInt("board_no"));
        b.setTitle(rs.getString("title"));
        b.setContent(rs.getString("content"));
        b.setWriter(rs.getString("writer"));

        list.add(b);
      }
      return list;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Board findBy(int no) {
    try(Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select board_no, title, content, writer from myapp_board where board_no=" + no)) {

      if(rs.next()) {
        Board b = new Board();
        b.setNo(rs.getInt("board_no"));
        b.setTitle(rs.getString("title"));
        b.setContent(rs.getString("content"));
        b.setWriter(rs.getString("writer"));
        return b;
      }
      return null;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int update(Board board) {
    try(Statement stmt = con.createStatement()){
      return stmt.executeUpdate(String.format(
          "update myapp_board set"
              + " title='%s',"
              + " content='%s',"
              + " writer='%s',"
              + " password='%s'"
              + " where board_no=%d",
              board.getTitle(),
              board.getContent(),
              board.getWriter(),
              board.getPassword(),
              board.getNo()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(int no) {
    try(Statement stmt = con.createStatement()){
      return stmt.executeUpdate(String.format(
          "delete from myapp_board where board_no=%d",no));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}