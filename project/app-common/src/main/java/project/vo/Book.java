package project.vo;

import java.sql.Timestamp;
import java.util.Calendar;

public class Book {
  public static int bookNo = 1;

  private int no;
  private String bookTitle;
  private String author;
  private Timestamp rentalDate;
  private Timestamp returnDate;
  private String name;

  public Book() {}

  public Book(String name, int no) {
    this.name = name;
    this.no = no;
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    Book book = (Book) obj;

    if (!this.getName().equals(book.getName())) {
      return false;
    }

    return true;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Timestamp getRentalDate() {
    return rentalDate;
  }

  //  public void setRentalDate(Timestamp rentalDate) {
  //    this.rentalDate = rentalDate;
  //    // rentalDate로부터 7일 후의 returnDate를 계산합니다.
  //    Calendar calendar = Calendar.getInstance();
  //    calendar.setTime(rentalDate);
  //    calendar.add(Calendar.DAY_OF_MONTH, 7);
  //    this.returnDate = new Timestamp(calendar.getTimeInMillis());
  //  }

  public void setRentalDate(Timestamp rentalDate) {
    this.rentalDate = rentalDate;
  }

  public Timestamp getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(Timestamp rentaldate) {
    // rentalDate로부터 7일 후의 returnDate를 계산합니다.
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(rentalDate);
    calendar.add(Calendar.DAY_OF_MONTH, 7);
    this.returnDate = new Timestamp(calendar.getTimeInMillis());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}