package project.dao;

import project.vo.Book;

import java.util.List;

public interface BookDao {
  int insert(Book Book);
  List<Book> findAll();
  Book findBy(int no);
  Book findByTitle(String bookTitle);
  int update(Book book);
  int updateIncreaseCount(String bookTitle);
  int updateDecreaseCount(String bookTitle);
  int delete(int no);
}