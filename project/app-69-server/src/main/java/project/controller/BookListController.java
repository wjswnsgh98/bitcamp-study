package project.controller;

import org.springframework.stereotype.Component;
import project.dao.BookDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/book/list")
public class BookListController implements PageController {
  BookDao bookDao;

  public BookListController(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      request.setAttribute("list", bookDao.findAll());
      return "/WEB-INF/jsp/book/list.jsp";

    } catch(Exception e){
      request.setAttribute("refresh", "1;url=/");
      throw e;
    }
  }
}