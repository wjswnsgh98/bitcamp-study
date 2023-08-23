package project.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.vo.Book;

@WebServlet("/book/view")
public class BookViewServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Book book = InitServlet.bookDao.findBy(request.getParameter("booktitle"), request.getParameter("author"));

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

    out.println("<h1>도서 대여</h1>");

    if(book == null) {
      out.println("<p>해당 도서의 대여자가 없습니다!</p>");
      return;
    } else {
      out.println("<form action='/book/update' method='post'>");
      out.println("<table border='1'>");
      out.printf("<tr><th style='width:120px;'>도서 제목</th>"
          + " <td style='width:300px;'><input type='text' name='booktitle' value='%s' readonly></td></tr>\n", book.getBookTitle());
      out.printf("<tr><th>저자</th>"
          + " <td><input type='text' name='author' value='%s'></td></tr>\n", book.getAuthor());
      out.printf("<tr><th>대여자</th> <td>%s</td></tr>\n", book.getLender().getName());
      out.printf("<tr><th>대여일</th> <td>%tY-%1$tm-%1$td</td></tr>\n", book.getRentalDate());
      out.printf("<tr><th>반납일</th> <td>%tY-%1$tm-%1$td</td></tr>\n", book.getReturnDate());
      out.println("</table>");

      out.println("<div>");
      out.println("<button>변경</button>");
      out.println("<button type='reset'>초기화</button>");
      out.printf("<a href='/book/delete?booktitle=%s&author=%s'>삭제</a>\n", book.getBookTitle(), book.getAuthor());
      out.println("<a href='/book/rent'>목록</a>\n");
      out.println("</div>");
      out.println("</form>");
    }
    request.getRequestDispatcher("/footer").include(request, response);

    out.println("</body>");
    out.println("</html>");
  }
}
