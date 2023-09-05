package project.controller;

import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import project.dao.BookDao;
import project.vo.Book;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/book/view")
public class BookViewController implements PageController {
  BookDao bookDao;
  PlatformTransactionManager txManager;

  public BookViewController(BookDao bookDao, PlatformTransactionManager txManager) {
    this.bookDao = bookDao;
    this.txManager = txManager;
  }

  @Override
  public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
    DefaultTransactionDefinition def = new DefaultTransactionDefinition();
    def.setName("tx1");
    def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
    TransactionStatus status = txManager.getTransaction(def);

    try {
      String bookTitle = request.getParameter("booktitle");
      String author = request.getParameter("author");

      Book book = bookDao.findBy(bookTitle, author);
      if(book != null){
        txManager.commit(status);
        request.setAttribute("book", book);
      }
      return "/WEB-INF/jsp/book/view.jsp";

    } catch (Exception e){
      txManager.rollback(status);
      request.setAttribute("refresh", "5;url=/book/list");
      throw e;
    }
  }
}
