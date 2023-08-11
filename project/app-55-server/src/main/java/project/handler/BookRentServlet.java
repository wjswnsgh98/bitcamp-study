package project.handler;

import java.io.PrintWriter;
import project.dao.BookDao;
import util.Component;
import util.HttpServletRequest;
import util.HttpServletResponse;
import util.Servlet;

@Component("/book/rent")
public class BookRentServlet implements Servlet{
  BookDao bookDao;

  public BookRentServlet(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception{
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>도서 대여</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>대여 가능한 도서 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.printf("<a href='/book/form'>대여 등록</a>\n");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>대여가능한 도서 제목</th> <th>수량</th></tr>");
    out.println("</thead>");

    String[][] BOOKS = BookDao.BOOKS;

    for (int i = 0; i < BOOKS.length; i++) {
      out.printf("<tr> <td>%s</td> <td>%s<td></tr>\n", BOOKS[i][0], BOOKS[i][1]);
    }

    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");
    out.println("<a href='/book/list'>도서 대여 목록</a>");
    out.println("</body>");
    out.println("</html>");
  }
}