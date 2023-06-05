package m_project;

import java.util.Scanner;

public class App {
  public static void main(String[] args) {
    System.out.println("도서 관리 시스템");
    System.out.println("----------------------------------");

    Scanner sc = new Scanner(System.in);

    final int SIZE = 2;

    int[] book_no = new int[SIZE];
    String[] name = new String[SIZE];
    String[] p_num = new String[SIZE];
    boolean[] sch_person = new boolean[SIZE];
    char[] gender = new char[SIZE];
    
    for(int i=0; i<SIZE; i++){
    System.out.print("도서 번호? ");
    book_no[i] = sc.nextInt(); 
    System.out.print("이름? ");
    name[i] = sc.next();
    System.out.print("핸드폰번호? ");
    p_num[i] = sc.next();
    System.out.print("학교재직여부(true/false)? ");
    sch_person[i] = sc.nextBoolean();
    System.out.print("성별(남자(M), 여자(W))? ");
    String str = sc.next(); 
    gender[i] = str.charAt(0);
    }
    

    System.out.println("-------------------------");

    for(int i=0; i<SIZE; i++){
    System.out.printf("도서 번호: %d\n", book_no[i]);
    System.out.printf("이름: %s\n", name[i]);
    System.out.printf("핸드폰번호: %s\n", p_num[i]);
    System.out.printf("학교재직여부(true/false): %b\n", sch_person[i]);
    System.out.printf("성별(남자(M), 여자(W)): %c\n", gender[i]);
    }
    sc.close();
  }
}
