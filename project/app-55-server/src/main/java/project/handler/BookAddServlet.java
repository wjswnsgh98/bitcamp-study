package project.handler;

import java.io.PrintWriter;
import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.BookDao;
import project.vo.Book;
import project.vo.Member;
import util.Component;
import util.HttpServletRequest;
import util.HttpServletResponse;
import util.Servlet;

@Component("/book/add")
public class BookAddServlet implements Servlet{
  BookDao bookDao;
  SqlSessionFactory sqlSessionFactory;

  public BookAddServlet(BookDao bookDao, SqlSessionFactory sqlSessionFactory) {
    this.bookDao = bookDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  String[][] BOOKS = BookDao.BOOKS;

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception{
    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    Book book = new Book();
    book.setBookTitle(request.getParameter("booktitle"));
    book.setAuthor(request.getParameter("author"));
    book.setLender(loginUser);

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.printf("<meta http-equiv='refresh' content='1;url=/book/rent'>\n");
    out.println("<title>도서</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>도서 대여 등록</h1>");

    boolean foundBook = false; // 책을 찾았는지 확인하기 위한 변수

    for (int i = 0; i < BOOKS.length; i++) {
      String str = BOOKS[i][0];
      if (str.equals(book.getBookTitle())) {
        int count = Integer.parseInt(BOOKS[i][1]);
        if (count > 0) {
          book.setBookTitle(book.getBookTitle());
          count--; // 책의 수량을 1 감소시킴
          BOOKS[i][1] = Integer.toString(count); // 수정된 수량을 다시 BOOKS 배열에 반영
          foundBook = true;
          break;
        } else {
          out.println("<p>해당 제목의 도서는 모두 대여 중입니다!</p>");
          return;
        }
      }
    }

    if (!foundBook) {
      out.println("<p>해당 제목의 도서가 없습니다!</p>");
      return;
    }

    try {
      bookDao.insert(book);
      sqlSessionFactory.openSession(false).commit();
      out.println("<p>등록 성공입니다!</p>");

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      out.println("<p>등록 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }
}