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

<h1>도서</h1>
<form action='add' method='post' enctype='multipart/form-data'>
    <table border='1'>
        <tr>
            <th>사진</th> <td><input type='file' name='photo'></td>
        </tr>
        <tr>
            <th>도서 제목</th> <td><input type='text' name='booktitle'></td>
        </tr>
        <tr>
            <th>저자</th> <td><input type='text' name='author'></td>
        </tr>
        <tr>
            <th>출판사</th> <td><input type='text' name='publisher'></td>
        </tr>
        <tr>
            <th>줄거리</th> <td><input type='text' name='content'></td>
        </tr>
        <tr>
            <th>수량</th> <td><input type='text' name='count'></td>
        </tr>
    </table>
<button>등록</button>
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>