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

<div class="storyMain cover-container d-flex w-100 h-100 mx-auto flex-column bg-ligh">
	<div class="container main-vh-100 pt-3 text-center">
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
	<div align="right">
		<input type="button" class="btn btn-outline-dark insert_btn" 
			name="storyForm" value="글쓰기" onclick="location.href='storyForm'">
	</div>
	
	<hr>
	
	<div>
		<table class="table-responsive">
			<tr>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
				<th>좋아요</th>
			<tr>
			<tbody>
			<c:forEach items="${list}" var="list" >
		          <tr>
		          	<td><a href="storyDetail?story_board_idx=${list.story_board_idx}">"${list.title}"</a></td>
		          	<td><c:out value="${list.member_id}" /></td>
		          	<td><c:out value="${list.reg_date}" /></td>
		          	<td><c:out value="${list.view_count}" /></td>
		          	<td><c:out value="${list.heart}" /></td>
		          </tr>
			</c:forEach> 
			</tbody>
		</table>
	</div>
	
	<form action="view/story/storyBoardSearch">
		<select name="search">
			<option value="0">작성자</option>
			<option value="1">제목</option>
			<option value="2">내용</option>
		</select>
		<input type="text" name="search" size="20" maxLength="40">
		<input type="submit" value="검색">
	</form>
	
	</div>
</div>
</form>
</body>
</html>