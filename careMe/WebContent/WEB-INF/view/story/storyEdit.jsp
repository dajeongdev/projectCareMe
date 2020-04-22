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
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<style>
@font-face { font-family: 'S-CoreDream-4Regular'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-4Regular.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'S-CoreDream-6Bold'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-6Bold.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'S-CoreDream-4Regular'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-4Regular.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'S-CoreDream-6Bold'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-6Bold.woff') format('woff'); font-weight: normal; font-style: normal; }
.story_form {margin: 0 auto; padding: 0;}
h3 { font-family: 'S-CoreDream-6Bold'; }
.whole {
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
#hash-search, #content, #title, .custom-file-label, #tag_name {
	width: 1000px;
}
.custom-file-label {
	position: relative;
}
#content {
	height: 300px;
}
#title { margin-bottom: 30px;}
#hash-inbox { 
	margin-top:2px;
	background: #bdbdbd;
	width: 1000px;
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
.preview { padding-left: 30px; width:800px; }

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

var storedFiles = [];
var deletedFiles = [];
var selDivs = "";

$(function() {
	selDiv = $("#selectedFiles");

	$("#files").on("change", handleFileSelect);
	
	$("body").on("click", ".fa-trash", removeFile);

	form = $("form[name="insert"]")[0];
	form.onsubmit = function (e) {
		e.preventDefault();
		var formData = new FormData(form);
		for (var i = 0; i < storedFiles.length; i++) {
			formData.append("files", storedFiles[i]);
		}

		formData.append("deletedFiles", deletedFiles);

		 $.ajax({
	         url: "storyEdit"
             , type : "POST"
	         , contentType: false
	         , processData: false
	         , enctype: "multipart/form-data"
             , data : formData
        	 , success : function() {
        		 location.href="/careMe/story/storyMain";
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
			var html  = "<div class='preview'>";
			html += "<i class='fa fa-trash fa-2x' data-file='"+f.name+"' title='Click to remove'></i>";
			html += "<img src=\"" + e.target.result + "\" class='w-100 h-70'>";
			html += "</div>"
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
</head>
<body>

<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>
<div class="story_form col-md-4-order-md-2 mb-4">
	<div class="whole">
		<input type="hidden" name="story_board_idx" value="${story_board_idx}">
		<form name="insert" method="POST" enctype="multipart/form-data">
		<div class="container">
		<h3><strong>펫스토리</strong></h3>
		<hr>
			<input type="hidden" name="member_idx" value="1">
			<div class="story_content">
				<input type="text" class="form-control" id="title" name="title" 
					value="${title}">
				
				 <label class="custom-file-label" for="files">사진 선택</label>
				<div class="row" id="selectedFiles">
					<c:if test="${getContent.fileDto.size() > 0}">
						<c:forEach var="image" items="${getContent.fileDto}">
							<div class="preview">
								<i class='fa fa-trash fa-2x' data-file="${image.file_name}" data-idx="${image.story_file_idx}" title='Click to remove'></i>
								<img src="${fullName}${image.file_path}" class='w-100 h-70'>
							</div>
						</c:forEach>
					</c:if>
				</div>
			<div class="form-group">
			 	<textarea class="form-control" name="content"
    			id="content" rows="3" ><c:out value="${content}"/></textarea>
  			</div>
  			
  			</div>
			<div id="info_tag">
				<input type="hidden" name="tag_idx" name="tag_idx" value="3">
				<input type="text" class="form-control" id="tag_name" name="tag_name" placeholder="태그를 입력해보세요." style="margin-bottom: 0;">
				<div class="tag_selected">
					<div id="hash-inbox">
					
					</div>					
				</div>
			</div>

			<div class="btn-group">
				<button type="submit" class="insert_btn btn btn-outline-dark" >등록</button>
				<button type="button" class="list_btn btn btn-outline-dark" OnClick="document.location.href='storyMain?currentPage=1'">목록</button>
			</div>
		</div>
	</form>
	<input type="file" class="custom-file-input" id="files" name="file" multiple>
	</div>
</div>
</body>
</html>