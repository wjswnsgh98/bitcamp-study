package bitcamp.myapp.handler;

import bitcamp.myapp.vo.Member;
import bitcamp.util.BreadcrumbPrompt;
import bitcamp.util.List;

public class MemberListListener extends AbstractMemberListener{

  public MemberListListener(List list) {
    super(list);
  }

  @Override
  public void service(BreadcrumbPrompt prompt) {
    System.out.println("---------------------------------------");
    System.out.println("번호, 이름, 이메일, 성별");
    System.out.println("---------------------------------------");

    for (int i = 0; i < this.list.size(); i++) {
      Member m = (Member) this.list.get(i);
      System.out.printf("%d, %s, %s, %s\n", m.no, m.name, m.email, toGenderString(m.gender));
    }
  }

  public static String toGenderString(char gender){
    return gender == 'M' ? "남성" : "여성";
  }
}
