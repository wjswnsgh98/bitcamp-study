package m_project.handler;

import java.util.List;
import m_project.dao.MemberDao;
import m_project.vo.Member;
import util.ActionListener;
import util.BreadcrumbPrompt;

public class MemberListListener implements ActionListener{
  MemberDao memberDao;

  public MemberListListener(MemberDao memberDao){
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt){
    System.out.println("-----------------------------------------------");
    System.out.println("도서번호, 도서제목, 글쓴이, 이름, 핸드폰번호, 성별");
    System.out.println("-----------------------------------------------");

    List<Member> list = memberDao.list();
    for(Member m : list){
      System.out.printf("%d, %s, %s, %s, %s, %s\n", m.getBook_no(), m.getB_title(), m.getAuthor(),
          m.getName(), m.getP_num(), m.getGender() == 'M' ? "남성" : "여성");
    }
  }
}