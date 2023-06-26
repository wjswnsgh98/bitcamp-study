package m_project.handler;

import m_project.vo.Member;
import util.BreadcrumbPrompt;
import util.List;


public class MemberListListener extends AbstractMemberListener{

  public MemberListListener(List<Member> list){
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt){
    System.out.println("-----------------------------------------------");
    System.out.println("도서번호, 도서제목, 글쓴이, 이름, 핸드폰번호, 성별");
    System.out.println("-----------------------------------------------");

    for(int i = 0; i < this.list.size(); i++){
      Member m = (Member) this.list.get(i);
      System.out.printf("%d, %s, %s, %s, %s, %s\n", m.getBook_no(), m.getB_title(), m.getAuthor(),
          m.getName(), m.getP_num(), toGenderString(m.getGender()));
    }
  }
}