package project.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.BoardDao;
import project.vo.Board;
import project.vo.Member;
import util.ActionListener;
import util.BreadcrumbPrompt;

public class BoardAddListener implements ActionListener{
  BoardDao boardDao;
  SqlSessionFactory sqlSessionFactory;

  public BoardAddListener(BoardDao boardDao, SqlSessionFactory sqlSessionFactory) {
    this.boardDao = boardDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException{
    Board board = new Board();
    board.setTitle(prompt.inputString("제목? "));
    board.setContent(prompt.inputString("내용? "));
    board.setWriter((Member) prompt.getAttribute("loginUser"));

    try {
      boardDao.insert(board);
      //      Thread.sleep(5000);

      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}
