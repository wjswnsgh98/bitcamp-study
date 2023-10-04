package bitcamp.myapp.vo;

import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

@Data
public class Member implements Serializable{
  private static final long serialVersionUID = 1L;

  public static final char MALE = 'M';
  public static final char FEMALE = 'W';

  public int no;
  public String name;
  public String email;
  public String password;
  public char gender;
  private Date createdDate;
  private String photo;
}

