<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<spring:url value="resources/upload/img/pet/profile/1586253298948.jpg" var="profileImg" />
<% String hostname = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/careMe/"; %>
<c:set var="hostname" value="<%=hostname%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>펫 정보수정</title>
<script>

	$(function() {
		$("#profileImage").change(function() {
	        readURL(this);
	    });
		
		$("#additional").on("hide.bs.collapse", function () {
			$(".bi-caret-down-fill").show();
			$(".bi-caret-up-fill").hide();
		}).on('show.bs.collapse', function () {
			$(".bi-caret-down-fill").hide();
			$(".bi-caret-up-fill").show();
		});

		$("#petSpeciesLevel1").on("change", function () {
			var ancestor = $(this).find("option:selected").data("num");
			if (!ancestor) {
				$("#petSpeciesLevel2 option").remove();
				return false;
			}
			var url = "/careMe/api/pet/species?level=2&ancestor=" + ancestor;
			$.ajax({
				type : "get",
				url : url,				
				dataType : "json"
			}).done(function(items) {
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
	
	
	function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#previewImg').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }

    

	
</script>
</head>
<body>
<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>

<div class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light"> 
	<div class="container min-vh-100 pt-3"  style="max-width:720px">
	
		<form name="pet_regist" method="post" action="/careMe/pet/update" enctype="multipart/form-data">
		
			<!-- S: 필수 -->
			<div class="my-3 p-3 bg-white rounded shadow-sm">
			
				<div class="row mb-3">
					<div class="col-12">
						<label for="name">프로필 사진</label>
						<img id="previewImg" class="w-100" src="${hostname}${profileImg}">
						<input type="file" class="form-control" id="profileImage" name="profileImage" placeholder="" max="20" required>
					</div>					
				</div>
							
				<div class="row mb-3">
					<div class="col-12">
						<label for="name">이름</label>
						<input type="text" class="form-control" id="name" name="name" placeholder="" max="20" required>
					</div>					
				</div>
				
				<div class="row">
					<div class="col-md-6  mb-3">
						<label for="petSpecies1">동물의 종류(대분류)</label>
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
						<label for="petSpecies2">동물의 종류(소분류)</label>
						<select class="form-control" id="petSpeciesLevel2" name="species" required>
						</select>
					</div>
				</div>
				
				<div class="row mb-3">
					<div class="col-12">
						<p class="mb-1">중성화 여부</p>
						<div class="d-block mb-3">
					          <div class="custom-control custom-radio">
					            <input id="neutY" name="neutralized" type="radio" class="custom-control-input" value="y" checked>
					            <label class="custom-control-label" for="neutY">예</label>
					          </div>
					          <div class="custom-control custom-radio">
					            <input id="neutN" name="neutralized" type="radio" class="custom-control-input" value="n" required>
					            <label class="custom-control-label" for="neutN">아니오</label>
					          </div>
					          <div class="custom-control custom-radio">
					            <input id="neutD" name="neutralized" type="radio" class="custom-control-input" value="d" required>
					            <label class="custom-control-label" for="neutD">모름</label>
					          </div>
					    </div>
					</div>					
				</div>
				
			</div>
			<!-- E: 필수 -->
			
			
			<!-- S: 추가 -->
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
							<input type="date" class="form-control" id="birth" name="birth">
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
							<input type="number" class="form-control" id="weight" name="weight" placeholder="" >
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
							<label for="bloodType">혈액형</label>
							<input type="text" class="form-control" id="bloodType" name="bloodType" placeholder="">
						</div>					
					</div>
					
					<div class="row mb-3">
						<div class="col-12">
							<label for="registrationNumber">등록번호</label>
							<input type="text" class="form-control" id="registrationNumber" name="registrationNumber" placeholder="">
						</div>					
					</div>
					
					<div class="row mb-3">
						<div class="col-12">
							<label for="memo">기타 특이사항</label>
							<textarea class="form-control" id="memo" name="memo" placeholder=""></textarea>
						</div>					
					</div>
					
				</div>
			</div>
			
			<!-- E: 추가 -->

			
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