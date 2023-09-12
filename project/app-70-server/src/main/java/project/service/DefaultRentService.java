package project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import project.dao.RentDao;
import project.vo.Rent;

import java.util.List;

@Service
public class DefaultRentService implements RentService {
    RentDao rentDao;
    PlatformTransactionManager txManager;

    public DefaultRentService(RentDao rentDao, PlatformTransactionManager txManager) {
        this.rentDao = rentDao;
        this.txManager = txManager;
    }

    @Override
    public int add(Rent rent) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("tx1");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);

        try {
            int count = rentDao.insert(rent);
            txManager.commit(status);
            return count;

        } catch (Exception e) {
            txManager.rollback(status);
            throw e;
        }
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
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("tx1");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);

        try {
            int count = rentDao.delete(rentNo);
            txManager.commit(status);
            return count;

        } catch (Exception e) {
            txManager.rollback(status);
            throw e;
        }
    }
}