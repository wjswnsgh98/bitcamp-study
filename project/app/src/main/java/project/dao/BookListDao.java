package project.dao;

import java.util.ArrayList;
import java.util.List;
import project.vo.Board;
import project.vo.Book;
import util.JsonDataHelper;

public class BookListDao implements BookDao{
  String filename;
  ArrayList<Book> list = new ArrayList<>();

  public BookListDao(String filename) {
    this.filename = filename;
    JsonDataHelper.loadJson(filename, list, Board.class);
  }

  @Override
  public void insert(Book book) {
    book.setNo(Book.bookNo++);
    book.setRentalDate(System.currentTimeMillis());
    this.list.add(book);
    JsonDataHelper.saveJson(filename, list);
  }

  @Override
  public List<Board> list() {
    return this.list;
  }

  @Override
  public Board findBy(int no) {
    for (int i = 0; i < this.list.size(); i++) {
      Board m = this.list.get(i);
      if (m.getNo() == no) {
        return m;
      }
    }
    return null;
  }

  @Override
  public String update(Board board) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == board.getNo()) {
        list.set(i, board);
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }

  @Override
  public int delete(int no) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == no) {
        list.remove(i);
        JsonDataHelper.saveJson(filename, list);
        return 1;
      }
    }
    return 0;
  }
}