package project;

import project.handler.BoardHandler;
import project.handler.BookHandler;
import project.handler.MemberHandler;
import util.Prompt;

public class App {
  public static void main(String[] args) {

    // 기본 생성자를 이용해 Prompt 인스턴스를 준비한다.
    // => 기본 생성자는 Scanner를 키보드와 연결한다.
    Prompt prompt = new Prompt();

    MemberHandler memberHandler = new MemberHandler(prompt, "회원");
    BookHandler bookHandler = new BookHandler(prompt, "도서 대여");
    BoardHandler boardHandler = new BoardHandler(prompt, "도서추천게시판");


    printTitle();

    printMenu();

    while(true){
      String menuNo = prompt.inputString("메인> ");
      if(menuNo.equals("0")){
        break;
      } else if(menuNo.equals("menu")){
        printMenu();
      } else if(menuNo.equals("1")){
        memberHandler.execute();
      } else if(menuNo.equals("2")){
        bookHandler.service();
      } else if(menuNo.equals("3")){
        boardHandler.service();
      } else{
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }

    prompt.close();
  }

  static void printMenu(){
    System.out.println("1. 회원");
    System.out.println("2. 도서 대여");
    System.out.println("3. 게시글");
    System.out.println("0. 종료");
  }

  static void printTitle(){
    System.out.println("도서 대여 관리 시스템");
    System.out.println("----------------------------------");
  }
}