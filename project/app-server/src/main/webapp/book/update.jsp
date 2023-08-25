<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@ page import="project.dao.BoardDao"%>
<%@ page import="project.dao.BookDao"%>
<%@ page import="project.vo.Board"%>
<%@ page import="project.vo.Member"%>
<%@ page import="util.NcpObjectStorageService"%>

<%
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

    if (bookDao.update(book) == 0) {
        throw new Exception("해당 도서가 없거나 변경 권한이 없습니다.");
    } else {
        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("list.jsp");
    }
%>