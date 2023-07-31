package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import project.vo.Book;
import project.vo.Member;

public class MySQLBookDao implements BookDao{
  Connection con;

  public MySQLBookDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Book book) {
    try (PreparedStatement stmt = con.prepareStatement(
        "insert into project_book(booktitle,author,name)"
            + " values(?,?,?)")) {

      stmt.setString(1, book.getBookTitle());
      stmt.setString(2, book.getAuthor());
      stmt.setInt(3, book.getName().getNo());

      stmt.executeUpdate();
      stmt.executeUpdate("update project_book set"
          + " return_date=rental_date + INTERVAL 7 DAY");

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Book> list() {
    try (PreparedStatement stmt = con.prepareStatement(
        "select book.booktitle, book.author, book.rental_date, book.return_date,"
            + "m.member_no, m.name"
            + " from project_book book inner join project_member m on book.name=m.member_no"
            + " order by name desc")) {

      try(ResultSet rs = stmt.executeQuery()){
        List<Book> list = new ArrayList<>();
        while (rs.next()) {
          Book book = new Book();
          book.setBookTitle(rs.getString("booktitle"));
          book.setAuthor(rs.getString("author"));
          book.setRentalDate(rs.getTimestamp("rental_date"));
          book.setReturnDate(rs.getTimestamp("return_date"));

          Member name = new Member();
          name.setNo(rs.getInt("member_no"));
          name.setName(rs.getString("name"));
          book.setName(name);

          list.add(book);
        }
        return list;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Book findBy(String str) {
    try (PreparedStatement stmt = con.prepareStatement(
        "select book.booktitle, book.author, book.rental_date, book.return_date, m.member_no, m.name"
            + " from project_book book inner join project_member m on book.name=m.member_no"
            + " where name=?"
            + " order by name desc")) {

      stmt.setString(1, str);
      try(ResultSet rs = stmt.executeQuery()){
        if (rs.next()) {
          Book book = new Book();
          book.setBookTitle(rs.getString("booktitle"));
          book.setAuthor(rs.getString("author"));
          book.setRentalDate(rs.getTimestamp("rental_date"));
          book.setReturnDate(rs.getTimestamp("return_date"));

          Member bname = new Member();
          bname.setNo(rs.getInt("member_no"));
          bname.setName(rs.getString("name"));
          book.setName(bname);

          return book;
        }
        return null;
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(String str) {
    try (PreparedStatement stmt = con.prepareStatement("delete from project_book where name=?")) {
      stmt.setString(1, str);
      return stmt.executeUpdate();

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}