package m_project;

import java.util.Scanner;

// 기능별 메서드 나누기
public class App {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    final int MAX_SIZE = 100;
    int userId = 1;
    int length = 0;

    int[] bookId = new int[MAX_SIZE];
    String[] title = new String[MAX_SIZE];
    String[] author = new String[MAX_SIZE];
    String[] name = new String[MAX_SIZE];
    String[] phoneNum = new String[MAX_SIZE];
    char[] gender = new char[MAX_SIZE];

    printTitle();

    for(int i=0; i<MAX_SIZE; i++){
      inputMember(sc, i, title, author, name, phoneNum, gender, bookId, userId++);
      length++;
      if(!promptContinue(sc)){
        break;
      }
    }
    printMembers(length, bookId, title, author, name, phoneNum, gender);

    sc.close();
  }

  static void printTitle(){
    System.out.println("도서 목록 관리 시스템");
    System.out.println("----------------------------------");
  }

  static void inputMember(Scanner sc, int i, String[] title, String[] author, String[] name, 
    String[] phoneNum, char[] gender, int[] bookId, int userId){
    System.out.print("도서번호? ");
    bookId[i] = sc.nextInt();
    System.out.print("도서제목? ");
    title[i] = sc.next();
    System.out.print("글쓴이? ");
    author[i] = sc.next(); 
    System.out.print("이름? ");
    name[i] = sc.next();
    System.out.print("핸드폰번호? ");
    phoneNum[i] = sc.next();
    
    loop: while(true){
      System.out.println("성별: ");
      System.out.println(" 1. 남자");
      System.out.println(" 2. 여자");
      System.out.print("> ");
      String menuNo = sc.next();
      sc.nextLine();

      switch(menuNo){
        case "1":
          gender[i] = 'M';
          break loop;
        case "2":
          gender[i] = 'W';
          break loop;
        default:
          System.out.println("무효한 번호입니다.");
      }
    }
    bookId[i]=userId;
  }

  static boolean promptContinue(Scanner sc){
    System.out.print("계속 하시겠습니까?(Y/n) ");
    String response = sc.nextLine();
    if(!response.equals("") && !response.equalsIgnoreCase("Y")){
       return false;
    }
    return true;
  }

  static void printMembers(int length, int[] bookId, String[] title, String[] author,
    String[] name, String[] phoneNum, char[] gender){
      System.out.println("-------------------------------------------");
      System.out.println("도서번호, 도서제목, 글쓴이, 이름, 핸드폰번호, 성벌");
      System.out.println("-------------------------------------------");

      for(int i=0; i<length; i++){
        System.out.printf("%d, %s, %s, %s, %s, %c\n", bookId[i], title[i], author[i], name[i], phoneNum[i], gender[i]);
      }
  }
}