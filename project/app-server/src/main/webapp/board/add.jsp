<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="project.vo.AttachedFile"%>
<%@ page import="project.vo.Board"%>

<jsp:useBean id="boardDao" type="project.dao.BoardDao" scope="application"/>
<jsp:useBean id="sqlSessionFactory" type="org.apache.ibatis.session.SqlSessionFactory" scope="application"/>
<jsp:useBean id="ncpObjectStorageService" type="util.NcpObjectStorageService" scope="application"/>
<jsp:useBean id="loginUser" class="project.vo.Member" scope="session"/>

<%
    if (loginUser.getNo() == 0) {
      response.sendRedirect("/auth/form.jsp");
      return;
    }

    // 오류가 발생했을 때 refresh 할 URL을 미리 지정한다.
    request.setAttribute("refresh", "2;url=list.jsp");

    Board board = new Board();
    board.setWriter(loginUser);
    board.setTitle(request.getParameter("title"));
    board.setContent(request.getParameter("content"));

    ArrayList<AttachedFile> attachedFiles = new ArrayList<>();
    for (Part part : request.getParts()) {
        if (part.getName().equals("files") && part.getSize() > 0) {
          String uploadFileUrl = ncpObjectStorageService.uploadFile(
                  "bitcamp-nc7-bucket-10", "board/", part);
          AttachedFile attachedFile = new AttachedFile();
          attachedFile.setFilePath(uploadFileUrl);
          attachedFiles.add(attachedFile);
        }
    }
    board.setAttachedFiles(attachedFiles);

    boardDao.insert(board);
    if (attachedFiles.size() > 0) {
        boardDao.insertFiles(board);
    }

    sqlSessionFactory.openSession(false).commit();
    response.sendRedirect("list.jsp");
%>