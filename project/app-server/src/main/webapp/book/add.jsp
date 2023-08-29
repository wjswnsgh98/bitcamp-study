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
    if (loginUser == null) {
        response.sendRedirect("/auth/form.jsp");
        return;
    }

    // 오류가 발생했을 때 refresh 할 URL을 미리 지정한다.
    request.setAttribute("refresh", "2;url=list.jsp");

    String[][] BOOKS = bookDao.BOOKS;
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