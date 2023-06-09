package m_project.handler;

import m_project.vo.Member;
import util.Prompt;

public class MemberHandler {

  static final int MAX_SIZE = 100;
  static Member[] members = new Member[MAX_SIZE];
  static int userId = 1;
  static int length = 0;

  static final char MALE = 'M';
  static final char FEMALE = 'W';

  public static void inputMember(){
    if(!available()){
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Member m = new Member(); // 멤버 객체 생성
    m.bookTitle = Prompt.inputString("도서제목? ");
    m.author = Prompt.inputString("글쓴이? ");
    m.name = Prompt.inputString("이름? ");
    m.phoneNum = Prompt.inputString("핸드폰번호? ");
    m.gender = inputGender((char)0);
    m.bookId = userId++;

    // 위에서 만든 Member 인스턴스의 주소를 잃어버리지 않게 레퍼런스 배열에 담는다.
    members[length++] = m;
  }

  public static void printMembers(){
    System.out.println("-----------------------------------------------");
    System.out.println("도서번호, 도서제목, 글쓴이, 이름, 핸드폰번호, 성별");
    System.out.println("-----------------------------------------------");

    for(int i=0; i<length; i++){
      Member m = members[i];
      System.out.printf("%d, %s, %s, %s, %s, %s\n", m.bookId, m.bookTitle, m.author, m.name, m.phoneNum, toGenderString(m.gender));
    }
  }

  public static void viewMember(){
    String memberNo = Prompt.inputString("도서번호? ");
    for(int i=0; i < length; i++){
      Member m = members[i];
      if(m.bookId == Integer.parseInt(memberNo)){
        System.out.printf("도서제목: %s\n", m.bookTitle);
        System.out.printf("글쓴이: %s\n", m.author);
        System.out.printf("이름: %s\n", m.name);
        System.out.printf("핸드폰번호: %s\n", m.phoneNum);
        System.out.printf("성별: %s\n", toGenderString(m.gender));
        return;
      }
    }
    System.out.println("해당 번호의 책을 빌려간 사람이 없습니다");
  }

  public static String toGenderString(char gender){
    return gender == 'M' ? "남성" : "여성";
  }

  public static void updateMember(){
    String memberNo = Prompt.inputString("번호? ");
    for(int i=0; i < length; i++){
      Member m = members[i];
      if(m.bookId == Integer.parseInt(memberNo)){
        System.out.printf("도서제목(%s)? ", m.bookTitle);
        m.bookTitle = Prompt.inputString("");
        System.out.printf("글쓴이(%s)? ", m.author);
        m.author = Prompt.inputString("");
        System.out.printf("이름(%s)? ", m.name);
        m.name = Prompt.inputString("");
        System.out.printf("핸드폰번호(%s)? ", m.phoneNum);
        m.phoneNum = Prompt.inputString("");
        m.gender = inputGender(m.gender);
        return;
      }
    }
    System.out.println("해당 번호의 책을 빌려간 사람이 없습니다");
  }

  public static char inputGender(char gender){
    String label;
    if(gender == 0){
      label = "성별?\n";
    } else{
      label = String.format("성별(%s)?\n", toGenderString(gender));
    }

    loop: while(true){
      String menuNo = Prompt.inputString(label +
          " 1. 남자\n" +
          " 2. 여자\n" +
          "> ");

      switch(menuNo){
        case "1":
          return MALE;
        case "2":
          return FEMALE;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public static void deleteMember(){
    int memberNo = Prompt.inputInt("번호? ");

    int deleteIndex = indexOf(memberNo);
    if(deleteIndex == -1){
      System.out.println("해당 번호의 책을 빌려간 사람이 없습니다!");
    }

    for(int i=deleteIndex; i<length-1; i++){
      members[i] = members[i+1];
    }

    members[--length] = null;
  }

  public static int indexOf(int memberNo){
    for(int i=0; i<length; i++){
      Member m = members[i];
      if(m.bookId == memberNo){
        return i;
      }
    }
    return -1;
  }

  public static boolean available(){
    return length < MAX_SIZE;
  }
}