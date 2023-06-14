package util;

import java.util.Scanner;

public class Prompt {
    static Scanner sc = new Scanner(System.in);
    
    public static String inputString(String title, Object... args){
        System.out.printf(title, args);
        return sc.nextLine();
    }

    public static int inputInt(String title, Object... args){
        return Integer.parseInt(inputString(title, args));
    }
    
    public static void close(){
        sc.close();
    }
}
