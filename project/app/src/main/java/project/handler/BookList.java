package project.handler;

import project.vo.Book;

public class BookList {
  private static final int DEFAULT_SIZE = 3;
  private Book[] books = new Book[DEFAULT_SIZE];
  private int length = 0;

  public void add(Book book) {
    if (this.length == books.length) {
      increase();
    }

    this.books[this.length++] = book;
  }

  private void increase() {
    // 기존 배열 보다 50% 큰 배열을 새로 만든다.
    Book[] arr = new Book[books.length + (books.length >> 1)];

    // 기존 배열의 값을 새 배열로 복사한다.
    for (int i = 0; i < books.length; i++) {
      arr[i] = books[i];
    }

    // boards 레퍼런스가 새 배열을 가리키도록 한다.
    books = arr;

    //    System.out.println("배열 확장: " + boards.length);
  }

  public Book[] list() {
    Book[] arr = new Book[this.length];
    for (int i = 0; i < this.length; i++) {
      arr[i] = books[i];
    }
    return arr;
  }

  public Book get(String str) {
    for (int i = 0; i < this.length; i++) {
      Book book = this.books[i];
      if (book.getName().equals(str)) {
        return book;
      }
    }
    return null;
  }

  public boolean delete(String str) {
    int deletedIndex = this.findIndex(str);
    if (deletedIndex == -1) {
      return false;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.books[i] = this.books[i + 1];
    }
    this.books[--this.length] = null;
    return true;
  }

  public int findIndex(String target) {
    for (int i = 0; i < this.length; i++) {
      Book book = this.books[i];
      if (book.getName().equals(target)) {
        return i; // 찾은 경우 해당 인덱스 번호를 반환
      }
    }
    return -1; // 찾지 못한 경우 -1을 반환
  }
}
