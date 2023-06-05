package bitcamp.myapp;

import java.util.Scanner;

public class Prompt {
  static Scanner scanner = new Scanner(System.in);

  static String inputString(String title){
    System.out.print(title);
    return scanner.nextLine();
  }
}
