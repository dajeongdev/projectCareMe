<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="Spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="Form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false" />
<title>메인 화면</title>
</head>
<body>
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />
	<div class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light">
	<div class="container min-vh-100 pt-3 text-center">

		<div class="row">
			<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
			<h2 align="left">고민 상담</h2>
			<p></p>



			<!-- 게시글 본문 -->
			<div align="left" class="card">
				<div class="card-header">
					<p style="strong">
						<c:out value="${list.title}" />
					</p>
					<p align="right">
						<i><c:out value="${list.reg_date}" /> by <c:out	value="${list.member_id}"/></i>
						<img height="100" width="80" src="<%=request.getContextPath()%>/resources/img/dog.jpg">
					</p>
				</div>
				
				<div align="left" class="card-body">
					<div>
						<p style="font:20">
							<c:out value="${list.content}" />
						</p>
					</div>
				</div>
			</div>
					

			<!-- 고정되는 하단 -->
			<table align="right">
				<tr height="30">
					<td colspan="4" align="right"><input type="button" value="글수정"
						onClick="document.location.href='casualBoardUpdateForm?question_table_idx=${list.question_table_idx}'">
					<input type="button" value="글삭제"
						onClick="document.location.href='deleteCasualArticle?question_table_idx=${list.question_table_idx}'">
					<input type="button" value="글목록" onClick="location.href='casualBoard'"></td>
				</tr>
			</table>
			<br>
			
			<!-- comment append -->
			<c:forEach var="commentList" items="${commentList}">
			<div align="left" class="card">
				<div class="card-header">
					<p style="strong" align="right">
						<img height="100" width="80" src="<%=request.getContextPath()%>/resources/img/dog.jpg">
						작성자: <c:out value="${commentList.member_id}" /> on <i><c:out value="${commentList.reg_date}" /></i>
					</p>
				</div>
				
				<div align="left" class="card-body">
					<div>
						<p style="font:20">
							<c:out value="${commentList.content}" />
						</p>
					</div>
				</div>
			</div>
			</c:forEach>


			<!-- comment 작성 -->
			<div align="left" class="comments">
				<h5 class="uppercase">0 Comments</h5>
				<hr>
			<div id="respond" class="comment-respond">
				<h5 id="reply-title" class="comment-reply-title">
					댓글을 달아주세요 
				</h5>

			<form action="view/casualBoardView/casualCommentAdd?question_board_idx='${list.question_board_idx}'" method="post" id="commentform" class="comment-form" novalidate="">
				<textarea name="comment" style="width: 900px; height: 100px" placeholder="Your Comment Here" id="comment" aria-required="true" rows="3"></textarea>
						<p class="form-submit">
						<input type="submit" id="submit" value="확인"> 
						<input type="hidden" name="member_idx" value="1">
						<input type="hidden" name="question_board_idx" value="${list.question_board_idx}">
						</p>
			</form>
			</div>
			</div>
		</main>
	</div>


<!-- 고정되는 상단 -->
<!--<div align="left"><h3><i><c:out value="${list.title}" /></i>
</h3><p>Views:<c:out value="${list.view_count}"/> ArticleNo. <c:out value="${list.question_table_idx}"/></p>
<h4><i><c:out value="${list.reg_date}" /> by <c:out value="${list.member_id}" /></i></h4></div>-->

<!-- <table border="1"><tr><td align="center" width="125"><img height="100" width="80" src="<%=request.getContextPath()%>/resources/img/dog.jpg">
<strong>member_Id</strong><br> <input type="button" value="To My Page"></td><td align="left" width="375" colspan="3"><pre>
<c:out value="${list.content}" /></pre></td></tr>  -->


		</div>
	</div>
</body>
</html>