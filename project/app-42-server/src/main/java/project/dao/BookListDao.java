package project.dao;

import java.util.ArrayList;
import java.util.List;
import project.vo.Book;
import util.JsonDataHelper;

public class BookListDao implements BookDao{
  String filename;
  ArrayList<Book> list = new ArrayList<>();

  public BookListDao(String filename) {
    this.filename = filename;
    JsonDataHelper.loadJson(filename, list, Book.class);
  }

  @Override
  public void insert(Book book) {
    book.setNo(Book.bookNo++);
    book.setRentalDate(System.currentTimeMillis());
    book.setReturnDate(book.getRentalDate() + (5 * 24 * 60 * 60 * 1000));
    this.list.add(book);
    JsonDataHelper.saveJson(filename, list);
  }

  @Override
  public List<Book> list() {
    return this.list;
  }

  @Override
  public Book findBy(String str) {
    for (int i = 0; i < this.list.size(); i++) {
      Book book = this.list.get(i);
      if (book.getName().equals(str)) {
        return book;
      }
    }
    return null;
  }

  @Override
  public String delete(String str) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getName().equals(str)) {
        list.remove(i);
        JsonDataHelper.saveJson(filename, list);
        return str;
      }
    }
    return null;
  }
}