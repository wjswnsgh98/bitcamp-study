package bitcamp.test.step08;
// 8) 인스턴스 메서드 도입 
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
  }
  public static void main(String[] args) {
    final int MAX_SIZE = 10;
    Score scores[] = new Score[MAX_SIZE];
    int length=0;

    Score s = new Score();
    s.name = "홍길동";
    s.kor = 100;
    s.eng = 100;
    s.math = 100;
    s.compute();
    scores[length++] = s;

    s = new Score();
    s.name = "임꺽정";
    s.kor = 90;
    s.eng = 90;
    s.math = 90;
    s.compute();
    scores[length++] = s;

    s = new Score();
    s.name = "유관순";
    s.kor = 80;
    s.eng = 80;
    s.math = 80;
    s.compute();
    scores[length++] = s;

    for(int i=0; i<length; i++){
      printScore(scores[i]);
    }
  }

  public static void printScore(Score s){
    System.out.printf("%s: 합계=%d, 평균=%.1f\n", s.name, s.sum, s.aver);
  }
}