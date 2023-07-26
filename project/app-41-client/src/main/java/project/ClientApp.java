package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import net.RequestEntity;
import project.dao.BoardDao;
import project.dao.BookDao;
import project.dao.DaoBuilder;
import project.dao.MemberDao;
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
import util.BreadcrumbPrompt;
import util.Menu;
import util.MenuGroup;

public class ClientApp {
  Socket socket;
  DataOutputStream out;
  DataInputStream in;

  MemberDao memberDao;
  BookDao bookDao;
  BoardDao boardDao;

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public ClientApp(String ip, int port) throws Exception{
    this.socket = new Socket(ip, port);
    this.out = new DataOutputStream(socket.getOutputStream());
    this.in = new DataInputStream(socket.getInputStream());

    DaoBuilder daoBuilder = new DaoBuilder(in, out);

    this.memberDao = daoBuilder.build("member", MemberDao.class);
    this.bookDao = daoBuilder.build("book", BookDao.class);
    this.boardDao = daoBuilder.build("board", BoardDao.class);

    prepareMenu();
  }

  public void close() throws Exception {
    prompt.close();
    out.close();
    in.close();
    socket.close();
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 2) {
      System.out.println("실행 예) java ... project.ClientApp 서버주소 포트번호");
      return;
    }

    ClientApp app = new ClientApp(args[0], Integer.parseInt(args[1]));
    app.execute();
    app.close();
  }

  static void printTitle(){
    System.out.println("도서 대여 관리 시스템");
    System.out.println("----------------------------------");
  }

  public void execute() {
    printTitle();
    mainMenu.execute(prompt);

    try {
      out.writeUTF(new RequestEntity().command("quit").toJson());

    } catch (Exception e) {
      System.out.println("종료 오류!");
      e.printStackTrace();
    }
  }

  private void prepareMenu() {
    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberDao)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberDao)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberDao)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberDao)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberDao)));
    mainMenu.add(memberMenu);

    MenuGroup bookMenu = new MenuGroup("도서 대여");
    bookMenu.add(new Menu("대여 가능한 도서 목록", new BookRentListener(bookDao)));
    bookMenu.add(new Menu("도서 대여 등록", new BookAddListener(bookDao)));
    bookMenu.add(new Menu("대여 도서 목록", new BookListListener(bookDao)));
    bookMenu.add(new Menu("대여 도서 조회", new BookViewListener(bookDao)));
    bookMenu.add(new Menu("대여 도서 반납", new BookDeleteListener(bookDao)));
    mainMenu.add(bookMenu);

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