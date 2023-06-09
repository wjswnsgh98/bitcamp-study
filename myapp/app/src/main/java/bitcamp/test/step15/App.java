package bitcamp.test.step15;

import bitcamp.test.step15.vo.Score;

// 15) 프로그래밍의 일관성을 위해 보통 다른 필드에 대해서도 getter를 만들고 사용한다.
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
    System.out.printf("%s: 합계=%d, 평균=%.1f\n", s.getName(), s.getSum(), s.getAver());
  }
}