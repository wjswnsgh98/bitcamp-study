package project;

import java.util.ArrayList;
import java.util.LinkedList;
import project.handler.BoardAddListener;
import project.handler.BoardDeleteListener;
import project.handler.BoardDetailListener;
import project.handler.BoardListListener;
import project.handler.BoardUpdateListener;
import project.handler.BookAddListener;
import project.handler.BookDeleteListener;
import project.handler.BookListListener;
import project.handler.BookRentListener;
import project.handler.BookViewListener;
import project.handler.FooterListener;
import project.handler.HeaderListener;
import project.handler.HelloListener;
import project.handler.MemberAddListener;
import project.handler.MemberDeleteListener;
import project.handler.MemberDetailListener;
import project.handler.MemberListListener;
import project.handler.MemberUpdateListener;
import project.vo.Board;
import project.vo.Book;
import project.vo.Member;
import util.BreadcrumbPrompt;
import util.Menu;
import util.MenuGroup;

public class App {
  public static void main(String[] args) {
    ArrayList<Member> memberList = new ArrayList<>();
    LinkedList<Board> boardList = new LinkedList<>();
    LinkedList<Book> bookList = new LinkedList<>();

    BreadcrumbPrompt prompt = new BreadcrumbPrompt();

    MenuGroup mainMenu = new MenuGroup("메인");

    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberList)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberList)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberList)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberList)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberList)));
    mainMenu.add(memberMenu);

    MenuGroup bookMenu = new MenuGroup("도서 대여");
    bookMenu.add(new Menu("대여 가능한 도서 목록", new BookRentListener(bookList)));
    bookMenu.add(new Menu("도서 대여 등록", new BookAddListener(bookList)));
    bookMenu.add(new Menu("대여 도서 목록", new BookListListener(bookList)));
    bookMenu.add(new Menu("대여 도서 조회", new BookViewListener(bookList)));
    bookMenu.add(new Menu("대여 도서 반납", new BookDeleteListener(bookList)));
    mainMenu.add(bookMenu);

    MenuGroup boardMenu = new MenuGroup("도서 추천 게시글");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardList)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardList)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardList)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardList)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardList)));
    mainMenu.add(boardMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);

    printTitle();

    mainMenu.execute(prompt);

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