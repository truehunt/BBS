<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정 확인</title>
</head>
<body>
	<h3>수정: password check</h3>
	<form name="delGuest" method="post" action="/BBS/modifyCheck.do"
		encType="UTF-8">
		<input type="hidden" name="messageId" value="${param.messageId}">
		암호입력 : <input type="password" name="password"> <input
			type="submit" value="확인"> <input type="reset" value="취소"
			onclick="history.back()">
	</form>
</body>
</html>