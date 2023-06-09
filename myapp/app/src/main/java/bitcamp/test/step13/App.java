package bitcamp.test.step13;

import bitcamp.test.step13.vo.Score;

// 13) 클래스를 유지보수 하기 쉽게 패키지로 분류: import, public
public class App {
  public static void main(String[] args) {
    final int MAX_SIZE = 10;
    Score scores[] = new Score[MAX_SIZE];
    int length=0;
    
    scores[length++] = new Score("홍길동", 100, 100, 100);
    scores[length++] = new Score("임꺽정", 90, 90, 90);
    scores[length++] = new Score("유관순", 80, 80, 80);

    for(int i=0; i<length; i++){
      printScore(scores[i]);
    }
  }

  public static void printScore(Score s){
    System.out.printf("%s: 합계=%d, 평균=%.1f\n", s.name, s.sum, s.aver);
  }
}