package project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import project.vo.Book;

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
      stmt.setString(3, book.getName());

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
        "select booktitle, author, name, rental_date, return_date"
            + " from project_book"
            + " order by name desc")) {

      try(ResultSet rs = stmt.executeQuery()){
        List<Book> list = new ArrayList<>();
        while (rs.next()) {
          Book book = new Book();
          book.setBookTitle(rs.getString("booktitle"));
          book.setAuthor(rs.getString("author"));
          book.setName(rs.getString("name"));
          book.setRentalDate(rs.getTimestamp("rental_date"));
          book.setReturnDate(rs.getTimestamp("return_date"));

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
        "select booktitle, author, name, rental_date, return_date"
            + " from project_book"
            + " where name=?"
            + " order by name desc")) {

      stmt.setString(1, str);
      try(ResultSet rs = stmt.executeQuery()){
        if (rs.next()) {
          Book book = new Book();
          book.setBookTitle(rs.getString("booktitle"));
          book.setAuthor(rs.getString("author"));
          book.setName(rs.getString("name"));
          book.setRentalDate(rs.getTimestamp("rental_date"));
          book.setReturnDate(rs.getTimestamp("return_date"));

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