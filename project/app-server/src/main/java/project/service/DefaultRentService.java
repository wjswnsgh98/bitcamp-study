package project.service;

import project.dao.RentDao;
import project.vo.Rent;
import util.Transactional;

import java.util.List;

// @Service
public class DefaultRentService implements RentService {
    RentDao rentDao;

    public DefaultRentService(RentDao rentDao) {
        this.rentDao = rentDao;
    }

    @Transactional
    @Override
    public int add(Rent rent) throws Exception {
        return rentDao.insert(rent);
    }

    @Override
    public List<Rent> list() throws Exception {
        return rentDao.findAll();
    }

    @Override
    public Rent get(int rentNo) throws Exception {
        return rentDao.findBy(rentNo);
    }

    @Transactional
    @Override
    public int delete(int rentNo) throws Exception {
        return rentDao.delete(rentNo);
    }
}