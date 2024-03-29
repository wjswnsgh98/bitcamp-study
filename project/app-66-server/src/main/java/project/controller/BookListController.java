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

@WebServlet("/book/list")
public class BookListController extends HttpServlet{
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      BookDao bookDao = (BookDao) this.getServletContext().getAttribute("bookDao");
      request.setAttribute("list", bookDao.findAll());
      request.setAttribute("viewUrl", "/WEB-INF/jsp/book/list.jsp");

    } catch(Exception e){
      request.setAttribute("refresh", "1;url=/");
      request.setAttribute("exception", e);
    }
  }
}