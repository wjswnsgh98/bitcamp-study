package m_project;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import io.BufferedDataInputStream;
import io.BufferedDataOutputStream;
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
import m_project.vo.Board;
import m_project.vo.Member;
import util.BreadcrumbPrompt;
import util.Menu;
import util.MenuGroup;

public class App {
  ArrayList<Member> memberList = new ArrayList<>();
  LinkedList<Board> boardList = new LinkedList<>();
  LinkedList<Board> readingList = new LinkedList<>();

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
    loadData();
    mainMenu.execute(prompt);
    saveData();
    prompt.close();
  }

  public void loadData(){
    loadMember();
    loadBoard("board.data", boardList);
    loadBoard("reading.data", readingList);
  }

  public void saveData(){
    saveMember();
    saveBoard("board.data", boardList);
    saveBoard("reading.data", readingList);
  }

  private void prepareMenu(){
    MenuGroup memberMenu = new MenuGroup("회원");
    memberMenu.add(new Menu("등록", new MemberAddListener(memberList)));
    memberMenu.add(new Menu("목록", new MemberListListener(memberList)));
    memberMenu.add(new Menu("조회", new MemberDetailListener(memberList)));
    memberMenu.add(new Menu("변경", new MemberUpdateListener(memberList)));
    memberMenu.add(new Menu("삭제", new MemberDeleteListener(memberList)));
    mainMenu.add(memberMenu);

    MenuGroup boardMenu = new MenuGroup("게시글");
    boardMenu.add(new Menu("등록", new BoardAddListener(boardList)));
    boardMenu.add(new Menu("목록", new BoardListListener(boardList)));
    boardMenu.add(new Menu("조회", new BoardDetailListener(boardList)));
    boardMenu.add(new Menu("변경", new BoardUpdateListener(boardList)));
    boardMenu.add(new Menu("삭제", new BoardDeleteListener(boardList)));
    mainMenu.add(boardMenu);

    MenuGroup readingMenu = new MenuGroup("독서록");
    readingMenu.add(new Menu("등록", new BoardAddListener(readingList)));
    readingMenu.add(new Menu("목록", new BoardListListener(readingList)));
    readingMenu.add(new Menu("조회", new BoardDetailListener(readingList)));
    readingMenu.add(new Menu("변경", new BoardUpdateListener(readingList)));
    readingMenu.add(new Menu("삭제", new BoardDeleteListener(readingList)));
    mainMenu.add(readingMenu);

    Menu helloMenu = new Menu("안녕!");
    helloMenu.addActionListener(new HeaderListener());
    helloMenu.addActionListener(new HelloListener());
    helloMenu.addActionListener(new FooterListener());
    mainMenu.add(helloMenu);
  }

  private void loadMember(){
    try{
      BufferedDataInputStream in = new BufferedDataInputStream("member.data");
      int size = in.readShort();

      for(int i = 0; i < size; i++){
        Member member = new Member();
        member.setBook_no(in.readInt());
        member.setB_title(in.readUTF());
        member.setAuthor(in.readUTF());
        member.setName(in.readUTF());
        member.setP_num(in.readUTF());
        member.setGender(in.readChar());
        memberList.add(member);
      }

      // 데이터를 로딩한 이후에 추가할 회원의 번호를 설정한다.
      Member.userId = memberList.get(memberList.size() - 1).getBook_no() + 1;

      in.close();
    } catch(Exception e){
      System.out.println("회원 정보를 읽는 중 오류 발생!");
    }
  }

  private void loadBoard(String filename, List<Board> list){
    try{
      BufferedDataInputStream in = new BufferedDataInputStream(filename);
      int size = in.readShort();

      for(int i = 0; i < size; i++){
        Board board = new Board();
        board.setNo(in.readInt());
        board.setTitle(in.readUTF());
        board.setContent(in.readUTF());
        board.setWriter(in.readUTF());
        board.setPassword(in.readUTF());
        board.setViewCount(in.readInt());
        board.setCreatedDate(in.readLong());
        list.add(board);
      }
      Board.boardNo = Math.max(
          Board.boardNo,
          list.get(list.size() - 1).getNo() + 1);

      in.close();
    } catch(Exception e){
      System.out.println(filename + "파일을 읽는 중 오류 발생!");
    }
  }

  private void saveMember(){
    try{
      BufferedDataOutputStream out = new BufferedDataOutputStream("member.data");

      out.writeShort(memberList.size());

      for(Member member : memberList){
        out.writeInt(member.getBook_no());
        out.writeUTF(member.getB_title());
        out.writeUTF(member.getAuthor());
        out.writeUTF(member.getName());
        out.writeUTF(member.getP_num());
        out.writeChar(member.getGender());
      }
      out.close();
    } catch(Exception e){
      System.out.println("회원 정보를 저장하는 중 오류 발생!");
    }
  }

  private void saveBoard(String filename, List<Board> list){
    try{
      BufferedDataOutputStream out = new BufferedDataOutputStream(filename);

      out.writeShort(list.size());

      for(Board board : list){
        out.writeInt(board.getNo());
        out.writeUTF(board.getTitle());
        out.writeUTF(board.getContent());
        out.writeUTF(board.getWriter());
        out.writeUTF(board.getPassword());
        out.writeInt(board.getViewCount());
        out.writeLong(board.getCreatedDate());
      }
      out.close();
    } catch(Exception e){
      System.out.println(filename + "파일을 저장하는 중 오류 발생!");
    }
  }
}