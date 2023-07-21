package project.handler;

import java.util.List;
import project.vo.Book;
import util.BreadcrumbPrompt;

public class BookRentListener extends AbstractBookListener{
  public BookRentListener(List<Book> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("대여가능한 도서 제목, 수량");
    System.out.println("---------------------------------------");

    for (int i = 0; i < BOOKS.length; i++) {
      System.out.print(BOOKS[i][0] + " " + BOOKS[i][1]);
      System.out.println();
    }
  }
}