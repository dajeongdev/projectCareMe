<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
.container {
	width: 1000px;
	width: 1000px;
	position: absolute;
	margin: 40px;
}
#hash-search, #content, #title, .custom-file-label {
	width: 700px;
}
.custom-file-label {
	position: relative;
	margin-botton: 40px;
}
#content {
	height: 300px;
}
#hash-inbox { 
	margin-top:2px;
	background: #bdbdbd;
	width: 700px;
	height: 100px;
}
.added-tag {
	background: #82b1ff;
	padding: 5px 5px;
	margin: 5px 5px;
	border-radius: 10%;
	display: inline-block;
}
.btn-group { 
	float: right; 
	margin-top: 10px;
}
#preview img {
	width: 100px;
	height: 100px;
}
</style>
<title>스토리 글쓰기</title>
<script>

// 해쉬태그 입력
$(function (){
	  $("#hash-search").on("keyup", function (e) {// 해시태그 입력칸 이벤트
	    var tag = "";
	    var existed = false;// 이미 태그에 올라갔나 확인하기 위함

	    if (e.which == 188 || e.which == 13) {// 누른게 쉼표거나 엔터
	      tag = $(this).val().replace(/[\s,]+/g, ""); // 쉼표나 엔터 ""으로 바꿔서 tag에 저장
	      $(this).val("");// 입력창 비우기

	      $("#hash-inbox span").each(function () { // 해시태그 들어간 div 안의 span
	        var name = $(this).find(".htag-name").val();// input hidden의 값
	        if (name == tag) existed = true;// 이미 있음
	      });

	      if (tag != "" && !existed) { // 태그가 빈문자열이 아니고 이미 올린게 아니면
	        $("#hash-inbox").append(
	            '<span class="added-tag">#' +
	            tag + '<a href="javascript:;"> X</a>' +
	            '<input type="hidden" class="htag-name" name="recipe[hashtags][][name]" value="' + tag + '" >' +
	            '</span> '
	        );
	      }
	    }
	  });
});

</script>
</head>
<body>

<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>
<div class="story_form col-md-4-order-md-2 mb-4">
	<div class="container">
		<form name="insertForm" method="POST" action="storyFormAdd" enctype="multipart/form-data">
			<input type="hidden" name="story_board_idx" value="0">
			<input type="hidden" name="member_idx" value="1">
			<div class="story_content">
				<input type="text" class="form-control" id="title" name="title" 
				placeholder="제목을 입력해주세요.">
				<input type="file" name="file" id="file customFile" class="custom-file-input" multiple/>
				<label class="custom-file-label" for="customFile">사진을 선택해주세요.</label>
				<div id="preview">
				</div>
			<div class="form-group">
			 	<textarea class="form-control" name="content"
    			id="content" rows="3" placeholder="스토리를 들려주세요."></textarea>
  			</div>
  			</div>
			<!-- <div id="info-tag">
				<input type="text" class="form-control" id="hash-search" placeholder="태그를 입력해보세요." style="margin-bottom: 0;">
				<div class="tag_selected">
					<div id="hash-inbox">
					</div>
				</div>
			</div> -->
			<input type="hidden" name="tag_idx" value="3">
			<div class="btn-group">
				<button type="submit" class="insert_btn btn btn-outline-dark">등록</button>
				<button type="submit" class="list_btn btn btn-outline-dark" OnClick="location.href='storyDetail'">목록</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>