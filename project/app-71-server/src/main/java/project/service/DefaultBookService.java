package project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import project.dao.BookDao;
import project.vo.Book;
import util.TransactionTemplate;

import java.util.List;

@Service
public class DefaultBookService implements BookService{
    BookDao bookDao;
    TransactionTemplate txTemplate;

    public DefaultBookService(BookDao bookDao, PlatformTransactionManager txManager) {
        this.bookDao = bookDao;
        this.txTemplate = new TransactionTemplate(txManager);
    }

    @Override
    public int add(Book book) throws Exception {
        return txTemplate.execute(status -> bookDao.insert(book));
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
        return txTemplate.execute(status -> bookDao.update(book));
    }

    @Override
    public int delete(int bookNo) throws Exception {
        return txTemplate.execute(status -> bookDao.delete(bookNo));
    }

    @Override
    public int increaseCount(String bookTitle) throws Exception {
        return txTemplate.execute(status -> bookDao.updateIncreaseCount(bookTitle));
    }

    @Override
    public int decreaseCount(String bookTitle) throws Exception {
        return txTemplate.execute(status -> bookDao.updateDecreaseCount(bookTitle));
    }
}
