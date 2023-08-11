package project.handler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import project.dao.BoardDao;
import project.dao.BookDao;
import project.dao.MemberDao;
import project.dao.MySQLBoardDao;
import project.dao.MySQLBookDao;
import project.dao.MySQLMemberDao;
import util.SqlSessionFactoryProxy;

@WebServlet(
    value="/init",
    loadOnStartup = 1
    )
public class InitServlet extends HttpServlet {

  private static final long serialVersionUID = 1L;

  public static SqlSessionFactory sqlSessionFactory;
  public static BoardDao boardDao;
  public static BookDao bookDao;
  public static MemberDao memberDao;

  @Override
  public void init() throws ServletException {
    System.out.println("InitServlet.init() 호출됨!");

    try {
      sqlSessionFactory = new SqlSessionFactoryProxy(
          new SqlSessionFactoryBuilder().build(
              Resources.getResourceAsStream("project/config/mybatis-config.xml")));

      bookDao = new MySQLBookDao(sqlSessionFactory);
      boardDao = new MySQLBoardDao(sqlSessionFactory);
      memberDao = new MySQLMemberDao(sqlSessionFactory);

    } catch (Exception e) {
      System.out.println("InitServlet.init() 실행 중 오류 발생!");
      e.printStackTrace();
    }
  }
}