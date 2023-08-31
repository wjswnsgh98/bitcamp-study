package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.BoardDao;
import project.vo.AttachedFile;
import project.vo.Board;
import project.vo.Member;
import util.NcpObjectStorageService;

@WebServlet("/board/update")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10)
public class BoardUpdateController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            response.sendRedirect("/auth/login");
            return;
        }

        BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
        NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");

        try {
            Board board = new Board();
            board.setWriter(loginUser);
            board.setNo(Integer.parseInt(request.getParameter("no")));
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

            if (boardDao.update(board) == 0) {
                throw new Exception("게시글이 없거나 변경 권한이 없습니다.");
            } else {
                if (attachedFiles.size() > 0) {
                    int count = boardDao.insertFiles(board);
                    System.out.println(count);
                }
                sqlSessionFactory.openSession(false).commit();
                response.sendRedirect("list");
            }

        } catch (Exception e) {
            sqlSessionFactory.openSession(false).rollback();
            request.setAttribute("refresh", "2;url=detail?no=" + request.getParameter("no"));
            throw new ServletException(e);
        }
    }
}