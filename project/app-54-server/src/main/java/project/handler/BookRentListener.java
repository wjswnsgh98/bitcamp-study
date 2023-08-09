package project.handler;

import project.dao.BookDao;
import util.ActionListener;
import util.BreadcrumbPrompt;
import util.Component;

@Component("/book/rent")
public class BookRentListener implements ActionListener{
  BookDao bookDao;

  public BookRentListener(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    prompt.println("---------------------------------------");
    prompt.println("대여가능한 도서 제목, 수량");
    prompt.println("---------------------------------------");

    String[][] BOOKS = BookDao.BOOKS;

    for (int i = 0; i < BOOKS.length; i++) {
      prompt.print(BOOKS[i][0] + " " + BOOKS[i][1]);
      prompt.println("");
    }
  }
}