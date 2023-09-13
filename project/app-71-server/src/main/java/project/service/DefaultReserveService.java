package project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import project.dao.ReserveDao;
import project.vo.Reserve;
import util.TransactionTemplate;

import java.util.List;

@Service
public class DefaultReserveService implements ReserveService {
    ReserveDao reserveDao;
    TransactionTemplate txTemplate;

    public DefaultReserveService(ReserveDao reserveDao, PlatformTransactionManager txManager) {
        this.reserveDao = reserveDao;
        this.txTemplate = new TransactionTemplate(txManager);
    }

    @Override
    public int add(Reserve reserve) throws Exception {
        return txTemplate.execute(status -> reserveDao.insert(reserve));
    }

    @Override
    public List<Reserve> list() throws Exception {
        return reserveDao.findAll();
    }

    @Override
    public Reserve get(int reserveNo) throws Exception {
        return reserveDao.findBy(reserveNo);
    }

    @Override
    public int delete(int reserveNo) throws Exception {
        return txTemplate.execute(status -> reserveDao.delete(reserveNo));
    }
}