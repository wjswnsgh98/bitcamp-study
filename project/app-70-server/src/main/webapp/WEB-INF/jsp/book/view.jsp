<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/WEB-INF/jsp/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="refresh" value="2;url=list.jsp" scope="request"/>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>도서</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>도서</h1>

<c:if test="${empty book}">
    <p>해당 도서가 없습니다!</p>
</c:if>

<c:if test="${not empty book}">
    <form action='update' method='post' enctype='multipart/form-data'>
    <table border='1'>
        <tr>
            <th style='width:120px;'>사진</th>
            <td style='width:300px;'>
                <c:if test="${empty book.photo}">
                    <img style='height:80px' src='/images/avatar.png'>
                </c:if>
                <c:if test="${not empty book.photo}">
                    <a href='https://kr.object.ncloudstorage.com/bitcamp-nc7-bucket-10/book/${book.photo}'>
                        <img src='http://hhyervzvcodl19010726.cdn.ntruss.com/book/${book.photo}?type=f&w=60&h=80&faceopt=true&ttype=jpg'>
                    </a>
                </c:if>
                <input type='file' name='photo'></td></tr>
        <tr>
            <th style='width:120px;'>번호</th>
            <td style='width:300px;'><input type='text' name='no' value='${book.no}' readonly></td>
        </tr>
        <tr>
            <th>도서 제목</th>
            <td><input type='text' name='bookTitle' value='${book.bookTitle}'></td>
        </tr>
        <tr>
            <th>저자</th>
            <td><input type='text' name='author' value='${book.author}'></td>
        </tr>
        <tr>
            <th>출판사</th>
            <td><input type='text' name='publisher' value='${book.publisher}'></td>
        </tr>
        <tr>
            <th>줄거리</th>
            <td><textarea name='content' style='height:200px; width:400px;'>${book.content}</textarea></td>
        </tr>
        <tr>
            <th>수량</th>
            <td><input type='text' name='count' value='${book.count}'></td>
        </tr>
    </table>

    <div>
    <button>변경</button>
    <button type='reset'>초기화</button>
    <a href='delete?no=${book.no}'>삭제</a>
    <a href='list'>목록</a>
    </div>
    </form>
</c:if>

<jsp:include page="../footer.jsp"/>

</body>
</html>