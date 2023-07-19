package project.handler;

import project.vo.Book;
import util.ArrayList;
import util.Prompt;

public class BookHandler implements Handler{
  String[][] BOOKS = {{"노인과바다", "3"}, {"멈추지않는도전", "3"}, {"챔스", "3"}};
  private ArrayList list = new ArrayList();
  private Prompt prompt;
  private String title;

  public BookHandler(Prompt prompt, String title) {
    this.prompt = prompt;
    this.title = title;
  }

  public void execute() {
    printMenu();

    while (true) {
      String menuNo = prompt.inputString("%s> ", this.title);
      if (menuNo.equals("0")) {
        return;
      } else if (menuNo.equals("menu")) {
        printMenu();
      } else if (menuNo.equals("1")) {
        this.rentBook();
      } else if (menuNo.equals("2")) {
        this.inputBook();
      } else if (menuNo.equals("3")) {
        this.printBooks();
      } else if (menuNo.equals("4")) {
        this.viewBook();
      } else if (menuNo.equals("5")) {
        this.deleteBook();
      } else {
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }
  }

  private static void printMenu() {
    System.out.println("1. 대여 가능한 도서 목록");
    System.out.println("2. 도서 대여 등록");
    System.out.println("3. 대여 도서 목록");
    System.out.println("4. 대여 도서 조회");
    System.out.println("5. 대여 도서 반납");
    System.out.println("0. 메인");
  }

  private void rentBook() {
    System.out.println("---------------------------------------");
    System.out.println("대여가능한 도서 제목, 수량");
    System.out.println("---------------------------------------");

    for (int i = 0; i < BOOKS.length; i++) {
      System.out.print(BOOKS[i][0] + " " + BOOKS[i][1]);
      System.out.println();
    }
  }

  private void inputBook() {
    boolean bookFound = false;
    Book book = new Book();
    String bTitle = this.prompt.inputString("도서 제목? ");
    for(int i = 0; i < BOOKS.length; i++) {
      String str = BOOKS[i][0];
      if(str.equals(bTitle)) {
        book.setBookTitle(bTitle);
        bookFound = true;
        break;
      }
    }
    if(!bookFound) {
      System.out.println("해당 제목의 도서가 없습니다!");
      return;
    }
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

    this.list.add(book);
  }

  public void printBooks() {
    System.out.println("---------------------------------------");
    System.out.println("제목, 저자, 대여자 이름, 대여일, 반납일");
    System.out.println("---------------------------------------");

    Object[] arr = this.list.list();
    for (Object obj : arr) {
      Book book = (Book) obj;
      System.out.printf("%s, %s, %s, %tY-%4$tm-%4$td, %tY-%5$tm-%5$td\n",
          book.getBookTitle(),
          book.getAuthor(),
          book.getName(),
          book.getRentalDate(),
          book.getReturnDate());
    }
  }

  private void viewBook() {
    String lender = this.prompt.inputString("대여자 이름? ");

    Book book = (Book) this.list.get(new Book(lender));
    if(lender.equals("")) {
      System.out.println("해당 이름의 대여자는 없습니다!");
      return;
    }
    System.out.printf("도서 제목: %s\n", book.getBookTitle());
    System.out.printf("저자: %s\n", book.getAuthor());
    System.out.printf("대여일: %tY-%1$tm-%1$td\n", book.getRentalDate());
    System.out.printf("반납일: %tY-%1$tm-%1$td\n", book.getReturnDate());
    return;
  }

  private void deleteBook() {
    String lender = this.prompt.inputString("대여자 이름? ");

    if (!this.list.delete(new Book(lender))) {
      System.out.println("해당 이름의 대여자는 없습니다!");
    }

    for(int i = 0; i < BOOKS.length; i++) {
      if(lender.equals(BOOKS[i][0])) {
        BOOKS[i][1] = String.valueOf(Integer.parseInt(BOOKS[i][1]) + 1);
      }
    }
  }
}
