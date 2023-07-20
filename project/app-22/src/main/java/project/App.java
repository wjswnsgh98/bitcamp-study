package project;

import project.handler.BoardHandler;
import project.handler.BookHandler;
import project.handler.Handler;
import project.handler.MemberHandler;
import util.ArrayList;
import util.LinkedList;
import util.MenuPrompt;

public class App {
  public static void main(String[] args) {
    MenuPrompt prompt = new MenuPrompt();
    prompt.appendBreadcrumb("메인", getMenu());

    Handler memberHandler = new MemberHandler(prompt, "회원", new ArrayList());
    Handler bookHandler = new BookHandler(prompt, "도서 대여", new LinkedList());
    Handler boardHandler = new BoardHandler(prompt, "도서추천게시판", new LinkedList());


    printTitle();

    prompt.printMenu();

    loop: while (true) {
      String menuNo = prompt.inputMenu();
      switch (menuNo) {
        case "0": break loop;
        case "1": memberHandler.execute(); break;
        case "2": bookHandler.execute(); break;
        case "3": boardHandler.execute(); break;
      }
    }

    prompt.close();
  }

  static String getMenu() {
    StringBuilder menu = new StringBuilder();
    menu.append("1. 회원\n");
    menu.append("2. 도서 대여\n");
    menu.append("3. 도서추천게시글\n");
    menu.append("0. 종료\n");
    return menu.toString();
  }

  static void printTitle(){
    System.out.println("도서 대여 관리 시스템");
    System.out.println("----------------------------------");
  }
}