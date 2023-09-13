package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.service.RentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/rent/list")
public class RentListController implements PageController {
  @Autowired
  RentService rentService;

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      request.setAttribute("list", rentService.list());
      return "/WEB-INF/jsp/rent/list.jsp";

    } catch (Exception e){
      request.setAttribute("refresh", "1;url=/");
      throw e;
    }
  }
}