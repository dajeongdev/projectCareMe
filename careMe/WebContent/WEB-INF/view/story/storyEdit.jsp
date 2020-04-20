<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<% String fullName = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/careMe/"; %>
<c:set var="fullName" value="<%=fullName%>" />
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
@font-face { font-family: 'S-CoreDream-4Regular'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-4Regular.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'S-CoreDream-6Bold'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-6Bold.woff') format('woff'); font-weight: normal; font-style: normal; }
h3 { font-family: 'S-CoreDream-6Bold'; }
.container {
	width: 1000px;
	height: 1100px;  
	margin: 40px;
	font-size: 16px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -500px;
	margin-top: -330px;
	font-family: 'S-CoreDream-4Regular';
}
#hash-search, #content, #title, .custom-file-label {
	width: 1000px;
}
.custom-file-label {
	position: relative;
	margin-botton: 40px;
}
#content {
	height: 300px;
}
#hash-inbox { 
	margin-top:2px;
	background: #bdbdbd;
	width: 700px;
	height: 100px;
}
.added-tag {
	background: #82b1ff;
	padding: 5px 5px;
	margin: 5px 5px;
	border-radius: 10%;
	display: inline-block;
}
.btn-group { 
	float: right; 
	margin-top: 10px;
}
.form-group, #file {
	padding-top: 20px;
}
#preview img {
	width: 700px;
	height: 500px;
}
#preview {
	display: inline-block;
}
</style>
<title>스토리 글쓰기</title>
<script>

// 해쉬태그 입력
$(function (){
	  $("#hash-search").on("keyup", function (e) {// 해시태그 입력칸 이벤트
	    var tag = "";
	    var existed = false;// 이미 태그에 올라갔나 확인하기 위함

	    if (e.which == 188 || e.which == 13) {// 누른게 쉼표거나 엔터
	      tag = $(this).val().replace(/[\s,]+/g, ""); // 쉼표나 엔터 ""으로 바꿔서 tag에 저장
	      $(this).val("");// 입력창 비우기

	      $("#hash-inbox span").each(function () { // 해시태그 들어간 div 안의 span
	        var name = $(this).find(".htag-name").val();// input hidden의 값
	        if (name == tag) existed = true;// 이미 있음
	      });

	      if (tag != "" && !existed) { // 태그가 빈문자열이 아니고 이미 올린게 아니면
	        $("#hash-inbox").append(
	            '<span class="added-tag">#' +
	            tag + '<a href="javascript:;"> X</a>' +
	            '<input type="hidden" class="htag-name" name="recipe[hashtags][][name]" value="' + tag + '" >' +
	            '</span> '
	        );
	      }
	    }
	  });
});

var files = [];
var previewIndex = 0;

// image preview 기능, input = file object[]
function addPreview(input) {
	if(input[0].files) {
		for(var f = 0; f < input[0].files.length; f++) {
			var file = input[0].files[f];
			
			if(validation(file.name)) continue;
			
			setPreviewForm(file);
		}
	} else {
		alert("invalid file input");
	}
}
function setPreviewForm(file, img) {
	var reader = new FileReader();
	reader.onload = function(img) {
		var imgNum = previewIndex++;
		$("#preview").append("<div class=\"preview-box\" value=\"" + imgNum + "\">"
				+ "<a href=\"#\" value=\"" + imgNum + "\" onclick=\"deletePreview(this)\">"
				+ "<i class='fas fa-trash-alt'></i>" + "</a>" 
				+ "<img class=\"thumbnail\" src=\"" + img.target.result + "\"\/>"
			    + "</div>");
		files[imgNum] = file;
	};
	reader.readAsDataURL(file);
}

// preview에서 삭제 버튼 클릭시 미리보기 이미지 영역 삭제
function deletePreview(obj) {
	var imgNum = obj.attributes['value'].value;
	delete files[imgNum];
	$("#preview .preview-box[value=" + imgNum + "]").remove();
	resizeHeight();
}

