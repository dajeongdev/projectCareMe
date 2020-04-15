<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<spring:url value="/resources/img/profile_dog.jpg" var="default_image" />
<% String hostname = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/careMe/"; %>
<c:set var="hostname" value="<%=hostname%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>메인 화면</title>
<script>
	var storedFiles = [];
	var deletedFiles = [];
	var selDivs = "";

	$(function() {
		selDiv = $("#selectedFiles");

		$("#files").on("change", handleFileSelect);
		
		$("body").on("click", ".fa-trash", removeFile);

		form = $("form[name=pet_regist]")[0];
		form.onsubmit = function (e) {
			e.preventDefault();
			var formData = new FormData(form);
			for (var i = 0; i < storedFiles.length; i++) {
				formData.append("files", storedFiles[i]);
			}

			formData.append("deletedFiles", deletedFiles);

			 $.ajax({
		         url: "update"
	             , type : "POST"
		         , contentType: false
		         , processData: false
	             , data : formData
            	 , success : function() {
                     console.log("성공");
                 }
	        })
		}

		

	})
	
	function handleFileSelect(e) {
		var files = e.target.files;
		var filesArr = Array.prototype.slice.call(files);
		filesArr.forEach(function(f) {			

			if(!f.type.match("image.*")) {
				return;
			}
			storedFiles.push(f);
			
			var reader = new FileReader();
			reader.onload = function (e) {
				var html  = "<div class='col-md-3 mb-5'>";
					html += "<img src=\"" + e.target.result + "\"  class='w-100 h-80'>";
					html += "<i class='fa fa-trash' data-file='"+f.name+"' title='Click to remove'></i>";
					html += "</div>";
				selDiv.append(html);
			}
			reader.readAsDataURL(f); 
		});
	}

	function removeFile(e) {
		var file = $(this).data("file");
		var idx = $(this).data("idx");

		if (idx) deletedFiles.push(idx);
		
		for(var i=0;i<storedFiles.length;i++) {
			if(storedFiles[i].name === file) {
				storedFiles.splice(i,1);
				break;
			}
		}
		$(this).parent().remove();
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
	
		<form name="pet_regist" enctype="multipart/form-data">
		
		<input type="hidden" name="pet_care_idx" value="${diaryInfo.diary.pet_care_idx}">

		<div class="my-3 p-3 bg-white rounded shadow-sm">
			<div class="row mb-3 col-12" style="font-size:20px">
				<strong>일기작성</strong>
			</div>
			<hr>
			
			<div class="row mb-3">
				<div class="col-12">
					<label for="diary_date">날짜</label>
					<input type="date" class="form-control" id="diary_date" name="diary_date" value="${diaryInfo.diary.diary_date}">
				</div>					
			</div>
			
			<div class="row mb-3">
				<div class="col-12">
					<label for="title">제목</label>
					<input type="text" class="form-control" id="title" name="title" placeholder="" max="40" value="${diaryInfo.diary.title}" required>
				</div>					
			</div>
			
			<div class="row mb-3">
				<div class="col-12">
					<label for="exercise">산책(운동시간)</label>
					<input type="number" class="form-control" id="exercise" name="exercise" placeholder="" value="${diaryInfo.diary.exercise}">
				</div>					
			</div>
			
			<div class="row mb-3">
				<div class="col-12">
					<p class="mb-1">소변</p>
					<div class="d-block mb-2">
						  <c:if test="${smallDef != null}">
								<c:forEach var="sDef" items="${smallDef}">
									<div class="d-inline-block pr-3">
							            <input id="urine${sDef.defecation_idx}" name="urine" type="radio" class="u-control-input" value="${sDef.defecation_idx}" style="display:none;" required
							            	<c:if test="${sDef.defecation_idx == diaryInfo.diary.urine}">checked</c:if>
							            >
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
						            <input id="feces${bDef.defecation_idx}" name="feces" type="radio" class="custom-control-input" value="${bDef.defecation_idx}" required
						            	<c:if test="${bDef.defecation_idx == diaryInfo.diary.feces}">checked</c:if>
						            >
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
					<textarea class="form-control" id="memo" name="memo" placeholder="">${diaryInfo.diary.memo}</textarea>
				</div>					
			</div>
			
			<div class="row mb-3">
				<div class="col-12" id="images">
					<label for="">사진등록</label>
					<div>
						<label for="files">
							<span class="btn btn-success">등록</span>
						</label>						
					</div>
					<div class="row" id="selectedFiles">
						<c:if test="${diaryInfo.files.size() > 0}">
							<c:forEach var="image" items="${diaryInfo.files}">
								
									<div class='col-md-3 mb-5'>
										<img src="${hostname}${image.file_path}" class='w-100 h-80'>
										<i class='fa fa-trash' data-file="${image.file_name}"  data-idx="${image.pet_care_file_idx}" title='Click to remove'></i>
									</div>
								
							</c:forEach>
						</c:if>
					</div>
				</div>					
			</div>
			
			<div class="row p-3">
				<div class="col-12 text-center">
					<button type="submit" class="btn btn-success">제출</button>
				</div>
			</div>
			
		</div>	
		</form>
		<input type="file" class="form-control mb-3 d-none" id="files" name="image" placeholder="" max="5" multiple>
		
	</div>
</div>

</body>
</html>