package project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import net.RequestEntity;
import net.ResponseEntity;
import project.dao.BoardDao;
import project.dao.BoardListDao;
import project.dao.BookDao;
import project.dao.BookListDao;
import project.dao.MemberDao;
import project.dao.MemberListDao;
import project.vo.Board;
import project.vo.Book;
import project.vo.Member;

public class ServerApp {
  int port;
  ServerSocket serverSocket;

  MemberDao memberDao = new MemberListDao("member.json");
  BookDao bookDao = new BookListDao("book.json");
  BoardDao boardDao = new BoardListDao("board.json");

  public ServerApp(int port) throws Exception {
    this.port = port;
  }

  public void close() throws Exception {
    serverSocket.close();
  }

  public static void main(String[] args) throws Exception {
    if (args.length < 1) {
      System.out.println("실행 예) java ... bitcamp.myapp.ServerApp 포트번호");
      return;
    }

    ServerApp app = new ServerApp(Integer.parseInt(args[0]));
    app.execute();
    app.close();
  }

  public void execute() throws Exception {
    System.out.println("[MyList 서버 애플리케이션]");

    this.serverSocket = new ServerSocket(port);
    System.out.println("서버 실행 중...");

    Socket socket = serverSocket.accept();
    DataInputStream in = new DataInputStream(socket.getInputStream());
    DataOutputStream out = new DataOutputStream(socket.getOutputStream());

    while (true) {
      RequestEntity request = RequestEntity.fromJson(in.readUTF());

      String command = request.getCommand();
      System.out.println(command);

      if (command.equals("quit")) {
        break;
      }

      ResponseEntity response = new ResponseEntity();

      switch (command) {
        case "book/list":
          response.status(ResponseEntity.SUCCESS).result(bookDao.list());
          break;
        case "book/insert":
          bookDao.insert(request.getObject(Book.class));
          response.status(ResponseEntity.SUCCESS);
          break;
        case "book/findBy":
          Book book = bookDao.findBy(request.getObject(String.class));
          if (book.equals(null)) {
            response.status(ResponseEntity.FAILURE).result("해당 이름의 대여자가 없습니다!");
          } else {
            response.status(ResponseEntity.SUCCESS).result(book);
          }
          break;
        case "book/delete":
          String str = bookDao.delete(request.getObject(String.class));
          response.status(ResponseEntity.SUCCESS).result(str);
          break;
        case "member/list":
          response.status(ResponseEntity.SUCCESS).result(memberDao.list());
          break;
        case "member/insert":
          memberDao.insert(request.getObject(Member.class));
          response.status(ResponseEntity.SUCCESS);
          break;
        case "member/findBy":
          Member member = memberDao.findBy(request.getObject(Integer.class));
          if (member == null) {
            response.status(ResponseEntity.FAILURE).result("해당 번호의 회원이 없습니다!");
          } else {
            response.status(ResponseEntity.SUCCESS).result(member);
          }
          break;
        case "member/update":
          int value = memberDao.update(request.getObject(Member.class));
          response.status(ResponseEntity.SUCCESS).result(value);
          break;
        case "member/delete":
          value = memberDao.delete(request.getObject(Integer.class));
          response.status(ResponseEntity.SUCCESS).result(value);
          break;
        case "board/list":
          response.status(ResponseEntity.SUCCESS).result(boardDao.list());
          break;
        case "board/insert":
          boardDao.insert(request.getObject(Board.class));
          response.status(ResponseEntity.SUCCESS);
          break;
        case "board/findBy":
          Board board = boardDao.findBy(request.getObject(Integer.class));
          if (board == null) {
            response.status(ResponseEntity.FAILURE).result("해당 번호의 게시글이 없습니다!");
          } else {
            response.status(ResponseEntity.SUCCESS).result(board);
          }
          break;
        case "board/update":
          value = boardDao.update(request.getObject(Board.class));
          response.status(ResponseEntity.SUCCESS).result(value);
          break;
        case "board/delete":
          value = boardDao.delete(request.getObject(Integer.class));
          response.status(ResponseEntity.SUCCESS).result(value);
          break;
        default:
          response.status(ResponseEntity.ERROR).result("해당 명령을 지원하지 않습니다!");
      }

      out.writeUTF(response.toJson());
    }

    in.close();
    out.close();
    socket.close();
  }
}