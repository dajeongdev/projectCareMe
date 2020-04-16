<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="Spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<% String fullName = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/careMe/"; %>
<style>
.storyMain { margin: 40px; }
.card-text, .card-heart, .card-count { font-size: 15px; }
.part > div { float: left; }
.part:after { clear: both; content:''; display:block; }
.profile { margin-right: 10px;}
</style>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>스토리 메인</title>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>

<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>

<div class="storyMain">
	<form action="/story/storyForm" method="post">
	<h3><strong>인기 스토리</strong></h3>
	<hr>
	<div class="album py-5 bg-light">
    <div class="container">
    
	<div class="row">
	<c:forEach items="${hlist}" var="hlist">
        <div class="col-md-4">
          <div class="card mb-4 shadow-sm"  onClick="location.href='storyDetail?story_board_idx=${hlist.story_board_idx}'">
            <img width="100%" height="170" class="img" src="${fullName}${fList.file_path}">
            <div class="card-body">
             <div class="part">
              <div class="profile">
               <svg class="bd-placeholder-img rounded-circle" width="40" height="40" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#777"/></svg>
              </div>
              <div class="content">
               <h5 class="card-text">${hlist.title}</h5>
               <span class="card-text">${hlist.member_id}</span>
               <i class="fas fa-heart"></i>&nbsp<c:out value="${hlist.heart}"/>&nbsp&nbsp
               <i class="far fa-comments"></i>&nbsp<c:out value="${hlist.view_count}"/>
              </div>
              </div>
              <div class="d-flex justify-content-between align-items-center">
               
              </div>
            </div>
          </div>
        </div>
     	</c:forEach>
       </div>
        

	
	<br>
	<div align="right">
		<button type="button" class="btn btn-outline-dark insert_btn" 
			name="storyForm" onclick="location.href='storyForm'">글쓰기</button>
	</div>
	
	<hr>
	<div>
	<div class="row">
    <c:forEach items="${list}" var="item">
         <div class="col-md-4">
          <div class="card mb-4 shadow-sm" onClick="document.location.href='storyDetail?story_board_idx=${item.story_board_idx}'">
            <img width="100%" height="170" class="img" src="${fullName}${fList.file_path}">
            <div class="card-body">
            <div class="part">
             <div class="profile">
              <svg class="bd-placeholder-img rounded-circle" width="40" height="40" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#777"/></svg>
              </div>
              <div class="content">
              <h5 class="card-text">${item.title}</h5>
              <span class="card-text">${item.member_id}</span>
              <i class="fas fa-heart"></i>&nbsp<span class="card-heart">${item.heart}</span>&nbsp&nbsp
              <i class="far fa-comments"></i>&nbsp<span class="card-count">${item.view_count}</span>
              </div>
              </div>
              
              <div class="d-flex justify-content-between align-items-center">
              </div>
            </div>
          </div>
        </div>
       </c:forEach>
        </div>
        </div>
     </div>
    </div>
	
	</form>
	<form class="searching" action="/view/story/storyBoardSearch">
		<select name="search">
			<option value="0">작성자</option>
			<option value="1">제목</option>
			<option value="2">내용</option>
		</select>
		<input type="text" name="search" size="20" maxLength="40">
		<input type="submit" value="검색">
	</form>
</div> 


	
</body>
</html>