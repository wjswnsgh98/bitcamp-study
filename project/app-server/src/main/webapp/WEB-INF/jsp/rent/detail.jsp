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
<title>도서 대여</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>도서 대여 상세보기</h1>

<c:if test="${empty rent}">
    <p>해당 도서의 대여한 이력이 없습니다!</p>
</c:if>

<c:if test="${not empty rent}">
    <table border='1'>
        <tr>
            <th style='width:120px;'>번호</th>
            <td style='width:300px;'><input type='text' name='no' value='${rent.no}' readonly></td>
        </tr>
        <tr>
            <th>작성자</th><td>${rent.lender.name}</td>
        </tr>
        <tr>
            <th>도서 제목</th><td><input type='text' name='rent_book' value='${rent.rentBook.bookTitle}'></td>
        </tr>
        <tr>
            <th>대여일</th><td><fmt:formatDate value="${rent.rentalDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
        <tr>
            <th>반납일</th><td><fmt:formatDate value="${rent.returnDate}" pattern="yyyy-MM-dd"/></td>
        </tr>
    </table>

    <div>
    <button type='reset'>초기화</button>
    <a href='delete?no=${param.no}'>반납</a>
    <a href='list'>목록</a>
    </div>
</c:if>

<jsp:include page="../footer.jsp"/>

</body>
</html>