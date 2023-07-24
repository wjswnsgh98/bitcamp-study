package project.dao;

import java.util.List;
import project.vo.Book;

public interface BookDao {
  void insert(Book Book);
  List<Book> list();
  Book findBy(String str);
  String delete(String str);
}
