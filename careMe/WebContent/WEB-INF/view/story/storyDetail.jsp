<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<% String fullName = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/careMe/"; %>
<c:set var="fullName" value="<%=fullName%>" />
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>스토리 글보기</title>
<style>
@font-face { font-family: 'GmarketSansMedium'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'GmarketSansBold'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff'); font-weight: normal; font-style: normal; }
.whole { margin: 0 auto !important; padding: 0 !important; font-family: 'GmarketSansMedium';}
.detail-form { 
	width: 1000px;
	height: 1100px;  
	margin: 40px;
	font-size: 16px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -500px;
	margin-top: -330px;
}
h3 { font-family: 'GmarketSansBold';}
.container { margin:0 auto;}
hr { width: 700px; }
.container, .input-group, .box_tag { width: 700px; }
.header > div { float: left; margin-bottom: 20px; }
.header:after, .comL:after { clear: both; content: ''; display: block; }
.comL > div { float: left; margin: 10px; }
.profile { margin-right: 10px; }
.comId { font-size: 18px; font-weight: 500;}
.btn-group { float: right;}
.input-group { margin-top: 10px;}
.updateCom, .deleteCome { float: right;}
.content { font-size: 20px; margin-top: 20px;}
.content-heart {float: right; color: red;}
.hashTag { 
	background: #82b1ff;
	padding: 5px 5px;
	margin: 5px 5px;
	border-radius: 10%;
	display: inline-block;
}
</style>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<script>
function delete(story_board_idx) {
	if(confirm("정말 삭제하시겠습니까?") == true) {
		location.href="delete?story_board_idx=" + story_board_idx;
	}
};
function deleteCom(story_comment_idx) {
	if(confirm("정말 삭제하시겠습니까?") == true) {
		location.href="delete?story_comment_idx=" + story_comment_idx;
	}
}
$(function(){
	$("#heart").click(function(){
		var idx=$("#heartInfo").data("idx");
		var url="addSubComHeart?story_comment_idx="+idx;
		$.ajax({
			type:"GET",
			url:url,
			dataType:"json"
			}).done(function(currentHeart){
				$("#heartCount").append(1);
				}).fail(function(e) {
				alert(e.responseText);
			});
	});
});

</script>
</head>
<body>
<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>
<div class="whole">
	<div class="detail-form">
	<div class="container">
	<form name="readForm" >
		<h3><strong>펫스토리</strong></h3>
		<hr>
			<div class="header">
				<div class="profile">
					<svg class="bd-placeholder-img rounded-circle" width="40" height="40" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#777"/></svg>
				</div>
				<div class="rest">
				<h4><c:out value="${dto.title}"/></h4>
				<span class="date"><c:out value="${dto.reg_date}"/></span>&nbsp
				<span class="view"><i class="fas fa-eye"></i><c:out value="${dto.view_count}"/></span>
				<span class="heart"><i class="fas fa-heart"></i>&nbsp<c:out value="${dto.heart}"/></span>
				<input type="hidden" name="member_idx" id="member_idx" value="${info.member_idx}">
				</div>
			</div>
			<div class="img">
				<img width="700" height="500" src="${fullName}${fileDto[0].file_path}">
			</div>
			<div>
				<p class="content"><c:out value="${dto.content}"/><c:out value="${info.member_id}"/><span class="content-heart"><i class="fas fa-heart fa-2x"></i></span></p>
			</div>
			<div id="tag-list">
				<c:forEach var="taging" items="${tags}">
					<span class="hashTag">#<c:out value="${taging.tag_name}"/></span>
				</c:forEach>
			</div>
				<div class="btn-group">
					<button type="button" class="update_btn btn btn-outline-dark" onClick="document.location.href='storyEdit?story_board_idx=${dto.story_board_idx}'">수정</button>
					<button type="button" class="delete_btn btn btn-outline-dark delete_btn" onClick="delete('${dto.story_board_idx}')">삭제</button>
					<button type="button" class="list_btn btn btn-outline-dark" onClick="location.href='storyMain?currentPage=1'">목록</button>
				</div>
		</form>
		
		<br>
		<div class="bottom">
		<br>
		<hr>
			<div class="com">
				댓글 <c:out value="${comCount}"/>
				
				<div>
					<form name="insertCom" action="insertCom?story_board_idx=${dto.story_board_idx}" method="POST">
						<div class="input-group mb-3">
						  <input type="text" name="content" class="form-control comm" placeholder="댓글을 입력해주세요.">
						  <div class="input-group-append">
						    <input type="submit" name="submit" value="등록" class="btn btn-outline-secondary">
						  </div>
						</div>
					</form>
				</div>
				<c:forEach items="${comList}" var="coms" varStatus="status">
					<div class="comL">
						<div class="profile">
							<svg class="bd-placeholder-img rounded-circle" width="40" height="40" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#777"/></svg>
						</div>
						<div>
							<span class="comId"><c:out value="${coms.member_id}"/></span>&nbsp;&nbsp;<c:out value="${coms.content}"/><br>
							<c:out value="${coms.reg_date}"/>
							<span id="heartInfo">
							<span id="heartDiv${status.index}">
								<label for="heart${coms.story_comment_idx}"><span id="count${coms.story_comment_idx}">${coms.heart}</span><i class="fas fa-heart" onclick="testFunction(${coms.story_comment_idx})"></i></label>
								<button id="heart${coms.story_comment_idx}" style="display:none" onclick="testFunction(${coms.story_comment_idx})"></button>
							</span>
							</span>
							<c:if test="">
							<input type="hidden" name="member_idx" value="${info.member_idx}">
							<span class="updateCom"><i class="fas fa-edit"></i></span>
							</c:if>
							<span class="deleteCom"><i class="fas fa-trash-alt" onClick="deleteCom('${coms.story_comment_idx}')"></i></span>
						</div>
					</div>
				</c:forEach>

			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>