package project.handler;

import java.io.IOException;
import project.dao.BoardDao;
import project.vo.Board;
import project.vo.Member;
import util.ActionListener;
import util.BreadcrumbPrompt;
import util.DataSource;

public class BoardAddListener implements ActionListener{
  BoardDao boardDao;
  DataSource ds;

  public BoardAddListener(BoardDao boardDao, DataSource ds) {
    this.boardDao = boardDao;
    this.ds = ds;
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

      ds.getConnection().commit();

    } catch (Exception e) {
      try {ds.getConnection().rollback();} catch (Exception e2) {}
      throw new RuntimeException(e);
    }
  }
}
