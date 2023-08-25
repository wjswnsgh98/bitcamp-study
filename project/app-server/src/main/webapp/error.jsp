<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    isErrorPage="true"%>

<%
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    sqlSessionFactory.openSession(false).commit();

    if (request.getAttribute("refresh") != null) {
      response.setHeader("Refresh", (String) request.getAttribute("refresh"));
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset=\"UTF-8\">
<title>게시글</title>
</head>
<body>

<jsp:include page="header.jsp"/>

<h1>실행 오류!</h1>

<p><%exception%></p>

<jsp:include page="footer.jsp"/>

</body>
</html>