<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"%>

<div style='height:50px;background-color:orange;'>
    <img src='https://www.ncloud.com/public/img/logo-m.png' style='height:40px'>
    <a href='/member/list.jsp'>회원</a>
    <a href='/book/rent.jsp'>도서 대여</a>
    <a href='/board/list.jsp'>게시글</a>

<jsp:useBean id="loginUser" class="project.vo.Member" scope="session"/>
<% if (loginUser.getNo() == 0) { %>
    <a href='/auth/form.jsp'>로그인</a>
<% } else {
     if(loginUser.getPhoto() == null){ %>
       <img style='height:40px' src='/images/avatar.png'>
  <% } else { %>
       <img src='http://hhyervzvcodl19010726.cdn.ntruss.com/member/${loginUser.photo}?type=f&w=30&h=40&faceopt=true&ttype=jpg'>
  <% } %>
       ${loginUser.name} <a href='/auth/logout.jsp'>로그아웃</a>
<% } %>
</div>