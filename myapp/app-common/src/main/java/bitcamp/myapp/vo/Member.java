package bitcamp.myapp.vo;

import java.io.Serializable;

public class Member implements Serializable{
  private static final long serialVersionUID = 1L;

  public static final char MALE = 'M';
  public static final char FEMALE = 'W';

  public int no;
  public String name;
  public String email;
  public String password;
  public char gender;

  public Member() {}

  public Member(int no) {
    this.no = no;
  }

  public boolean equals(Object obj) {
    if(obj == null) {
      return false;
    }

    if(this.getClass() != obj.getClass()) {
      return false;
    }

    Member m = (Member) obj;
    if(this.getNo() != m.getNo()) {
      return false;
    }

    return true;
  }

  public int getNo(){
    return this.no;
  }

  public void setNo(int no){
    this.no = no;
  }

  public String getName(){
    return this.name;
  }

  public void setName(String name){
    this.name = name;
  }

  public String getEmail(){
    return this.email;
  }

  public void setEmail(String email){
    this.email = email;
  }

  public String getPassword(){
    return this.password;
  }

  public void setPassword(String password){
    this.password = password;
  }

  public char getGender(){
    return this.gender;
  }

  public void setGender(char gender){
    this.gender = gender;
  }
}

