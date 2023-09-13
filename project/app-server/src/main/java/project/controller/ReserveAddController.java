package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.service.BookService;
import project.service.ReserveService;
import project.vo.Book;
import project.vo.Member;
import project.vo.Reserve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/reserve/add")
public class ReserveAddController implements PageController {
    @Autowired
    ReserveService reserveService;

    @Autowired
    BookService bookService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:../auth/login";
        }

        if (request.getMethod().equals("GET")) {
            return "/WEB-INF/jsp/rent/form.jsp";
        }

        try {
            String bookTitle = request.getParameter("bookTitle");
            Book book = bookService.get(bookTitle);
            if(book != null){
                request.setAttribute("book", book);
            }

            Reserve reserve = new Reserve();
            reserve.setReserveName(loginUser);
            reserve.setReserveBook(book);

            reserveService.add(reserve);
            return "redirect:list";

        } catch (Exception e) {
            request.setAttribute("message", "도서 예약 등록 오류!");
            request.setAttribute("refresh", "2;url=list");
            throw e;
        }
    }
}
