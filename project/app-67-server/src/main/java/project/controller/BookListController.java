package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project.dao.BookDao;
import project.vo.Book;

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