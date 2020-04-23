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
@font-face { font-family: 'GmarketSansMedium'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'GmarketSansBold'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff'); font-weight: normal; font-style: normal; }
.story_form {margin: 0 auto; padding: 0;}
h3 { font-family: 'GmarketSansBold'; }
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
	font-family: 'GmarketSansMedium';
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
#tag-list { 
	margin-top:2px;
	background: #bdbdbd;
	width: 1000px;
	height: 100px;
}
.hashTag { 
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
//태그를 저장할 배열
var tags = [];
var tagNames = [];
//태그를 보여줄 element
$(function() {
	$("#tag").on("keypress", function (e) {
		if (e.key === "Enter" || e.keyCode == 32) {
			var inputText = $("#tag").val(); // input 태그에 입력한 값
			
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
	var url = $(location).attr('pathname') + "/hashCheck"
	var board_idx = 
	$.ajax({
		type: "get",
		url: url + "?tag_name=" + tag,
		dataType:"json"
	}).done(function(data) {
		console.log(data);
		
		//배열에 tag의 idx를 넣어준다
		var idx = data.tag_idx;
		var name = data.tag_name;
		var html = "<span class='hashTag' data-idx=" + idx + ">" + "#" + name + "<a href='javascript:;'>X</a>" + "</span>";

		//서버에 보낼 배열에 넣기
		tags.push(idx);
		$("#rdTag").val(tags);
		// input enter 눌렸을때 input 있는 value text 와 배열에 있는 text를 비교해서 있으면 중복알림! 없으면 ajax!
		tagNames.push(name);

		// 태그 붙이기
		$("#tag-list").append(html);
		// input 비우기
		$("#tag").val("");
		
		//alert("성공!");
	}).fail(function() {
		alert("실패!");
	});
	$("#tag-list").on("click", ".hashTag", function () {
		$(this).remove();
	});
}
var storedFiles = [];
var deletedFiles = [];
var selDivs = "";

$(function() {
	selDiv = $("#selectedFiles");

	$("#files").on("change", handleFileSelect);
	
	$("body").on("click", ".fa-trash", removeFile);

	form = $("form[name='storyEdit']")[0];
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
		<input type="hidden" id="member_idx" name="member_idx" value="${member_idx}">
		<form name="storyEdit" method="POST" enctype="multipart/form-data">
		<div class="container">
		<h3><strong>펫스토리</strong></h3>
		<hr>
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
				<input type="hidden" name="tag" id="rdtag">
				<input type="text" class="form-control" id="tag" placeholder="태그를 입력해보세요." style="margin-bottom: 0;">
				<div id="tag-list">
					<c:forEach var="taging" items="${tags}">
						<span class='hashTag'><c:out value="${taging.tag_name}" /><a href='javascript:;'>X</a></span>
					</c:forEach>				
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