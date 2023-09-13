package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.service.ReserveService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/reserve/list")
public class ReserveListController implements PageController {
  @Autowired
  ReserveService reserveService;

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      request.setAttribute("list", reserveService.list());
      return "/WEB-INF/jsp/reserve/list.jsp";

    } catch (Exception e){
      request.setAttribute("refresh", "1;url=/");
      throw e;
    }
  }
}