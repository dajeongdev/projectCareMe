<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<style>
.storyMain { margin: 40px; }
</style>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>스토리 메인</title>
</head>
<body>

<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>
<form action="/story/storyForm" method="post">
<div class="storyMain">
	<h3><strong>인기글</strong></h3>
	<hr>
	<div class="album py-5 bg-light">
    <div class="container">
    
	<div class="row">
        <div class="col-md-3">
          <div class="card mb-3 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#55595c"/></svg>
            <div class="card-body">
              <p class="card-text">제목</p>
              <p class="card-text">사용자</p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                  <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                </div>
              </div>
            </div>
          </div>
        </div>
         <div class="col-md-3">
          <div class="card mb-3 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#55595c"/></svg>
            <div class="card-body">
              <p class="card-text">제목</p>
              <p class="card-text">사용자</p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                  <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card mb-3 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#55595c"/></svg>
            <div class="card-body">
              <p class="card-text">제목</p>
              <p class="card-text">사용자</p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                  <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="card mb-3 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#55595c"/></svg>
            <div class="card-body">
              <p class="card-text">제목</p>
              <p class="card-text">사용자</p>
              <p class="card-text">조회수</p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                  <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                </div>
              </div>
            </div>
          </div>
        </div>
       
        

	
	<br>
	<div align="right">
		<button type="button" class="btn btn-outline-dark insert_btn" 
			name="storyForm" onclick="location.href='storyForm'">글쓰기</button>
	</div>
	
	<hr>
	<div>
    <c:forEach items="${list}" var="list">
         <div class="col-md-3">
          <div class="card mb-3 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#55595c"/></svg>
            <div class="card-body">
              <p class="card-text">${list.title}</p>
              <p class="card-text">${list.member_id}</p>
              <p class="card-text">${list.view_count}</p>
              <div class="d-flex justify-content-between align-items-center">
                <div class="btn-group">
                  <button type="button" class="btn btn-sm btn-outline-secondary">View</button>
                  <button type="button" class="btn btn-sm btn-outline-secondary">Edit</button>
                </div>
              </div>
            </div>
          </div>
        </div>
       </c:forEach>
        </div>
        </div>
     </div>
    </div>
	

</div>
</form>

	<form action="view/story/storyBoardSearch">
		<select name="search">
			<option value="0">작성자</option>
			<option value="1">제목</option>
			<option value="2">내용</option>
		</select>
		<input type="text" name="search" size="20" maxLength="40">
		<input type="submit" value="검색">
	</form>
</body>
</html>