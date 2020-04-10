<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<style>
#storyMain { margin: 40px; }
</style>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>스토리 메인</title>
</head>
<body>
<form action="storyForm" method="post">
<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>

<div id="storyMain">
	<h3><strong>인기글</strong></h3>
	<hr>
	<div class="card-group">
	  	<div class="card">
		    <img class="card-img-top" src="cat.jpg" alt="Card image cap">
		    <div class="card-body">
		      <h5 class="card-title" name="title">title</h5>
		      <p class="card-text" name="member_id">user</p>
		      <p class="card-text"><small class="text-muted">reg_date</small></p>
		    </div>
		  </div>
		  <div class="card">
		    <img class="card-img-top" src="..." alt="Card image cap">
		    <div class="card-body">
		      <h5 class="card-title" name="title">title</h5>
		      <p class="card-text" name="member_id">user</p>
		      <p class="card-text"><small class="text-muted">reg_date</small></p>
		    </div>
		  </div>
		  <div class="card">
		    <img class="card-img-top" src="..." alt="Card image cap">
		    <div class="card-body">
		      <h5 class="card-title" name="title">title</h5>
		      <p class="card-text" name="member_id">user</p>
		      <p class="card-text"><small class="text-muted">reg_date</small></p>
		    </div>
		  </div>
	</div>

	
	<br>
	<input type="submit" class="btn btn-outline-dark insert_btn" value="글쓰기" onclick="storyForm">
	<hr>

	
		
	<table border="1">
	<c:forEach items="${list}" var="list" >
          
	</c:forEach> 
	</table>
		
	
	
</div>
<a href="/careMe/view/story/storyDetail.jsp">글보기</a>
</form>
</body>
</html>