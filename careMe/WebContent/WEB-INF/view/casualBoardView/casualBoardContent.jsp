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
			<main role="main" class="col-lg-12 px-4">
			<h2 align="left">고민 상담</h2>
			<p></p>

			<!-- 게시글 본문 -->
			
		<div class="row card border-dark ">
			<div class="card">
  				
  				<div class="card-header">
  					<h2 class="blog-post-title" align="left"><c:out value="${mlist.title}" /></h2>
  				</div>
  				

		  		<div class="row card-body">

  				
  					<div class="col-md-3" style="height: auto;"> 
  						<div class="card shadow-sm">
         					<div class="card-header">
         					<img align="left" class="bd-placeholder-img card-img-top" src="<%=request.getContextPath()%>/resources/img/dog.jpg"/>
            				</div>
            				<div class="card-body">
              					<h4 class="card-title"><c:out value="${mlist.member_id}"/></h4>
    		  					<p class="card-text">written on<br><c:out value="${mlist.reg_date}" /></p>
            				</div>
          				</div>
  					</div>	
  					
  					<div class="col-md-9 shadow-sm" style="height: auto;">
  						<blockquote>
          					<p style="font:20" align="left">
								<c:out value="${mlist.content}" />
							</p>
        				</blockquote>
					</div>
				
  				</div>
  				
  			
  			</div>
		</div>
		
		
		<p></p>				
			<!-- 고정되는 하단 -->
			<table align="right">
				<tr height="30">
					<td colspan="4" align="right">
					<input type="button" class="btn btn-dark btn-sm" value="글수정"
						onClick="document.location.href='casualBoardUpdateForm?question_table_idx=${mlist.question_table_idx}'">
					<input type="button" class="btn btn-dark btn-sm" value="글삭제"
						onClick="document.location.href='deleteCasualArticle?question_table_idx=${mlist.question_table_idx}'">
					<input type="button" class="btn btn-dark btn-sm" value="글목록" onClick="location.href='casualBoard?currentPage=1'"></td>
				</tr>
			</table>
			<br>

			
			<!-- comment append -->
			<div align="left">
				<h5 class="uppercase"><c:out value="${commCount}"/> Comments</h5>
				<hr>
			<div>
			
			<c:forEach var="item" items="${clist}">
			<div class="row">
				<div class="card border-dark col-md-3">
  					<div class="card-header">
  						<img align="left" height="100" width="80" src="<%=request.getContextPath()%>/resources/img/dog.jpg">
  					</div>
  		
  					<div class="card-body">
    					<h4 class="card-title" style="font: 10"><c:out value="${item.member_id}" /></h4>
    					<p class="card-text">written on<br><c:out value="${item.reg_date}" /></p>
  					</div>
				</div>
		
		
				<div class="card border-dark col-md-9">
  					<div class="row card-body my-3 p-3 bg-white rounded shadow-sm">
    					<blockquote>
          					<p style="font:20" align="left">
							<c:out value="${item.content}" />
							</p>
        				</blockquote>
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
						<div class="col-md-12" align="right">
							<input class="btn btn-dark btn-sm" type="submit" name="submit" value="확인"> 
							<input type="hidden" name="member_idx" value="1">
						</div>
			</form>
			</div>
			</div>
		</main>
	</div>



		</div>
	</div>
</body>
</html>