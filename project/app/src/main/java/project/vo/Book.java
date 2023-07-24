package project.vo;

public class Book {
  public static int bookNo = 1;

  private int no;
  private String bookTitle;
  private String author;
  private long rentalDate;
  private long returnDate;
  private String name;

  public Book() {
    this.no = bookNo++;
    this.rentalDate = System.currentTimeMillis();
    this.returnDate = this.rentalDate + (5 * 24 * 60 * 60 * 1000); // 5일을 밀리초로 변환하여 더합니다.
  }

  public Book(String name) {
    this.name = name;
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