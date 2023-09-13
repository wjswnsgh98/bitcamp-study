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
<title>도서 대여 목록</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>도서 대여 목록</h1>
<div style='margin:5px;'>
<a href='add'>대여 등록</a>
</div>
<table border='1'>
<thead>
  <tr><th>번호</th> <th>대여자</th> <th>대여 도서 제목</th> <th>대여일</th> <th>반납일</th></tr>
</thead>

<tbody>
<c:forEach items="${list}" var="rent">
      <tr>
          <td>${rent.no}</td>
          <td>${rent.lender.name}</td>
          <td><a href='detail?no=${rent.no}'>${rent.rentBook.bookTitle}</a></td>
          <td><fmt:formatDate value="${rent.rentalDate}" pattern="yyyy-MM-dd"/></td>
          <td><fmt:formatDate value="${rent.returnDate}" pattern="yyyy-MM-dd"/></td>
      </tr>
</c:forEach>
</tbody>
</table>
<a href='/'>메인</a>

<jsp:include page="../footer.jsp"/>

</body>
</html>