<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>스토리 메인</title>
</head>
<body>

<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>

<h1>First Story!</h1>
<button type="button" onclick="location.href='storyForm'">글쓰기</button>
<div class="row">
	<div class="col-md-4">
		<article class="card mb-4">
			<a class="" href="#"></a> <!-- 전체 링크 -->
			<div class=""> <!-- 사진 부분 -->
				<img class="" src=""> 
				<!-- 사진 위의 아이콘 -->
				<span class=""><!-- 이 안에 svg 아이콘, 조회수/하트수 --></span> 
				<span class=""></span>
			</div>
			<div class=""> <!-- 작성자 주소(페이지) -->
				<a class="" href=""></a> <!-- 작성자 프로필 이미지 -->
				<p class=""></p> <!-- 글제목 -->
				<span class="writer"></span> 
			</div>
		</article>
	</div>
</div>
<a href="/careMe/view/story/storyDetail.jsp">글보기</a>

</body>
</html>