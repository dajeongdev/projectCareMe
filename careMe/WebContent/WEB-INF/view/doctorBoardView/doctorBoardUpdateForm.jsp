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

					<form action="doctorBoardUpdateAdd" method="post" name="updateInfo">

						제목<input name="title" type="text"><br> <input
							name="question_table_idx" type="hidden" value="${idx}" /> <input
							name="doctor_idx" type="hidden" value="1" /> <input
							name="pet_idx" type="hidden" value="1" /> <input
							name="question_type" type="hidden" value="y" /> <input
							name="is_private" type="hidden" value="n" /> 소분류<select
							name="pet_species_idx">
							<c:forEach var="specs" items="${specs}">
								<option value="${specs.pet_species_idx}">${specs.pet_species_idx}</option>
							</c:forEach>
						</select>

						<!-- MyPet<select name="pet_idx"><br>
			<option value="1">Pet 1</option>
			<option value="2">Pet 2</option>
			</select><br> -->

						내용<br>
						<textarea name="content" rows="13" cols="40"></textarea>
						<br> <input type="submit" value="수정"> <input
							type="reset" value="다시쓰기"> <input type="button"
								value="목록으로" OnClick="location.href='doctorBoard'">

					</form>

				</main>
			</div>


		</div>
	</div>
</body>
</html>