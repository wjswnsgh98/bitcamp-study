package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.service.BookService;
import project.vo.Book;
import project.vo.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/book/update")
public class BookUpdateController implements PageController {
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
            if (book == null || book.getLender().getNo() != loginUser.getNo()) {
                throw new Exception("해당 도서의 대여자가 없거나 삭제 권한이 없습니다!");
            }

            book.setBookTitle(request.getParameter("booktitle"));
            book.setAuthor(request.getParameter("author"));

            bookService.update(book);
            return "redirect:list";

        } catch (Exception e) {
            request.setAttribute("refresh", "2;url=list");
            throw e;
        }
    }
}