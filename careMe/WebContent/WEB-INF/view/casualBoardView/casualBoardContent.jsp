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
			
			<div class="row blog-post">
				<div class="col-md-2">
        			<img align="left" height="100" width="80" src="<%=request.getContextPath()%>/resources/img/dog.jpg">
				</div>
			
				<div class="col-md-10">
      				<h2 class="blog-post-title" align="left"><c:out value="${mlist.title}" /></h2>
        			<p class="blog-post-meta" align="left"><c:out value="${mlist.reg_date}" /> by <c:out value="${mlist.member_id}"/></p>
				</div>
			</div>
			
			<hr>
			
			<div class="row">
       		 	<blockquote>
          			<p style="font:20" align="left">
						<c:out value="${mlist.content}" />
					</p>
        		</blockquote>
			</div>
			<hr>	
				
					
			<!-- 고정되는 하단 -->
			<table align="right">
				<tr height="30">
					<td colspan="4" align="right"><input type="button" value="글수정"
						onClick="document.location.href='casualBoardUpdateForm?question_table_idx=${mlist.question_table_idx}'">
					<input type="button" value="글삭제"
						onClick="document.location.href='deleteCasualArticle?question_table_idx=${mlist.question_table_idx}'">
					<input type="button" value="글목록" onClick="location.href='casualBoard'"></td>
				</tr>
			</table>
			<br>

			
			<!-- comment append -->
			<div align="left">
				<h5 class="uppercase"><c:out value="${commCount}"/> Comments</h5>
				<hr>
			<div>
			<c:forEach var="item" items="${clist}">
			<div>
				<div>
					<p style="strong"  align="left">
						<img height="100" width="80" src="<%=request.getContextPath()%>/resources/img/dog.jpg">
						작성자: <c:out value="${item.member_id}" /> on <i><c:out value="${item.reg_date}" /></i>
					</p>
				</div>
				
				<div align="left" class="card-body">
					<div>
						<p style="font:20">
							<c:out value="${item.content}" />
						</p>
					</div>
				</div>
			</div>
			<hr>
			</c:forEach>


			<!-- comment 작성 -->
				<h5>
					댓글을 달아주세요 
				</h5>

			<form action="casualCommentAdd?question_table_idx=${mlist.question_table_idx}" method="post">
				<textarea name="content" style="width: 900px; height: 100px" rows="3"></textarea>
						<p>
							<input type="submit" name="submit" value="확인"> 
							<input type="hidden" name="member_idx" value="1">
						</p>
			</form>
			</div>
			</div>
		</main>
	</div>



		</div>
	</div>
</body>
</html>