package project.handler;

import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.BoardDao;
import project.vo.Board;
import project.vo.Member;
import util.Component;
import util.HttpServletRequest;
import util.HttpServletResponse;
import util.Servlet;

@Component("/board/delete")
public class BoardDeleteServlet implements Servlet {
  BoardDao boardDao;
  SqlSessionFactory sqlSessionFactory;

  public BoardDeleteServlet(BoardDao boardDao, SqlSessionFactory sqlSessionFactory) {
    this.boardDao = boardDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    Board b = new Board();
    b.setNo(Integer.parseInt(request.getParameter("no")));
    b.setWriter(loginUser);

    try {
      if (boardDao.delete(b) == 0) {
        throw new Exception("해당 번호의 게시글이 없거나 삭제 권한이 없습니다.");
      } else {
        response.sendRedirect("/board/list");
      }
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}