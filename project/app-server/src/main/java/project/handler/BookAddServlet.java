package project.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.vo.Book;
import project.vo.Member;

@WebServlet("/book/add")
public class BookAddServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;
  //String[][] BOOKS = PredefinedBookData.BOOKS;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
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

    //    boolean foundBook = false; // 책을 찾았는지 확인하기 위한 변수
    //
    //    for (int i = 0; i < BOOKS.length; i++) {
    //      String str = BOOKS[i][0];
    //      if (str.equals(book.getBookTitle())) {
    //        int count = Integer.parseInt(BOOKS[i][1]);
    //        if (count > 0) {
    //          book.setBookTitle(book.getBookTitle());
    //          count--; // 책의 수량을 1 감소시킴
    //          BOOKS[i][1] = Integer.toString(count); // 수정된 수량을 다시 BOOKS 배열에 반영
    //          foundBook = true;
    //          break;
    //        } else {
    //          out.println("<p>해당 제목의 도서는 모두 대여 중입니다!</p>");
    //          return;
    //        }
    //      }
    //    }
    //
    //    if (!foundBook) {
    //      out.println("<p>해당 제목의 도서가 없습니다!</p>");
    //      return;
    //    }

    try {
      InitServlet.bookDao.insert(book);
      InitServlet.sqlSessionFactory.openSession(false).commit();
      out.println("<p>등록 성공입니다!</p>");

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      out.println("<p>등록 실패입니다!</p>");
      e.printStackTrace();
    }
    out.println("</body>");
    out.println("</html>");
  }
}