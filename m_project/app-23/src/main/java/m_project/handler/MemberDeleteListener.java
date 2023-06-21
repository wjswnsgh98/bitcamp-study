package m_project.handler;

import m_project.vo.Member;
import util.BreadcrumbPrompt;
import util.List;


public class MemberDeleteListener extends AbstractMemberListener{

  public MemberDeleteListener(List list){
    super(list);
  }

  public void service(BreadcrumbPrompt prompt){
    if(!this.list.remove(new Member(prompt.inputInt("번호? ")))){
      System.out.println("해당 번호의 책을 빌려간 사람이 없습니다!");
    }
  }
}