package project.handler;

import java.util.List;
import project.vo.Member;
import util.BreadcrumbPrompt;

public class MemberListListener extends AbstractMemberListener{
  public MemberListListener(List<Member> list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 이메일, 성별");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Member m = this.list.get(i);
      System.out.printf("%d, %s, %s, %s\n",
          m.getNo(), m.getName(), m.getEmail(),
          toGenderString(m.getGender()));
    }
  }
}