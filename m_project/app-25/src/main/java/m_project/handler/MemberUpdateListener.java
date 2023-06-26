package m_project.handler;

import m_project.vo.Member;
import util.BreadcrumbPrompt;
import util.List;


public class MemberUpdateListener extends AbstractMemberListener{

  public MemberUpdateListener(List<Member> list){
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt){
    int memberNo = prompt.inputInt("번호? ");

    Member m = this.findBy(memberNo);
    if(m == null){
      System.out.println("해당 번호의 책을 빌려간 사람이 없습니다");
      return;
    }

    m.setB_title(prompt.inputString("도서제목(%s)? ", m.getB_title()));
    m.setAuthor(prompt.inputString("글쓴이(%s)? ", m.getAuthor()));
    m.setName(prompt.inputString("이름(%s)? ", m.getName()));
    m.setP_num(prompt.inputString("핸드폰번호(%s)? ", m.getP_num()));
    m.setGender(inputGender(m.getGender(), prompt));
  }
}