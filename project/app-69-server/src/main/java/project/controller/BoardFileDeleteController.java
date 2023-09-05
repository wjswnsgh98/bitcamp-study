package project.controller;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import project.dao.BoardDao;
import project.vo.AttachedFile;
import project.vo.Board;
import project.vo.Member;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/board/fileDelete")
public class BoardFileDeleteController implements PageController {
    BoardDao boardDao;
    PlatformTransactionManager txManager;

    public BoardFileDeleteController(BoardDao boardDao, PlatformTransactionManager txManager) {
        this.boardDao = boardDao;
        this.txManager = txManager;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:../auth/login";
        }

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("tx1");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);

        Board board = null;
        try {
            int fileNo = Integer.parseInt(request.getParameter("no"));

            AttachedFile attachedFile = boardDao.findFileBy(fileNo);
            board = boardDao.findBy(attachedFile.getBoardNo());
            if (board.getWriter().getNo() != loginUser.getNo()) {
                throw new ServletException("게시글 변경 권한이 없습니다!");
            }

            if (boardDao.deleteFile(fileNo) == 0) {
                throw new Exception("해당 번호의 첨부파일이 없거나 삭제 권한이 없습니다.");
            } else {
                txManager.commit(status);
                return "redirect:detail?no=" + board.getNo();
            }

        } catch (Exception e) {
            txManager.rollback(status);
            request.setAttribute("refresh", "2;url=/board/detail?no=" + board.getNo());
            throw e;
        }
    }
}