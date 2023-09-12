package project.dao;

import project.vo.Reserve;

import java.util.List;

public interface ReserveDao {
  int insert(Reserve reserve);
  List<Reserve> findAll();
  Reserve findBy(int no);
  int delete(int no);
}