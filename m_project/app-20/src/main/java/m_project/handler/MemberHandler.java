package m_project.handler;

import m_project.vo.Member;
import util.LinkedList;
import util.Prompt;

public class MemberHandler implements Handler{
  private LinkedList list = new LinkedList();
  private Prompt prompt;
  private String title;

  public MemberHandler(Prompt prompt, String title){
    this.prompt = prompt;
    this.title = title;
  }

  public void execute(){
    printMenu();

    while(true){
      String menuNo = prompt.inputString("%s> ", this.title);
      if(menuNo.equals("0")){
        break;
      } else if(menuNo.equals("menu")){
        printMenu();
      } else if(menuNo.equals("1")){
        this.inputMember();
      } else if(menuNo.equals("2")){
        this.printMembers();
      } else if(menuNo.equals("3")){
        this.viewMember();
      } else if(menuNo.equals("4")){
        this.updateMember();
      } else if(menuNo.equals("5")){
        this.deleteMember();
      } else{
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }
  
  private static void printMenu(){
    System.out.println("1. 등록");
    System.out.println("2. 목록");
    System.out.println("3. 조회");
    System.out.println("4. 변경");
    System.out.println("5. 삭제");
    System.out.println("0. 메인");
  }

  public void inputMember(){
    Member m = new Member();
    m.setB_title(this.prompt.inputString("도서제목? "));
    m.setAuthor(this.prompt.inputString("글쓴이? "));
    m.setName(this.prompt.inputString("이름? "));
    m.setP_num(this.prompt.inputString("핸드폰번호? "));
    m.setGender(inputGender((char)0));

    this.list.add(m);
  }

  public void printMembers(){
    System.out.println("-----------------------------------------------");
    System.out.println("도서번호, 도서제목, 글쓴이, 이름, 핸드폰번호, 성별");
    System.out.println("-----------------------------------------------");

    Object[] arr = this.list.getList();
    for(Object obj : arr){
      Member m = (Member) obj;
      System.out.printf("%d, %s, %s, %s, %s, %s\n", m.getBook_no(), m.getB_title(), m.getAuthor(),
          m.getName(), m.getP_num(), toGenderString(m.getGender()));
    }
  }

  public void viewMember(){
    int memberNo = this.prompt.inputInt("도서번호? ");

    Member m = (Member) this.list.retrieve(new Member(memberNo));
    if(m == null){
      System.out.println("해당 번호의 책을 빌려간 사람이 없습니다");
      return;
    }

    System.out.printf("도서제목: %s\n", m.getB_title());
    System.out.printf("글쓴이: %s\n", m.getAuthor());
    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("핸드폰번호: %s\n", m.getP_num());
    System.out.printf("성별: %s\n", toGenderString(m.getGender()));
  }

  public static String toGenderString(char gender){
    return gender == 'M' ? "남성" : "여성";
  }

  public void updateMember(){
    int memberNo = this.prompt.inputInt("번호? ");

    Member m = (Member) this.list.retrieve(new Member(memberNo));
    if(m == null){
      System.out.println("해당 번호의 책을 빌려간 사람이 없습니다");
      return;
    }

    m.setB_title(this.prompt.inputString("도서제목(%s)? ", m.getB_title()));
    m.setAuthor(this.prompt.inputString("글쓴이(%s)? ", m.getAuthor()));
    m.setName(this.prompt.inputString("이름(%s)? ", m.getName()));
    m.setP_num(this.prompt.inputString("핸드폰번호(%s)? ", m.getP_num()));
    m.setGender(inputGender(m.getGender()));
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
    if(!this.list.remove(new Member(this.prompt.inputInt("번호? ")))){
      System.out.println("해당 번호의 책을 빌려간 사람이 없습니다!");
    }
  }
}