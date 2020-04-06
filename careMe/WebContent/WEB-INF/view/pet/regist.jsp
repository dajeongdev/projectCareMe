<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<spring:url value="/resources/img/icons/" var="icons" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>메인 화면</title>
<script>

	$(function() {
		
		
		
		$("#additional").on("hide.bs.collapse", function () {
			$(".bi-caret-down-fill").show();
			$(".bi-caret-up-fill").hide();
		}).on('show.bs.collapse', function () {
			$(".bi-caret-down-fill").hide();
			$(".bi-caret-up-fill").show();
		});

		$("#pet_species_level1").on("change", function () {
			var ancestor = $(this).find("option:selected").data("num");
			if (!ancestor) {
				$("#pet_species_level2 option").remove();
				return false;
			}
			var url = "/careMe/api/pet/species?level=2&ancestor=" + ancestor;
			$.ajax({
				type : "get",
				url : url,				
				dataType : "json"
			}).done(function(items) {
				$("#pet_species_level2 option").remove();
				if (items.length > 0) {
					for (item in items) {
						var s = items[item]; 
						var option = "<option value=" + s.pet_species_idx + ">" + s.pet_species_name + "</option>"
						$("#pet_species_level2").append(option);
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
<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>

<div class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light"> 
	<div class="container min-vh-100 pt-3"  style="max-width:720px">
	
		<form name="pet_regist" method="get" action="/careMe/pet/regist">
			<div class="my-3 p-3 bg-white rounded shadow-sm">
							
				<div class="row mb-3">
					<div class="col-12">
						<label for="name">이름은 무엇인가요</label>
						<input type="text" class="form-control" id="name" name="name" placeholder="" max="20" required>
					</div>					
				</div>
				
				<div class="row">
					<div class="col-md-6  mb-3">
						<label for="petSpecies1">동물의 종류(대분류)</label>
						<select class="form-control" id="pet_species_level1">
							<option>==선택==</option>
							<c:if test="${speciesOption != null}">
								<c:forEach var="option" items="${speciesOption}">
									<option data-num="${option.pet_species_idx}">${option.pet_species_name}</option>
								</c:forEach>
							</c:if>
						</select>
					</div>
					<div class="col-md-6  mb-3">
						<label for="petSpecies2">동물의 종류(소분류)</label>
						<select class="form-control" id="pet_species_level2" name="pet_species_idx" required>
						</select>
					</div>
				</div>
				
				<div class="row mb-3">
					<div class="col-12">
						<p class="mb-1">중성화 여부</p>
						<div class="d-block mb-3">
					          <div class="custom-control custom-radio">
					            <input id="neutY" name="is_neutralized" type="radio" class="custom-control-input" value="y" checked>
					            <label class="custom-control-label" for="neutY">예</label>
					          </div>
					          <div class="custom-control custom-radio">
					            <input id="neutN" name="is_neutralized" type="radio" class="custom-control-input" value="n" required>
					            <label class="custom-control-label" for="neutN">아니오</label>
					          </div>
					          <div class="custom-control custom-radio">
					            <input id="neutD" name="is_neutralized" type="radio" class="custom-control-input" value="d" required>
					            <label class="custom-control-label" for="neutD">모름</label>
					          </div>
					    </div>
					</div>					
				</div>
			</div>
			
			<div class="my-3 p-3 bg-white rounded shadow-sm">
			
				<div class="row">
					<div class="col-6">
						<div class="h-100 pt-2" style="font-size:20px;">추가정보</div>
					</div>
					<div class="col-6 text-right">
						<button class="btn btn-white" type="button" data-toggle="collapse" data-target="#additional" aria-expanded="false" aria-controls="additional">
							<svg class="bi bi-caret-down-fill" width="2em" height="2em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
								<path d="M7.247 11.14L2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 01.753 1.659l-4.796 5.48a1 1 0 01-1.506 0z"/>
							</svg>
							<svg class="bi bi-caret-up-fill" width="2em" height="2em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg" style="display:none;">
						  		<path d="M7.247 4.86l-4.796 5.481c-.566.647-.106 1.659.753 1.659h9.592a1 1 0 00.753-1.659l-4.796-5.48a1 1 0 00-1.506 0z"/>
							</svg>
						</button>
						
					</div>
				</div>
				
				<div class="collapse" id="additional">
					<hr class="mb-4">
				
					<div class="row mb-3">
						<div class="col-12">
							<label for="birth">생일은 언제인가요</label>
							<input type="date" class="form-control" id="birth" name="birth" placeholder="" value="">
						</div>					
					</div>
					
					<div class="row mb-3">
						<div class="col-12">
							<p class="mb-1">성별</p>
							<div class="d-block">
						          <div class="custom-control custom-radio">
						            <input id="genM" name="gender" type="radio" class="custom-control-input" value="m">
						            <label class="custom-control-label" for="genM">남</label>
						          </div>
						          <div class="custom-control custom-radio">
						            <input id="genF" name="gender" type="radio" class="custom-control-input" value="f">
						            <label class="custom-control-label" for="genF">여</label>
						          </div>
						          <div class="custom-control custom-radio">
						            <input id="genD" name="gender" type="radio" class="custom-control-input" value="n">
						            <label class="custom-control-label" for="genD">기타</label>
						          </div>
						    </div>
						</div>					
					</div>
					
					<div class="row mb-3">
						<div class="col-12">
							<label for="weight">몸무게(kg)</label>
							<input type="number" class="form-control" id="weight" name="weight" placeholder="" value="0">
						</div>					
					</div>
					
					<div class="row mb-3">
						<div class="col-12">
							<p class="mb-1">기초 예방접종</p>
							<div class="d-block">
						          <div class="custom-control custom-radio">
						            <input id="vaccY" name="vaccination" type="radio" class="custom-control-input" value="y">
						            <label class="custom-control-label" for="vaccY">예</label>
						          </div>
						          <div class="custom-control custom-radio">
						            <input id="vaccN" name="vaccination" type="radio" class="custom-control-input"  value="n">
						            <label class="custom-control-label" for="vaccN">아니오</label>
						          </div>
						          <div class="custom-control custom-radio">
						            <input id="vaccD" name="vaccination" type="radio" class="custom-control-input"  value="d">
						            <label class="custom-control-label" for="vaccD">모름</label>
						          </div>
						    </div>
						</div>					
					</div>
					
					<div class="row mb-3">
						<div class="col-12">
							<label for="blood_type">혈액형</label>
							<input type="text" class="form-control" id="blood_type" name="blood_type" placeholder="" value="">
						</div>					
					</div>
					
					<div class="row mb-3">
						<div class="col-12">
							<label for="registration_number">등록번호</label>
							<input type="text" class="form-control" id="registration_number" name="registration_number"  value="" placeholder="">
						</div>					
					</div>
					
					<div class="row mb-3">
						<div class="col-12">
							<label for="memo">기타 특이사항</label>
							<textarea class="form-control" id="memo" name="memo" placeholder="" value=""></textarea>
						</div>					
					</div>
					
				</div>
			</div>

			
			<div class="row p-3">
				<div class="col-12 text-center">
					<button type="submit" class="btn btn-success">제출</button>
				</div>
			</div>
			
		</form>
	</div>
</div>

</body>
</html>