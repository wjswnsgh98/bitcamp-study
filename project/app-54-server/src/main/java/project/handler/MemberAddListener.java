package project.handler;

import java.io.IOException;
import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.MemberDao;
import project.vo.Member;
import util.BreadcrumbPrompt;
import util.Component;

@Component("/member/add")
public class MemberAddListener implements MemberActionListener{
  MemberDao memberDao;
  SqlSessionFactory sqlSessionFactory;

  public MemberAddListener(MemberDao memberDao, SqlSessionFactory sqlSessionFactory) {
    this.memberDao = memberDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException{
    Member m = new Member();
    m.setName(prompt.inputString("이름? "));
    m.setEmail(prompt.inputString("이메일? "));
    m.setPassword(prompt.inputString("암호? "));
    m.setGender(MemberActionListener.inputGender((char)0, prompt));

    try {
      memberDao.insert(m);
      sqlSessionFactory.openSession(false).commit();

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      throw new RuntimeException(e);
    }
  }
}