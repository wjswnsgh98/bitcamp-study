package com.eomcs.io.ex01;

import java.io.File;

public class Exam0622x {
  public static void main(String[] args) throws Exception {
    File dir = new File(".");
    File[] files = dir.listFiles(file -> file.isFile() && file.getName().endsWith(".java"));
    for (File file : files) {
      System.out.printf("%s %12d %s\n",
          file.isDirectory() ? "d" : "-",
              file.length(),
              file.getName());
    }
  }
}