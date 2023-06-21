package m_project.handler;

import m_project.vo.Member;
import util.BreadcrumbPrompt;
import util.List;


public class MemberDetailListener extends AbstractMemberListener{

  public MemberDetailListener(List list){
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt){
    int memberNo = prompt.inputInt("도서번호? ");

    Member m = this.findBy(memberNo);
    if(m == null){
      System.out.println("해당 번호의 책을 빌려간 사람이 없습니다");
      return;
    }

    System.out.printf("도서제목: %s\n", m.getB_title());
    System.out.printf("글쓴이: %s\n", m.getAuthor());
    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("핸드폰번호: %s\n", m.getP_num());
    System.out.printf("성별: %s\n", toGenderString(m.getGender()));
  }
}