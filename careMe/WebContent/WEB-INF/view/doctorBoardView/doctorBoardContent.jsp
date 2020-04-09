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

	<div
		class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light">
		<div class="container min-vh-100 pt-3 text-center">



			<div class="row">
				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">


					<h2 align="left">전문 상담</h2>
					<p></p>
					<form>
						<table border="1">
							<tr height="30">
								<td align="center" width="125">No.</td>
								<td align="center" width="125" align="center"><c:out
										value="${list.question_table_idx}" /></td>
								<td align="center" width="125">조회수</td>
								<td align="center" width="125" align="center"><c:out
										value="${list.view_count}" /></td>
							</tr>
							<tr height="30">
								<td align="center" width="125">작성자</td>
								<td align="center" width="125" align="center"><c:out
										value="${list.member_id}" /></td>
								<td align="center" width="125">작성 날짜</td>
								<td align="center" width="125" align="center"><c:out
										value="${list.reg_date}" /></td>
							</tr>
							<tr height="30">
								<td align="center" width="125">제목</td>
								<td align="center" width="375" align="center" colspan="3"><c:out
										value="${list.title}" /></td>
							</tr>
							<tr>
								<td align="center" width="125">내용</td>
								<td align="left" width="375" colspan="3"><pre>
								<c:out value="${list.content}" />
							</pre></td>
							</tr>
							<tr height="30">
								<td colspan="4" align="right">
									<input type="button"
									value="글수정" onClick="document.location.href='doctorBoardUpdateForm?question_table_idx=${list.question_table_idx}'">
									<input type="button" value="글삭제"
									onClick="document.location.href='deleteArticle?question_table_idx=${list.question_table_idx}'"> <input
									type="button" value="답글쓰기"
									onClick="document.location.href='commentForm'"> <input
									type="button" value="글목록"
									onClick="location.href='doctorBoard'"></td>
							</tr>
						</table>
					</form>



				</main>
			</div>


		</div>
	</div>
</body>
</html>