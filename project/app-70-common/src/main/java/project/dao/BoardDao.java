package project.dao;

import project.vo.AttachedFile;
import project.vo.Board;

import java.util.List;

public interface BoardDao {
  int insert(Board board);
  List<Board> findAll();
  Board findBy(int no);
  int update(Board board);
  int updateCount(int no);
  int delete(int no);

  int insertFiles(Board board);
  AttachedFile findFileBy(int no);
  int deleteFile(int fileNo);
  int deleteFiles(int boardNo);
}