package project.handler;

import project.dao.BoardDao;
import project.vo.Board;
import project.vo.Member;
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

    Member writer = new Member();
    writer.setNo(prompt.inputInt("작성자? "));
    board.setWriter(writer);

    board.setPassword(prompt.inputString("암호? "));
    boardDao.insert(board);
  }
}
