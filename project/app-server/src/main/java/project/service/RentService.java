package project.service;

import project.vo.Rent;

import java.util.List;

public interface RentService {
    int add(Rent rent) throws Exception;
    List<Rent> list() throws Exception;
    Rent get(int rentNo) throws Exception;
    int update(Rent rent) throws Exception;
    int delete(int rentNo) throws Exception;
}
