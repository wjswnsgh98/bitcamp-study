<<<<<<< HEAD
package bitcamp.myapp;

public class Test {
  public static void main(String[] args) {
    System.out.println(args); 
    int a = 100, b = 200;
    swap(a, b);
    System.out.printf("main(): %d, %d\n", a, b);
  }
  static void swap(int a, int b){
    int temp = a;
    a = b;
    b = temp;
    System.out.printf("swap(): %d, %d\n", a, b);
  }
}
=======
package bitcamp.myapp;
// 소스코드에서 Calculator 클래스는 bitcamp.util 패키지에 소속된 클래스를 가리킨다.
import bitcamp.util.Calculator;

public class Test {
  public static void main(String[] args) {
    // 2 * 3 + 7 - 2 / 2 = ?
    // => 연산자 우선 순위를 고려하지 않고 앞에서부터 뒤로 순차적으로 계산한다.

    Calculator.init(2);
    Calculator.multiple(3);
    Calculator.plus(7); // 13
    Calculator.minus(2); // 11
    Calculator.divide(2); // 5
    System.out.println(Calculator.result);
  }
}
>>>>>>> 61dd9d49b4e49daac9c8fd63dfd0227bff9e8d13
