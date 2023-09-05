package project.controller;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import project.dao.BookDao;
import project.vo.Book;
import project.vo.Member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/book/update")
public class BookUpdateController implements PageController {
  BookDao bookDao;
  PlatformTransactionManager txManager;

  public BookUpdateController(BookDao bookDao, PlatformTransactionManager txManager) {
    this.bookDao = bookDao;
    this.txManager = txManager;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      return "redirect:../auth/login";
    }

    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = txManager.getTransaction(def);

    try {
      Book book = new Book();
      book.setBookTitle(request.getParameter("booktitle"));
      book.setAuthor(request.getParameter("author"));
      book.setLender(loginUser);

      if (bookDao.update(book) == 0) {
        throw new Exception("해당 도서가 없거나 변경 권한이 없습니다.");
      } else {
        txManager.commit(status);
        return "redirect:list";
      }

    } catch (Exception e) {
      txManager.rollback(status);
      request.setAttribute("refresh", "2;url=list");
      throw e;
    }
  }
}