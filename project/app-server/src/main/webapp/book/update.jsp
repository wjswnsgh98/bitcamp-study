<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="project.vo.Book"%>

<jsp:useBean id="bookDao" type="project.dao.BookDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="ncpObjectStorageService" type="util.NcpObjectStorageService" scope="application"/>
<jsp:useBean id="loginUser" class="project.vo.Member" scope="session"/>

<%
    if (loginUser.getNo() == 0) {
      response.sendRedirect("/auth/form.jsp");
      return;
    }

    request.setAttribute("refresh", "2;url=list.jsp");

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