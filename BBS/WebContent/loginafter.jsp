<%@ page language="java" contentType="text/html; charset=UTF-8"
	session="true"
	import ="java.util.*"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>

<%
	String name = (String)request.getAttribute("name");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript">
	function fn_logout(){
		var frmLogout = document.frm;
		frmLogout.method = "post";
		frmLogout.action = "BBS/logout.do";
		frmLogout.submit();
	}

</script>
<title>로그인 후 초기화면</title>
</head>
<body>
	<form name = "frm" method="post">
	&nbsp;${name}님 안녕하세요. <a href="logout.do" onclick="fn_logout()">[로그아웃하기]</a>
	<a href = "/BBS/changepw.do">[암호변경하기]</a><br><br>
	&nbsp;<a href = "/BBS/view/guestbook.jsp">방명록</a><br>
	&nbsp;<a href = "list.do">게시판</a>
	</form>
</body>
</html>