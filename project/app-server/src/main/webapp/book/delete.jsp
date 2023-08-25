<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@ page import="project.dao.BoardDao"%>
<%@ page import="project.dao.BookDao"%>
<%@ page import="project.vo.Book"%>
<%@ page import="project.vo.Member"%>

<%
    String[][] BOOKS = BookDao.BOOKS;
    BookDao bookDao = (BookDao) this.getServletContext().getAttribute("bookDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");

    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
        response.sendRedirect("/auth/form.jsp");
        return;
    }

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