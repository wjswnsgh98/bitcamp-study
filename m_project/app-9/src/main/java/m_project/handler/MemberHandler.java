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
    bookTitle[length] = Prompt.inputString("도서제목? ");
    author[length] = Prompt.inputString("글쓴이? ");
    name[length] = Prompt.inputString("이름? ");
    phoneNum[length] = Prompt.inputString("핸드폰번호? ");
    
    loop: while(true){
      String menuNo = Prompt.inputString("성별:\n" + 
      " 1. 남자\n" + 
      " 2. 여자\n" + 
      "> ");

      switch(menuNo){
        case "1":
          gender[length] = MALE;
          break loop;
        case "2":
          gender[length] = FEMALE;
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
    bookId[length]=userId++;
    length++;
  }

  public static void printMembers(){
    System.out.println("-----------------------------------------------");
    System.out.println("도서번호, 도서제목, 글쓴이, 이름, 핸드폰번호, 성벌");
    System.out.println("-----------------------------------------------");

    for(int i=0; i<length; i++){
      System.out.printf("%d, %s, %s, %s, %s, %c\n", bookId[i], bookTitle[i], author[i], name[i], phoneNum[i], gender[i]);
    }
  }

  public static boolean available(){
    return length < MAX_SIZE;
  }
}