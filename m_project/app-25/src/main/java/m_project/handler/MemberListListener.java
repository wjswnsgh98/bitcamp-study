package m_project.handler;

import m_project.vo.Member;
import util.BreadcrumbPrompt;
import util.Iterator;
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

    // 목록에서 데이터를 대신 꺼내주는 객체를 얻는다.
    Iterator<Member> iterator = list.iterator();
    while(iterator.hasNext()){
      Member m = iterator.next();
      System.out.printf("%d, %s, %s, %s, %s, %s\n", m.getBook_no(), m.getB_title(), m.getAuthor(),
          m.getName(), m.getP_num(), toGenderString(m.getGender()));
    }
  }
}