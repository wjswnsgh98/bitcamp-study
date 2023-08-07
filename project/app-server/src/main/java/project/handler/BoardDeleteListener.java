package project.handler;

import java.io.IOException;
import project.dao.BoardDao;
import project.vo.Board;
import project.vo.Member;
import util.ActionListener;
import util.BreadcrumbPrompt;

public class BoardDeleteListener implements ActionListener {

  BoardDao boardDao;

  public BoardDeleteListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException{
    Board b = new Board();
    b.setNo(prompt.inputInt("번호? "));
    b.setWriter((Member) prompt.getAttribute("loginUser"));

    if (boardDao.delete(b) == 0) {
      System.out.println("해당 번호의 게시글이 없거나 암호가 맞지 않습니다.");
    } else {
      System.out.println("삭제했습니다.");
    }
  }
}