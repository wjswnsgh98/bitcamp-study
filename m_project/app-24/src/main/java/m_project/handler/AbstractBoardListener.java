package m_project.handler;

import m_project.vo.Board;
import util.ActionListener;
import util.List;

public abstract class AbstractBoardListener implements ActionListener{
    protected List<Board> list;

    public AbstractBoardListener(List list){
        this.list = list;
    }

    protected Board findBy(int no){
        for(int i = 0; i < this.list.size(); i++){
          Board b = this.list.get(i);
          if(b.getNo() == no){
            return b;
          }
        }
        return null;
      }
}
