package m_project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import dao.DaoBuilder;
import m_project.dao.BoardDao;
import m_project.dao.MemberDao;
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
import net.RequestEntity;
import util.BreadcrumbPrompt;
import util.Menu;
import util.MenuGroup;

public class ClientApp {
  Socket socket;
  DataInputStream in;
  DataOutputStream out;

  MemberDao memberDao;
  BoardDao boardDao;
  BoardDao readingDao;

  BreadcrumbPrompt prompt = new BreadcrumbPrompt();

  MenuGroup mainMenu = new MenuGroup("메인");

  public ClientApp(String ip, int port) throws Exception{
    this.socket = new Socket(ip, port);
    this.out = new DataOutputStream(socket.getOutputStream());
    this.in = new DataInputStream(socket.getInputStream());

    DaoBuilder daoBuilder = new DaoBuilder(in, out);
    this.memberDao = daoBuilder.build("member", MemberDao.class);
    this.boardDao = daoBuilder.build("board", BoardDao.class);
    this.readingDao = daoBuilder.build("reading", BoardDao.class);

    prepareMenu();
  }

  public void close() throws Exception{
    prompt.close();
    out.close();
    in.close();
    socket.close();
  }

  public static void main(String[] args) throws Exception{
    if(args.length < 2) {
      System.out.println("실행 예) java ... m_project.ClientApp 서버주소 포트번호");
      return;
    }

    ClientApp app = new ClientApp(args[0], Integer.parseInt(args[1]));
    app.execute();
    app.close();
  }

  static void printTitle(){
    System.out.println("도서 대여자 관리 시스템");
    System.out.println("----------------------------------");
  }

  public void execute(){
    printTitle();
    mainMenu.execute(prompt);

    try {
      out.writeUTF(new RequestEntity().command("quit").toJson());

    } catch(Exception e) {
      System.out.println("종료 오류!");
      e.printStackTrace();
    }
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
    readingMenu.add(new Menu("등록", new BoardAddListener(readingDao)));
    readingMenu.add(new Menu("목록", new BoardListListener(readingDao)));
    readingMenu.add(new Menu("조회", new BoardDetailListener(readingDao)));
    readingMenu.add(new Menu("변경", new BoardUpdateListener(readingDao)));
    readingMenu.add(new Menu("삭제", new BoardDeleteListener(readingDao)));
    mainMenu.add(readingMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);
  }
}