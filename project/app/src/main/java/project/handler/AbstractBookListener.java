package project.handler;

import java.util.List;
import project.vo.Book;
import util.ActionListener;

public abstract class AbstractBookListener implements ActionListener{
  protected List<Book> list;
  protected static String[][] BOOKS = {{"노인과바다", "3"}, {"멈추지않는도전", "3"}, {"챔스", "3"}};

  public AbstractBookListener(List<Book> list) {
    this.list = list;
  }

  protected Book findBy(String str) {
    for (int i = 0; i < this.list.size(); i++) {
      Book b = this.list.get(i);
      if (b.getName().equals(str)) {
        return b;
      }
    }
    return null;
  }
}
