package m_project;

public class App {
  public static void main(String[] args) {
    System.out.println("도서 목록 관리 시스템");
    System.out.println("----------------------------------");
    
    int bookId = 100;
    String title = "노인과바다";
    String author = "어니스트 헤밍웨이";
    String name = "홍길동";
    String phoneNum = "01033333333";
    boolean schPerson = true;
    char gender = 'M';
    
    System.out.printf("도서번호: %d\n", bookId);
    System.out.printf("도서제목: %s\n", title);
    System.out.printf("글쓴이: %s\n", author);
    System.out.printf("이름: %s\n", name);
    System.out.printf("핸드폰번호: %s\n", phoneNum);
    System.out.printf("학교재직여부: %b\n", true);
    System.out.printf("성별(남자(M), 여자(W)): %c\n", 'M');
  }
}
