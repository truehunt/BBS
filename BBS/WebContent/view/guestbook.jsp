<%@ page language="java" contentType="text/html; charset=UTF-8"
    import="java.util.*"
    import="guestbook.dao.*"
    pageEncoding="UTF-8"
    %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>방명록</title>
</head>
<body>
<h3 align="left">방명록</h3>
<div style="border:1px solid; padding:5px; width:270px;background-color:ivory; text-align:center">
	<span style="font-weight: bold;">[ 방명록 등록 ]</span>
	<form name="addGuest" method="post" action="/BBS/addGuest.do"  encType="UTF-8">
	이름 : <input type="text" name ="guest_name"><br>
	암호 : <input type="password" name ="password"><br>	
	-------------메시지---------------
	<br><textarea rows="5" cols="30" name="message"></textarea><br>
	<input type="submit" value="등록">
	<input type="reset" value="다시입력">
	<input type="hidden" name="command" value="addGuest">
	<input type="reset" value="새로고침" onclick="location.href='/BBS/view/guestbook.jsp'">
	<c:if test="${isLogon }">
		<input type="button" value="메인으로" onClick="location.href='/BBS/loginafter.jsp'">
	</c:if>
	<c:if test="${!isLogon }">
		<input type="button" value="메인으로" onClick="location.href='/BBS/main.jsp'">
	</c:if>
	</form>
	</div>
	<%
		int pageNumber = 1;
		if(request.getParameter("pageNumber")!=null){
			pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
		}
		request.setCharacterEncoding("utf-8");
		MessageList msgList = new MessageList();
		List guestList = new ArrayList();	
		guestList = msgList.message(pageNumber);
	%>
	<c:set var="guestList" value="<%=guestList %>"/>
	<br><table border=1 width=400 align=left>
	<c:forEach var="guest" items="${guestList }">
		<tr  bgcolor="#F8ECE0">
			<td>메시지 번호 : ${guest.messageId}<br>
			손님 이름 :	${guest.name}<br>
			메시지 :${guest.message}<br>
			<a href='/BBS/view/deletePwd.jsp?messageId=${guest.messageId }'>삭제</a>
			<a href='/BBS/view/modifyPwd.jsp?messageId=${guest.messageId }'>수정</a>
			</td>
		</tr>
	</c:forEach> 
		<tr bgcolor="#F6D8CE"><td align="center">
			<c:forEach var="pageNum" begin="1" end="<%=msgList.pageTotalCount() %>">
				<a href="/BBS/view/guestbook.jsp?pageNumber=${pageNum}">[${pageNum}]</a>
			</c:forEach>
		</td></tr>
	</table>
</body>
</html>