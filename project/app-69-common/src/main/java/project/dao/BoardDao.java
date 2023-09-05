package project.dao;

import java.util.List;
import project.vo.AttachedFile;
import project.vo.Board;

public interface BoardDao {
  void insert(Board board);
  List<Board> findAll();
  Board findBy(int no);
  int update(Board board);
  int updateCount(Board board);
  int delete(Board board);

  int insertFiles(Board board);
  AttachedFile findFileBy(int no);
  int deleteFile(int fileNo);
  int deleteFiles(int boardNo);
}