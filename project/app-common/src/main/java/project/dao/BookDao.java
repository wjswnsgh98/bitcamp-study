package project.dao;

import java.util.List;
import project.vo.Book;

public interface BookDao {
  void insert(Book Book);
  List<Book> findAll();
  Book findBy(String booktitle, String author);
  int update(Book book);
  int delete(Book book);
}