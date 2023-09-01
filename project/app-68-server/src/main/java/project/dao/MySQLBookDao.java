package project.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Component;
import project.vo.Book;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MySQLBookDao implements BookDao{
  SqlSessionFactory sqlSessionFactory;

  public MySQLBookDao(SqlSessionFactory sqlSessionFactory) {
    System.out.println("MySQLBookDao() 호출됨!");
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public void insert(Book book) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    sqlSession.insert("project.dao.BookDao.insert", book);
  }

  @Override
  public List<Book> findAll() {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    return sqlSession.selectList("project.dao.BookDao.findAll");
  }


  @Override
  public Book findBy(String booktitle, String author) {
    SqlSession sqlSession = sqlSessionFactory.openSession();

    Map<String,Object> paramMap = new HashMap<>();
    paramMap.put("bookTitle", booktitle);
    paramMap.put("author", author);

    return sqlSession.selectOne("project.dao.BookDao.findBy", paramMap);
  }

  @Override
  public int update(Book book) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.update("project.dao.BookDao.update", book);
  }

  @Override
  public int delete(Book book) {
    SqlSession sqlSession = sqlSessionFactory.openSession(false);
    return sqlSession.delete("project.dao.BookDao.delete", book);
  }
}