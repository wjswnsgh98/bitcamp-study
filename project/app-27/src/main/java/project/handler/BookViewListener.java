package project.handler;

import java.util.List;
import project.vo.Book;
import util.BreadcrumbPrompt;

public class BookViewListener extends AbstractBookListener{
  public BookViewListener(List<Book> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String lender = prompt.inputString("대여자 이름? ");

    Book book = this.findBy(lender);
    if(lender.equals("")) {
      System.out.println("해당 이름의 대여자는 없습니다!");
      return;
    }
    System.out.printf("도서 제목: %s\n", book.getBookTitle());
    System.out.printf("저자: %s\n", book.getAuthor());
    System.out.printf("대여일: %tY-%1$tm-%1$td\n", book.getRentalDate());
    System.out.printf("반납일: %tY-%1$tm-%1$td\n", book.getReturnDate());
    return;
  }
}
