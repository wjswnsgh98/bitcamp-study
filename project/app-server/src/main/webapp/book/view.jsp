<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@ page import="project.dao.BoardDao"%>
<%@ page import="project.dao.BookDao"%>
<%@ page import="project.vo.Book"%>

<%
    BookDao bookDao = (BookDao) this.getServletContext().getAttribute("bookDao");
    Book book = bookDao.findBy(request.getParameter("booktitle"), request.getParameter("author"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>도서 대여</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>도서 대여</h1>

<%
    if(book == null) {
%>
<p>해당 도서의 대여자가 없습니다!</p>
<%
      return;
    } else {
%>
<form action='/book/update.jsp' method='post'>
<table border='1'>
<tr><th style='width:120px;'>도서 제목</th>
 <td style='width:300px;'><input type='text' name='booktitle' value='<%=book.getBookTitle()%>' readonly></td></tr>
<tr><th>저자</th>
 <td><input type='text' name='author' value='<%=book.getAuthor()%>'></td></tr>
<tr><th>대여자</th> <td><%=book.getLender().getName()%></td></tr>
<tr><th>대여일</th> <td><%=String.format("%tY-%1$tm-%1$td", book.getRentalDate())%></td></tr>
<tr><th>반납일</th> <td><%=String.format("%tY-%1$tm-%1$td", book.getReturnDate())%></td></tr>
</table>

<div>
<button>변경</button>
<button type='reset'>초기화</button>
<a href='/book/delete.jsp?booktitle=<%=book.getBookTitle()%>&author=<%=book.getAuthor()%>'>삭제</a>
<a href='/book/rent.jsp'>목록</a>
</div>
</form>
<%
    }
%>

<jsp:include page="../footer.jsp"/>

</body>
</html>