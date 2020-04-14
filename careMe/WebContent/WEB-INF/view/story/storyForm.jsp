<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
.story_form { width: 100%; height: 100%; position: relative; }
.container {
	width: 1000px;
	width: 1000px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -350px;
	margin-top: 100px;
}
#hash-search, #content, #title {
	width: 700px;
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
#preview p {
	text-overflow: ellipsis;
	overflow: hidden;
}
.preview-box {
	border: 1px solid;
	padding: 5px;
	margin-bottom: 10px;
}
</style>
<title>스토리 글쓰기</title>
<script>

// 해쉬태그 입력
$(function (){
	  $("#hash-search").on("keyup", function (e) {//해시태그 입력칸 이벤트
	    var tag = "";
	    var existed = false;//이미 태그에 올라갔나 확인하기 위함

	    if (e.which == 188 || e.which == 13) {//누른게 쉼표거나 엔터
	      tag = $(this).val().replace(/[\s,]+/g, ""); //쉼표나 엔터 ""으로 바꿔서 tag에 저장
	      $(this).val("");//입력창 비우기

	      $("#hash-inbox span").each(function () { //해시태그 들어간 div 안의 span
	        var name = $(this).find(".htag-name").val();//input hidden의 값
	        if (name == tag) existed = true;//이미 있음
	      });

	      if (tag != "" && !existed) { //태그가 빈문자열이 아니고 이미 올린게 아니면
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

$("#testUploadFile").change(function() {
	var formData = new FormData($("#fileForm")[0]);

	$.ajax({
		type: 'post',
		url: 'resources/img/story',
		data: formData,
		processdata: false,
		dataType: "json",
		contentType: false,
		async: false,
		success : function(data) {
			alert("파일 업로드 성공");
		}, 
		error : function(error) {
			alert("파일 업로드에 실패하였습니다.");
		}
	});
});
</script>
</head>
<body>

<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>
<div class="story_form">
	<div class="container">
		<form name="story_insert" method="post" action="/story/storyDetail" enctype="multipart/form-data">
			<input type="hidden" name="story_board_idx" value="0">
			<input type="text" class="form-control" id="title" name="title" 
				placeholder="제목을 입력해주세요.">
			<div class="story_content">
				<div id="attach">
					<span class="file" id="file">
					<input type="file" name="file filedata" id="file testUploadFile" multiple/>
					</span>
				</div>
				<div id="preview">
				</div>
				<a href="#this" name="delete" class="btn">X</a>
			<div class="form-group">
			 	<textarea class="form-control" name="content"
    			id="content" rows="3" placeholder="스토리를 들려주세요."></textarea>
  			</div>
  			</div>
			<div id="info-tag">
				<input type="text" class="form-control" id="hash-search" placeholder="태그를 입력해보세요." style="margin-bottom: 0;">
				<div class="tag_selected">
					<div id="hash-inbox">
					</div>
				</div>
			</div>
			<div class="btn-group">
				<button type="submit" class="insert_btn btn btn-outline-dark"><a href="#" title="등록" class="btnline">등록</a></button>
				<button type="submit" class="list_btn btn btn-outline-dark">목록</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>