package project.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import project.vo.AttachedFile;
import project.vo.Board;

public class MySQLBoardDao implements BoardDao {
  SqlSessionFactory sqlSessionFactory;

  public MySQLBoardDao(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.dao.BoardDao.insert", board);
  }

  @Override
  public List<Board> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    return sqlSession.selectList("project.dao.BoardDao.findAll");
  }


  @Override
  public Board findBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(true);
    return sqlSession.selectOne("project.dao.BoardDao.findBy", no);
  }

  @Override
  public int update(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.dao.BoardDao.update", board);
  }

  @Override
  public int updateCount(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.dao.BoardDao.updateCount", board);
  }

  @Override
  public int delete(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.dao.BoardDao.delete", board);
  }

  @Override
  public int insertFiles(Board board) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.insert("project.dao.BoardDao.insertFiles", board);
  }

  @Override
  public AttachedFile findFileBy(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.selectOne("project.dao.BoardDao.findFileBy", no);
  }

  @Override
  public int deleteFile(int no) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.dao.BoardDao.deleteFile", no);
  }
}