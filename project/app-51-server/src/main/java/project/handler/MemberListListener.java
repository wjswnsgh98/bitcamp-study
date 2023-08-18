package project.handler;

import java.util.List;
import project.dao.MemberDao;
import project.vo.Member;
import util.ActionListener;
import util.BreadcrumbPrompt;

public class MemberListListener implements ActionListener {
  MemberDao memberDao;

  public MemberListListener(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    prompt.println("---------------------------------------");
    prompt.println("번호, 이름, 이메일, 성별");
    prompt.println("---------------------------------------");

    List<Member> list = memberDao.list();
    for (Member m : list) {
      prompt.printf("%d, %s, %s, %s\n",
          m.getNo(), m.getName(), m.getEmail(),
          m.getGender() == 'M' ? "남성" : "여성");
    }
  }

}