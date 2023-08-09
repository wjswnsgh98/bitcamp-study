package project.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.BookDao;
import project.vo.Book;
import util.ActionListener;
import util.BreadcrumbPrompt;
import util.Component;

@Component("/book/view")
public class BookViewListener implements ActionListener{
  BookDao bookDao;
  SqlSessionFactory sqlSessionFactory;

  public BookViewListener(BookDao bookDao, SqlSessionFactory sqlSessionFactory) {
    this.bookDao = bookDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException{
    String booktitle = prompt.inputString("도서 제목? ");
    String author = prompt.inputString("저자? ");

    Book book = bookDao.findBy(booktitle, author);
    if(book == null) {
      prompt.println("해당 도서의 대여자가 없습니다!");
      return;
    }
    prompt.printf("도서 제목: %s\n", book.getBookTitle());
    prompt.printf("저자: %s\n", book.getAuthor());
    prompt.printf("대여일: %tY-%1$tm-%1$td\n", book.getRentalDate());
    prompt.printf("반납일: %tY-%1$tm-%1$td\n", book.getReturnDate());
  }
}
