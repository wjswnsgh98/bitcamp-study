package m_project;

import m_project.handler.BoardHandler;
import m_project.handler.Handler;
import m_project.handler.MemberHandler;
import util.ArrayList;
import util.LinkedList;
import util.Prompt;

public class App {
  public static void main(String[] args) {

    Prompt prompt = new Prompt();

    Handler memberHandler = new MemberHandler(prompt, "대여자", new ArrayList());
    Handler boardHandler = new BoardHandler(prompt, "게시글", new LinkedList());
    Handler readingHandler = new BoardHandler(prompt, "독서록", new LinkedList());

    printTitle();

    printMenu();

    while(true){
      String menuNo = prompt.inputString("메인> ");
      if(menuNo.equals("0")){
        break;
      } else if(menuNo.equals("menu")){
        printMenu();
      } else if(menuNo.equals("1")){
        memberHandler.execute();
      } else if(menuNo.equals("2")){
        boardHandler.execute();
      } else if(menuNo.equals("3")){
        readingHandler.execute();
      } else{
        System.out.println("메뉴 번호가 옳지 않습니다!");
      }
    }

    prompt.close();
  }

  static void printMenu(){
    System.out.println("1. 대여자");
    System.out.println("2. 게시글");
    System.out.println("3. 독서록");
    System.out.println("0. 종료");
  }

  static void printTitle(){
    System.out.println("도서 대여자 관리 시스템");
    System.out.println("----------------------------------");
  }
}