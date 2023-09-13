package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.service.BookService;
import project.service.RentService;
import project.vo.Book;
import project.vo.Member;
import project.vo.Rent;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller("/rent/add")
public class RentAddController implements PageController {
    @Autowired
    RentService rentService;

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
                bookService.decreaseCount(bookTitle);
                request.setAttribute("book", book);
            }

            Rent rent = new Rent();
            rent.setLender(loginUser);
            rent.setRentBook(book);

            rentService.add(rent);
            return "redirect:list";

        } catch (Exception e) {
            request.setAttribute("message", "도서 대여 등록 오류!");
            request.setAttribute("refresh", "2;url=list");
            throw e;
        }
    }
}
