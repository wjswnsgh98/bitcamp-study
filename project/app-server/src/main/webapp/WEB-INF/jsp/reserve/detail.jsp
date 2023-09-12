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
<title>도서 예약</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>도서 예약 상세보기</h1>

<c:if test="${empty reserve}">
    <p>해당 도서의 예약한 이력이 없습니다!</p>
</c:if>

<c:if test="${not empty reserve}">
    <table border='1'>
        <tr>
            <th style='width:120px;'>번호</th>
            <td style='width:300px;'><input type='text' name='no' value='${reserve.no}' readonly></td>
        </tr>
        <tr>
            <th>예약자</th><td>${reserve.reserveName.name}</td>
        </tr>
        <tr>
            <th>예약 도서 제목</th><td><input type='text' name='reserve_book' value='${reserve.reserveBook.bookTitle}'></td>
        </tr>
        <tr>
            <th>예약일</th><td><fmt:formatDate value="${reserve.reserveDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
    </table>

    <div>
    <button type='reset'>초기화</button>
    <a href='delete?no=${reserve.no}'>예약 도서 삭제</a>
    <a href='list'>예약 목록</a>
    </div>
</c:if>

<jsp:include page="../footer.jsp"/>

</body>
</html>