package project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.BoardDao;
import project.vo.Board;
import project.vo.Member;

@WebServlet("/board/delete")
public class BoardDeleteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            request.setAttribute("viewUrl", "redirect:../auth/login");
            return;
        }

        BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
        SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");

        try {
            Board b = new Board();
            b.setNo(Integer.parseInt(request.getParameter("no")));
            b.setWriter(loginUser);

            boardDao.deleteFiles(b.getNo());

            if (boardDao.delete(b) == 0) {
                throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
            } else {
                sqlSessionFactory.openSession(false).commit();
                request.setAttribute("viewUrl", "redirect:list");
            }

        } catch (Exception e) {
            sqlSessionFactory.openSession(false).rollback();
            request.setAttribute("refresh", "2;url=list");
            request.setAttribute("exception", e);
        }
    }
}