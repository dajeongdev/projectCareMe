<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@ taglib prefix="Spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="Form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="EUC-KR">	
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>고민 상담</title>
</head>
<body>
	<div class="container-fluid" style="padding: 0;">
		<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />


		<div class="container-fluid">
			<div class="row">
				<main role="main" class="col-md-9 col-lg-10 px-4">

					<h2>고민 상담</h2>
					<p>
						(전체 글:
						<c:out value="${count}" />
						)
					</p>
					<div class="table-responsive">
						<table class="table table-striped table-sm">
							<!-- 맨 윗 줄 -->
							<thead>
								<tr>
									<th>번 호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>작성일자</th>
									<th>조회</th>
								</tr>
							</thead>
							<tbody>

								<!-- 글 들어가는 곳 -->
								<c:forEach var="item" items="${list}">
									<tr>
										<td><c:out value="${item.question_table_idx}" /></td>
										<td><a
											href="casualBoardContent?question_table_idx=${item.question_table_idx}">"${item.title}"</a></td>
										<td><c:out value="${item.member_id}" /></td>
										<td><c:out value="${item.brd_reg_date}" /></td>
										<td><c:out value="${item.views}" /></td>
									</tr>
								</c:forEach>
							</tbody>

							
						</table>
					</div>
				</main>
			</div>
		  <!-- 게시판 글쓰기 -->
		  <tr>
		      <td align="right"><a href="writeForm">글쓰기</a></td>
			  <td colspan="4" width="50" align="center">number</td>
	      </tr>
		  <!-- 게시판 검색 -->
		  <form action="searchPro">
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