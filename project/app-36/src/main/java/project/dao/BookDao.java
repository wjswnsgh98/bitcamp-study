package project.dao;

import java.util.List;
import project.vo.Book;

public interface BookDao {
  void insert(Book Book);
  List<Book> list();
  Book findBy(String str);
  String delete(String str);
  static String[][] BOOKS = {{"노인과바다", "3"}, {"멈추지않는도전", "3"}, {"챔스", "3"}};
}
