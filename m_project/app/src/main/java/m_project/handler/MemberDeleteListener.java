package m_project.handler;

import m_project.dao.MemberDao;
import util.ActionListener;
import util.BreadcrumbPrompt;

public class MemberDeleteListener implements ActionListener{
  MemberDao memberDao;

  public MemberDeleteListener(MemberDao memberDao){
    this.memberDao = memberDao;
  }

  public void service(BreadcrumbPrompt prompt){
    if(memberDao.delete(prompt.inputInt("번호? ")) == 0){
      System.out.println("해당 번호의 책을 빌려간 사람이 없습니다!");
    }
  }
}