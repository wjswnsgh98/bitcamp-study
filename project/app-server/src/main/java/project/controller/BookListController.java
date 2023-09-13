package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/book/list")
public class BookListController implements PageController {
  @Autowired
  BookService bookService;

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      request.setAttribute("list", bookService.list());
      return "/WEB-INF/jsp/book/list.jsp";

    } catch(Exception e){
      request.setAttribute("refresh", "1;url=/");
      throw e;
    }
  }
}