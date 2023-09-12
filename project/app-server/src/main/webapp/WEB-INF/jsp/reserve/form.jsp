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

<h1>도서 예약 등록</h1>
<form action='add' method='post'>
    <table border='1'>
        <tr>
            <th>도서 제목</th> <td><input type='bookTitle' name='bookTitle'></td>
        </tr>
    </table>
<button>등록</button>
</form>

<jsp:include page="../footer.jsp"/>

</body>
</html>