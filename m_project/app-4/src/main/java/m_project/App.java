package m_project;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    System.out.println("도서 목록 관리 시스템");
    System.out.println("----------------------------------");

    Scanner sc = new Scanner(System.in);
    
    System.out.print("도서번호? ");
    int bookId = sc.nextInt();
    System.out.print("도서제목? ");
    String title = sc.next();
    System.out.print("글쓴이? ");
    String author = sc.next(); 
    System.out.print("이름? ");
    String name = sc.next();
    System.out.print("핸드폰번호? ");
    String phoneNum = sc.next();
    System.out.print("학교재직여부(true/false)? ");
    boolean schPerson = sc.nextBoolean();
    System.out.print("성별(남자(M), 여자(W))? ");
    String str1 = sc.next(); 
    char gender = str1.charAt(0);

    System.out.println("-------------------------");

    System.out.printf("도서번호: %d\n", bookId);
    System.out.printf("도서제목: %s\n", title);
    System.out.printf("글쓴이: %s\n", author);
    System.out.printf("이름: %s\n", name);
    System.out.printf("핸드폰번호: %s\n", phoneNum);
    System.out.printf("학교재직여부(true/false): %b\n", schPerson);
    System.out.printf("성별(남자(M), 여자(W)): %c\n", gender);

    sc.close();
  }
}
