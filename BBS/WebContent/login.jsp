<%@ page language="java" contentType="text/html; charset=UTF-8"
	session ="true"
    pageEncoding="UTF-8"
    import="member.*"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 화면</title>
</head>
<body>
<form method="post" action="login.do" >
<p>
아이디 : <br><input type ="text" name="id" value="${param.id }"><br><br>
</p>
<p>
비밀번호 : <br><input type="password" name="password"><br>
<c:if test="${errors.result}">일치하지 않습니다</c:if>
</p>
<input type="submit" value="로그인">
<input type="button" value="메인으로" onClick="location.href='/BBS/main.jsp'">

</form>
</body>
</html>