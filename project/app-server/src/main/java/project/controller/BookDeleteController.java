package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.service.BookService;
import project.vo.Book;
import project.vo.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/book/delete")
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
            Book book = bookService.get(Integer.parseInt(request.getParameter("no")));

            if (book == null) {
                throw new Exception("해당 번호의 도서가 없습니다.");
            } else {
                bookService.delete(book.getNo());
                return "redirect:list";
            }

        } catch (Exception e) {
            request.setAttribute("refresh", "2;url=list");
            throw e;
        }
    }
}