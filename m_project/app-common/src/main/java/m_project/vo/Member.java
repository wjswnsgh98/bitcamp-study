package m_project.vo;

import java.io.Serializable;

public class Member implements Serializable{
  private static final long serialVersionUID = 1L;

  public static final char MALE = 'M';
  public static final char FEMALE = 'W';

  private int book_no; // 책 번호
  private String b_title; // 책 제목
  private String author; // 저자
  private String name; // 대여자 이름
  private String p_num; // 대여자 핸드폰 번호
  private char gender; // 대여자 성별

  public Member() {}

  public Member(int no){
    this.book_no = no;
  }

  public boolean equals(Object obj){
    if(obj == null){
      return false;
    }

    if(this.getClass() != obj.getClass()){
      return false;
    }

    Member m = (Member) obj;

    if(this.getBook_no() != m.getBook_no()){
      return false;
    }
    return true;
  }

  public int getBook_no() {
    return book_no;
  }

  public void setBook_no(int book_no) {
    this.book_no = book_no;
  }

  public String getB_title() {
    return b_title;
  }

  public void setB_title(String b_title) {
    this.b_title = b_title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getP_num() {
    return p_num;
  }

  public void setP_num(String p_num) {
    this.p_num = p_num;
  }

  public char getGender() {
    return gender;
  }

  public void setGender(char gender) {
    this.gender = gender;
  }
}