package project.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
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
    try (Statement stmt = con.createStatement()) {

      stmt.executeUpdate(String.format(
          "insert into project_book(booktitle,author,name)"
              + " values('%s','%s','%s')",
              book.getBookTitle(),
              book.getAuthor(),
              book.getName()));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  @Override
  public List<Book> list() {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select booktitle, author, name, rental_date, return_date"
                + " from project_book"
                + " order by name desc")) {

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

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public Book findBy(String str) {
    try (Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
            "select booktitle, author, name, rental_date, return_date"
                + " from project_book"
                + " where name=" + str
                + " order by name desc")) {

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

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public int delete(String str) {
    try (Statement stmt = con.createStatement()) {
      return stmt.executeUpdate(String.format("delete from project_book where name=%s", str));

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}