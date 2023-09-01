package project.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.BookDao;
import project.vo.Book;
import project.vo.Member;
import util.NcpObjectStorageService;

public class BookAddController implements PageController {
  BookDao bookDao;
  SqlSessionFactory sqlSessionFactory;

  public BookAddController(BookDao bookDao, SqlSessionFactory sqlSessionFactory) {
    this.bookDao = bookDao;
    this.sqlSessionFactory = sqlSessionFactory;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    if (request.getMethod().equals("GET")) {
      return "/WEB-INF/jsp/book/form.jsp";
    }

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:../auth/login";
    }

    try {
      Book book = new Book();
      String[][] BOOKS = bookDao.BOOKS;
      book.setBookTitle(request.getParameter("booktitle"));
      book.setAuthor(request.getParameter("author"));
      book.setLender(loginUser);

      boolean foundBook = false; // 책을 찾았는지 확인하기 위한 변수

      for (int i = 0; i < BOOKS.length; i++) {
        String str = BOOKS[i][0];
        if (str.equals(book.getBookTitle())) {
          int count = Integer.parseInt(BOOKS[i][1]);
          if (count > 0) {
            book.setBookTitle(book.getBookTitle());
            count--; // 책의 수량을 1 감소시킴
            BOOKS[i][1] = Integer.toString(count); // 수정된 수량을 다시 BOOKS 배열에 반영
            foundBook = true;
            break;
          } else {
            request.setAttribute("message", "해당 제목의 도서는 모두 대여 중입니다!");
          }
        }
      }

      if (!foundBook) {
        request.setAttribute("message", "해당 제목의 도서가 없습니다!");
      }

      bookDao.insert(book);
      sqlSessionFactory.openSession(false).commit();
      return "redirect:rent";

    } catch (Exception e) {
      sqlSessionFactory.openSession(false).rollback();
      request.setAttribute("message", "도서 대여 등록 오류!");
      request.setAttribute("refresh", "2;url=rent");
      throw e;
    }
  }
}