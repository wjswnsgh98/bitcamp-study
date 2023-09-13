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

@Controller("/rent/delete")
public class RentDeleteController implements PageController {
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

        try {
            int rentNo = Integer.parseInt(request.getParameter("no"));
            Rent rent = rentService.get(rentNo);

            if (rent == null || rent.getLender().getNo() != loginUser.getNo()) {
                throw new Exception("해당 번호의 대여정보가 없거나 삭제 권한이 없습니다.");
            } else {
                String bookTitle = rent.getRentBook().getBookTitle();
                Book book = bookService.get(bookTitle);
                if(book != null){
                    bookService.increaseCount(bookTitle);
                    request.setAttribute("book", book);
                }
                rentService.delete(rentNo);
                return "redirect:list";
            }

        } catch (Exception e) {
            request.setAttribute("refresh", "2;url=list");
            throw e;
        }
    }
}