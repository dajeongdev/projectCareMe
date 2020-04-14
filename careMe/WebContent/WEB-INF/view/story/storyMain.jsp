<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<style>
.storyMain { margin: 40px; }
.card-text, .card-heart, .card-count { font-size: 15px; }
.searching { margin: 0 auto; }
.card-s { position:none; }
.rounded-circle { position: inline;}
.card-t { position: block; }
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
	<h3><strong>인기글</strong></h3>
	<hr>
	<div class="album py-5 bg-light">
    <div class="container">
    
	<div class="row">
	<c:forEach items="${hlist}" var="hlist">
        <div class="col-md-4">
          <div class="card mb-4 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="170" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#55595c"/></svg>
            <div class="card-body">
              <svg class="bd-placeholder-img rounded-circle" width="40" height="40" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#777"/></svg>
              <h5 class="card-text">${hlist.title}</h5>
              <p class="card-text">${hlist.member_id }</p>
              <div class="d-flex justify-content-between align-items-center">
               <div class="btn-group">
                  <i class="fas fa-heart"></i>&nbsp<span class="card-heart">${hlist.heart}</span>&nbsp&nbsp
              	  <i class="far fa-comments"></i>&nbsp<span class="card-count">${hlist.view_count}</span>
                </div>
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
    <c:forEach items="${list}" var="list">
         <div class="col-md-4">
          <div class="card mb-4 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="170" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#55595c"/></svg>
            <div class="card-body">
              <svg class="bd-placeholder-img rounded-circle" width="40" height="40" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#777"/></svg>
              <h5 class="card-text">${list.title}</h5>
              <p class="card-text">${list.member_id}</p>
              <div class="btn-group">
                  <i class="fas fa-heart"></i>&nbsp<span class="card-heart">${list.heart}</span>&nbsp&nbsp
              	  <i class="far fa-comments"></i>&nbsp<span class="card-count">${list.view_count}</span>
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
	<form class="searching" action="view/story/storyBoardSearch">
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