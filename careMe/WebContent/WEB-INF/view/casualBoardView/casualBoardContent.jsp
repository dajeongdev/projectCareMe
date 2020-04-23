<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="Spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="Form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<Spring:url value="/resources/img/profile_dog.jpg" var="default_image" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false" />
<title>메인 화면</title>
<% String fullName = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/careMe/"; %>
<c:set var="fullName" value="<%=fullName%>" />

<script type="text/javascript">

function deleteArticle(question_table_idx){
        if(confirm("정말 삭제하시겠습니까?")==true){
            location.href="deleteCasualArticle?question_table_idx="+question_table_idx;
        }
    };
</script>

<script type="text/javascript">

$(function(){
	$("#heart").click(function(){
		var idx=$("#heartInfo").data("idx");
		var url="updateHeart?question_board_comment_idx="+idx;
		$.ajax({
			type:"GET",
			url:url,
			dataType:"json"})
			.done(function(currentHeart){
				$("#heartCount").append(1);

				}).fail(function(e) {
				alert(e.responseText);
			});
	});
});

	var testFunction = function (idx) {
		var url="updateHeart?question_board_comment_idx="+idx;
		$.ajax({
			type:"GET",
			url:url,
			dataType:"json"})
			.done(function(currentHeart){
				$("#count"+idx).html(currentHeart);
				}).fail(function(e) {
				alert(e.responseText);
		});
	}

</script>

<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>

</head>
<body>
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />
	<div class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light">
	<div class="container min-vh-100 pt-3 text-center">


		<div class="row">
			<main role="main" class="col-lg-12 px-4">
			<h2 align="left">고민 나누기</h2>
			<p></p>

			<!-- 게시글 본문 -->
			
		<div class="row card border-dark ">
			<div class="card">
  				
  				<div class="card-header" >
  					<h2 class="blog-post-title" align="left"><c:out value="${mlist.title}" /></h2>
  				</div>
  				

		  		<div class="row card-body" style="height:auto;">
  					<div class="col-md-3">
	    				<div>
	    	   			<svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 140x140">
	    	   		 	<rect width="100%" height="100%" fill="#777">
	        			</rect><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>
        					<h2 class="card-title"><c:out value="${mlist.member_id}"/></h2>
        					<p class="card-text">written on<br><c:out value="${mlist.reg_date}" /></p>
  						</div>	
  			
  						<div>
  							<div id="tag-list">
								<c:forEach var="taging" items="${tlist}">
									<span class="hashTag" data-idx="${taging.tag_idx}">#<c:out value="${taging.tag_name}"/></span>
								</c:forEach>
							</div>
  						</div>
  			
  					</div>
  				
  					<div class="col-md-9 shadow-sm">
  						<div>
  							<c:if test="${flist.size()>0}">
  								<c:forEach var="flist" items="${flist}">
  									<div class="img">
  										<img style="float:left" height="500" border="1px" src="${fullName}${flist.file_path}">
  									</div>
  								</c:forEach>
  							</c:if>
  						</div>
  						<br>
  						<div>
  							<p align="left">
								${mlist.content}
							</p>
        				</div>
					</div>
				</div>
  				
  			</div>
		</div>
		
		
		<p></p>				
			<!-- 고정되는 하단 -->
			<table align="right">
				<tr height="30">
					<td colspan="4" align="right">
					<c:if test="${sc.memberDto.member_idx==mlist.member_idx}">
					<button type="button" class="btn btn-dark btn-sm" onclick="document.location.href='casualBoardUpdateForm?question_table_idx=${mlist.question_table_idx}'">글수정</button>
					<button type="button" class="btn btn-dark btn-sm" onclick="deleteArticle('${mlist.question_table_idx}')">글삭제</button>
					</c:if>
					<button type="button" class="btn btn-dark btn-sm" onClick="location.href='casualBoard?currentPage=1'">글목록</button>
					</td>
				</tr>
			</table>
			<br>

			
			<!-- comment append -->
			<div align="left">
				<h5 class="uppercase"><c:out value="${commCount}"/> Comments</h5>
				<hr>
			<div>
			
			<c:forEach var="item" items="${clist}" varStatus="status">
			<div class="row">
				
				<div class="card border-dark col-md-3" align="center" height="auto">
  				<p></p>
  					<div class="py-30">
	  					<svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 140x140">
		    	   		<rect width="100%" height="100%" fill="#777">
		        		</rect><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>
		        	       
        				<h2 class="card-title"><c:out value="${item.member_id}"/></h2>
        
        				<p class="card-text">written on<br><c:out value="${item.reg_date}" /></p>
        			</div>
        		<p></p>
        		</div>
				
				<div class="card border-dark col-md-9">
  					<div class="row card-body my-3 p-3 bg-white rounded shadow-sm">
    					<blockquote>
          					<p style="font:20; white-space: normal;" align="left">
							<c:out value="${item.content}" />
							</p>
        				</blockquote>
					</div>
					<div class="row" id="heartInfo" data-idx="${item.question_board_comment_idx}">
					<div class="col-md-2">
						<div id="heartDiv${status.index}" class="col-md-1" align="left">
							<label for="heart${item.question_board_comment_idx}"><span id="count${item.question_board_comment_idx}">${item.heart}</span>&nbsp;<i class="fas fa-heart"></i></label>
							<button id="heart${item.question_board_comment_idx}" onclick="testFunction(${item.question_board_comment_idx})" style="display:none"></button>
						</div>
					</div>	
					<div class="col-md-7"></div>	
						<div class="col-md-3" align="right">
						<c:if test="${sc.memberDto.member_idx==item.member_idx}">
						<input type="button" class="btn btn-dark btn-sm" value="댓글 수정"
							onClick="">
						<input type="button" class="btn btn-dark btn-sm" value="댓글 삭제"
							onClick="">
						</c:if>	
						</div>
					</div>
					<p></p>
				</div>
			</div>
			<p></p>
			
			<hr>
			</c:forEach>

			<!-- comment 작성 -->
			<div class="col-lg-12" style="width:100%;">
				<h5>
					댓글을 달아주세요 
				</h5>

			<form action="casualCommentAdd?question_table_idx=${mlist.question_table_idx}" method="post">
				<textarea name="content" style="width: 100%; height: 100px" rows="3"></textarea>
						<div class="col-md-12" align="right">
							<input class="btn btn-dark btn-sm" type="submit" name="submit" value="확인"> 
							<input type="hidden" name="member_idx" value="${sc.memberDto.member_idx}">
						</div>
			</form>
			</div>
			</div>
			</div>
		</main>
	</div>



		</div>
	</div>
</body>
</html>