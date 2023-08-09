package project.handler;

import java.io.PrintWriter;
import java.util.List;
import project.dao.BookDao;
import project.vo.Book;
import util.Component;
import util.HttpServletRequest;
import util.HttpServletResponse;
import util.Servlet;

@Component("/book/list")
public class BookListServlet implements Servlet{
  BookDao bookDao;

  public BookListServlet(BookDao bookDao) {
    this.bookDao = bookDao;
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception{
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>도서 대여 목록</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>도서 대여 목록</h1>");
    out.println("<div style='margin:5px;'>");
    out.println("</div>");
    out.println("<table border='1'>");
    out.println("<thead>");
    out.println("  <tr><th>제목</th> <th>저자</th> <th>대여자 이름</th> <th>대여일</th> <th>반납일</th></tr>");
    out.println("</thead>");

    List<Book> list = bookDao.findAll();

    out.println("<tbody>");
    for (Book book : list) {
      out.printf("<tr> <td>%s</td>, <td>%s</td>, <td>%s</td>, <td>%tY-%4$tm-%4$td</td>, <td>%tY-%5$tm-%5$td</td>\n",
          book.getBookTitle(),
          book.getAuthor(),
          book.getLender().getName(),
          book.getRentalDate(),
          book.getReturnDate());
    }
  }
}