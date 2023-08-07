package project.handler;

import project.dao.MemberDao;
import project.vo.Member;
import util.BreadcrumbPrompt;

public class MemberAddListener implements MemberActionListener{
  MemberDao memberDao;

  public MemberAddListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    Member m = new Member();
    m.setName(prompt.inputString("이름? "));
    m.setEmail(prompt.inputString("이메일? "));
    m.setPassword(prompt.inputString("암호? "));
    m.setGender(MemberActionListener.inputGender((char)0, prompt));

    memberDao.insert(m);
  }
}