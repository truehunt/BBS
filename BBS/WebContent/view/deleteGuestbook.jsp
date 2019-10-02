<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	request.setCharacterEncoding("utf-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록 등록 성공 페이지</title>
</head>
<body>
<c:if test="${'false'==check }">
	<h3>비밀번호가 일치하지 않습니다!</h3>
</c:if>
<c:if test="${'false'!=check }">
	<h3>메시지를 삭제하였습니다.</h3>
</c:if>

<a href='/BBS/view/guestbook.jsp'>[목록 보기]</a>
</body>
</html>