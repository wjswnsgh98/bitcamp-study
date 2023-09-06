package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.dao.BookDao;
import project.service.BookService;
import project.vo.Book;
import project.vo.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/book/delete")
public class BookDeleteController implements PageController {
    @Autowired
    BookService bookService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:../auth/login";
        }

        try {
            Book book = bookService.get(request.getParameter("booktitle"), request.getParameter("author"));
            String[][] BOOKS = BookDao.BOOKS;

            if (book == null || book.getLender().getNo() != loginUser.getNo()) {
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
                bookService.delete(book.getNo());
                return "redirect:list";
            }

        } catch (Exception e) {
            request.setAttribute("refresh", "2;url=rent");
            throw e;
        }
    }
}