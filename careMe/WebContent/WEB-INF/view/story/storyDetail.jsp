<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>스토리 글보기</title>
<style>
.container { 
	width: 1000px; 
	height: 1000px; 
	position: absolute; 
	margin: 40px;
	font-size: 16px;
}
hr { width: 1000px; }
.container, .input-group { width: 700px; }
.header > div { float: left; margin-bottom: 20px; }
.header:after, .comL:after { clear: both; content: ''; display: block; }
.comL > div { float: left; margin: 10px; }
.profile { margin-right: 10px; }
.comId { font-size: 18px; font-weight: 500;}
.btn-list { float: right; }
.input-group { margin-top: 10px;}
</style>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
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
	var regForm = $("form[name='detail']) .chk").length;
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
<div class="detail-form">
	<form name="detail" >
	<div class="container">
		<div class="top">
		<h3><strong>펫스토리</strong></h3>
		<hr>
			<div class="header">
				<div class="profile">
					<svg class="bd-placeholder-img rounded-circle" width="40" height="40" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#777"/></svg>
				</div>
				<div class="rest">
				<h4><c:out value="${dList.title}"/></h4>
				<span class="date"><c:out value="${dList.reg_date}"/></span>
				<span class="view"><c:out value="${dList.view_count}"/></span>
				<span class="heart"><i class="fas fa-heart"></i><c:out value="${dList.heart}"/></span>
				</div>
			</div>
			<div class="img">
				<img width="700" height="500" src="">
			</div>
			<div>
				<p><c:out value="${dList.content}"/></p>
			</div>
			<div class="box_tag">
				<a href="">#강아지</a>
				<a href="">#산책</a>
			</div>
			<div class="btn-list">
				<button type="button" class="update_btn btn btn-outline-dark" onClick="document.location.href='/story/storyEdit?story_board_idx=${list.story_board_idx}'">수정</button>
				<button type="button" class="delete_btn btn btn-outline-dark" onClick="document.location.href='delete/${list.story_board_idx}'">삭제</button>
				<button type="button" class="list_btn btn btn-outline-dark" onClick="document.location.href='storyMain'">목록</button>
			</div>
		</div>
		<div class="hr"><hr></div>
		<div id="bottom">
			<div class="com">
				댓글 <c:out value="${comCount}"/>
				
				<div>
					<form name="insertCom" method="POST" action="insertCom">
						<input type="hidden" name="member_idx">
						<div class="input-group mb-3">
						  <input type="text" class="form-control comm" placeholder="댓글을 입력해주세요.">
						  <div class="input-group-append">
						    <button class="btn btn-outline-secondary" type="button">등록</button>
						  </div>
						</div>
					</form>
				</div>
				<c:forEach items="${comList}" var="coms">
					<div class="comL">
						<div class="profile">
							<svg class="bd-placeholder-img rounded-circle" width="40" height="40" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#777"/></svg>
						</div>
						<div>
							<span class="comId"><c:out value="${coms.member_id}"/></span>&nbsp&nbsp<c:out value="${coms.content}"/><br>
							<c:out value="${coms.reg_date}"/>&nbsp<i class="fas fa-heart"></i>&nbsp<c:out value="${coms.heart}"/>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	</form>
</div>
</body>
</html>