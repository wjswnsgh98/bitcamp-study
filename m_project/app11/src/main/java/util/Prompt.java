package util;

import java.util.Scanner;

public class Prompt {
    static Scanner sc = new Scanner(System.in);
    
    public static String inputString(String title){
        System.out.print(title);
        return sc.nextLine();
    }

    public static int inputInt(String title){
        return Integer.parseInt(inputString(title));
    }
    
    public static void close(){
        sc.close();
    }
}
