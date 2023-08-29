<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="project.vo.Book"%>

<jsp:useBean id="bookDao" type="project.dao.BookDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="loginUser" class="project.vo.Member" scope="session"/>
<%
    if (loginUser == null) {
        response.sendRedirect("/auth/form.jsp");
        return;
    }

    request.setAttribute("refresh", "2;url=list.jsp");
    String[][] BOOKS = bookDao.BOOKS;

    String booktitle = request.getParameter("booktitle");
    String author = request.getParameter("author");

    Book book = new Book();
    book.setBookTitle(booktitle);
    book.setAuthor(author);
    book.setLender(loginUser);

    if (bookDao.delete(book) == 0) {
        throw new Exception("해당 도서의 대여자가 없거나 삭제 권한이 없습니다!");
    } else {
        sqlSessionFactory.openSession(false).commit();
        response.sendRedirect("/book/list.jsp");
    }

    for (int i = 0; i < BOOKS.length; i++) {
        if (book.getBookTitle().equals(BOOKS[i][0])) {
            int count = Integer.parseInt(BOOKS[i][1]);
            BOOKS[i][1] = String.valueOf(count + 1);
            break; // 해당 도서를 찾았으므로 더 이상 반복할 필요가 없음
        }
    }
%>