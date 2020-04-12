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

<script>
	$(function(){
		$("#petSpeciesLevel1").on("change", function(){
		var ancestor=$(this).find("option:selected").data("num");
			if(!ancestor){
				$("#petSpeciesLevel2 option").remove();
				return false;
			}
		var url ="casualWriteForm/pet_species_idx?level=2&ancestor="+ancestor;
		$.ajax(
			{type:"get",
			url:url,
			dataType:"json"})
			.done(function(items){
			$("#petSpeciesLevel2 option").remove();
				if (items.length > 0) {
					for (item in items) {
					var s = items[item];
					var option = "<option value=" + s.pet_species_idx + ">" + s.pet_species_name + "</option>"
					$("#petSpeciesLevel2").append(option);
					}
				}
				}).fail(function(e) {
					alert(e.responseText);
				});
			})
		})
</script>

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


					<form action="doctorBoardWriteAdd" method="post" name="addWrites" enctype="multipart/form-data">

						<div style="width: 900px; height: 100px" align="left">
							<input placeholder="제목" type="text" name="title">
						</div>
						<input name="question_type" type="hidden" value="y" /> 
						<input name="is_private" type="hidden" value="n" /> 
						<input name="doctor_idx" type="hidden" value="1" /> 
						<input name="pet_idx" type="hidden" value="1" />

						<!-- 동물 종류 찾기 -->

						<div class="row">
							<div class="col-md-6  mb-3">
								<label for="petSpecies1">대분류</label> 
								<select class="form-control" id="petSpeciesLevel1">
									<option>==선택==</option>
									<c:if test="${speciesOption != null}">
										<c:forEach var="option" items="${speciesOption}">
											<option data-num="${option.pet_species_idx}">${option.pet_species_name}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
							<div class="col-md-6  mb-3">
								<label for="petSpecies2">소분류</label> 
								<select class="form-control" id="petSpeciesLevel2" name="pet_species_idx" required>
								</select>
							</div>
						</div>

						<div align="left">
							내용<br>
							<textarea name="content" style="width: 900px; height: 500px"></textarea>
							<br> <input name="member_idx" type="text" id="subject"><br>
						</div>

						<div>
							<p>tag 추가하기</p>
							<input type="text" name="tagArea" placeholder="#">
						</div>

						<!-- <div align="left">
						파일첨부<br>
						<form name="fileUpload" enctype="multipart/form-data">
							<input type="file" id="file_name" aria-describedby="inputGroupFileAddon01"  >
    					</form>
    					</div> -->

						<input type="submit" value="제출"> <input type="reset"
							value="다시쓰기"> <input type="button" value="목록으로"
							OnClick="location.href='doctorBoard'">

					</form>
				</main>
			</div>


		</div>
	</div>
</body>
</html>