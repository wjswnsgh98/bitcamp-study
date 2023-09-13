package project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import project.dao.RentDao;
import project.vo.Rent;
import util.TransactionTemplate;

import java.util.List;

@Service
public class DefaultRentService implements RentService {
    RentDao rentDao;
    TransactionTemplate txTemplate;

    public DefaultRentService(RentDao rentDao, PlatformTransactionManager txManager) {
        this.rentDao = rentDao;
        this.txTemplate = new TransactionTemplate(txManager);
    }

    @Override
    public int add(Rent rent) throws Exception {
        return txTemplate.execute(status -> rentDao.insert(rent));
    }

    @Override
    public List<Rent> list() throws Exception {
        return rentDao.findAll();
    }

    @Override
    public Rent get(int rentNo) throws Exception {
        return rentDao.findBy(rentNo);
    }

    @Override
    public int delete(int rentNo) throws Exception {
        return txTemplate.execute(status -> rentDao.delete(rentNo));
    }
}