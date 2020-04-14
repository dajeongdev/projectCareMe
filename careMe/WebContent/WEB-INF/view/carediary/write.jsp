<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/resources/img/profile_dog.jpg" var="default_image" />
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

		$("input[type=file]").on("change", function(e) {
			if (this.files.length > 5) {
				alert("파일 최대5개 업로드 가능");
				this.value = "";				
			} else {
				readURL(this);
			}
		});
		
	})
	
	function readURL(input) {
        if (input.files && input.files[0]) {
            for (var i = 0; i < input.files.length; i++) {
            	var reader = new FileReader();
                reader.onload = function(e) {
                    var el = '<img id="previewImg'+ i +'" class="col-3" src="'+ e.target.result +'">';                    
                    $("#images").append(el);
                    //$('#previewImg').attr('src', e.target.result);
                }
            	reader.readAsDataURL(input.files[i]);
            }
        }
    }
	
</script>

<style type="text/css">
.u-control-label {
	width: 30px;
	height: 30px;
	border-radius: 50%;
}

.u-control-input:checked + label {
	border: solid #f11;
}

</style>

</head>
<body>
<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>

<div class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light"> 
	<div class="container min-vh-100 pt-3"  style="max-width:720px">
	
		<form name="pet_regist" method="post" action="/careMe/pet/regist" enctype="multipart/form-data">

		<div class="my-3 p-3 bg-white rounded shadow-sm">
			<div class="row mb-3 col-12" style="font-size:20px">
				<strong>일기작성</strong>
			</div>
			<hr>
			
			<div class="row mb-3">
				<div class="col-12">
					<label for="birth">날짜</label>
					<input type="date" class="form-control" id="birth" name="birth">
				</div>					
			</div>
			
			<div class="row mb-3">
				<div class="col-12">
					<label for="name">제목</label>
					<input type="text" class="form-control" id="title" name="title" placeholder="" max="40" required>
				</div>					
			</div>
			
			<div class="row mb-3">
				<div class="col-12">
					<label for="weight">산책(운동시간)</label>
					<input type="number" class="form-control" id="exercise" name="exercise" placeholder="" >
				</div>					
			</div>
			
			<div class="row mb-3">
				<div class="col-12">
					<p class="mb-1">소변</p>
					<div class="d-block mb-2">
						  <c:if test="${smallDef != null}">
								<c:forEach var="sDef" items="${smallDef}">
									<div class="d-inline-block pr-3">
							            <input id="urine${sDef.defecation_idx}" name="urine" type="radio" class="u-control-input" value="${sDef.defecation_idx}" style="display:none;" required>
							            <label class="u-control-label" for="urine${sDef.defecation_idx}" style="background-color:${sDef.defecation_content}">
							            	<span></span>
							            </label>
							         </div>
								</c:forEach>
							</c:if>
				    </div>
				</div>					
			</div>
			
			<div class="row mb-3">
				<div class="col-12">
					<p class="mb-1">대변</p>
					<div class="d-block mb-3">
						<c:if test="${bigDef != null}">
							<c:forEach var="bDef" items="${bigDef}">
								<div class="d-inline-block custom-control custom-radio pr-2">
						            <input id="feces${bDef.defecation_idx}" name="feces" type="radio" class="custom-control-input" value="${bDef.defecation_idx}" required>
						            <label class="custom-control-label" for="feces${bDef.defecation_idx}">${bDef.defecation_content}</label>
						        </div>
							</c:forEach>
						</c:if>
				    </div>
				</div>					
			</div>
				
			<div class="row mb-3">
				<div class="col-12">
					<label for="memo">기타 특이사항</label>
					<textarea class="form-control" id="memo" name="memo" placeholder=""></textarea>
				</div>					
			</div>
			
			<div class="row mb-3">
				<div class="col-12" id="images">
					<label for="name" for="previewImg">이미지 첨부</label>
					<div class="row" id="imagesPreview">
					</div>
					<input type="file" class="form-control" id="image" name="image" placeholder="" max="5" multiple>
				</div>					
			</div>
				
			
			<div class="row p-3">
				<div class="col-12 text-center">
					<button type="submit" class="btn btn-success">제출</button>
				</div>
			</div>
			
		</div>	
		</form>
		
	</div>
</div>

</body>
</html>