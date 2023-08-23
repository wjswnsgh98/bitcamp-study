package project.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.dao.BookDao;
import project.vo.Book;
import project.vo.Member;

@WebServlet("/book/delete")
public class BookDeleteServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;
  String[][] BOOKS = BookDao.BOOKS;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    String booktitle = request.getParameter("booktitle");
    String author = request.getParameter("author");

    Book book = new Book();
    book.setBookTitle(booktitle);
    book.setAuthor(author);
    book.setLender(loginUser);

    try {
      if (InitServlet.bookDao.delete(book) == 0) {
        throw new Exception("해당 도서의 대여자가 없거나 삭제 권한이 없습니다!");
      } else {
        InitServlet.sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("/book/list");
      }

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();

      request.setAttribute("error", e);
      request.setAttribute("message", e.getMessage());
      request.setAttribute("refresh", "2;url=rent");

      request.getRequestDispatcher("/error").forward(request, response);
    }

    // BOOKS에서 같은 도서 제목의 수량을 1 증가
    for (int i = 0; i < BOOKS.length; i++) {
      if (book.getBookTitle().equals(BOOKS[i][0])) {
        int count = Integer.parseInt(BOOKS[i][1]);
        BOOKS[i][1] = String.valueOf(count + 1);
        break; // 해당 도서를 찾았으므로 더 이상 반복할 필요가 없음
      }
    }
  }
}