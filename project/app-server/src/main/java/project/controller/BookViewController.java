package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.service.BookService;
import project.vo.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/book/view")
public class BookViewController implements PageController {
  @Autowired
  BookService bookService;

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Book book = bookService.get(no);
      if(book != null){
        request.setAttribute("book", book);
      }
      return "/WEB-INF/jsp/book/view.jsp";

    } catch (Exception e){
      request.setAttribute("refresh", "5;url=/book/list");
      throw e;
    }
  }
}
