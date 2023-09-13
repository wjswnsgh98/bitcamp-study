package project.service;

import project.dao.ReserveDao;
import project.vo.Reserve;
import util.Transactional;

import java.util.List;

// @Service
public class DefaultReserveService implements ReserveService {
    ReserveDao reserveDao;

    public DefaultReserveService(ReserveDao reserveDao) {
        this.reserveDao = reserveDao;
    }

    @Transactional
    @Override
    public int add(Reserve reserve) throws Exception {
        return reserveDao.insert(reserve);
    }

    @Override
    public List<Reserve> list() throws Exception {
        return reserveDao.findAll();
    }

    @Override
    public Reserve get(int reserveNo) throws Exception {
        return reserveDao.findBy(reserveNo);
    }

    @Transactional
    @Override
    public int delete(int reserveNo) throws Exception {
        return reserveDao.delete(reserveNo);
    }
}