// client-side validation
// always server-side validation required
function validation(fileName) {
	fileName = fileName + "";
	var fileNameExtensionIndex = fileName.lastIndexOf(".") + 1;
	var fileNameExtension = fileName.toLowerCase().substring(fileNameExtensionIndex, fileName.length);
	if(!((fileNameExtension == 'jpg') || (fileNameExtension == 'gif') || (fileNameExtension == 'png'))) {
		alert("jpg, gif, png 확장자만 업로드 가능합니다.");
		return true;
	} else {
		return false;
	}
}

$(document).ready(function() {
	$(".submit a").on("click", function() {
		var form = $("#update")[0];
		var formData = new FormData(form);

		for(var i = 0; i < Object.keys(files).length; i++) {
			formData.append("files", files[i]);
		}

		$.ajax({
			type: "POST",
			enctype: "multipart/form-data",
			processData: false,
			contentType: false,
			cache: false,
			url: "/storyEdit",
			dataType: "json",
			data: formData,
			success: function(result) {
				if(result = -1) {
					alert("jpg, gif, png 확장자만 업로드 가능합니다.");
				} else {
					alert("이미지 업로드 성공");
				}
			}
		});
	});
	$("input[type=file]").change(function() {
		addPreview($(this));
	});
});
var storedFiles = [];
var deletedFiles = [];
var selDivs = "";

$(function() {
	selDiv = $("#selectedFiles");

	$("#file").on("change", handleFileSelect);
	
	$("body").on("click", ".fa-trash", removeFile);

	form = $("form[name=update]")[0];
	form.onsubmit = function (e) {
		e.preventDefault();
		var formData = new FormData(form);
		for (var i = 0; i < storedFiles.length; i++) {
			formData.append("file", storedFiles[i]);
		}

		formData.append("fileDelete", fileDelete);

		 $.ajax({
	         url: "update"
             , type : "POST"
	         , contentType: false
	         , processData: false
             , data : formData
        	 , success : function() {
        		 location.href="/careMe/story";
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
			var html  = "<div class=\"preview-box\" value=\"" + imgNum + "\">"
			+ "<a href=\"#\" value=\"" + imgNum + "\" onclick=\"deletePreview(this)\">"
			+ "<i class='fas fa-trash-alt'></i>" + "</a>" 
			+ "<img class=\"thumbnail\" src=\"" + img.target.result + "\"\/>"
		    + "</div>";
			selDiv.append(html);
		}
		reader.readAsDataURL(f); 
	});
}

function removeFile(e) {
	var file = $(this).data("file");
	var idx = $(this).data("idx");

	if (idx) fileDelete.push(idx);
	
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

<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>
<div class="story_form col-md-4-order-md-2 mb-4">
	<div class="container">
	<h3><strong>펫스토리</strong></h3>
		<hr>
		<form name="update" method="POST" action="storyEdit" enctype="multipart/form-data">
			<input type="hidden" name="member_idx" value="${dto.member_idx}">
			<div class="story_content">
				<input type="text" class="form-control" id="title" name="title" 
				value="${dto.title}">
				<input type="file" class="custom-file-input" id=inputGroupFile04 name="file">
				 <label class="custom-file-label" for="inputGroupFile04">사진 선택</label>
				<i class="fas fa-trash-alt" onClick="deletePreview(this)"></i>
				<div class="row" id="selectedFiles"></div>
				<div id="preview">
				</div>
			<div class="form-group">
			 	<textarea class="form-control" name="content"
    			id="content" rows="3" ><c:out value="${dto.content}"/></textarea>
  			</div>
  			</div>
			<div id="info_tag">
				<input type="hidden" name="tag_idx" name="tag_idx">
				<input type="text" class="form-control" id="tag_name" name="tag_name" placeholder="태그를 입력해보세요." style="margin-bottom: 0;">
				<div class="tag_selected">
					<div id="hash-inbox">
					
					</div>					
				</div>
			</div>	
			<input type="hidden" name="tag_idx" value="3">
			<div class="btn-group">
				<button type="submit" class="insert_btn btn btn-outline-dark" OnClick="document.location.href='storyDetail?story_board_idx=${story_board_idx}'">등록</button>
				<button type="submit" class="list_btn btn btn-outline-dark" OnClick="document.location.href='storyMain'">목록</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>