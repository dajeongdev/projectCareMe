<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<style>
.story_form { width: 100%; height: 100%; position: relative; }
.container {
	width: 1000px;
	width: 1000px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -250px;
	margin-top: 100px;
}
#hash-search, #content, #title {
	width: 600px;
}
#hash-inbox {
	background: #bdbdbd;
	width: 600px;
	height: 150px;
}
.added-tag {
	background: #82b1ff;
	padding: 5px 5px;
	margin: 5px 5px;
	border-radius: 10%;
	display: inline-block;
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
	  
	$("#hash-inbox").on("click", ".added-tag", function () {
		    $(this).remove();
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
		<div class="story_title">
			<input type="text" id="title" placeholder="제목을 입력해주세요.">
		</div>
		<div class="story_content">
			<textarea id="content" cols="30" rows="5" placeholder="스토리를 들려주세요."></textarea>
			<div id="info-tag">
				<input type="text" id="hash-search" placeholder="태그를 입력해보세요." style="margin-bottom: 0;">
				<div class="tag_selected">
					<div id="hash-inbox">
					</div>
				</div>
			</div>
		</div>
		<button type="submit" class="btn">등    록</button>
	</div>
</div>
</body>
</html>