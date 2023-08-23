package project.handler;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.vo.Book;

@WebServlet("/book/list")
public class BookListServlet extends HttpServlet{
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

    out.println("<h1>도서 대여 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>제목</th> <th>저자</th> <th>대여자 이름</th> <th>대여일</th> <th>반납일</th></tr>");
    out.println("</thead>");

    List<Book> list = InitServlet.bookDao.findAll();

    out.println("<tbody>");
    for (Book book : list) {
      out.printf("<tr> <td><a href='/book/view?booktitle=%s&author=%s'>%s</a></td>"
          + " <td>%s</td> <td>%s</td> <td>%tY-%6$tm-%6$td</td> <td>%tY-%7$tm-%7$td</td>\n",
          book.getBookTitle(),
          book.getAuthor(),
          book.getBookTitle(),
          book.getAuthor(),
          book.getLender().getName(),
          book.getRentalDate(),
          book.getReturnDate());
    }
    out.println("</tbody>");
    out.println("</table>");
    out.println("<a href='/'>메인</a>");

    request.getRequestDispatcher("/footer").include(request, response);

    out.println("</body>");
    out.println("</html>");
  }
}