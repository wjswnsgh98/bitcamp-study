package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.service.BoardService;
import project.vo.Board;
import project.vo.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/board/delete")
public class BoardDeleteController implements PageController {
    @Autowired
    BoardService boardService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:../auth/login";
        }

        try {
            Board b = boardService.get(Integer.parseInt(request.getParameter("no")));

            if (b == null || b.getWriter().getNo() != loginUser.getNo()) {
                throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
            } else {
                boardService.delete(b.getNo());
                return "redirect:list";
            }

        } catch (Exception e) {
            request.setAttribute("refresh", "2;url=list");
            throw e;
        }
    }
}