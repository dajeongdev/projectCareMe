<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>스토리 글보기</title>
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
<div id="body">
	<form name="detail" role="form" method="post">
		<input type="hidden" id="story_board_idx" name="story_board_idx"
			value="${detail.story_board_idx}">
	</form>
	<div id="header">
		<h3>${detail.title}</h3>
		<p>${detail.reg_date}</p>
		<p>${update.view_count}</p>
	</div>
	<div>
		<!-- 사진 -->
	</div>
	<div>
		<p>${update.content}</p>
	</div>
	<div class="box_tag">
		<a href="">#강아지</a>
		<a href="">#산책</a>
	</div>
	
	<div id="comment">
		<ol class="comList">
			<c:forEach items="${comList}" var="comList">
				<li>
					<p>
					${comList.member_idx}<br>
					${comList.content}<br>
					<fmt:formatDate value="${comList.reg_date}" pattern="MM-dd"/>
					</p>
					
				</li>
			</c:forEach>
		</ol>
	</div>
		<button type="submit" class="update_btn">수정</button>
		<button type="submit" class="delete_btn">삭제</button>
		<button type="submit" class="list_btn">목록</button>
	
</div>
</body>
</html>