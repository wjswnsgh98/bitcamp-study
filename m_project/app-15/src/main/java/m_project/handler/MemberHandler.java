package m_project.handler;

import m_project.vo.Member;
import util.Prompt;

public class MemberHandler {

  private static final int MAX_SIZE = 100;
  //variable initializer(변수초기화 문장) => static 블록으로 이동
  // 단 final 변수는 static 블록에서 값을 할당하지 않고 그냥 상수로 취급한다.

  private Prompt prompt;

  private Member[] members = new Member[MAX_SIZE];
  // variable initializer(변수초기화 문장) => 생성자로 이동
  
  private int length = 0;

  // 생성자: 인스턴스를 사용할 수 있도록 유효한 값으로 초기화시키는 일을 한다.
  // => 필요한 값을 외부에서 받고 싶으면 파라미터를 선언하라.
  public MemberHandler(Prompt prompt){
    this.prompt = prompt;
  }

  public void inputMember(){
    if(!this.available()){
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Member m = new Member();
    m.setB_title(this.prompt.inputString("도서제목? "));
    m.setAuthor(this.prompt.inputString("글쓴이? "));
    m.setName(this.prompt.inputString("이름? "));
    m.setP_num(this.prompt.inputString("핸드폰번호? "));
    m.setGender(inputGender((char)0));

    this.members[this.length++] = m;
  }

  public void printMembers(){
    System.out.println("-----------------------------------------------");
    System.out.println("도서번호, 도서제목, 글쓴이, 이름, 핸드폰번호, 성별");
    System.out.println("-----------------------------------------------");

    for(int i=0; i<this.length; i++){
      Member m = this.members[i];
      System.out.printf("%d, %s, %s, %s, %s, %s\n", m.getBook_no(), m.getB_title(), m.getAuthor(),
          m.getName(), m.getP_num(), toGenderString(m.getGender()));
    }
  }

  public void viewMember(){
    String memberNo = this.prompt.inputString("도서번호? ");
    for(int i=0; i < this.length; i++){
      Member m = this.members[i];
      if(m.book_no == Integer.parseInt(memberNo)){
        System.out.printf("도서제목: %s\n", m.getB_title());
        System.out.printf("글쓴이: %s\n", m.getAuthor());
        System.out.printf("이름: %s\n", m.getName());
        System.out.printf("핸드폰번호: %s\n", m.getP_num());
        System.out.printf("성별: %s\n", toGenderString(m.getGender()));
        return;
      }
    }
    System.out.println("해당 번호의 책을 빌려간 사람이 없습니다");
  }

  public static String toGenderString(char gender){
    return gender == 'M' ? "남성" : "여성";
  }

  public void updateMember(){
    String memberNo = this.prompt.inputString("번호? ");
    for(int i=0; i < this.length; i++){
      Member m = this.members[i];
      if(m.getBook_no() == Integer.parseInt(memberNo)){
        m.setB_title(this.prompt.inputString("도서제목(%s)? ", m.getB_title()));
        m.setAuthor(this.prompt.inputString("글쓴이(%s)? ", m.getAuthor()));
        m.setName(this.prompt.inputString("이름(%s)? ", m.getName()));
        m.setP_num(this.prompt.inputString("핸드폰번호(%s)? ", m.getP_num()));
        m.setGender(inputGender(m.getGender()));
        return;
      }
    }
    System.out.println("해당 번호의 책을 빌려간 사람이 없습니다");
  }

  private char inputGender(char gender){
    String label;
    if(gender == 0){
      label = "성별?\n";
    } else{
      label = String.format("성별(%s)?\n", toGenderString(gender));
    }

    while(true){
      String menuNo = this.prompt.inputString(label +
          " 1. 남자\n" +
          " 2. 여자\n" +
          "> ");

      switch(menuNo){
        case "1":
          return Member.MALE;
        case "2":
          return Member.FEMALE;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public void deleteMember(){
    int memberNo = this.prompt.inputInt("번호? ");

    int deleteIndex = indexOf(memberNo);
    if(deleteIndex == -1){
      System.out.println("해당 번호의 책을 빌려간 사람이 없습니다!");
    }

    for(int i=deleteIndex; i<this.length-1; i++){
      this.members[i] = this.members[i+1];
    }

    this.members[--this.length] = null;
  }

  public int indexOf(int memberNo){
    for(int i=0; i<this.length; i++){
      Member m = this.members[i];
      if(m.getBook_no() == memberNo){
        return i;
      }
    }
    return -1;
  }

  public boolean available(){
    return this.length < MAX_SIZE;
  }
}