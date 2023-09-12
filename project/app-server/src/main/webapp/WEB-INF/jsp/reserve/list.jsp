<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/WEB-INF/jsp/error.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>도서 예약 목록</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>도서 예약 목록</h1>
<div style='margin:5px;'>
<a href='add'>예약 등록</a>
</div>
<table border='1'>
<thead>
  <tr><th>번호</th> <th>예약자</th> <th>예약 도서 제목</th> <th>예약일</th></tr>
</thead>

<tbody>
<c:forEach items="${list}" var="reserve">
      <tr>
          <td>${reserve.no}</td>
          <td>${reserve.reserveName.name}</td>
          <td><a href='detail?no=${reserve.no}'>${reserve.reserveBook.bookTitle}</a></td>
          <td><fmt:formatDate value="${reserve.reserveDate}" pattern="yyyy-MM-dd"/></td>
      </tr>
</c:forEach>
</tbody>
</table>
<a href='/'>메인</a>

<jsp:include page="../footer.jsp"/>

</body>
</html>