package m_project.handler;

import util.Prompt;

public class MemberHandler {

  static final int MAX_SIZE = 100;
  static int[] bookId = new int[MAX_SIZE];
  static String[] bookTitle = new String[MAX_SIZE];
  static String[] author = new String[MAX_SIZE];
  static String[] name = new String[MAX_SIZE];
  static String[] phoneNum = new String[MAX_SIZE];
  static char[] gender = new char[MAX_SIZE];
  static int userId = 1;
  static int length = 0;

  static final char MALE = 'M';
  static final char FEMALE = 'W';

  public static void inputMember(){
    if(!available()){
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    bookTitle[length] = Prompt.inputString("도서제목? ");
    author[length] = Prompt.inputString("글쓴이? ");
    name[length] = Prompt.inputString("이름? ");
    phoneNum[length] = Prompt.inputString("핸드폰번호? ");
    gender[length] = inputGender((char)0);

    bookId[length] = userId++;
    length++;
  }

  public static void printMembers(){
    System.out.println("-----------------------------------------------");
    System.out.println("도서번호, 도서제목, 글쓴이, 이름, 핸드폰번호, 성벌");
    System.out.println("-----------------------------------------------");

    for(int i=0; i<length; i++){
      System.out.printf("%d, %s, %s, %s, %s, %s\n", bookId[i], bookTitle[i], author[i], name[i], phoneNum[i], toGenderString(gender[i]));
    }
  }

  public static void viewMember(){
    String memberNo = Prompt.inputString("도서번호? ");
    for(int i=0; i < length; i++){
      if(bookId[i] == Integer.parseInt(memberNo)){
        System.out.printf("도서제목: %s\n", bookTitle[i]);
        System.out.printf("글쓴이: %s\n", author[i]);
        System.out.printf("이름: %s\n", name[i]);
        System.out.printf("핸드폰번호: %s\n", phoneNum[i]);
        System.out.printf("성별: %s\n", toGenderString(gender[i]));
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
      if(bookId[i] == Integer.parseInt(memberNo)){
        System.out.printf("도서제목(%s)? ", bookTitle[i]);
        bookTitle[i] = Prompt.inputString("");
        System.out.printf("글쓴이(%s)? ", author[i]);
        author[i] = Prompt.inputString("");
        System.out.printf("이름(%s)? ", name[i]);
        name[i] = Prompt.inputString("");
        System.out.printf("핸드폰번호(%s)? ", phoneNum[i]);
        phoneNum[i] = Prompt.inputString("");
        gender[i] = inputGender(gender[i]);
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
      bookId[i] = bookId[i+1];
      bookTitle[i] = bookTitle[i+1];
      author[i] = author[i+1];
      name[i] = name[i+1];
      phoneNum[i] = phoneNum[i+1];
      gender[i] = gender[i+1];
    }

    bookId[length-1] = 0;
    bookTitle[length-1] = null;
    author[length-1] = null;
    name[length-1] = null;
    phoneNum[length-1] = null;
    gender[length-1] = (char)0;

    length--;
  }

  public static int indexOf(int memberNo){
    for(int i=0; i<length; i++){
      if(bookId[i] == memberNo){
        return i;
      }
    }
    return -1;
  }
  
  public static boolean available(){
    return length < MAX_SIZE;
  }
}