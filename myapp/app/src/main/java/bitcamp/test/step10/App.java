package bitcamp.test.step10;
// 10) GRASP 패턴 : Information Expert
//     - createScore를 Score 클래스로 이동 
public class App {
  static class Score{
    String name;
    int kor;
    int eng;
    int math;
    int sum;
    float aver;

    void compute(){
      this.sum = this.kor + this.eng + this.math;
      this.aver = this.sum / 3f;
    }

    // 팩토리 메서드(factory method)
    static Score create(String name, int kor, int eng, int math){
      Score s = new Score();
      s.name = name;
      s.kor = kor;
      s.eng = eng;
      s.math = math;
      s.compute();
      return s;
    }
  }
  public static void main(String[] args) {
    final int MAX_SIZE = 10;
    Score scores[] = new Score[MAX_SIZE];
    int length=0;
    
    scores[length++] = Score.create("홍길동", 100, 100, 100);
    scores[length++] = Score.create("임꺽정", 90, 90, 90);
    scores[length++] = Score.create("유관순", 80, 80, 80);

    for(int i=0; i<length; i++){
      printScore(scores[i]);
    }
  }

  public static void printScore(Score s){
    System.out.printf("%s: 합계=%d, 평균=%.1f\n", s.name, s.sum, s.aver);
  }
}