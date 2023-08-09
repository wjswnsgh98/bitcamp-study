package util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import project.dao.BoardDao;
import project.dao.BookDao;
import project.dao.MemberDao;
import project.dao.MySQLBoardDao;
import project.dao.MySQLBookDao;
import project.dao.MySQLMemberDao;
import project.handler.BoardAddListener;
import project.handler.BoardDeleteListener;
import project.handler.BoardDetailListener;
import project.handler.BoardListListener;
import project.handler.BoardUpdateListener;
import project.handler.BookAddListener;
import project.handler.BookDeleteListener;
import project.handler.BookListListener;
import project.handler.BookRentListener;
import project.handler.BookViewListener;
import project.handler.LoginListener;
import project.handler.MemberAddListener;
import project.handler.MemberDeleteListener;
import project.handler.MemberDetailListener;
import project.handler.MemberListListener;
import project.handler.MemberUpdateListener;

public class DispatcherListener implements ActionListener {
  // 객체 보관소
  Map<String,Object> beanContainer = new HashMap<>();

  public DispatcherListener() throws Exception {

    // Mybatis 준비
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryProxy(
        new SqlSessionFactoryBuilder().build(
            Resources.getResourceAsStream("project/config/mybatis-config.xml")));
    beanContainer.put("sqlSessionFactory", sqlSessionFactory);

    // DAO 준비
    MemberDao memberDao = new MySQLMemberDao(sqlSessionFactory);
    BookDao bookDao = new MySQLBookDao(sqlSessionFactory);
    BoardDao boardDao = new MySQLBoardDao(sqlSessionFactory);
    beanContainer.put("memberDao", memberDao);
    beanContainer.put("bookDao", bookDao);
    beanContainer.put("boardDao", boardDao);

    // Listener 준비
    beanContainer.put("login", new LoginListener(memberDao));

    beanContainer.put("member/add", new MemberAddListener(memberDao, sqlSessionFactory));
    beanContainer.put("member/list", new MemberListListener(memberDao));
    beanContainer.put("member/detail", new MemberDetailListener(memberDao));
    beanContainer.put("member/update", new MemberUpdateListener(memberDao, sqlSessionFactory));
    beanContainer.put("member/delete", new MemberDeleteListener(memberDao, sqlSessionFactory));

    beanContainer.put("book/rent", new BookRentListener(bookDao));
    beanContainer.put("book/add", new BookAddListener(bookDao, sqlSessionFactory));
    beanContainer.put("book/list", new BookListListener(bookDao));
    beanContainer.put("book/view", new BookViewListener(bookDao, sqlSessionFactory));
    beanContainer.put("book/delete", new BookDeleteListener(bookDao, sqlSessionFactory));

    beanContainer.put("board/add", new BoardAddListener(boardDao, sqlSessionFactory));
    beanContainer.put("board/list", new BoardListListener(boardDao));
    beanContainer.put("board/detail", new BoardDetailListener(boardDao, sqlSessionFactory));
    beanContainer.put("board/update", new BoardUpdateListener(boardDao, sqlSessionFactory));
    beanContainer.put("board/delete", new BoardDeleteListener(boardDao, sqlSessionFactory));
  }

  @Override
  public void service(BreadcrumbPrompt prompt) throws IOException {
    ActionListener listener = (ActionListener) beanContainer.get(prompt.getAttribute("menuPath"));
    if (listener == null) {
      throw new RuntimeException("해당 요청을 처리할 수 없습니다.");
    }
    listener.service(prompt);
  }

  public Object getBean(String name) {
    return beanContainer.get(name);
  }
}