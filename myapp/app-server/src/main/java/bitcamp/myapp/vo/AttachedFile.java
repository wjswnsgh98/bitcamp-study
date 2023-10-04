package bitcamp.myapp.vo;

import java.io.Serializable;
import lombok.Data;

@Data
public class AttachedFile implements Serializable{
  private static final long serialVersionUID = 1L;

  private int no;
  private String originName;
  private String filePath;
  private int boardNo;
}