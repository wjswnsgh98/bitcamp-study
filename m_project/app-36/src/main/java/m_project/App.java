package m_project;

import m_project.dao.BoardDao;
import m_project.dao.BoardListDao;
import m_project.dao.MemberDao;
import m_project.dao.MemberListDao;
import m_project.handler.BoardAddListener;
import m_project.handler.BoardDeleteListener;
import m_project.handler.BoardDetailListener;
import m_project.handler.BoardListListener;
import m_project.handler.BoardUpdateListener;
import m_project.handler.FooterListener;
import m_project.handler.HeaderListener;
import m_project.handler.HelloListener;
import m_project.handler.MemberAddListener;
import m_project.handler.MemberDeleteListener;
import m_project.handler.MemberDetailListener;
import m_project.handler.MemberListListener;
import m_project.handler.MemberUpdateListener;
import util.BreadcrumbPrompt;
import util.Menu;
import util.MenuGroup;

public class App {
  MemberDao memberDao = new MemberListDao("member.json");
  BoardDao boardDao = new BoardListDao("board.json");
  BoardDao radingDao = new BoardListDao("reading.json");

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public App(){
    prepareMenu();
  }

  public static void main(String[] args) {
    new App().execute();
  }

  static void printTitle(){
    System.out.println("도서 대여자 관리 시스템");
    System.out.println("----------------------------------");
  }

  public void execute(){
    printTitle();
    mainMenu.execute(prompt);
    prompt.close();
  }

  private void prepareMenu(){
    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberDao)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberDao)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberDao)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberDao)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberDao)));
    mainMenu.add(memberMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardDao)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardDao)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardDao)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardDao)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardDao)));
    mainMenu.add(boardMenu);

    MenuGroup readingMenu = new MenuGroup("독서록");
    readingMenu.add(new Menu("등록", new BoardAddListener(radingDao)));
    readingMenu.add(new Menu("목록", new BoardListListener(radingDao)));
    readingMenu.add(new Menu("조회", new BoardDetailListener(radingDao)));
    readingMenu.add(new Menu("변경", new BoardUpdateListener(radingDao)));
    readingMenu.add(new Menu("삭제", new BoardDeleteListener(radingDao)));
    mainMenu.add(readingMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);
  }
}