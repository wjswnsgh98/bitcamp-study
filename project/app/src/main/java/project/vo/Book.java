package project.vo;

public class Book {
  private static int bookNo = 1;

  private int no;
  private String bookTitle;
  private String author;
  private long rentalDate;
  private long returnDate;
  private String name;

  public Book() {
    this.no = bookNo++;
    this.rentalDate = System.currentTimeMillis();
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

  public long getRentalDate() {
    return rentalDate;
  }

  public void setRentalDate(long rentalDate) {
    this.rentalDate = rentalDate;
  }

  public long getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(long returnDate) {
    this.returnDate = returnDate;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}