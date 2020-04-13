<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="Spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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

	<div class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light">
		<div class="container min-vh-100 pt-3 text-center">

		<form name="addWrite" action="casualBoardWriteAdd" method="POST" enctype="multipart/form-data">
			<div class="row">
				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
					
					<h2 align="left">고민 상담</h2>
					<p></p>
						<div class="mb-3" align="left">
			          		<label for="title">제목</label>
          					<input id="title" name="title" type="text" class="form-control"/>
        				</div>
						<input name="question_type" type="hidden" value="n" /> 
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
						<div align="left">
						<label for="file">파일첨부</label>
							<input name="file" type="file" />
						</div>
    					<br>
    					<div align="left">
						<label for="tagArea">태그 추가</label>  					
							<input type="text" name="tagArea" placeholder="#">
						</div>
						<br>
						
						
						<input type="submit" value="제출"> 
						<input type="reset" value="다시쓰기"> 
						<input type="button" value="목록으로" OnClick="location.href='casualBoard'">

					
				</main>
			</div>
			
		</form>
					

		</div>
	</div>
</body>
</html>