package bitcamp.test.step14;

import bitcamp.test.step14.vo.Score;

// 14) 외부접근 차단과 값 꺼내기: private, getter
public class App {
  public static void main(String[] args) {
    final int MAX_SIZE = 10;
    Score scores[] = new Score[MAX_SIZE];
    int length=0;
    
    scores[length++] = new Score("홍길동", 100, 100, 100);
    scores[length++] = new Score("임꺽정", 90, 90, 90);
    scores[length++] = new Score("유관순", 80, 80, 80);

    // 변수에 직접 접근 => 국영수 합계를 임의로 조작 가능!
    // scores[0].sum = 20000;

    for(int i=0; i<length; i++){
      printScore(scores[i]);
    }
  }

  public static void printScore(Score s){
    System.out.printf("%s: 합계=%d, 평균=%.1f\n", s.name, s.getSum(), s.getAver());
  }
}