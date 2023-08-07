package project.handler;

import java.text.SimpleDateFormat;
import java.util.List;
import project.dao.BoardDao;
import project.vo.Board;
import util.ActionListener;
import util.BreadcrumbPrompt;

public class BoardListListener implements ActionListener{
  BoardDao boardDao;
  SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

  public BoardListListener(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 제목, 작성자, 조회수, 등록일");
    System.out.println("---------------------------------------");

    List<Board> list = boardDao.list();

    //    // 조회수를 기준으로 내림차순으로 리스트를 정렬합니다.
    //    Collections.sort(list, Comparator.comparingInt(Board::getViewCount).reversed());

    // 정렬된 순서대로 게시물을 출력합니다.
    for (Board board : list) {
      System.out.printf("%d, %s, %s, %d, %s\n",
          board.getNo(),
          board.getTitle(),
          board.getWriter().getName(),
          board.getViewCount(),
          dateFormatter.format(board.getCreatedDate()));
    }
  }
}