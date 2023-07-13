package project.handler;

import project.vo.Book;
import util.Prompt;

public class BookHandler {
  String[][] BOOKS = {{"노인과바다", "3"}, {"박지성", "3"}, {"챔스우승맨시티", "3"}};
  private static int BOOK_COUNT = 3;
  private static final int MAX_SIZE = 100;

  private Prompt prompt;
  private Book[] books = new Book[MAX_SIZE];
  private int length = 0;
  private String title;

  public BookHandler(Prompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
  }

  public void service() {
    printMenu();

    while (true) {
      String menuNo = prompt.inputString("%s> ", this.title);
      if (menuNo.equals("0")) {
        return;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        this.inputBook();
      } else if (menuNo.equals("2")) {
        this.printBooks();
      } else if (menuNo.equals("3")) {
        this.viewBook();
      } else if (menuNo.equals("4")) {
        this.deleteBook();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }

  private static void printMenu() {
    System.out.println("1. 도서 대여 등록");
    System.out.println("2. 대여 가능한 도서 목록");
    System.out.println("3. 대여 도서 조회");
    System.out.println("4. 대여 도서 삭제");
    System.out.println("0. 메인");
  }

  private void inputBook() {
    if (!this.available()) {
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Book book = new Book();
    book.setBookTitle(this.prompt.inputString("도서 제목? "));
    book.setAuthor(this.prompt.inputString("저자? "));
    book.setName(this.prompt.inputString("대여자 이름? "));

    for(int i = 0; i < BOOKS.length; i++) {
      String str = BOOKS[i][0];
      int count = Integer.parseInt(BOOKS[i][1]);
      if(str.equals(book.getBookTitle())) {
        count--;
        BOOKS[i][1] = Integer.toString(count);
      }
    }

    this.books[this.length++] = book;
  }

  private void printBook() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 대여가능한 도서 제목, 수량");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.length; i++) {
      Book book = this.books[i];

      System.out.printf("%d, %s, %s, %tY-%4$tm-%4$td\n",
          book.getNo(),
          book.getBookTitle(),
          book.getAuthor(),
          book.getRentalDate());
    }
  }

  private void printBooks() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 도서 제목, 저자, 대여일");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.length; i++) {
      Book book = this.books[i];

      System.out.printf("%d, %s, %s, %tY-%4$tm-%4$td\n",
          book.getNo(),
          book.getBookTitle(),
          book.getAuthor(),
          book.getRentalDate());
    }
  }

  private void viewBook() {
    String bookNo = this.prompt.inputString("번호? ");
    for (int i = 0; i < this.length; i++) {
      Book book = this.books[i];
      if (book.getNo() == Integer.parseInt(bookNo)) {
        System.out.printf("도서 제목: %s\n", book.getBookTitle());
        System.out.printf("저자: %s\n", book.getAuthor());
        System.out.printf("대여일: %tY-%1$tm-%1$td\n", book.getRentalDate());
        System.out.printf("대여자: %s\n", book.getName());
        return;
      }
    }
    System.out.println("해당 번호는 없습니다!");
  }

  private void deleteBook() {
    int deletedIndex = indexOf(this.prompt.inputInt("번호? "));
    if (deletedIndex == -1) {
      System.out.println("해당 번호는 없습니다!");
      return;
    }

    for (int i = deletedIndex; i < this.length - 1; i++) {
      this.books[i] = this.books[i + 1];
    }

    this.books[--this.length] = null;
  }

  private int indexOf(int bookNo) {
    for (int i = 0; i < this.length; i++) {
      Book book = this.books[i];
      if (book.getNo() == bookNo) {
        return i;
      }
    }
    return -1;
  }

  private boolean available() {
    return this.length < MAX_SIZE;
  }
}
