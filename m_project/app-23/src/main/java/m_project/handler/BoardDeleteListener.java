package m_project.handler;

import m_project.vo.Board;
import util.BreadcrumbPrompt;
import util.List;

public class BoardDeleteListener extends AbstractBoardListener{

  public BoardDeleteListener(List list){
    super(list);
  }

  public void service(BreadcrumbPrompt prompt) {
    if (!this.list.remove(new Board(prompt.inputInt("번호? ")))) {
      System.out.println("해당 번호의 게시글이 없습니다!");
    }
  }
}