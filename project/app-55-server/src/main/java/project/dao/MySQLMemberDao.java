package project.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.vo.Member;
import util.Component;

@Component
public class MySQLMemberDao implements MemberDao {
  SqlSessionFactory sqlSessionFactory;

  public MySQLMemberDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.dao.MemberDao.insert", member);
  }

  @Override
  public List<Member> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectList("project.dao.MemberDao.findAll");
  }

  @Override
  public Member findBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("project.dao.MemberDao.findBy", no);
  }

  @Override
  public Member findByEmailAndPassword(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectOne("project.dao.MemberDao.findByEmailAndPassword", member);
  }

  @Override
  public int update(Member member) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.dao.MemberDao.update", member);
  }

  @Override
  public int delete(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.dao.MemberDao.delete", no);
  }
}