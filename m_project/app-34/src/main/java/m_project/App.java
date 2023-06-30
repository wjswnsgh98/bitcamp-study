package m_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
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
import m_project.vo.CsvObject;
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
    loadCsv("member.csv", memberList, Member.class);
    loadCsv("board.csv", boardList, Board.class);
    loadCsv("reading.csv", readingList, Board.class);
  }

  public void saveData(){
    saveCsv("member.csv", memberList);
    saveCsv("board.csv", boardList);
    saveCsv("reading.csv", readingList);
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

  @SuppressWarnings("unchecked")
  private <T extends CsvObject> void loadCsv(String filename, List<T> list, Class<T> clazz){
    try{
      Method factoryMethod = clazz.getDeclaredMethod("fromCsv", String.class);

      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!

      String line = null;

      while((line = in.readLine()) != null){
        list.add((T)factoryMethod.invoke(null, line)); // Reflection API를 사용하여 스태틱 메서드 호출
        // list.add(Member.fromCsv(line)); // 직접 스태틱 메서드 호출
      }

      in.close();
    } catch(Exception e){
      System.out.println(filename + "파일을 읽는 중 오류 발생!");
    }
  }

  private void saveCsv(String filename, List<? extends CsvObject> list){
    try{
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out1 = new BufferedWriter(out0); // <== Decorator 역할을 수행!
      PrintWriter out = new PrintWriter(out1); // <== Decorator 역할을 수행!

      for(CsvObject obj : list){
        out.println(obj.toCsvString());
        // Board나 Member 클래스에 필드가 추가/변경/삭제 되더라도
        // 여기 코드를 변경할 필요가 없다.
        // 이것이 Information Expert 설계를 적용하는 이유다!
        // 설계를 어떻게 하느냐에 따라 유지보수가 쉬워질 수도 있고,
        // 어려워질 수도 있다.
      }

      out.close();
    } catch(Exception e){
      System.out.println(filename + "파일을 저장하는 중 오류 발생!");
    }
  }
}