package project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.BookDao;
import project.vo.Book;

@WebServlet("/book/view")
public class BookViewController extends HttpServlet{
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    BookDao bookDao = (BookDao) this.getServletContext().getAttribute("bookDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");

    try {
      Book book = bookDao.findBy(request.getParameter("booktitle"), request.getParameter("author"));
      if(book != null){
        sqlSessionFactory.openSession(false).commit();
        request.setAttribute("book", book);
      }
      response.setContentType("text/html;charset=UTF-8");
      request.getRequestDispatcher("/WEB-INF/jsp/book/view.jsp").include(request, response);

    } catch (Exception e){
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("refresh", "5;url=/book/list");
      throw new ServletException(e);
    }
  }
}
