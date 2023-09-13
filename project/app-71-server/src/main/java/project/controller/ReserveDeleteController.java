package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.service.BookService;
import project.service.ReserveService;
import project.vo.Book;
import project.vo.Member;
import project.vo.Reserve;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/reserve/delete")
public class ReserveDeleteController implements PageController {
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

        try {
            int reserveNo = Integer.parseInt(request.getParameter("no"));
            Reserve reserve = reserveService.get(reserveNo);

            if (reserve == null || reserve.getReserveName().getNo() != loginUser.getNo()) {
                throw new Exception("해당 번호의 대여정보가 없거나 삭제 권한이 없습니다.");
            } else {
                String bookTitle = reserve.getReserveBook().getBookTitle();
                Book book = bookService.get(bookTitle);
                if(book != null){
                    request.setAttribute("book", book);
                }
                reserveService.delete(reserveNo);
                return "redirect:list";
            }

        } catch (Exception e) {
            request.setAttribute("refresh", "2;url=list");
            throw e;
        }
    }
}