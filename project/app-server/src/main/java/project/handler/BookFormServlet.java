package project.handler;

import java.io.PrintWriter;
import util.Component;
import util.HttpServletRequest;
import util.HttpServletResponse;
import util.Servlet;

@Component("/book/form")
public class BookFormServlet implements Servlet {
  @Override
  public void service(HttpServletRequest request, HttpServletResponse response) throws Exception {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    out.println("<!DOCTYPE html>");
    out.println("<html>");
    out.println("<head>");
    out.println("<meta charset='UTF-8'>");
    out.println("<title>비트캠프</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>도서 대여</h1>");
    out.println("<form action='/book/add' method='post'>");
    out.println("도서 제목 <input type='text' name='booktitle'><br>");
    out.println("저자 <textarea name='author'></textarea><br>");
    out.println("<button>등록</button>");
    out.println("</form>");
    out.println("</body>");
    out.println("</html>");
  }
}