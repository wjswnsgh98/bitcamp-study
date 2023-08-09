package project.handler;

import util.ActionListener;
import util.BreadcrumbPrompt;

public class HeaderListener implements ActionListener{
  @Override
  public void service(BreadcrumbPrompt prompt) {
    prompt.println("=============================[비트캠프!]==");
  }
}