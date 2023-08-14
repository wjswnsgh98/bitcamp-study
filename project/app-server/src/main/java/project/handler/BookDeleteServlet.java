package project.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.vo.Book;
import project.vo.Member;

@WebServlet("/book/delete")
public class BookDeleteServlet extends HttpServlet{
  private static final long serialVersionUID = 1L;
  //String[][] BOOKS = PredefinedBookData.BOOKS;

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
        throw new Exception("<p>해당 도서의 대여자가 없거나 삭제 권한이 없습니다!</p>");
      } else {
        // out.println("<p>반납 완료했습니다.</p>");
        response.sendRedirect("/book/list");
      }
      InitServlet.sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      InitServlet.sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }

    //    // BOOKS에서 같은 도서 제목의 수량을 1 증가
    //    for (int i = 0; i < BOOKS.length; i++) {
    //      if (book.getBookTitle().equals(BOOKS[i][0])) {
    //        int count = Integer.parseInt(BOOKS[i][1]);
    //        BOOKS[i][1] = String.valueOf(count + 1);
    //        break; // 해당 도서를 찾았으므로 더 이상 반복할 필요가 없음
    //      }
    //    }
  }
}