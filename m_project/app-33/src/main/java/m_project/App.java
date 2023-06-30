package m_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
    loadMember("member.csv", memberList);
    loadBoard("board.csv", boardList);
    loadBoard("reading.csv", readingList);
  }

  public void saveData(){
    saveMember("member.csv", memberList);
    saveBoard("board.csv", boardList);
    saveBoard("reading.csv", readingList);
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

  private void loadMember(String filename, List<Member> list){
    try{
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!

      String line = null;

      while((line = in.readLine()) != null){
        String[] values = line.split(",");
        Member member = new Member();
        member.setBook_no(Integer.parseInt(values[0]));
        member.setB_title(values[1]);
        member.setAuthor(values[2]);
        member.setName(values[3]);
        member.setP_num(values[4]);
        member.setGender(values[5].charAt(0));
        list.add(member);
      }

      if(list.size() > 0) {
        // 데이터를 로딩한 이후에 추가할 회원의 번호를 설정한다.
        Member.userId = memberList.get(memberList.size() - 1).getBook_no() + 1;
      }

      in.close();
    } catch(Exception e){
      System.out.println("회원 정보를 읽는 중 오류 발생!");
    }
  }

  private void loadBoard(String filename, List<Board> list){
    try{
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!

      String line = null;

      while((line = in.readLine()) != null){
        String[] values = line.split(",");

        Board board = new Board();
        board.setNo(Integer.parseInt(values[0]));
        board.setTitle(values[1]);
        board.setContent(values[2]);
        board.setWriter(values[3]);
        board.setPassword(values[4]);
        board.setViewCount(Integer.parseInt(values[5]));
        board.setCreatedDate(Long.parseLong(values[6]));
        list.add(board);
      }

      if(list.size() > 0) {
        Board.boardNo = Math.max(
            Board.boardNo,
            list.get(list.size() - 1).getNo() + 1);
      }
      in.close();
    } catch(Exception e){
      System.out.println(filename + "파일을 읽는 중 오류 발생!");
    }
  }

  private void saveMember(String filename, List<Member> list){
    try{
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out1 = new BufferedWriter(out0); // <== Decorator 역할을 수행!
      PrintWriter out = new PrintWriter(out1); // <== Decorator 역할을 수행!

      for(Member member : list){
        out.printf("%d,%s,%s,%s,%s,%c\n",
            member.getBook_no(),
            member.getB_title(),
            member.getAuthor(),
            member.getName(),
            member.getP_num(),
            member.getGender());
      }
      out.close();
    } catch(Exception e){
      System.out.println("회원 정보를 저장하는 중 오류 발생!");
    }
  }

  private void saveBoard(String filename, List<Board> list){
    try{
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out1 = new BufferedWriter(out0); // <== Decorator 역할을 수행!
      PrintWriter out = new PrintWriter(out1); // <== Decorator 역할을 수행!

      for(Board board : list){
        out.printf("%d,%s,%s,%s,%s,%d,%d\n",
            board.getNo(),
            board.getTitle(),
            board.getContent(),
            board.getWriter(),
            board.getPassword(),
            board.getViewCount(),
            board.getCreatedDate());
      }
      out.close();
    } catch(Exception e){
      System.out.println(filename + "파일을 저장하는 중 오류 발생!");
    }
  }
}