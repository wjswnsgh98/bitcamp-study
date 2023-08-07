package project.handler;

import project.dao.BookDao;
import util.ActionListener;
import util.BreadcrumbPrompt;

public class BookRentListener implements ActionListener{
  BookDao bookDao;

  public BookRentListener(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("대여가능한 도서 제목, 수량");
    System.out.println("---------------------------------------");

    String[][] BOOKS = BookDao.BOOKS;

    for (int i = 0; i < BOOKS.length; i++) {
      System.out.print(BOOKS[i][0] + " " + BOOKS[i][1]);
      System.out.println();
    }
  }
}