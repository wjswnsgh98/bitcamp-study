package project.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSessionFactory;
import project.dao.BookDao;
import project.vo.Book;
import project.vo.Member;

public class BookDeleteController implements PageController {
    BookDao bookDao;
    SqlSessionFactory sqlSessionFactory;

    public BookDeleteController(BookDao bookDao, SqlSessionFactory sqlSessionFactory) {
        this.bookDao = bookDao;
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
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

            if (bookDao.delete(book) == 0) {
                throw new Exception("해당 도서의 대여자가 없거나 삭제 권한이 없습니다!");
            } else {
                // BOOKS에서 같은 도서 제목의 수량을 1 증가
                for (int i = 0; i < BOOKS.length; i++) {
                    if (book.getBookTitle().equals(BOOKS[i][0])) {
                        int count = Integer.parseInt(BOOKS[i][1]);
                        BOOKS[i][1] = String.valueOf(count + 1);
                        break; // 해당 도서를 찾았으므로 더 이상 반복할 필요가 없음
                    }
                }

                sqlSessionFactory.openSession(false).commit();
                return "redirect:list";
            }

        } catch (Exception e) {
            sqlSessionFactory.openSession(false).rollback();
            request.setAttribute("refresh", "2;url=rent");
            throw e;
        }
    }
}