package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.service.BoardService;
import project.vo.AttachedFile;
import project.vo.Board;
import project.vo.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/board/fileDelete")
public class BoardFileDeleteController implements PageController {
    @Autowired
    BoardService boardService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:../auth/login";
        }

        Board board = null;
        try {
            int fileNo = Integer.parseInt(request.getParameter("no"));

            AttachedFile attachedFile = boardService.getAttachedFile(fileNo);
            board = boardService.get(attachedFile.getBoardNo());
            if (board.getWriter().getNo() != loginUser.getNo()) {
                throw new Exception("게시글 변경 권한이 없습니다!");
            }

            if (boardService.deleteAttachedFile(fileNo) == 0) {
                throw new Exception("해당 번호의 첨부파일이 없거나 삭제 권한이 없습니다.");
            } else {
                return "redirect:detail?no=" + board.getNo();
            }

        } catch (Exception e) {
            request.setAttribute("refresh", "2;url=/board/detail?no=" + board.getNo());
            throw e;
        }
    }
}