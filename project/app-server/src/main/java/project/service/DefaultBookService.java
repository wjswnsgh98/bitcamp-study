package project.service;

import project.dao.BookDao;
import project.vo.Book;
import util.Transactional;

import java.util.List;

// @Service
public class DefaultBookService implements BookService{
    BookDao bookDao;

    public DefaultBookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Transactional
    @Override
    public int add(Book book) throws Exception {
        return bookDao.insert(book);
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

    @Transactional
    @Override
    public int update(Book book) throws Exception {
        return bookDao.update(book);
    }

    @Transactional
    @Override
    public int delete(int bookNo) throws Exception {
        return bookDao.delete(bookNo);
    }

    @Transactional
    @Override
    public int increaseCount(String bookTitle) throws Exception {
        return bookDao.updateIncreaseCount(bookTitle);
    }

    @Transactional
    @Override
    public int decreaseCount(String bookTitle) throws Exception {
        return bookDao.updateDecreaseCount(bookTitle);
    }
}
