<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style>
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
}
@media (min-width: 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}
#storyMain {
	margin: 30px;
}

</style>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>스토리 메인</title>
</head>
<body>

<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>

<div id="storyMain">
	<h3><strong>인기글</strong></h3>
  <div class="album py-5 bg-light">
    <div class="container">

      <div class="row">
        <div class="col-md-4">
          <div class="card mb-4 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
            <div class="card-body">
            <svg class="bd-placeholder-img rounded-circle" width="50" height="50" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 50x50"><title>Placeholder</title><rect width="100%" height="100%" fill="#777"/>
	          <text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>
              <p class="card-text">content</p>
              <p class="card-user">user01</p>
              <div class="d-flex justify-content-between align-items-center">
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card mb-4 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
            <div class="card-body">
            <svg class="bd-placeholder-img rounded-circle" width="50" height="50" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 50x50"><title>Placeholder</title><rect width="100%" height="100%" fill="#777"/>
	          <text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>
              <p class="card-text">content</p>
              <p class="card-user">user01</p>
              <div class="d-flex justify-content-between align-items-center">
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card mb-4 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
            <div class="card-body">
            <svg class="bd-placeholder-img rounded-circle" width="50" height="50" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 50x50"><title>Placeholder</title><rect width="100%" height="100%" fill="#777"/>
	          <text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>
              <p class="card-text">content</p>
              <p class="card-user">user01</p>
              <div class="d-flex justify-content-between align-items-center">
              </div>
            </div>
          </div>
        </div>
        </div>
		</div>
		</div>

	
	<br>
	<button type="button" class="btn btn-outline-dark insert_btn" onclick="location.href='storyForm'">글쓰기</button>
	<hr>

	<div class="album py-5 bg-light">
	    <div class="container">
	    
	    <c:forEach items="${list}" var="list" >
		<div class="row">
        <div class="col-md-4">
          <div class="card mb-4 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
            <div class="card-body">
            <svg class="bd-placeholder-img rounded-circle" width="50" height="50" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 50x50"><title>Placeholder</title><rect width="100%" height="100%" fill="#777"/>
	          <text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>
              <p class="card-text">content</p>
              <p class="card-user">user01</p>
              <div class="d-flex justify-content-between align-items-center">
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card mb-4 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
            <div class="card-body">
            <svg class="bd-placeholder-img rounded-circle" width="50" height="50" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 50x50"><title>Placeholder</title><rect width="100%" height="100%" fill="#777"/>
	          <text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>
              <p class="card-text">content</p>
              <p class="card-user">user01</p>
              <div class="d-flex justify-content-between align-items-center">
              </div>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="card mb-4 shadow-sm">
            <svg class="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: Thumbnail"><title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"/><text x="50%" y="50%" fill="#eceeef" dy=".3em">Thumbnail</text></svg>
            <div class="card-body">
            <svg class="bd-placeholder-img rounded-circle" width="50" height="50" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 50x50"><title>Placeholder</title><rect width="100%" height="100%" fill="#777"/>
	          <text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>
              <p class="card-text">content</p>
              <p class="card-user">user01</p>
              <div class="d-flex justify-content-between align-items-center">
              </div>
            </div>
          </div>
        </div>
		</div>
	    </c:forEach> 
   
	      </div>
	   </div>
		
<%-- 		<tr>
			<td>${list.story_board_idx}</td>
			<td><a href='<c:url value="/storyDetail/${story_board_idx}"/>'>${list.title}</a></td>
			<td>${list.writer}</td>
			<td>${list.reg_date}</td>
			<td>${list.view_count}</td>
		</tr> --%>
		
	
	
</div>
<!-- <div class="row">
	<div class="col-md-4">
		<article class="card mb-4">
			<a class="" href="#"></a> 전체 링크
			<div class="card-body"> 사진 부분
				<img src="..." class="card-img-top" alt="...">
				사진 위의 아이콘
				<span class="">이 안에 svg 아이콘, 조회수/하트수</span> 
				<span class=""></span>
			</div>
			<div class="card-body"> 작성자 주소(페이지)
				<a class="" href=""></a> 작성자 프로필 이미지
				<p class="card-title">산책가자!</p> 글제목
				<span class="card-writer">user01</span> 
			</div>
		</article>
	</div>
</div> -->
<a href="/careMe/view/story/storyDetail.jsp">글보기</a>

</body>
</html>