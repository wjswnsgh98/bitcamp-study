package project.dao;

import org.apache.ibatis.annotations.Param;
import project.vo.Book;

import java.util.List;

public interface BookDao {
  int insert(Book Book);
  List<Book> findAll();
  Book findBy(@Param("bookTitle") String bookTitle, @Param("author") String author);
  int update(Book book);
  int delete(int no);
  static final String[][] BOOKS = {{"노인과바다", "3"}, {"멈추지않는도전", "3"}, {"챔스", "3"}};
}