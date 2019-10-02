<%@page import="dao.ArticleVO"%>
<%@page import="dao.BoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정</title>
<%
   request.setCharacterEncoding("utf-8");
   int article_no = Integer.parseInt(request.getParameter("article_no"));
   BoardDAO dao = BoardDAO.getInstance();
   ArticleVO bb = dao.selectArticle(article_no, 0);
%>

<script>
   function updateBtn(){
   
      if(frm.title.value==""){
         alert("제목을 입력해주세요");
         return false;
      }
      if(frm.content.value==""){
         alert("내용을 입력해주세요");
         return false;
      }
      frm.action="modArticle.do?article_no=<%=bb.getArticle_no() %>";
      frm.submit();
   }
   
   function move(url) {
         location.href=url;
   }

</script>

<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
</head>
<body>
<h2 align=center >글 수정</h2>
   <form name="frm" method="post">
      <table border solid align = center>
         <tr>
            <td>글번호</td>
            <td><input type = text name="article_no" value="<%=bb.getArticle_no() %>" disabled/></td>
         </tr>
         <tr>
            <td>제목</td>
            <td><input type=text name="title" size = 20 value ="<%=bb.getTitle() %>"></td>
         </tr>
         <tr>
            <td>내용</td>
            <td width = 100 height = 100><textarea name="content" rows = "20" cols = "50" ><%=bb.getContent() %></textarea></td>
         </tr>
          </tr>
         <tr>
            <td colspan=2 align=center>
            <input type=button value="수정" onclick ="updateBtn()">
               <input type="button" value="뒤로" onclick="javascript:location.href='/BBS/viewArticle.do?article_no=<%=bb.getArticle_no() %>'"></td>
         </tr>
      </table>
   </form>
</body>
</html>