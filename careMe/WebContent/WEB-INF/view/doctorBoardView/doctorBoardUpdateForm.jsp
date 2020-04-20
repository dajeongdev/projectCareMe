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
		var url ="doctorWriteForm/pet_species_idx?level=2&ancestor="+ancestor;
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
		
		<form action="doctorBoardWriteAdd" method="post" enctype="multipart/form-data">
			<div class="row my-3 p-3">
				<main role="main" class="col-lg-12 bg-white rounded shadow-sm">
					
					<h2 align="left">전문 상담</h2>
					<p></p>
						<div class="mb-3" align="left">
			          		<label for="title">제목</label>
          					<input name="title" type="text" class="form-control"/>
        				</div>
						<input name="question_type" type="hidden" value="y" /> 
						<input name="member_idx" type="hidden" id="subject" value="${info.member_idx}">
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
							<textarea name="content" style="width: 100%; height: 250px"></textarea>
							<br>
						</div>
						
						
						<div align="left">
						<label for="file">파일첨부</label><br>
							<input type="file" name="file" id="file" multiple/>
							<div class="row" id="selectedFiles"></div>
							<div id="preview"></div>
    					</div>
    					
    					<br>
    					
						<div class="content" align="left">
       					<input type="hidden" value="" name="tag" id="rdTag" />
    			   	<div>
     			       <input type="text" id="tag" size="7" placeholder="태그입력" />
     			    </div><br>  
    			    
    			    <ul id="tag-list"></ul>

    			   	</div> 
				</main>
			</div>
				
				<div class="row">
					<div class="col-md-12" align="center">	
						<input type="submit" class="btn btn-dark btn-sm" value="수정"> 
						<input type="reset" class="btn btn-dark btn-sm" value="다시쓰기"> 
						<input type="button" class="btn btn-dark btn-sm" value="목록으로" OnClick="location.href='doctorBoard?currentPage=1'">
					</div>
				</div>			
				
			
		</form>


		</div>
	</div>
</body>
</html>