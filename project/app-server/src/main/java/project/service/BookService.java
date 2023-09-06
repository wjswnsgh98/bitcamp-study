package project.service;

import project.vo.Book;

import java.util.List;

public interface BookService {
    int add(Book book) throws Exception;
    List<Book> list() throws Exception;
    Book get(String booktitle, String author) throws Exception;
    int update(Book book) throws Exception;
    int delete(int bookNo) throws Exception;
}
