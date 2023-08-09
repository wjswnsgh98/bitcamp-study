package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import net.NetProtocol;
import project.dao.BoardDao;
import project.dao.BookDao;
import project.dao.MemberDao;
import project.dao.MySQLBoardDao;
import project.dao.MySQLBookDao;
import project.dao.MySQLMemberDao;
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
import project.handler.LoginListener;
import project.handler.MemberAddListener;
import project.handler.MemberDeleteListener;
import project.handler.MemberDetailListener;
import project.handler.MemberListListener;
import project.handler.MemberUpdateListener;
import util.BreadcrumbPrompt;
import util.Menu;
import util.MenuGroup;
import util.SqlSessionFactoryProxy;

public class ServerApp {
  //자바 스레드풀 준비
  ExecutorService threadPool = Executors.newFixedThreadPool(2);

  SqlSessionFactory sqlSessionFactory;
  MemberDao memberDao;
  BookDao bookDao;
  BoardDao boardDao;

  MenuGroup mainMenu = new MenuGroup("메인");

  int port;

  public ServerApp(int port) throws Exception {
    this.port = port;

    // 1) mybatis 설정 파일을 읽어들일 도구를 준비한다.
    InputStream mybatisConfigIn = Resources.getResourceAsStream("project/config/mybatis-config.xml");

    // 2) SqlSessionFactory를 만들어줄 빌더 객체 준비
    SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

    // 3) 빌더 객체를 통해 SqlSessionFactory를 생성
    sqlSessionFactory = new SqlSessionFactoryProxy(builder.build(mybatisConfigIn));

    this.memberDao = new MySQLMemberDao(sqlSessionFactory);
    this.bookDao = new MySQLBookDao(sqlSessionFactory);
    this.boardDao = new MySQLBoardDao(sqlSessionFactory);

    prepareMenu();
  }

  public void close() throws Exception {}

  public static void main(String[] args) throws Exception {
    ServerApp app = new ServerApp(8888);
    app.execute();
    app.close();
  }

  public void execute() {
    try (ServerSocket serverSocket = new ServerSocket(this.port)) {
      System.out.println("서버 실행 중...");

      while (true) {
        Socket socket = serverSocket.accept();
        threadPool.execute(() -> processRequest(socket));
      }
    } catch (Exception e) {
      System.out.println("서버 실행 오류!");
      e.printStackTrace();
    }
  }

  private void processRequest(Socket socket) {
    try (Socket s = socket;
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream())) {

      BreadcrumbPrompt prompt = new BreadcrumbPrompt(in, out);

      InetSocketAddress clientAddress = (InetSocketAddress) socket.getRemoteSocketAddress();
      System.out.printf("%s 클라이언트 접속함!\n", clientAddress.getHostString());

      out.writeUTF("[도서 대여 관리 시스템]\n"
          + "-----------------------------------------");

      new LoginListener(memberDao).service(prompt);

      mainMenu.execute(prompt);
      out.writeUTF(NetProtocol.NET_END);

    } catch (Exception e) {
      System.out.println("클라이언트 통신 오류!");
      e.printStackTrace();

    } finally {
      ((SqlSessionFactoryProxy) sqlSessionFactory).clean();
    }
  }

  private void prepareMenu() {
    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberDao, sqlSessionFactory)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberDao)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberDao)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberDao, sqlSessionFactory)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberDao, sqlSessionFactory)));
    mainMenu.add(memberMenu);

    MenuGroup bookMenu = new MenuGroup("도서 대여");
    bookMenu.add(new Menu("대여 가능한 도서 목록", new BookRentListener(bookDao)));
    bookMenu.add(new Menu("도서 대여 등록", new BookAddListener(bookDao, sqlSessionFactory)));
    bookMenu.add(new Menu("대여 도서 목록", new BookListListener(bookDao)));
    bookMenu.add(new Menu("대여 도서 조회", new BookViewListener(bookDao, sqlSessionFactory)));
    bookMenu.add(new Menu("대여 도서 반납", new BookDeleteListener(bookDao, sqlSessionFactory)));
    mainMenu.add(bookMenu);

    MenuGroup boardMenu = new MenuGroup("도서 추천 게시글");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardDao, sqlSessionFactory)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardDao)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardDao, sqlSessionFactory)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardDao, sqlSessionFactory)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardDao, sqlSessionFactory)));
    mainMenu.add(boardMenu);

    //    Menu helloMenu = new Menu("안녕!");
    //    helloMenu.addActionListener(new HeaderListener());
    //    helloMenu.addActionListener(new HelloListener());
    //    helloMenu.addActionListener(new FooterListener());
    //    mainMenu.add(helloMenu);
  }
}