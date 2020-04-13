<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>스토리 수정</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	var formObj = $("form[name='readForm']");

	// 수정
	$(".update_btn").on("click", function() {
		if(fn_valiChk()) {
			return false;
		}
		formObj.attr("action", "/story/storyEdit");
		formObj.attr("method", "post");
		formObj.submit();
	})
	// 삭제
	$(".delete_btn").on("click", function() {
		var deleteYN = confirm("삭제하시겠습니까?");
		if(deleteYN == true) {
			formObj.attr("action", "/delete");
			formObj.attr("method", "post");
			formObj.submit();
		}
	})
	//목록
	$(".list_btn").on("click", function() {
		location.href="/story/storyMain";
	});
});
function fn_valiChk() {
	var regForm = $("form[name='insert']) .chk").length;
	for(var i = 0; i < regForm; i++) {
		if($(".chk".eq(i).val() == "" || $(".chk").eq(i).val == null) {
			alert($(".chk").eq(i).attr("title"));
			return true;
		});
	}
}
</script>
</head>
<body>

<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>
<div class="story_form">
	<div class="container">
		<form name="update" role="form" method="post" action="/story/update" enctype="multipart/form-data">
			<input type="hidden" name="story_board_idx" value="${update.story_board.idx}" readonly="readonly">
			<label for="title"></label>
			<input type="text" id="title" name="title" class="chk
				value="${update.title}" placeholder="제목을 입력해주세요.">
			<button type="button" class="btn btn-outline-dark">사진추가</button>
			<div class="story_content">
				<input type="file" name="uploadFiles" multiple/>
				<a href="#this" name="delete" class="btn">삭제</a>
			<div class="form-group">
			 	<label for="content"></label>
    			<textarea class="form-control" name="content" class="chk"
    			id="exampleFormControlTextarea1 content" rows="3" placeholder="스토리를 들려주세요."><c:out value="${update.content}"/></textarea>
  			</div>
  			</div>
			<div id="info-tag">
				<input type="text" id="hash-search" placeholder="태그를 입력해보세요." style="margin-bottom: 0;">
				<div class="tag_selected">
					<div id="hash-inbox">
					</div>
				</div>
			</div>
			<button type="submit" class="update_btn">수정</button>
			<button type="submit" class="delete_btn">삭제</button>
			<button type="submit" class="list_btn">목록</button>
		</form>
	</div>
</div>
</body>