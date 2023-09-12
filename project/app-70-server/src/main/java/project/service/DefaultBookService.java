package project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import project.dao.BookDao;
import project.vo.Book;

import java.util.List;

@Service
public class DefaultBookService implements BookService{
    BookDao bookDao;
    PlatformTransactionManager txManager;

    public DefaultBookService(BookDao bookDao, PlatformTransactionManager txManager) {
        this.bookDao = bookDao;
        this.txManager = txManager;
    }

    @Override
    public int add(Book book) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("tx1");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);

        try {
            int count = bookDao.insert(book);
            txManager.commit(status);
            return count;
        } catch (Exception e) {
            txManager.rollback(status);
            throw e;
        }
    }

    @Override
    public List<Book> list() throws Exception {
        return bookDao.findAll();
    }

    @Override
    public Book get(int bookNo) throws Exception {
        return bookDao.findBy(bookNo);
    }

    @Override
    public Book get(String bookTitle) throws Exception {
        return bookDao.findByTitle(bookTitle);
    }

    @Override
    public int update(Book book) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("tx1");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);

        try {
            int count = bookDao.update(book);
            txManager.commit(status);
            return count;
        } catch (Exception e) {
            txManager.rollback(status);
            throw e;
        }
    }

    @Override
    public int delete(int bookNo) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("tx1");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);

        try {
            int count = bookDao.delete(bookNo);
            txManager.commit(status);
            return count;
        } catch (Exception e) {
            txManager.rollback(status);
            throw e;
        }
    }

    @Override
    public int increaseCount(String bookTitle) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("tx1");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);

        try {
            int count = bookDao.updateIncreaseCount(bookTitle);
            txManager.commit(status);
            return count;
        } catch (Exception e) {
            txManager.rollback(status);
            throw e;
        }
    }

    @Override
    public int decreaseCount(String bookTitle) throws Exception {
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setName("tx1");
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
        TransactionStatus status = txManager.getTransaction(def);

        try {
            int count = bookDao.updateDecreaseCount(bookTitle);
            txManager.commit(status);
            return count;
        } catch (Exception e) {
            txManager.rollback(status);
            throw e;
        }
    }
}
