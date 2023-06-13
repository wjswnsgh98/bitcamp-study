package bitcamp.myapp.handler;

import java.util.Date;
import bitcamp.myapp.vo.Board;
import bitcamp.util.Prompt;

public class BoardHandler {
  static final int MAX_SIZE = 100;
  static Board[] boards = new Board[MAX_SIZE];
  static int length = 0;

  public static void inputBoard() {
    if(!available()){
      System.out.println("더이상 입력할 수 없습니다!");
      return;
    }

    Board board = new Board();
    board.setTitle(Prompt.inputString("제목? "));
    board.setContent(Prompt.inputString("내용? "));
    board.setWriter(Prompt.inputString("작성자? "));
    board.setPassword(Prompt.inputString("암호? "));

    boards[length++] = board;
  }

  public static void printBoards() {
    System.out.println("---------------------------------------");
    System.out.println("번호, 제목, 작성자, 조회수, 등록일");
    System.out.println("---------------------------------------");

    for (int i = 0; i < length; i++) {
      Board board = boards[i];

      // 게시글의 등록일 값을 가져와서 Date 인스턴스에 저장한다.
      Date date = new Date(board.getCreatedDate());

      System.out.printf("%d, %s, %s, %d, %tY-%5$tm-%5$td\n", board.getNo(), board.getTitle(), board.getWriter(),
          board.getViewCount(), date);
    }
  }

  public static void viewBoard(){
    String boardNo = Prompt.inputString("번호? ");
    // 입력받은 번호를 가지고 배열에서 해당 회원을 찾아야한다.
    for(int i=0; i<length; i++){
      Board board = boards[i];
      if(board.getNo() == Integer.parseInt(boardNo)){
        // i번째 항목에 저장된 회원 정보 출력
        System.out.printf("제목: %s\n", board.getTitle());
        System.out.printf("작성자: %s\n", board.getWriter());
        System.out.printf("조회수: %s\n", board.getViewCount());
        System.out.printf("등록일: %tY-%5$tm-%5$td\n", board.getCreatedDate());
        return;
      }
    }
    System.out.println("해당 번호의 회원이 없습니다!");
  }

  public static void updateBoard(){
    String boardNo = Prompt.inputString("번호? ");
    for(int i=0; i<length; i++){
      Board board = boards[i];
      if(board.getNo() == Integer.parseInt(boardNo)){
        String inputPassword = Prompt.inputString("암호? ");
        if(board.getPassword().equals(inputPassword)) {
          // i번째 항목에 저장된 회원 정보 출력
          System.out.printf("제목(%s)? ", board.getTitle());
          board.setTitle(Prompt.inputString("> "));
          System.out.printf("암호(%s)? ", board.getPassword());
          board.setPassword(Prompt.inputString("> "));
          return;
        }
        else {
          System.out.println("비밀번호가 틀렸습니다!");
          updateBoard();
        }
      }
      else {
        System.out.println("해당 번호의 회원이 없습니다!");
        updateBoard();
      }
    }
  }

  public static void deleteBoard() {
    // 삭제하려는 회원의 정보가 들어있는 인덱스를 알아낸다.
    int memberNo = Prompt.inputInt("번호? ");

    int deleteIndex = indexOf(memberNo);
    if(deleteIndex == -1){
      System.out.println("해당 번호의 회원이 없습니다!");
      return;
    }
    else if(deleteIndex == -2) {
      System.out.println("비밀번호가 틀렸습니다!");
      return;
    }

    for(int i=deleteIndex; i<length - 1; i++){
      boards[i] = boards[i+1];
    }

    boards[--length] = null;
  }

  private static int indexOf(int memberNo){
    for(int i=0; i<length; i++){
      Board board = boards[i];
      if(board.getNo() == memberNo){
        String inputPassword = Prompt.inputString("암호? ");
        if(board.getPassword().equals(inputPassword)) {
          return i;
        }
        else {
          return -2;
        }
      }
    }
    return -1;
  }

  public static boolean available(){
    return length < MAX_SIZE;
  }
}
