package project.dao;

import java.util.List;
import project.vo.Board;

public interface BoardDao {
  void insert(Board board);
  List<Board> findAll();
  Board findBy(int no);
  int update(Board board);
  int updateCount(Board board);
  int delete(Board board);
}