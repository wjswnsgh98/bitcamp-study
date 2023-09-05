package project.controller;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/book/rent")
public class BookRentController implements PageController {
  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try {
      return "/WEB-INF/jsp/book/rent.jsp";

    } catch (Exception e){
      request.setAttribute("refresh", "1;url=/");
      throw e;
    }
  }
}