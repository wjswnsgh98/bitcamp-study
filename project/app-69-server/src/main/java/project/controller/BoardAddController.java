package project.controller;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import project.dao.BoardDao;
import project.service.NcpObjectStorageService;
import project.vo.AttachedFile;
import project.vo.Board;
import project.vo.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.util.ArrayList;

@Component("/board/add")
public class BoardAddController implements PageController {
    BoardDao boardDao;
    PlatformTransactionManager txManager;
    NcpObjectStorageService ncpObjectStorageService;

    public BoardAddController(BoardDao boardDao, PlatformTransactionManager txManager, NcpObjectStorageService ncpObjectStorageService) {
        this.boardDao = boardDao;
        this.txManager = txManager;
        this.ncpObjectStorageService = ncpObjectStorageService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equals("GET")) {
            return "/WEB-INF/jsp/board/form.jsp";
        }

        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.getParts(); // 일단 클라이언트가 보낸 파일을 읽는다. 그래야 응답 가능!
            return "redirect:../auth/login";
        }

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("tx1");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);

        try {
            Board board = new Board();
            board.setWriter(loginUser);
            board.setTitle(request.getParameter("title"));
            board.setContent(request.getParameter("content"));

            ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
            for (Part part : request.getParts()) {
                if (part.getName().equals("files") && part.getSize() > 0) {
                    String uploadFileUrl = ncpObjectStorageService.uploadFile(
                            "bitcamp-nc7-bucket-10", "board/", part);
                    AttachedFile attachedFile = new AttachedFile();
                    attachedFile.setFilePath(uploadFileUrl);
                    attachedFiles.add(attachedFile);
                }
            }
            board.setAttachedFiles(attachedFiles);

            boardDao.insert(board);
            if (attachedFiles.size() > 0) {
                boardDao.insertFiles(board);
            }

            txManager.commit(status);
            return "redirect:list";

        } catch (Exception e) {
            txManager.rollback(status);
            request.setAttribute("message", "게시글 등록 오류!");
            request.setAttribute("refresh", "2;url=list");
            throw e;
        }
    }
}
