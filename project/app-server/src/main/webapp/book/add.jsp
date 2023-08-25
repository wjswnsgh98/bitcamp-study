<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@ page import="project.dao.BookDao"%>
<%@ page import="project.dao.MemberDao"%>
<%@ page import="project.vo.Book"%>
<%@ page import="project.vo.Member"%>
<%@ page import="util.NcpObjectStorageService"%>

<%
    String[][] BOOKS = BookDao.BOOKS;
    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
        response.sendRedirect("/auth/form.jsp");
        return;
    }

    BookDao bookDao = (BookDao) this.getServletContext().getAttribute("bookDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");

    Book book = new Book();
    book.setBookTitle(request.getParameter("booktitle"));
    book.setAuthor(request.getParameter("author"));
    book.setLender(loginUser);

    boolean foundBook = false; // 책을 찾았는지 확인하기 위한 변수

    for (int i = 0; i < BOOKS.length; i++) {
        String str = BOOKS[i][0];
        if (str.equals(book.getBookTitle())) {
          int count = Integer.parseInt(BOOKS[i][1]);
          if (count > 0) {
            book.setBookTitle(book.getBookTitle());
            count--; // 책의 수량을 1 감소시킴
            BOOKS[i][1] = Integer.toString(count); // 수정된 수량을 다시 BOOKS 배열에 반영
            foundBook = true;
            break;
          } else {
%>
<p>해당 제목의 도서는 모두 대여 중입니다!</p>
<%
            return;
          }
        }
    }

    if (!foundBook) {
%>
<p>해당 제목의 도서가 없습니다!</p>
<%
    return;
    }

    bookDao.insert(book);
    sqlSessionFactory.openSession(false).commit();
    response.sendRedirect("rent.jsp");
%>