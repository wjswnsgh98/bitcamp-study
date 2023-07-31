package project.handler;

import java.util.List;
import project.dao.BookDao;
import project.vo.Book;
import util.ActionListener;
import util.BreadcrumbPrompt;

public class BookListListener implements ActionListener{
  BookDao bookDao;

  public BookListListener(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("제목, 저자, 대여자 이름, 대여일, 반납일");
    System.out.println("---------------------------------------");

    List<Book> list = bookDao.list();

    for (Book book : list) {
      System.out.printf("%s, %s, %d, %tY-%4$tm-%4$td, %tY-%5$tm-%5$td\n",
          book.getBookTitle(),
          book.getAuthor(),
          book.getName().getName(),
          book.getRentalDate(),
          book.getReturnDate());
    }
  }
}