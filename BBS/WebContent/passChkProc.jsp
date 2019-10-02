<%@page import="dao.ModRequest"%>
<%@page import="dao.ArticleVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
request.setCharacterEncoding("utf-8");
String mode = request.getParameter("mode");
String password = request.getParameter("password");
int article_no = Integer.parseInt(request.getParameter("article_no"));
BoardDAO dao = BoardDAO.getInstance();
Boolean result = dao.PwdCheck(article_no, password);
if("alter".equals(mode) && result==true){
	System.out.println("수정 - 비번확인완료");
	response.sendRedirect("/BBS/contentalter.jsp?article_no=" + article_no);
}else if("del".equals(mode) && result==true){
	System.out.println("삭제 - 비번확인완료");
	response.sendRedirect("/BBS/removeArticle.do?article_no=" + article_no);
}else{
	%>
	<script>
	alert("비밀번호가 맞지 않습니다.")
	history.back();
	</script>
	<%
};
%>
