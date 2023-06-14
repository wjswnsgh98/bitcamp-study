package m_project.vo;

public class Member {
  private static int userId = 1;

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
