package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.service.BoardService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/board/list")
public class BoardListController implements PageController {
  @Autowired
  BoardService boardService;

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    try{
      request.setAttribute("list", boardService.list());
      return "/WEB-INF/jsp/board/list.jsp";

    } catch (Exception e){
      request.setAttribute("refresh", "1;url=/");
      throw e;
    }
  }
}