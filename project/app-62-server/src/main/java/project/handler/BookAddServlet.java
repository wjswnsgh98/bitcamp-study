package project.handler;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.dao.BookDao;
import project.vo.Book;
import project.vo.Member;

@WebServlet("/book/add")
public class BookAddServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;
  String[][] BOOKS = BookDao.BOOKS;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    try {
      PrintWriter out = response.getWriter();
      Book book = new Book();
      book.setBookTitle(request.getParameter("booktitle"));
      book.setAuthor(request.getParameter("author"));
      book.setLender(loginUser);

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

      InitServlet.bookDao.insert(book);
      InitServlet.sqlSessionFactory.openSession(false).commit();
      response.sendRedirect("rent");

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();

      request.setAttribute("error", e);
      request.setAttribute("message", "도서 대여 등록 오류!");
      request.setAttribute("refresh", "2;url=rent");

      request.getRequestDispatcher("/error").forward(request, response);
    }
  }
}