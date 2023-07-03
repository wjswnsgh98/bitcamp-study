package m_project;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
import m_project.vo.AutoIncrement;
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
    loadJson("member.json", memberList, Member.class);
    loadJson("board.json", boardList, Board.class);
    loadJson("reading.json", readingList, Board.class);
  }

  public void saveData(){
    saveJson("member.json", memberList);
    saveJson("board.json", boardList);
    saveJson("reading.json", readingList);
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

  private <T> void loadJson(String filename, List<T> list, Class<T> clazz){
    try{
      FileReader in0 = new FileReader(filename);
      BufferedReader in = new BufferedReader(in0); // <== Decorator 역할을 수행!

      StringBuilder strBuilder = new StringBuilder();
      String line = null;

      while((line = in.readLine()) != null){
        strBuilder.append(line);
      }

      in.close();

      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
      Collection<T> objects = gson.fromJson(strBuilder.toString(),
          TypeToken.getParameterized(Collection.class, clazz).getType());

      list.addAll(objects);

      Class<?>[] interfaces = clazz.getInterfaces();
      for(Class<?> info : interfaces) {
        if(info == AutoIncrement.class) {
          AutoIncrement autoIncrement = (AutoIncrement) list.get(list.size() - 1);
          autoIncrement.updateKey();
          break;
        }
      }

    } catch(Exception e){
      System.out.println(filename + "파일을 읽는 중 오류 발생!");
    }
  }

  private void saveJson(String filename, List<?> list){
    try{
      FileWriter out0 = new FileWriter(filename);
      BufferedWriter out = new BufferedWriter(out0); // <== Decorator 역할을 수행!

      Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").setPrettyPrinting().create();
      out.write(gson.toJson(list));

      out.close();
    } catch(Exception e){
      System.out.println(filename + "파일을 저장하는 중 오류 발생!");
    }
  }
}