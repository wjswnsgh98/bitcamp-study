package project.handler;

import java.util.List;
import project.vo.Book;
import util.BreadcrumbPrompt;

public class BookListListener extends AbstractBookListener{
  public BookListListener(List<Book> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("제목, 저자, 대여자 이름, 대여일, 반납일");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Book book = this.list.get(i);
      System.out.printf("%s, %s, %s, %tY-%4$tm-%4$td, %tY-%5$tm-%5$td\n",
          book.getBookTitle(),
          book.getAuthor(),
          book.getName(),
          book.getRentalDate(),
          book.getReturnDate());
    }
  }
}