<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@ taglib prefix="Spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="Form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="EUC-KR">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>게시판</title>
</head>
<body>
<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>
<p>전문 게시판(전체 글: <c:out value="${countPro}"/> )</p>

<table class="con" id="posts"> 
<!-- 맨 윗 줄 -->
    <tr height="30"> 
      <td align="center"  width="50"  >번 호</td> 
      <td align="center"  width="250" >제   목</td> 
      <td align="center"  width="100" >작성자</td>
      <td align="center"  width="150" >작성일</td> 
      <td align="center"  width="50" >조 회</td> 
    </tr>
   
<!-- 글 들어가는 곳 -->
	<c:forEach var="item" items="${listPro}">

	 <tr>
    	<td align="center"  width="50"  ><c:out value="${item.question_table_idx}"/></td> 
      	<td align="left"    width="250" ><a href="">"${item.title}"</a></td> 
     	<td align="center"  width="100" ><c:out value="${item.member_id}"/></td>
     	<td align="center"  width="150" ><c:out value="${item.brd_reg_date}"/></td> 
      	<td align="center"  width="50" ><c:out value="${item.views}"/></td> 	
     </tr>

	</c:forEach>
<!-- 게시판 글쓰기 -->
	<tr>
	  <td align="right"><a href="writeForm">글쓰기</a></td>
	  <td colspan="4" width="50" align="center">number</td>
	</tr>
</table>

<!-- 게시판 검색 -->
<form action="searchPro">
	<select name="searchn">
		<option value="0">작성자</option>
		<option value="1">제목</option>
		<option value="2">내용</option>
	</select>

	<input type="text" name="searchKeyword" size="15" maxlength="50" /> 
	<input type="submit" value="검색" />
</form>
</body>
</html>