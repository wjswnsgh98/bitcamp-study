<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/WEB-INF/jsp/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="refresh" value="2;url=list.jsp" scope="request"/>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>도서 대여</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>도서 대여</h1>

<c:if test="${empty book}">
    <p>해당 도서의 대여자가 없습니다!</p>
</c:if>

<c:if test="${not empty book}">
    <form action='/book/update' method='post'>
    <table border='1'>
    <tr><th style='width:120px;'>도서 제목</th>
     <td style='width:300px;'><input type='text' name='booktitle' value='${book.bookTitle}' readonly></td></tr>
    <tr><th>저자</th>
     <td><input type='text' name='author' value='${book.author}'></td></tr>
    <tr><th>대여자</th> <td>${book.lender.name}</td></tr>
    <tr><th>대여일</th> <td>${simpleDateFormatter.format(book.rentalDate)}</td></tr>
    <tr><th>반납일</th> <td>${simpleDateFormatter.format(book.returnDate)}</td></tr>
    </table>

    <div>
    <button>변경</button>
    <button type='reset'>초기화</button>
    <a href='/book/delete?booktitle=${book.getBookTitle()}&author=${book.getAuthor()}'>삭제</a>
    <a href='/book/rent'>목록</a>
    </div>
    </form>
</c:if>

<jsp:include page="../footer.jsp"/>

</body>
</html>