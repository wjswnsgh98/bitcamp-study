package project.handler;

import util.ActionListener;
import util.BreadcrumbPrompt;

public class HeaderListener implements ActionListener{
  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("=============================[비트캠프!]==");
  }
}