package project.handler;

import project.vo.Book;
import util.List;
import util.MenuPrompt;

public class BookHandler implements Handler{
  public String[][] BOOKS = {{"노인과바다", "3"}, {"멈추지않는도전", "3"}, {"챔스", "3"}};
  private List list;
  private MenuPrompt prompt;
  private String title;

  public BookHandler(MenuPrompt prompt, String title, List list) {
    this.prompt = prompt;
    this.title = title;
    this.list = list;
  }

  public void execute() {
    prompt.appendBreadcrumb(this.title, getMenu());

    prompt.printMenu();

    while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
        case "0": prompt.removeBreadcrumb(); return;
        case "1": this.rentBook(); break;
        case "2": this.inputBook(); break;
        case "3": this.printBooks(); break;
        case "4": this.viewBook(); break;
        case "5": this.deleteBook(); break;
      }
    }
  }

  private static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 대여 가능한 도서 목록\n");
    menu.append("2. 도서 대여 등록\n");
    menu.append("3. 대여 도서 목록\n");
    menu.append("4. 대여 도서 조회\n");
    menu.append("5. 대여 도서 반납\n");
    menu.append("0. 메인\n");
    return menu.toString();
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

    for (int i = 0; i < this.list.size(); i++) {
      Book book = (Book) this.list.get(i);
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

    Book book = this.findBy(lender);
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

    // 리스트에서 도서 삭제
    Book deletedBook = this.findBy(lender);
    if (deletedBook == null) {
      System.out.println("해당 이름의 대여자는 없습니다!");
      return;
    }
    this.list.remove(new Book(lender));

    // BOOKS에서 같은 도서 제목의 수량을 1 증가
    for (int i = 0; i < BOOKS.length; i++) {
      if (deletedBook.getBookTitle().equals(BOOKS[i][0])) {
        int count = Integer.parseInt(BOOKS[i][1]);
        BOOKS[i][1] = String.valueOf(count + 1);
        break; // 해당 도서를 찾았으므로 더 이상 반복할 필요가 없음
      }
    }
  }

  private Book findBy(String str) {
    for (int i = 0; i < this.list.size(); i++) {
      Book b = (Book) this.list.get(i);
      if (b.getName().equals(str)) {
        return b;
      }
    }
    return null;
  }
}
