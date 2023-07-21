package project.handler;

import java.util.List;
import project.vo.Board;
import util.ActionListener;

public abstract class AbstractBoardListener implements ActionListener{
  protected List<Board> list;

  public AbstractBoardListener(List<Board> list) {
    this.list = list;
  }

  protected Board findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Board b = this.list.get(i);
      if (b.getNo() == no) {
        return b;
      }
    }
    return null;
  }
}