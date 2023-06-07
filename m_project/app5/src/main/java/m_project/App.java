package m_project;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    System.out.println("도서 목록 관리 시스템");
    System.out.println("----------------------------------");

    Scanner sc = new Scanner(System.in);

    final int MAX_SIZE = 100;
    int bookId = 1;
    int length = 0;

    int[] book_no = new int[MAX_SIZE];
    String[] title = new String[MAX_SIZE];
    String[] author = new String[MAX_SIZE];
    String[] name = new String[MAX_SIZE];
    String[] p_num = new String[MAX_SIZE];
    boolean[] sch_person = new boolean[MAX_SIZE];
    char[] gender = new char[MAX_SIZE];
    
    for(int i=0; i<MAX_SIZE; i++){
      System.out.print("도서번호? ");
      book_no[i] = sc.nextInt();
      System.out.print("도서제목? ");
      title[i] = sc.next();
      System.out.print("글쓴이? ");
      author[i] = sc.next(); 
      System.out.print("이름? ");
      name[i] = sc.next();
      System.out.print("핸드폰번호? ");
      p_num[i] = sc.next();
      loop: while(true){
        System.out.println("성별: ");
        System.out.println(" 1. 남자");
        System.out.println(" 2. 여자");
        System.out.print("> ");
        String menuNo = sc.next();

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
      book_no[i]=bookId++;
      length++;

      System.out.print("계속 하시겠습니까?(Y/n) ");
      sc.nextLine();
      String response = sc.nextLine();
      if(!response.equals("") && !response.equalsIgnoreCase("Y")){
        break;
      }
    }
    
    System.out.println("-------------------------------------------");
    System.out.println("도서번호, 도서제목, 글쓴이, 이름, 핸드폰번호, 성벌");
    System.out.println("-------------------------------------------");

    for(int i=0; i<length; i++){
      System.out.printf("%d, %s, %s, %s, %s, %c\n", book_no[i], title[i], author[i], name[i], p_num[i], gender[i]);
    }
    sc.close();
  }
}