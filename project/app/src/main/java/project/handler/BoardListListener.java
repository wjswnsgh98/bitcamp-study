package project.handler;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import project.vo.Board;
import util.BreadcrumbPrompt;

public class BoardListListener extends AbstractBoardListener{
  public BoardListListener(List<Board> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 제목, 작성자, 조회수, 등록일");
    System.out.println("---------------------------------------");

    // 조회수를 기준으로 내림차순으로 리스트를 정렬합니다.
    Collections.sort(this.list, Comparator.comparingInt(Board::getViewCount).reversed());

    // 정렬된 순서대로 게시물을 출력합니다.
    for (Board board : this.list) {
      System.out.printf("%d, %s, %s, %d, %tY-%<tm-%<td\n", board.getNo(), board.getTitle(),
          board.getWriter(), board.getViewCount(), board.getCreatedDate());
    }
  }
}