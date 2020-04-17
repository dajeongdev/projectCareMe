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

<script>
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


<script>
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
</script>

<!-- <script>
$(function(){
	$("#tag").on("keypress", function (e) {
   	 var self = $(this);
   	 if (e.key === "Enter" || e.keyCode == 32) {
	    var tagValue = self.val();
		var url ="casualWriteForm/hashCheck?tagValue="+tagValue;
		$.ajax(
			{type:"get",
			url:url,
			dataType:"json"})
			.done(function(compared){
			
			if (compared.length>0){
				for (i in compared){
					var h = compared[i]
					var taging = "<li class='tag-item'>#"+h.tag_name+"<span class='del-btn' idx='"+counter+"'>x</span></li>"
					$("#tag-list").append(taging);
				}
			}else{
				var taging = "<li class='tag-item'>#"+tagValue+"<span class='del-btn' idx='"+counter+"'>x</span></li>"
				$("#tag-list").append(taging);
			}
			}).fail(function(e){
				alert(e.responseText);
				});
			}
    	})
	})

</script>
-->


</head>
<body>
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />

	<div class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light">
		<div class="container min-vh-100 pt-3 text-center">

		<form name="addWrite" action="casualBoardWriteAdd" method="POST" enctype="multipart/form-data">
			<div class="row my-3 p-3 bg-white rounded shadow-sm">
				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-4">
					
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

						<!-- 동물 종류 찾기 -->

						<div class="row" width="100%">
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
							<textarea name="content" style="width: 100%; height: 500px"></textarea>
							<br> 
						</div>
						
						<p><input name="member_idx" type="text" id="subject"></p>
						
						<div align="left">
						<label for="file">파일첨부</label><br>
							<input name="file" type="file" />
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
					<input type="submit" value="제출"> 
					<input type="reset" value="다시쓰기"> 
					<input type="button" value="목록으로" OnClick="location.href='casualBoard'">
				</div>	
				
			</div>
			
		</form>
		</div>
	</div>
</body>
</html>