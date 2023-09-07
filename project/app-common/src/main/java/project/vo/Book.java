package project.vo;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable{
  private static final long serialVersionUID = 1L;

  private int no;
  private String bookTitle;
  private String author;
  private String publisher;
  private String content;
  private int count;
  private String photo;

  @Override
  public int hashCode() {
    return Objects.hash(no);
  }

  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }

    if (this.getClass() != obj.getClass()) {
      return false;
    }

    Book book = (Book) obj;

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

  public String getPublisher() { return publisher; }

  public void setPublisher(String publisher) { this.publisher = publisher; }

  public String getContent() { return content; }

  public void setContent(String content) { this.content = content;}

  public int getCount() { return count; }

  public void setCount(int count) { this.count = count; }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }
}