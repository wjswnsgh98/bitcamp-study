package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import project.dao.BookDao;

@WebServlet("/book/rent")
public class BookRentController extends HttpServlet{
  private static final long serialVersionUID = 1L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      request.setAttribute("viewUrl", "/WEB-INF/jsp/book/rent.jsp");

    } catch (Exception e){
      request.setAttribute("refresh", "1;url=/");
      throw new ServletException(e);
    }
  }
}