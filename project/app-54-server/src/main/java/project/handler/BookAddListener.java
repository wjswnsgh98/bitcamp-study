package project.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.BookDao;
import project.vo.Book;
import project.vo.Member;
import util.ActionListener;
import util.BreadcrumbPrompt;
import util.Component;

@Component("/book/add")
public class BookAddListener implements ActionListener{
  BookDao bookDao;
  SqlSessionFactory sqlSessionFactory;

  public BookAddListener(BookDao bookDao, SqlSessionFactory sqlSessionFactory) {
    this.bookDao = bookDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  String[][] BOOKS = BookDao.BOOKS;

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException{
    Book book = new Book();
    String bTitle = prompt.inputString("도서 제목? ");
    boolean foundBook = false; // 책을 찾았는지 확인하기 위한 변수

    for (int i = 0; i < BOOKS.length; i++) {
      String str = BOOKS[i][0];
      if (str.equals(bTitle)) {
        int count = Integer.parseInt(BOOKS[i][1]);
        if (count > 0) {
          book.setBookTitle(bTitle);
          count--; // 책의 수량을 1 감소시킴
          BOOKS[i][1] = Integer.toString(count); // 수정된 수량을 다시 BOOKS 배열에 반영
          foundBook = true;
          break;
        } else {
          System.out.println("해당 제목의 도서는 모두 대여 중입니다!");
          return;
        }
      }
    }

    if (!foundBook) {
      System.out.println("해당 제목의 도서가 없습니다!");
      return;
    }

    book.setAuthor(prompt.inputString("저자? "));
    book.setLender((Member) prompt.getAttribute("loginUser"));

    try {
      bookDao.insert(book);
      //      Thread.sleep(5000);

      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}