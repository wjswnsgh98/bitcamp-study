package m_project;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    System.out.println("도서 목록 관리 시스템");
    System.out.println("----------------------------------");

    Scanner sc = new Scanner(System.in);

    final int SIZE = 2; // final로 선언, 변하지 않는 상수라는 의미

    int[] bookId = new int[SIZE];
    String[] title = new String[SIZE];
    String[] author = new String[SIZE];
    String[] name = new String[SIZE];
    String[] phoneNum = new String[SIZE];
    boolean[] schPerson = new boolean[SIZE];
    char[] gender = new char[SIZE];
    
    for(int i=0; i<SIZE; i++){
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
    System.out.print("학교재직여부(true/false)? ");
    schPerson[i] = sc.nextBoolean();
    System.out.print("성별(남자(M), 여자(W))? ");
    String str = sc.next(); 
    gender[i] = str.charAt(0);
    }
    

    System.out.println("-------------------------");

    for(int i=0; i<SIZE; i++){
    System.out.printf("도서번호: %d\n", bookId[i]);
    System.out.printf("도서제목: %s\n", title[i]);
    System.out.printf("글쓴이: %s\n", author[i]);
    System.out.printf("이름: %s\n", name[i]);
    System.out.printf("핸드폰번호: %s\n", phoneNum[i]);
    System.out.printf("학교재직여부(true/false): %b\n", schPerson[i]);
    System.out.printf("성별(남자(M), 여자(W)): %c\n", gender[i]);
    }
    sc.close();
  }
}
