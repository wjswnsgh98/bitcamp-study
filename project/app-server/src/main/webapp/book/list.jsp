<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="project.dao.BoardDao"%>
<%@ page import="project.dao.BookDao"%>
<%@ page import="project.vo.Book"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>도서 대여</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>도서 대여 목록</h1>
<div style='margin:5px;'>
</div>
<table border='1'>
<thead>
  <tr><th>제목</th> <th>저자</th> <th>대여자 이름</th> <th>대여일</th> <th>반납일</th></tr>
</thead>

<%
    BookDao bookDao = (BookDao) this.getServletContext().getAttribute("bookDao");
    List<Book> list = bookDao.findAll();
%>
<tbody>
<%
    for (Book book : list) {
%>
<tr>
 <td><a href='/book/view.jsp?booktitle=<%=book.getBookTitle()%>&author=<%=book.getAuthor()%>'><%=book.getBookTitle()%></a></td>
 <td><%=book.getAuthor()%></td> <td><%=book.getLender().getName()%></td>
 <td><%=String.format("%tY-%1$tm-%1$td", book.getRentalDate())%></td>
 <td><%=String.format("%tY-%1$tm-%1$td", book.getReturnDate())%></td>
</tr>
<%
    }
%>
</tbody>
</table>
<a href='/'>메인</a>

<jsp:include page="../footer.jsp"/>

</body>
</html>