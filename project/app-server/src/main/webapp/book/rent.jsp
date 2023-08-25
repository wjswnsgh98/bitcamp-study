<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>
<%@ page import="project.dao.BookDao"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>도서 대여</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>대여 가능한 도서 목록</h1>
<div style='margin:5px;'>
<a href='/book/form.jsp'>대여 등록</a>
</div>
<table border='1'>
<thead>
  <tr><th>대여가능한 도서 제목</th> <th>수량</th></tr>
</thead>

<%
    String[][] BOOKS = BookDao.BOOKS;
    for (int i = 0; i < BOOKS.length; i++) {
%>
<tr> <td><%=BOOKS[i][0]%></td> <td><%=BOOKS[i][1]%><td></tr>
<%
    }
%>
</tbody>
</table>
<a href='/'>메인</a>
<a href='/book/list.jsp'>도서 대여 목록</a>

<jsp:include page="../footer.jsp"/>

</body>
</html>