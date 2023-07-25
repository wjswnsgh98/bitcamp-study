package m_project.handler;

import m_project.dao.BoardDao;
import m_project.vo.Board;
import util.ActionListener;
import util.BreadcrumbPrompt;

public class BoardDeleteListener implements ActionListener{
  BoardDao boardDao;

  public BoardDeleteListener(BoardDao boardDao){
    this.boardDao = boardDao;
  }

  public void service(BreadcrumbPrompt prompt) {
    Board b = new Board();
    b.setNo(prompt.inputInt("번호? "));
    b.setPassword(prompt.inputString("암호? "));

    if (boardDao.delete(b) == 0) {
      System.out.println("해당 번호의 게시글이 없거나 암호가 맞지 않습니다.");
    } else {
      System.out.println("삭제했습니다.");
    }
  }
}