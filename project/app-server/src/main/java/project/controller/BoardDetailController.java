package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.service.BoardService;
import project.vo.Board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/board/detail")
public class BoardDetailController implements PageController {
    @Autowired
    BoardService boardService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int no = Integer.parseInt(request.getParameter("no"));
            Board board = boardService.get(no);
            if(board != null){
                boardService.increaseViewCount(no);
                request.setAttribute("board", board);
            }
            return "/WEB-INF/jsp/board/detail.jsp";

        } catch (Exception e) {
            request.setAttribute("refresh", "5;url=/board/list");
            throw e;
        }
    }
}