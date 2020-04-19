<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
.story_form {margin: 0 auto; padding: 0;}
.container {
	width: 1000px;
	margin: 40px;
}
#hash-search, #content, #title, .custom-file-label, #tag_name {
	width: 700px;
}
.custom-file-label {
	position: relative;
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
/*$(function (){
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
})*/
$(document).ready(function () {

    var tag = {};
    var counter = 0;

    // 태그를 추가한다.
    function addTag (value) {
        tag[counter] = value; // 태그를 Object 안에 추가
        counter++; // counter 증가 삭제를 위한 del-btn 의 고유 id 가 된다.
    }

    // 최종적으로 서버에 넘길때 tag 안에 있는 값을 array type 으로 만들어서 넘긴다.
    function marginTag () {
        return Object.values(tag).filter(function (word) {
            return word !== "";
        });
    }

    // 서버에 넘기기
    $("#tag-form").on("submit", function (e) {
        var value = marginTag(); // return array
        $("#rdTag").val(value); 

        $(this).submit();
    });

    $("#tag").on("keypress", function (e) {
        var self = $(this);

        // input 에 focus 되있을 때 엔터 및 스페이스바 입력시 구동
        if (e.key === "Enter" || e.keyCode == 32) {

            var tagValue = self.val(); // 값 가져오기

            // 값이 없으면 동작 ㄴㄴ
            if (tagValue !== "") {

                // 같은 태그가 있는지 검사한다. 있다면 해당값이 array 로 return 된다.
                var result = Object.values(tag).filter(function (word) {
                    return word === tagValue;
                })
            
                // 태그 중복 검사
                if (result.length == 0) { 
                    $("#tag-list").append("<li class='tag-item'>"+tagValue+"<span class='del-btn' idx='"+counter+"'>x</span></li>");
                    addTag(tagValue);
                    self.val("");
                } else {
                    alert("태그값이 중복됩니다.");
                }
            }
            e.preventDefault(); // SpaceBar 시 빈공간이 생기지 않도록 방지
        }
    });

    // 삭제 버튼 
    // 삭제 버튼은 비동기적 생성이므로 document 최초 생성시가 아닌 검색을 통해 이벤트를 구현시킨다.
    $(document).on("click", ".del-btn", function (e) {
        var index = $(this).attr("idx");
        tag[index] = "";
        $(this).parent().remove();
    });
})
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
			var form = $("#insert")[0];
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
				url: "/storyForm",
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

</script>
</head>
<body>

<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>
<div class="story_form col-md-4-order-md-2 mb-4">
	<div class="whole">
	<div class="container">
		<h3><strong>펫스토리</strong></h3>
		<hr>
			<input type="hidden" name="member_idx" value="1">
			<div class="story_content">
			<form name="insert" method="POST" action="storyForm" enctype="multipart/form-data">
				<input type="text" class="form-control" id="title" name="title" 
				placeholder="제목을 입력해주세요.">
				<input type="file" class="custom-file-input" id=inputGroupFile04 name="file">
				 <label class="custom-file-label" for="inputGroupFile04">사진 선택</label>
				<div class="row" id="selectedFiles"></div>
				<div id="preview">
				</div>
			<div class="form-group">
			 	<textarea class="form-control" name="content"
    			id="content" rows="3" placeholder="스토리를 들려주세요."></textarea>
  			</div>
  			</form>
  			</div>
			<div id="info-tag">
				<input type="hidden" name="tag_idx" name="tag_idx">
				<input type="text" class="form-control" id="tag_name" name="tag_name" placeholder="태그를 입력해보세요." style="margin-bottom: 0;">
				<div class="tag-list">
					
				</div>
			</div>		

			<div class="btn-group">
				<button type="submit" class="insert_btn btn btn-outline-dark" OnClick="document.location.href='storyDetail?story_board_idx=${story_board_idx}'">등록</button>
				<button type="submit" class="list_btn btn btn-outline-dark" OnClick="document.location.href='storyMain'">목록</button>
			</div>
	</div>
	</div>
</div>
</body>
</html>