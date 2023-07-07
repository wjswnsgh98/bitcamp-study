package m_project.handler;

import m_project.dao.MemberDao;
import m_project.vo.Member;
import util.BreadcrumbPrompt;

public class MemberAddListener implements MemberActionListener{
  MemberDao memberDao;

  public MemberAddListener(MemberDao memberDao){
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt){
    Member m = new Member();
    m.setBook_no(Member.userId++);
    m.setB_title(prompt.inputString("도서제목? "));
    m.setAuthor(prompt.inputString("글쓴이? "));
    m.setName(prompt.inputString("이름? "));
    m.setP_num(prompt.inputString("핸드폰번호? "));
    m.setGender(MemberActionListener.inputGender((char)0, prompt));

    memberDao.insert(m);
  }
}