package project;

import project.dao.BoardDao;
import project.dao.BoardListDao;
import project.dao.MemberDao;
import project.dao.MemberListDao;
import project.handler.BoardAddListener;
import project.handler.BoardDeleteListener;
import project.handler.BoardDetailListener;
import project.handler.BoardListListener;
import project.handler.BoardUpdateListener;
import project.handler.FooterListener;
import project.handler.HeaderListener;
import project.handler.HelloListener;
import project.handler.MemberAddListener;
import project.handler.MemberDeleteListener;
import project.handler.MemberDetailListener;
import project.handler.MemberListListener;
import project.handler.MemberUpdateListener;
import util.BreadcrumbPrompt;
import util.Menu;
import util.MenuGroup;

public class App {
  MemberDao memberDao = new MemberListDao("member.json");
  //BookDao bookDao = new BookListDao("book.json");
  BoardDao boardDao = new BoardListDao("board.json");

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public App() {
    prepareMenu();
  }

  public static void main(String[] args) {
    new App().execute();
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

  public void execute() {
    printTitle();
    mainMenu.execute(prompt);
    prompt.close();
  }

  private void prepareMenu() {
    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberDao)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberDao)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberDao)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberDao)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberDao)));
    mainMenu.add(memberMenu);

    //    MenuGroup bookMenu = new MenuGroup("도서 대여");
    //    bookMenu.add(new Menu("대여 가능한 도서 목록", new BookRentListener(bookDao)));
    //    bookMenu.add(new Menu("도서 대여 등록", new BookAddListener(bookDao)));
    //    bookMenu.add(new Menu("대여 도서 목록", new BookListListener(bookDao)));
    //    bookMenu.add(new Menu("대여 도서 조회", new BookViewListener(bookDao)));
    //    bookMenu.add(new Menu("대여 도서 반납", new BookDeleteListener(bookDao)));
    //    mainMenu.add(bookMenu);

    MenuGroup boardMenu = new MenuGroup("도서 추천 게시글");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardDao)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardDao)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardDao)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardDao)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardDao)));
    mainMenu.add(boardMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);
  }
}