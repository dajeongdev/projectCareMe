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
			{type:"get",
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
<!--<script>		
HASHTAG
$(function() {
    $(document).ready(function () {
        var tag = {};
        var counter = 0;
        // 태그를 추가
        function addTag (value) {
            tag[counter] = value; // Object 안에 tag 추가
            counter++; // counter 증가 삭제를 위한 del-btn id
        }
        // 최종적으로 서버에 넘길때 tag 안에 있는 값을 array type 으로 만들어서 넘김
        function marginTag () {
            return Object.values(tag).filter(function (word) {
                return word !== "";
            });
        }
        // 서버로 제출
        $(document).on("submit", function (e) {
            var value = marginTag(); // return array
            $("#rdTag").val(value); 
            $(this).submit();
        });
        $("#tag").on("keypress", function (e) {
            var self = $(this);
            if (e.key === "Enter" || e.keyCode == 32) {
                var tagValue = self.val();
                if (tagValue !== "") {
                    // 중복검사 겹치면 해당값 array 로 return
                    var result = Object.values(tag).filter(function (word) {
                        return word === tagValue;
                    })
                    // 태그 중복 검사
                    if (result.length == 0) { 
						var url = "casualWriteFrom?tagValue="+tagValue+"&member_idx="+member_idx;
                        $.ajax({
                            type:"get",
                            url=url,
                            dataType:"json"})
                            .done(function(compared){
                                if(compared.length>0){
                                    for(i in compared){
                                        var list=compared[i]
                                        $("#tag-list").append("<li class='tag-item'>#"+list.tag_name+"<span class='del-btn' idx='"+counter+"'>X</span></li>");
                                        addTag(list.tag_name);
                                        self.val("");
                                        } 
                                    }
                                }).fail(function(e) {
                					alert(e.responseText);
                				});
                     } else {
                        alert("태그값이 중복됩니다!");
                    }
                }
                e.preventDefault();
            }
        });
        // 삭제 버튼 
        $(document).on("click", ".del-btn", function (e) {
            var index = $(this).attr("idx");
            tag[index] = "";
            $(this).parent().remove();
        });
	})
})	
	
</script>-->

<script>

var storedFiles = [];
var selDivs = "";

$(function() {

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
          					<input id="title" name="title" width="100%" type="text" class="form-control" value="${mlist.title}"/>
        				</div>
						<input name="question_type" type="hidden" value="n" /> 
						<input name="is_private" type="hidden" value="n" /> 
						<input name="doctor_idx" type="hidden" value="1" /> 
						<input name="pet_idx" type="hidden" value="1" />
						<input name="member_idx" type="hidden" id="subject" value="${info.member_idx}">

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

						<div align="left">
							내용<br>
							<textarea name="content" style="width: 100%; height: 250px" >${mlist.content}</textarea>
							<br> 
						</div>
						
						
						
						<!--<div align="left">
						<label for="file">파일첨부</label><br>
							<input type="file" name="file" id="file" multiple/>
							<div class="row" id="selectedFiles"></div>
							<div id="preview"></div>
    					</div> -->


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
    					
    					
						
					<div class="content" align="left">
       					<input type="hidden" value="" name="tag" id="rdTag" />
    			   	<div>
     			       <input type="text" id="tag" size="7" placeholder="태그입력" />
     			    </div><br>  
    			    
    			    <ul id="tag-list"></ul>

    			   	</div> 
    			  </main>
			</div>		
						
			<div class="row">
				
				<div class="col-md-12" align="center">			
					<input type="submit" class="btn btn-dark btn-sm" value="수정"> 
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