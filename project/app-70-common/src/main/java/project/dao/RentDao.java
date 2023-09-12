package project.dao;

import project.vo.Rent;

import java.util.List;

public interface RentDao {
  int insert(Rent rent);
  List<Rent> findAll();
  Rent findBy(int no);
  int update(Rent rent);
  int delete(int no);
}