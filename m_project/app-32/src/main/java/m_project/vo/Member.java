package m_project.vo;

import java.io.Serializable;

public class Member implements Serializable{
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

  // 같은 기능을 수행하는 생성자가 위에 있다.
  // 다만 파라미터가 다를 뿐이다.
  // => "생성자 오버로딩(overloading)"
  public Member(int no){
    this.book_no = no;
  }

  // Object의 equals()는 Member 인스턴스를 비교하는데 적합하지 않다.
  // 왜? Object의 equals()는 단순히 인스턴스 주소가 같은지 비교하기 때문이다.
  // 우리가 원하는 것은 인스턴스 주소가 다르더라도
  // 두 인스턴스 안에 저장된 변수들의 값이 같다면
  // 두 인스턴스는 같은 것으로 처리하는 것이다.
  // 그렇게 하기 위해 수퍼 클래스의 equals()를 재정의한다.
  // => 이것을 "오버라이딩(overriding)"이라 부른다.
  public boolean equals(Object obj){
    if(obj == null){
      return false;
    }

    if(this.getClass() != obj.getClass()){
      return false;
    }

    // 위 조건에서 this가 가리키는 인스턴스의 클래스와
    // 파라미터 obj가 가리키는 인스턴스의 클래스가
    // 같다고 결론이 났기 때문에 다음과 같이
    // obj를 Member 타입으로 형변환한다.
    Member m = (Member) obj;

    if(this.getBook_no() != m.getBook_no()){
      return false;
    }

    // if(this.getB_title() != null && !this.getB_title().equals(m.getB_title())){
    //   return false;
    // }

    // if(this.getAuthor() != null && !this.getAuthor().equals(m.getAuthor())){
    //   return false;
    // }

    // if(this.getName() != null && !this.getName().equals(m.getName())){
    //   return false;
    // }

    // if(this.getP_num() != null && !this.getP_num().equals(m.getP_num())){
    //   return false;
    // }

    // if(this.getGender() != m.getGender()){
    //   return false;
    // }

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
