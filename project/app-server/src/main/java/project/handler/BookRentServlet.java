package project.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.dao.BookDao;

@WebServlet("/book/rent")
public class BookRentServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>도서 대여</title>");
    out.println("</head>");
    out.println("<body>");

    request.getRequestDispatcher("/header").include(request, response);

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

    request.getRequestDispatcher("/footer").include(request, response);

    out.println("</body>");
    out.println("</html>");
  }
}