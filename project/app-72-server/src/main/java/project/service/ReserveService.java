package project.service;

import project.vo.Reserve;

import java.util.List;

public interface ReserveService {
    int add(Reserve reserve) throws Exception;
    List<Reserve> list() throws Exception;
    Reserve get(int reserveNo) throws Exception;
    int delete(int reserveNo) throws Exception;
}
