package m_project.vo;

import java.io.Serializable;

public class Member implements Serializable, CsvObject{
  private static final long serialVersionUID = 1L;

  public static int userId = 1;

  public static final char MALE = 'M';
  public static final char FEMALE = 'W';

  public int book_no; // 책 번호
  public String b_title; // 책 제목
  public String author; // 저자
  public String name; // 대여자 이름
  public String p_num; // 대여자 핸드폰 번호
  public char gender; // 대여자 성별

  public Member() {
    this.book_no = userId++;
  }

  public Member(int no){
    this.book_no = no;
  }

  public static Member fromCsv(String csv) {
    String[] values = csv.split(",");

    Member member = new Member(Integer.parseInt(values[0]));
    member.setB_title(values[1]);
    member.setAuthor(values[2]);
    member.setName(values[3]);
    member.setP_num(values[4]);
    member.setGender(values[5].charAt(0));

    if(Member.userId <= member.getBook_no()) {
      Member.userId = member.getBook_no() + 1;
    }

    return member;
  }

  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%s,%c",
        this.getBook_no(),
        this.getB_title(),
        this.getAuthor(),
        this.getName(),
        this.getP_num(),
        this.getGender());
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
