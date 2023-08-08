package project.handler;

import java.io.IOException;
import project.dao.BookDao;
import project.vo.Book;
import util.ActionListener;
import util.BreadcrumbPrompt;
import util.DataSource;

public class BookDeleteListener implements ActionListener{
  BookDao bookDao;
  DataSource ds;

  public BookDeleteListener(BookDao bookDao, DataSource ds) {
    this.bookDao = bookDao;
    this.ds = ds;
  }

  String[][] BOOKS = BookDao.BOOKS;

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException{
    String lender = prompt.inputString("대여자 이름? ");
    Book book = bookDao.findBy(lender);

    try {
      if (bookDao.delete(book) == 0) {
        prompt.println("해당 이름의 대여자는 없습니다!");
      } else {
        prompt.println("반납 완료했습니다.");
      }
      ds.getConnection().commit();

    } catch (Exception e) {
      try {ds.getConnection().rollback();} catch (Exception e2) {}
      throw new RuntimeException(e);
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