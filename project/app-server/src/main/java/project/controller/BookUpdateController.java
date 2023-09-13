package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import project.service.BookService;
import project.service.NcpObjectStorageService;
import project.vo.Book;
import project.vo.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Controller("/book/update")
public class BookUpdateController implements PageController {
    @Autowired
    BookService bookService;

    @Autowired
    NcpObjectStorageService ncpObjectStorageService;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Member loginUser = (Member) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            return "redirect:../auth/login";
        }

        try {
            Book book = bookService.get(Integer.parseInt(request.getParameter("no")));
            if (book == null) {
                throw new Exception("해당 도서가 없습니다!");
            }

            book.setBookTitle(request.getParameter("bookTitle"));
            book.setAuthor(request.getParameter("author"));
            book.setPublisher(request.getParameter("publisher"));
            book.setContent(request.getParameter("content"));
            book.setCount(Integer.parseInt(request.getParameter("count")));

            Part photoPart = request.getPart("photo");
            if (photoPart.getSize() > 0) {
                String uploadFileUrl = ncpObjectStorageService.uploadFile(
                        "bitcamp-nc7-bucket-10", "book/", photoPart);
                book.setPhoto(uploadFileUrl);
            }

            bookService.update(book);
            return "redirect:list";

        } catch (Exception e) {
            request.setAttribute("refresh", "2;url=list");
            throw e;
        }
    }
}