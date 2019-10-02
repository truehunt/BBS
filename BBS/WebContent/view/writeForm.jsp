<%@page import="com.domain.Article2"%>
<%@page import="com.dao.ArticleDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    request.setCharacterEncoding("utf-8");
   String name = request.getParameter("name");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>게시판</title>

<script type="text/javaScript">
function writeSave(){
   
  
   if(writeform.title.value==""){
     alert("제목을 입력하십시요.");
     return false;
   }
   
   if(writeform.content.value==""){
     alert("내용을 입력하십시요.");
     return false;
   }
   
   if(writeform.password.value==""){
        alert(" 비밀번호를 입력하십시요.");        
        return false;
      }
   
   writeform.action="writePro.do";
   writeform.submit();
        
   return true;
 }    
</script>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
</head>
<body>
<center><b>글쓰기</b></center>
<br>
<form method="post" name="writeform">
<input type="hidden" name="name" value="${sessionScope.name}">
<table width="400" border="1" cellspacing="0" cellpadding="0" align="center">
   <tr>
    <td align="right" colspan="2">
       <a href="list.do"> 글목록</a> 
   </td>
   </tr>
   <tr>
    <td  width="70" align="center">이 름</td>
    <td  width="330">
       <input type="text" size="10" maxlength="10" name="writer_name" value="${sessionScope.name }" disabled="disabled" /></td>
  </tr>
  <tr>
    <td  width="70" align="center" >제 목</td>
    <td  width="330">
      <input type="text" size="40" maxlength="50" name="title" value="${article2.title }">
    </td>
  </tr>
 
  <tr>
    <td  width="70" align="center" >내 용</td>
    <td  width="330" >
     <textarea name="content" rows="13" cols="40">${article2.content }</textarea> </td>
  </tr>
    <tr>
    <td  width="70" align="center" >비밀번호</td>
    <td  width="330" >
     <input type="password" size="8" maxlength="12" name="password" value="${article2.password }"> 
    </td>
  </tr>

<tr>      
 <td colspan=2 align="center"> 
  <input type="button" value="글쓰기" onclick ="writeSave()">  
  <input type="reset" value="다시작성">
  <input type="button" value="목록보기" onClick="location.href='list.do'">
</td></tr></table>
</form>            
</body>
</html>      