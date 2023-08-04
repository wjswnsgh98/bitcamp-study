package project.handler;

import project.dao.BookDao;
import project.vo.Book;
import util.ActionListener;
import util.BreadcrumbPrompt;

public class BookDeleteListener implements ActionListener{
  BookDao bookDao;

  public BookDeleteListener(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  String[][] BOOKS = BookDao.BOOKS;

  @Override
  public void service(BreadcrumbPrompt prompt) {
    String lender = prompt.inputString("대여자 이름? ");

    Book book = bookDao.findBy(lender);
    if (bookDao.delete(book) == 0) {
      System.out.println("해당 이름의 대여자는 없습니다!");
      return;
    } else {
      System.out.println("반납 완료했습니다.");
    }

    // BOOKS에서 같은 도서 제목의 수량을 1 증가
    for (int i = 0; i < BOOKS.length; i++) {
      if (book.getBookTitle().equals(BOOKS[i][0])) {
        int count = Integer.parseInt(BOOKS[i][1]);
        BOOKS[i][1] = String.valueOf(count + 1);
        break; // 해당 도서를 찾았으므로 더 이상 반복할 필요가 없음
      }
    }
  }
}