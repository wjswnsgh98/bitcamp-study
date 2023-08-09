package project.handler;

import java.util.List;
import project.dao.BookDao;
import project.vo.Book;
import util.ActionListener;
import util.BreadcrumbPrompt;
import util.Component;

@Component("/book/list")
public class BookListListener implements ActionListener{
  BookDao bookDao;

  public BookListListener(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    prompt.println("---------------------------------------");
    prompt.println("제목, 저자, 대여자 이름, 대여일, 반납일");
    prompt.println("---------------------------------------");

    List<Book> list = bookDao.findAll();

    for (Book book : list) {
      prompt.printf("%s, %s, %s, %tY-%4$tm-%4$td, %tY-%5$tm-%5$td\n",
          book.getBookTitle(),
          book.getAuthor(),
          book.getLender().getName(),
          book.getRentalDate(),
          book.getReturnDate());
    }
  }
}