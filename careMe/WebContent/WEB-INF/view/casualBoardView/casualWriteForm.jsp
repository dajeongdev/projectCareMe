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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script>
<!-- PET CHOOSE -->
$(function(){
		$("#petSpeciesLevel1").on("change", function(){
		var ancestor=$(this).find("option:selected").data("num");
			if(!ancestor){
				$("#petSpeciesLevel2 option").remove();
				return false;
			}
		var url ="casualWriteForm/pet_species_idx?level=2&ancestor="+ancestor;
		$.ajax(
			{type:"GET",
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

<script>
<!-- MyPET CHOOSE -->
$(function(){
		$("#myPet").on("change", function(){
		var selectPet=$(this).find("option:selected").data("num");
			if(!selectPet){
				$("#petDiary option").remove();
				return false;
			}
		var url ="casualWriteForm/pet_idx?level=2&selectPet="+selectPet;
		$.ajax(
			{type:"GET",
			url:url,
			dataType:"json"})
			.done(function(items){
			$("#petDiary option").remove();
				if (items.length > 0) {
					for (item in items) {
					var s = items[item];
					var option = "<option value=" + s.pet_care_idx + ">" + s.title + " " + s.diary_date + "</option>"
					$("#petDiary").append(option);
					}
				}
				}).fail(function(e) {
					alert(e.responseText);
				});
			})
		})

</script>
	
	
<script>
<!-- 해시태그 기능 -->
//태그를 저장할 배열
var tags = [];
var tagNames = [];
//태그를 보여줄 element

$(function() {

	$("#tag").on("keypress", function (e) {
		if (e.key === "Enter" || e.keyCode == 32) {
			var inputText = $("#tag").val(); // 내가 입력한 값
			//inputText를 tagNames[]를 for문 돌려서 비교해서 같으면 중복!
			
			for(var i=0; i < tagNames.length; i++){
				if(tagNames[i] == inputText){
					console.log(tagNames[i] + "==" + inputText);
					$("#tag").val("");
					alert("중복!");
					return;
				}				
			}

			// 태그 중복확인
			tagCheck(inputText);
			e.preventDefault();
		}
		console.log("enter");
	})
	
})	

var tagCheck = function (tag) {
	var url = $(location).attr('pathname') + "/hashCheck";
	$.ajax({
		type:"POST",
		url:url + "?tag_name=" + tag,
		dataType:"json"
	}).done(function(data) {
		console.log(data);
		
		//배열에 tag의 idx를 넣어준다
		var idx = data.tag_idx;
		var name = data.tag_name;
		var html = "<li class='hashTag' data-idx=" + idx + ">" + "#" + name + "</li>";

		//서버에 보낼 배열에 넣기
		tags.push(idx);
		// input enter 눌렸을때 input 있는 value text 와 배열에 있는 text를 비교해서 있으면 중복알림! 없으면 ajax!
		tagNames.push(name);
		// hidden input 에 넣어주기
		$("#rdTag").val(tags);


		// 태그 붙이기
		$("#tag-list").append(html);
		// input 비우기
		$("#tag").val("");
		
		//alert("성공!");
	}).fail(function() {
		alert("실패!");
	});
}
	
</script>

<script>
<!-- 추가한 파일 보여주기 -->
var storedFiles = [];
var selDivs = "";

$(function(){

	selDiv = $("#selectedFiles");

	$("#files").on("change", handleFileSelect);
	$("body").on("click", ".fa-trash", removeFile);

	form = $("form[name=addWrite]")[0];
	form.onsubmit = function (e) {
		e.preventDefault();
		var formData = new FormData(form);
		for (var i = 0; i < storedFiles.length; i++) {
			formData.append("files", storedFiles[i]);
		}
		formData.append("rdTag", tags);
		//console.log(formData);		
		$.ajax({
	         url:"casualBoardWriteAdd"
             ,type:"POST"
			 ,contentType:false
			 ,processData:false
	         ,data:formData
        	 ,success:function() {
                 location.href="/careMe/view/casualBoardView/casualBoard?currentPage=1";
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
				var html = "<div class='col-md-3 mb-5'>";
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
		for(var i=0;i<storedFiles.length;i++) {
			if(storedFiles[i].name === file) {
				storedFiles.splice(i,1);
				break;
			}
		}
		$(this).parent().remove();
	}
</script>

</head>

<body>
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />

	<div class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light">
		<div class="container min-vh-100 pt-3 text-center">

		<form name="addWrite" enctype="multipart/form-data">
			<div class="row my-3 p-3">
				<main role="main" class="col-lg-12 bg-white rounded shadow-sm">
					
					<h2 align="left">고민 상담</h2>
					<p></p>
						<div class="mb-3" align="left">
			          		<label for="title">제목</label>
          					<input id="title" name="title" width="100%" type="text" class="form-control"/>
        				</div>
						<input name="question_type" type="hidden" value="n" /> 
						<input name="is_private" type="hidden" value="n" /> 
						<input name="doctor_idx" type="hidden" value="1" /> 
						<input name="pet_idx" type="hidden" value="1" />
						<input name="member_id" type="hidden" id="subject" value="${sc.memberDto.member_id}">

					<!-- 동물 종류 찾기 -->

						<div class="row" style="width: 100%;">
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

					<!-- 마이펫 찾기 -->
						<div class="row" style="width: 100%;">
							<div class="col-md-6  mb-3">
								<label for="myPet">등록 펫 찾기</label> 
								<select class="form-control" id="selectMyPet">
									<option>==선택==</option>
									<c:if test="${petOption != null}">
										<c:forEach var="option" items="${petOption}">
											<option data-num="${option.pet_species_idx}">${option.pet_species_name}</option>
										</c:forEach>
									</c:if>
								</select>
							</div>
							<div class="col-md-6  mb-3">
								<label for="careDiary">다이어리 찾기</label> 
								<select class="form-control" id="selectPetDiary" name="pet_care_idx" required>
								</select>
							</div>
						</div>

						<div align="left">
							내용<br>
							<textarea name="content" style="width: 100%; height: 250px"></textarea>
							<br> 
						</div>
						
					<!-- 파일 / 사진 등록 -->
						<div class="row mb-3" align="left">
							<div class="col-12" id="images">
								<label for="">사진등록</label>
								<div>
									<label for="files"> <span class="btn btn-dark btn-sm">등록</span>
									</label>
								</div>

								<div class="row" id="selectedFiles"></div>
							</div>
						</div>

						<br>
    					
					<!-- 해시태그 추가 -->
						<div class="content" align="left">
       						<input type="hidden" value="" id="rdTag"/>
 	 	  			   	<div>
     				       <input type="text" id="tag" size="7" placeholder="태그입력" />
     				    </div>
     				    <br>  
      			        <ul id="tag-list"></ul>
    			   		</div> 
    			  </main>
			</div>		
						
			<div class="row">
				
				<div class="col-md-12" align="center">			
					<input type="submit" class="btn btn-dark btn-sm" value="제출"> 
					<input type="reset" class="btn btn-dark btn-sm" value="다시쓰기"> 
					<input type="button" class="btn btn-dark btn-sm" value="목록으로" OnClick="location.href='casualBoard?currentPage=1'">
				</div>	
				
			</div>
			
		</form>
		<input type="file" class="form-control mb-3 d-none" id="files" name="image" placeholder="" max="5" multiple>
		</div>
	</div>
</body>
</html>