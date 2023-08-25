<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@ page import="project.dao.BoardDao"%>
<%@ page import="project.vo.AttachedFile"%>
<%@ page import="project.vo.Board"%>
<%@ page import="project.vo.Member"%>
<%@ page import="util.NcpObjectStorageService"%>

<%
    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.html");
      return;
    }

    BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");
    NcpObjectStorageService ncpObjectStorageService = (NcpObjectStorageService) this.getServletContext().getAttribute("ncpObjectStorageService");

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