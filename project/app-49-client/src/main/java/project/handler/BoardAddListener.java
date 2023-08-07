package project.handler;

import project.ClientApp;
import project.dao.BoardDao;
import project.vo.Board;
import util.ActionListener;
import util.BreadcrumbPrompt;

public class BoardAddListener implements ActionListener{
  BoardDao boardDao;

  public BoardAddListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Board board = new Board();
    board.setTitle(prompt.inputString("제목? "));
    board.setContent(prompt.inputString("내용? "));
    board.setWriter(ClientApp.loginUser);

    boardDao.insert(board);
  }
}
