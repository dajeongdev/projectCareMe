<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>스토리 글보기</title>
</head>
<body>
<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>
<div id="body">
	<div>
		<h3>제목</h3>
	</div>
	<div>
		<!-- 사진 -->
	</div>
	<div>
		<p>내용</p>
	</div>
	<div class="box_tag">
		<a href="">#강아지</a>
		<a href="">#산책</a>
	</div>
	<input type="button" value="수정">
	<input type="button" value="삭제"> <!-- 모달 -->
	<input type="button" value="목록">
	<div id="comment">
		
	</div>
</div>
</body>
</html>