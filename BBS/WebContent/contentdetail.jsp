<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
%>
<%
	String name = (String)request.getAttribute("name");
%>
<c:set var="contextPath"  value="${pageContext.request.contextPath}"  />
<head>
<meta charset="UTF-8">
   <title>게시글 보기</title>
   <script type="text/javascript">
   
      <!--목록으로 이동-->
      function move(url) {
  		location.href=url;
  	  }
      
	</script>
</head>
<body>
  <form name="frmArticle" method="post"   enctype="multipart/form-data">
  <h2 align=center>글 상세보기</h2>
  <table border solid align = center>
  <tr>
   <td>글번호</td>
    <td><input type="text" name="article_no" value="${article.article_no }"  disabled />
    <input type="hidden" name="article_no" value="${article.article_no}"  /></td>
     <!--글 수정시 글 번호를 컨트롤러로 전송하기 위해 미리 hidden 태그를 이용해 글 번호를 저장-->
  </tr>
  
  <tr>
   <td>작성자</td>
   <td width = 100><input type="text" value="${article.writer_name }" name="writer_name" disabled/></td>
  </tr>
  
  <tr>
   <td>등록일자</td>
 	<td width = 100><input type=text value="<fmt:formatDate value="${article.regdate}" />" name="regdate" disabled/></td>
  </tr>
  
    <tr>
	   <td>수정일자</td>
	    <td width = 100><input type=text value="<fmt:formatDate value="${article.moddate}" />" name="moddate" disabled /></td>
  </tr>
  
      <tr>
	   <td>조회수</td>
	    <td width = 100><input type=text value="${article.read_cnt}" name="read_cnt" disabled /></td>
  </tr>
  
  <tr>
   <td>제목</td>
    <td width = 100><input type="text" value="${article.title }"  name="title"  id="i_title" disabled /></td>
  </tr>
  
  <tr>
   <td>내용</td>
	<td width = 100 height = 100><textarea id=content name="content" rows = "20" cols = "50" disabled >${article.content}</textarea></td>
  </tr>
    
  <tr  id="tr_btn"    >
   <td colspan=2 align="center">
	  <input type=button value="게시글수정" onClick="location.href='contentdelalterChk.jsp?mode=alter&article_no=${article.article_no }'">
	  <input type=button value="게시글삭제" onClick="location.href='contentdelalterChk.jsp?mode=del&article_no=${article.article_no }'">
	  <input type=button value="목록"  onClick="move('list.do');">
   </td>
  </tr>
 </table>
 </form>
</body>
</html>