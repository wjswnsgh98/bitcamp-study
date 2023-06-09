package m_project;

import java.util.Scanner;

public class App {
  static Scanner sc = new Scanner(System.in);
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
  public static void main(String[] args) {

    printTitle();

    while(length<MAX_SIZE){
      inputMember();
      if(!promptContinue()){
        break;
      }
    }
    printMembers();

    sc.close();
  }

  // 제목 출력하는 메서드
  static void printTitle(){
    System.out.println("대여자 관리 시스템");
    System.out.println("----------------------------------");
  }

  // 대여자 입력하는 메서드
  static void inputMember(){
    bookTitle[length] = prompt("도서제목? ");
    author[length] = prompt("글쓴이? ");
    name[length] = prompt("이름? ");
    phoneNum[length] = prompt("핸드폰번호? ");
    
    loop: while(true){
      String menuNo = prompt("성별:\n" + 
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

  // 대여자 등록을 더 할건지 확인하는 메서드 
  static boolean promptContinue(){
    String response = prompt("계속 하시겠습니까?(Y/n) ");
    if(!response.equals("") && !response.equalsIgnoreCase("Y")){
       return false;
    }
    return true;
  }

  // 등록된 대여자들 목록 출력해주는 메서드
  static void printMembers(){
      System.out.println("------------------------------------------");
      System.out.println("도서번호, 도서제목, 글쓴이, 이름, 핸드폰번호, 성벌");
      System.out.println("------------------------------------------");

      for(int i=0; i<length; i++){
        System.out.printf("%d, %s, %s, %s, %s, %c\n", bookId[i], bookTitle[i], author[i], name[i], phoneNum[i], gender[i]);
      }
  }

  static String prompt(String title){
    System.out.print(title);
    return sc.nextLine();
  }
}