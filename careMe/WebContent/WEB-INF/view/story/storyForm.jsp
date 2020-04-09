<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<style>
.story_form { width: 100%; height: 100%; position: relative; }
.container {
	width: 1000px;
	width: 1000px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-left: -250px;
	margin-top: 100px;
}
#hash-search, #content, #title {
	width: 600px;
}
#hash-inbox { 
	background: #bdbdbd;
	width: 600px;
	height: 100px;
}
.added-tag {
	background: #82b1ff;
	padding: 5px 5px;
	margin: 5px 5px;
	border-radius: 10%;
	display: inline-block;
}
</style>
<title>스토리 글쓰기</title>
<script>
	// 해쉬태그 입력
$(function (){
	  $("#hash-search").on("keyup", function (e) {//해시태그 입력칸 이벤트
	    var tag = "";
	    var existed = false;//이미 태그에 올라갔나 확인하기 위함

	    if (e.which == 188 || e.which == 13) {//누른게 쉼표거나 엔터
	      tag = $(this).val().replace(/[\s,]+/g, ""); //쉼표나 엔터 ""으로 바꿔서 tag에 저장
	      $(this).val("");//입력창 비우기

	      $("#hash-inbox span").each(function () { //해시태그 들어간 div 안의 span
	        var name = $(this).find(".htag-name").val();//input hidden의 값
	        if (name == tag) existed = true;//이미 있음
	      });

	      if (tag != "" && !existed) { //태그가 빈문자열이 아니고 이미 올린게 아니면
	        $("#hash-inbox").append(
	            '<span class="added-tag">#' +
	            tag + '<a href="javascript:;"> X</a>' +
	            '<input type="hidden" class="htag-name" name="recipe[hashtags][][name]" value="' + tag + '" >' +
	            '</span> '
	        );
	      }
	    }
	  });
	  
	$("#hash-inbox").on("click", ".added-tag", function () {
		    $(this).remove();
	});
});
	$(document).ready(function() {
		var formObj = $("form[name='insert']");

		// 수정
		$(".insert_btn").on("click", function() {
			if(fn_valiChk()) {
				return false;
			}
			formObj.attr("action", "/story/storyEdit");
			formObj.attr("method", "post");
			formObj.submit();
		})
		// 삭제
		$(".delete_btn").on("click", function() {
			formObj.attr("action", "/story/stoyDelete");
			formObj.attr("method", "post");
			formObj.submit();
		})

		$("a[name='delete']").on("click", function(e) {
			e.preventDefault();
			fn_fileDelete($(this));
		})
		$("a[name='delete']").on("click", function(e) {
			e.preventDefault();
			fn_fileAdd($(this));
		})
	});
	function fn_valiChk() {
		var regForm = $("form[name='insert']) .chk").length;
		for(var i = 0; i < regForm; i++) {
			if($(".chk".eq(i).val() == "" || $(".chk").eq(i).val == null) {
				alert($(".chk").eq(i).attr("title"));
				return true;
			});
		}
	}
	function fn_fileDelete(obj) {
		obj.parent.remove();
	}
	function fn_fileAdd() {
		var str = "<p><input type='file' name='file' /><a href='#this' name='delete' class='btn'>삭제</a>";
		$("#fileDiv").append(str);
	
		$("a[name='delete']").on("click", function(e) {
			e.preventDefault();
			fn_fileDelete($(this));
		});
	}

</script>
</head>
<body>

<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>
<div class="story_form">
	<div class="container">
		<form name="insert" role="form" method="post" action="/story/update" enctype="multipart/form-data">
			<input type="hidden" name="story_board_idx" value="${update.story_board.idx}" readonly="readonly">
			<label for="title"></label>
			<input type="text" id="title" name="title" 
				value="${update.title}" placeholder="제목을 입력해주세요.">
			<button type="button" class="btn btn-outline-dark">사진추가</button>
			<div class="story_content">
				<input type="file" name="uploadFiles" multiple/>
				<a href="#this" name="delete" class="btn">삭제</a>
			<div class="form-group">
			 	<label for="content"></label>
    			<textarea class="form-control" name="content"
    			id="exampleFormControlTextarea1 content" rows="3" placeholder="스토리를 들려주세요."></textarea>
  			</div>
  			</div>
			<div id="info-tag">
				<input type="text" id="hash-search" placeholder="태그를 입력해보세요." style="margin-bottom: 0;">
				<div class="tag_selected">
					<div id="hash-inbox">
					</div>
				</div>
			</div>
			<button type="submit" class="insert_btn">등록</button>
			<button type="submit" class="list_btn">목록</button>
		</form>
	</div>
</div>
</body>
</html>