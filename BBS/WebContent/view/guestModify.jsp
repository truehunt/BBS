<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="border:1px solid; padding:5px; width:270px;background-color:ivory; text-align:center">
	<span style="font-weight: bold;">[ 방명록 수정 ]</span>
	<form name="modifyGuest" method="post" action="/BBS/modify.do"  encType="UTF-8">
	<table>
	<tr><td>이름  : </td><td><input type="text" name ="guest_name" value=${guest_name }></td></tr>
	<tr><td>암호변경 : </td><td><input type="password" name ="password"></td></tr>
	</table>
	-------------메시지---------------
	<br><textarea rows="5" cols="30" name="message" >${message }</textarea><br>
	<input type="submit" value="수정">
	<input type="reset" value="다시입력">
	<input type="hidden" name="messageId" value=${param.messageId }>
	<input type="button" value="목록" onClick="location.href='/BBS/view/guestbook.jsp'">
	</form>
	</div>
</body>
</html>