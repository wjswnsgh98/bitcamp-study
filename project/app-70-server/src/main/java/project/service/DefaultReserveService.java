package project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import project.dao.ReserveDao;
import project.vo.Reserve;

import java.util.List;

@Service
public class DefaultReserveService implements ReserveService {
    ReserveDao reserveDao;
    PlatformTransactionManager txManager;

    public DefaultReserveService(ReserveDao reserveDao, PlatformTransactionManager txManager) {
        this.reserveDao = reserveDao;
        this.txManager = txManager;
    }

    @Override
    public int add(Reserve reserve) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("tx1");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);

        try {
            int count = reserveDao.insert(reserve);
            txManager.commit(status);
            return count;

        } catch (Exception e) {
            txManager.rollback(status);
            throw e;
        }
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
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("tx1");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);

        try {
            int count = reserveDao.delete(reserveNo);
            txManager.commit(status);
            return count;

        } catch (Exception e) {
            txManager.rollback(status);
            throw e;
        }
    }
}