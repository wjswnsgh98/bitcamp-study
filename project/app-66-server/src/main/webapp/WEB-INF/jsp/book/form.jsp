<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>비트캠프</title>
</head>
<body>

<jsp:include page="../header.jsp"/>

<h1>도서 대여</h1>
<form action='add' method='post'>
도서 제목 <input type='text' name='booktitle'><br>
저자 <textarea name='author'></textarea><br>
<button>등록</button>
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>