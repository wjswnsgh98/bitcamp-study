package m_project.handler;

import m_project.dao.MemberDao;
import m_project.vo.Member;
import util.ActionListener;
import util.BreadcrumbPrompt;

public class MemberDetailListener implements ActionListener{
  MemberDao memberDao;

  public MemberDetailListener(MemberDao memberDao){
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt){
    int memberNo = prompt.inputInt("도서번호? ");

    Member m = memberDao.findBy(memberNo);
    if(m == null){
      System.out.println("해당 번호의 책을 빌려간 사람이 없습니다");
      return;
    }

    System.out.printf("도서제목: %s\n", m.getB_title());
    System.out.printf("글쓴이: %s\n", m.getAuthor());
    System.out.printf("이름: %s\n", m.getName());
    System.out.printf("핸드폰번호: %s\n", m.getP_num());
    System.out.printf("성별: %s\n", m.getGender() == 'M' ? "남성" : "여성");
  }
}