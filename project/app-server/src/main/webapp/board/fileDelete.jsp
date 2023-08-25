<%@ page
    language="java"
    pageEncoding="UTF-8"
    contentType="text/html;charset=UTF-8"
    trimDirectiveWhitespaces="true"
    errorPage="/error.jsp"%>
<%@ page import="org.apache.ibatis.session.SqlSessionFactory"%>
<%@ page import="project.dao.BoardDao"%>
<%@ page import="project.vo.AttachedFile"%>
<%@ page import="project.vo.Board"%>
<%@ page import="project.vo.Member"%>

<%
    Member loginUser = (Member) request.getSession().getAttribute("loginUser");
    if (loginUser == null) {
      response.sendRedirect("/auth/form.jsp");
      return;
    }

    int fileNo = Integer.parseInt(request.getParameter("no"));

    BoardDao boardDao = (BoardDao) this.getServletContext().getAttribute("boardDao");
    SqlSessionFactory sqlSessionFactory = (SqlSessionFactory) this.getServletContext().getAttribute("sqlSessionFactory");

    AttachedFile attachedFile = boardDao.findFileBy(fileNo);
    Board board = boardDao.findBy(attachedFile.getBoardNo());

    if (board.getWriter().getNo() != loginUser.getNo()) {
      throw new ServletException("게시글 변경 권한이 없습니다!");
    }

    if (boardDao.deleteFile(fileNo) == 0) {
        throw new Exception("해당 번호의 첨부파일이 없거나 삭제 권한이 없습니다.");
    } else {
        response.sendRedirect("/board/detail.jsp?no=" + board.getNo());
    }
    sqlSessionFactory.openSession(false).commit();
%>