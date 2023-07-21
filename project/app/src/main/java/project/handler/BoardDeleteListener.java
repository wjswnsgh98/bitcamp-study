package project.handler;

import java.util.List;
import project.vo.Board;
import util.BreadcrumbPrompt;

public class BoardDeleteListener extends AbstractBoardListener{
  public BoardDeleteListener(List<Board> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    if (!this.list.remove(new Board(prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 게시글이 없습니다!");
    }
  }
}