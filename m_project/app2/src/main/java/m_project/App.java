package m_project;

public class App {
  public static void main(String[] args) {
    System.out.println("도서 관리 시스템");
    System.out.println("----------------------------------");
    
    int book_no = 100;
    String name = "홍길동";
    String p_num = "01033333333";
    boolean sch_person = true;
    char gender = 'M';
    
    System.out.printf("도서 번호: %d\n", book_no);
    System.out.printf("이름: %s\n", name);
    System.out.printf("핸드폰번호: %s\n", p_num);
    System.out.printf("학교재직여부: %b\n", true);
    System.out.printf("성별(남자(M), 여자(W)): %c\n", 'M');
  }
}
