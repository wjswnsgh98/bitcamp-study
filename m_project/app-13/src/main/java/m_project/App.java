package m_project;

import m_project.handler.BoardHandler;
import m_project.handler.MemberHandler;
import util.Prompt;

public class App {
  public static void main(String[] args) {

    printTitle();

    printMenu();

    while(true){
      String menuNo = Prompt.inputString("메인> ");
      if(menuNo.equals("99")){
        break;
      } else if(menuNo.equals("menu")){
        printMenu();
      } else if(menuNo.equals("1")){
        MemberHandler.inputMember();
      } else if(menuNo.equals("2")){
        MemberHandler.printMembers();
      } else if(menuNo.equals("3")){
        MemberHandler.viewMember();
      } else if(menuNo.equals("4")){
        MemberHandler.updateMember();
      } else if(menuNo.equals("5")){
        MemberHandler.deleteMember();
      } else if(menuNo.equals("6")){
        BoardHandler.inputBoard();
      } else if(menuNo.equals("7")){
        BoardHandler.printBoards();
      } else if(menuNo.equals("8")){
        BoardHandler.viewBoard();
      } else if(menuNo.equals("9")){
        BoardHandler.updateBoard();
      } else if(menuNo.equals("10")){
        BoardHandler.deleteBoard();
      } else{
        System.out.println(menuNo);
      }
    }

    Prompt.close();
  }

  static void printMenu(){
    System.out.println("1. 대여자등록");
    System.out.println("2. 대여자목록");
    System.out.println("3. 대여자조회");
    System.out.println("4. 대여자 정보 변경");
    System.out.println("5. 대여자 정보 삭제");
    System.out.println("6. 게시글등록");
    System.out.println("7. 게시글목록");
    System.out.println("8. 게시글조회");
    System.out.println("9. 게시글변경");
    System.out.println("10. 게시글삭제");
    System.out.println("99. 종료");
  }

  static void printTitle(){
    System.out.println("도서 대여자 관리 시스템");
    System.out.println("----------------------------------");
  }
}