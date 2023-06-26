package m_project.handler;

import m_project.vo.Member;
import util.BreadcrumbPrompt;
import util.List;


public class MemberAddListener extends AbstractMemberListener{

  public MemberAddListener(List<Member> list){
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt){
    Member m = new Member();
    m.setB_title(prompt.inputString("도서제목? "));
    m.setAuthor(prompt.inputString("글쓴이? "));
    m.setName(prompt.inputString("이름? "));
    m.setP_num(prompt.inputString("핸드폰번호? "));
    m.setGender(inputGender((char)0, prompt));

    this.list.add(m);
  }
}