package project.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.BookDao;
import project.vo.Book;
import project.vo.Member;
import util.ActionListener;
import util.BreadcrumbPrompt;
import util.Component;

@Component("/book/delete")
public class BookDeleteListener implements ActionListener{
  BookDao bookDao;
  SqlSessionFactory sqlSessionFactory;

  public BookDeleteListener(BookDao bookDao, SqlSessionFactory sqlSessionFactory) {
    this.bookDao = bookDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  String[][] BOOKS = BookDao.BOOKS;

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException{
    Book book = new Book();
    book.setLender((Member) prompt.getAttribute("loginUser"));
    book.setBookTitle(prompt.inputString("도서 제목? "));
    book.setAuthor(prompt.inputString("저자? "));

    try {
      if (bookDao.delete(book) == 0) {
        prompt.println("해당 도서의 대여자가 없거나 삭제 권한이 없습니다!");
      } else {
        prompt.println("반납 완료했습니다.");
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
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