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


			<div class="row mb-3">
				<main role="main" class="col-lg-12">
					<h2 align="left">고민 상담</h2>
					<p align="left"> (전체 글: <c:out value="${count}" />)</p>
					<div class="table">
						<table class="table table-striped table-lg table-hover">
							<!-- 맨 윗 줄 -->
							<thead class="thead-dark">
								<tr>
									<th width="10%">번 호</th>
									<th align="left" width="30%">제목</th>
									<th width="20%">작성자</th>
									<th width="20%">작성일자</th>
									<th width="10%">조회</th>
								</tr>
							</thead>	
							<tbody>

								<!-- 글 들어가는 곳 -->
								<c:forEach var="item" items="${list}">
									<tr onClick="location.href='casualBoardContent?question_table_idx=${item.question_table_idx}'">
										<td><c:out value="${item.question_table_idx}" /></td>
										<td><c:out value="${item.title}"/></td>
										<td><c:out value="${item.member_id}" /></td>
										<td><c:out value="${item.reg_date}" /></td>
										<td><c:out value="${item.view_count}" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>

						<!-- 게시판 페이지넘버링 및 글쓰기 -->
						
						<div class="row mb-3" >
						<div class="col-md-2" align="left">
							<jsp:include page="/WEB-INF/view/include/paging.jsp" flush="false"/>
						</div>
						<div class="col-md-8"></div>
						<div class="col-md-2" align="right">
							<button class="btn btn-dark btn-sm btn-block" onClick="location.href='casualWriteForm'">글쓰기</button>	
						</div>
						</div>
						
					</div>
				</main>
			</div>

			<!-- 게시판 검색 -->
			<form action="view/casualBoardView/casualBoardSearch">
				<select name="searchn">
					<option value="0">작성자</option>
					<option value="1">제목</option>
					<option value="2">내용</option>
				</select> <input type="text" name="searchKeyword" size="15" maxlength="50" />
				<input type="submit" value="검색" />
			</form>



		</div>
	</div>
</body>
</html>