<%@ page language="java" contentType="text/html; charset=UTF-8"
    session="true"
    import="java.util.*"
    isELIgnored="false"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>암호 변경</title>
</head>
<body>
	<form method="post" action="changepw.do">
	현재 암호:<br><input type = "password" name="old_pwd"><br>
	새 암호:<br><input type = "password" name="new_pwd"><br>
	<input type="submit" value="암호 변경">
	<input type="button" value="메인으로" onClick="location.href='/BBS/loginafter.jsp'">
	</form>
</body>
</html>