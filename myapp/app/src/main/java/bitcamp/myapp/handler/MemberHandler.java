package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.Prompt;

public class MemberHandler {
  static final int MAX_SIZE = 100;
  static Member[] members = new Member[MAX_SIZE];
  static int length = 0;

  public static void inputMember() {
    if(!available()){
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Member m = new Member();
    m.name = Prompt.inputString("이름? ");
    m.email = Prompt.inputString("이메일? ");
    m.password = Prompt.inputString("암호? ");
    m.gender = inputGender((char)0);

    // 위에서 만든 Member 인스턴스의 주소를 잃어버리지 않게 레퍼런스 배열에 담는다.
    members[length++] = m;
  }

  public static void printMembers() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 이메일, 성별");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      Member m = members[i];
      System.out.printf("%d, %s, %s, %s\n", m.no, m.name, m.email, toGenderString(m.gender));
    }
  }

  public static void viewMember(){
    String memberNo = Prompt.inputString("번호? ");
    // 입력받은 번호를 가지고 배열에서 해당 회원을 찾아야한다.
    for(int i=0; i<length; i++){
      Member m = members[i];
      if(m.no == Integer.parseInt(memberNo)){
        // i번째 항목에 저장된 회원 정보 출력
        System.out.printf("이름: %s\n", m.getName());
        System.out.printf("이메일: %s\n", m.getEmail());
        System.out.printf("성별: %s\n", toGenderString(m.getGender()));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static void updateMember(){
    String memberNo = Prompt.inputString("번호? ");
    for(int i=0; i<length; i++){
      Member m = members[i];
      if(m.getNo() == Integer.parseInt(memberNo)){
        // i번째 항목에 저장된 회원 정보 출력
        System.out.printf("이름(%s)? ", m.getName());
        m.setName(Prompt.inputString("> "));
        System.out.printf("이메일(%s)? ", m.getEmail());
        m.setEmail(Prompt.inputString("> "));
        System.out.printf("새암호? ");
        m.setPassword(Prompt.inputString("> "));
        m.setGender(inputGender(m.getGender()));
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  private static char inputGender(char gender){
    String label;
    if(gender == 0){
      label = "성별?\n";
    } else{
      label = String.format("성별(%s)?\n", toGenderString(gender));
    }

    while (true) {
      String menuNo = Prompt.inputString(label +
          "  1. 남자\n" +
          "  2. 여자\n" +
          "> ");

      switch (menuNo) {
        case "1":
          return Member.MALE;
        case "2":
          return Member.FEMALE;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
  }

  public static void deleteMember(){
    // 삭제하려는 회원의 정보가 들어있는 인덱스를 알아낸다.
    int memberNo = Prompt.inputInt("번호? ");

    int deleteIndex = indexOf(memberNo);
    if(deleteIndex == -1){
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }

    for(int i=deleteIndex; i<length - 1; i++){
      members[i] = members[i+1];
    }

    members[--length] = null;
  }

  private static int indexOf(int memberNo){
    for(int i=0; i<length; i++){
      Member m = members[i];
      if(m.no == memberNo){
        return i;
      }
    }
    return -1;
  }

  public static String toGenderString(char gender){
    return gender == 'M' ? "남성" : "여성";
  }

  public static boolean available(){
    return length < MAX_SIZE;
  }
}
