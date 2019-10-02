<%@page import="dao.ArticleVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
	String mode = request.getParameter("mode");
	String password = request.getParameter("password");
	int article_no = Integer.parseInt(request.getParameter("article_no"));
%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />

<html>
<head>
<meta charset="UTF-8">
<title>암호 확인</title>
<script type="text/javascript">
	
	function passChkProcBtn(){
		frm.method='post';
		frm.action='passChkProc.jsp';
		frm.submit();
	}
	
	function move(url) {
  		location.href=url;
  	}
	
	
</script>
</head>

<body>
<%
	if("del".equals(mode)){
%>
	<h2 align=center>글 삭제</h2>
	<form id = frm method="post" action="passChkProc.jsp?mode=del&article_no=<%=article_no %>"
		encType="UTF-8">
		<table border 1 align = center>
			<tr>
				<th>비밀번호를 입력해주세요</th>
			</tr>
 	<tr>
     	<td>비밀번호 : <input type=password name=password>
		<input type = hidden name =password value=password>
		</td>
    </tr>
    <tr>
		<td align=center>
		<input type=submit value="게시글 삭제하기" >
		<input type=button value="뒤로" onClick="history.back()">
		</td>
	</tr>
<% 	} %>
<%
	if("alter".equals(mode)){
%> 
	<h2 align=center>글 수정</h2>
	<form id = frm method="post" action="passChkProc.jsp?mode=alter&article_no=<%=article_no %>"
		encType="UTF-8">
		<table border 1 align = center>
			<tr>
				<th>비밀번호를 입력해주세요</th>
			</tr>
 	<tr>
     	<td>비밀번호 : <input type=password name=password>
		<input type = hidden name = password value=password>
		</td>
    </tr>
    <tr>
		<td align=center>
		<input type=submit value="게시글 수정하기" >
		<input type=button value="뒤로" onClick="history.back()">
		</td>
	</tr>
<% 	} %>
	</form> 
</body>
</html>