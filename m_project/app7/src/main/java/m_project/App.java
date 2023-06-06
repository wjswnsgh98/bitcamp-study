package m_project;

import java.util.Scanner;

public class App {
  static Scanner sc = new Scanner(System.in);
  static final int MAX_SIZE = 100;
  static int[] book_no = new int[MAX_SIZE];
  static String[] name = new String[MAX_SIZE];
  static String[] p_num = new String[MAX_SIZE];
  static char[] gender = new char[MAX_SIZE];
  static int bookId = 1;
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

  static void printTitle(){
    System.out.println("도서 관리 시스템");
    System.out.println("----------------------------------");
  }

  static void inputMember(){
    name[length] = prompt("이름? ");
    p_num[length] = prompt("핸드폰번호? ");
    
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
    book_no[length]=bookId++;
    length++;
  }

  static boolean promptContinue(){
    String response = prompt("계속 하시겠습니까?(Y/n) ");
    if(!response.equals("") && !response.equalsIgnoreCase("Y")){
       return false;
    }
    return true;
  }

  static void printMembers(){
      System.out.println("-------------------------");
      System.out.println("도서번호, 이름, 핸드폰번호, 성벌");
      System.out.println("-------------------------");

      for(int i=0; i<length; i++){
        System.out.printf("%d, %s, %s, %c\n", book_no[i], name[i], p_num[i], gender[i]);
      }
  }

  static String prompt(String title){
    System.out.print(title);
    return sc.nextLine();
  }
}