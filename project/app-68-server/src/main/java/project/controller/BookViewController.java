package project.controller;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import project.dao.BookDao;
import project.vo.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/book/view")
public class BookViewController implements PageController {
  BookDao bookDao;
  SqlSessionFactory sqlSessionFactory;

  public BookViewController(BookDao bookDao, SqlSessionFactory sqlSessionFactory) {
    this.bookDao = bookDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      Book book = bookDao.findBy(request.getParameter("booktitle"), request.getParameter("author"));
      if(book != null){
        sqlSessionFactory.openSession(false).commit();
        request.setAttribute("book", book);
      }
      return "/WEB-INF/jsp/book/view.jsp";

    } catch (Exception e){
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("refresh", "5;url=/book/list");
      throw e;
    }
  }
}