<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>도서</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>도서 목록</h1>
<div style='margin:5px;'>
    <a href='add'>새 도서</a>
</div>
<table border='1'>
<thead>
    <tr><th>번호</th> <th>제목</th> <th>저자</th> <th>출판사</th> <th>줄거리</th> <th>수량</th></tr>
</thead>

<tbody>
<c:forEach items="${list}" var="book">
    <tr>
        <td>${book.no}</td>
        <td>
            <img src='http://hhyervzvcodl19010726.cdn.ntruss.com/book/${book.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
            <a href='view?no=${book.no}'>${book.bookTitle}</a></td>
        <td>${book.author}</td>
        <td>${book.publisher}</td>
        <td>${book.content}</td>
        <td>${book.count}</td>
    </tr>
</c:forEach>
</tbody>
</table>
<a href='/'>메인</a>

<jsp:include page="../footer.jsp"/>

</body>
</html>