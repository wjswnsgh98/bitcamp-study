package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import project.service.BookService;
import project.service.NcpObjectStorageService;
import project.vo.Book;
import project.vo.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@Component("/book/add")
public class BookAddController implements PageController {
  @Autowired
  BookService bookService;

  @Autowired
  NcpObjectStorageService ncpObjectStorageService;

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
      book.setBookTitle(request.getParameter("booktitle"));
      book.setAuthor(request.getParameter("author"));
      book.setPublisher(request.getParameter("publisher"));
      book.setContent(request.getParameter("content"));

      Part photoPart = request.getPart("photo");
      if (photoPart.getSize() > 0) {
        String uploadFileUrl = ncpObjectStorageService.uploadFile(
                "bitcamp-nc7-bucket-10", "book/", photoPart);
        book.setPhoto(uploadFileUrl);
      }

      bookService.add(book);
      return "redirect:list";

    } catch (Exception e) {
      request.setAttribute("message", "도서 등록 오류!");
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}