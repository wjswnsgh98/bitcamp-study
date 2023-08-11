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

@Component("/book/update")
public class BookUpdateServlet implements Servlet{
  BookDao bookDao;
  SqlSessionFactory sqlSessionFactory;

  public BookUpdateServlet(BookDao bookDao, SqlSessionFactory sqlSessionFactory) {
    this.bookDao = bookDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
    out.printf("<meta http-equiv='refresh' content='1;url=/book/list'>\n");
    out.println("<title>도서 대여</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>도서 대여 변경</h1>");
    try {
      if (bookDao.update(book) == 0) {
        out.println("<p>해당 도서가 없거나 변경 권한이 없습니다.</p>");
      } else {
        out.println("<p>변경했습니다!</p>");
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      out.println("<p>게시글 변경 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }
}