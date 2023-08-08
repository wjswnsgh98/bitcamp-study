package project.handler;

import java.io.IOException;
import project.dao.BookDao;
import project.vo.Book;
import util.ActionListener;
import util.BreadcrumbPrompt;

public class BookViewListener implements ActionListener{
  BookDao bookDao;

  public BookViewListener(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException{
    String lender = prompt.inputString("대여자 이름? ");

    Book book = bookDao.findBy(lender);
    if(book == null) {
      prompt.println("해당 이름의 대여자는 없습니다!");
      return;
    } else {
      prompt.printf("도서 제목: %s\n", book.getBookTitle());
      prompt.printf("저자: %s\n", book.getAuthor());
      prompt.printf("대여일: %tY-%1$tm-%1$td\n", book.getRentalDate());
      prompt.printf("반납일: %tY-%1$tm-%1$td\n", book.getReturnDate());
    }
  }
}
