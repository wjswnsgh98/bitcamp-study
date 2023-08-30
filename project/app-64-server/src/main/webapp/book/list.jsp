<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="refresh" value="2;url=rent.jsp" scope="request"/>
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

<jsp:useBean id="bookDao" type="project.dao.BookDao" scope="application"/>
<c:set var="list" value="${bookDao.findAll()}" scope="page"/>

<tbody>
<c:forEach items="${list}" var="book">
    <tr>
     <td><a href='/book/view.jsp?booktitle=${book.bookTitle}&author=${book.author}'>${book.bookTitle}</a></td>
     <td>${book.getAuthor()}</td> <td>${book.lender.getName()}</td>
     <td><fmt:formatDate value="${book.rentalDate}" pattern="yyyy-MM-dd"/></td>
     <td><fmt:formatDate value="${book.returnDate}" pattern="yyyy-MM-dd"/></td>
    </tr>
</c:forEach>
</tbody>
</table>
<a href='/'>메인</a>

<jsp:include page="../footer.jsp"/>

</body>
</html>