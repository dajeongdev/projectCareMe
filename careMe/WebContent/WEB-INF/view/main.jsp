<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<% String fullName = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/careMe/"; %>
<c:set var="fullName" value="<%=fullName%>" />
<meta charset="UTF-8">
<link rel="stylesheet" href="https://unpkg.com/swiper/css/swiper.min.css">

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<style>
@font-face { font-family: 'S-CoreDream-4Regular'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-4Regular.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'S-CoreDream-6Bold'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-6Bold.woff') format('woff'); font-weight: normal; font-style: normal; }
.cover-container { font-family: 'S-CoreDream-4Regular'; }
h3 { font-family: 'S-CoreDream-6Bold'; }
.container, .row-doctor1, .row-doctor2 {width: 1140px;}
.p-4 > h4 { padding-bottom: 5px;}
.no-gutters { background-color: #fff;}
.row-content { padding-top: 50px; }
.row-doctor1, .row-doctor2 { margin-left: 15px;}
.row-doctor1 > .col-sm-3, .row-doctor2 > .col-sm-3, .row-story > .col-sm-3 { width:285px; margin-left: 0 !important; padding-right: 0 !important; padding-left: 0 !important;}
.row-story > .col-sm-3 { padding-right: 0 !important; padding-left: 0 !important;}
.row-doctor2 > .col-sm-3 { padding-bottom: 10px;}
.row-story, .row-doctor { margin:0 auto !important; padding: 0 !important;}
.row-doctor > h3 { float:left; }
.doctor-content { margin: 0 auto;width: 1140px; }
#slide{position:relative;width: 1140px;}
 #slide li{
 position:absolute;
top:0;
left:0;
display:none;
-webkit-display:block;
}
#slide img{width:1140px; height: 300px;} 
</style>
<script>
imgslide(); //페이지가 로딩될때 함수를 실행합니다
function imgslide(){
	var val = $("#slide").attr("val"); //현재 이미지 번호를 가져옵니다
	 var mx = $("#slide").attr("mx"); //총 이미지 개수를 가져옵니다
	$("#img"+ val).hide(); //현재 이미지를 사라지게 합니다.
	if(val == mx){ val = 1; } //현재이미지가 마지막 번호라면 1번으로 되돌립니다.
	else{ val++; } //마지막 번호가 아니라면 카운트를 증가시켜줍니다
	$("#img" + val).fadeIn(500); //변경된 번호의 이미지영역을 나타나게 합니다.괄호 안에 숫자는 페이드인 되는 시간을 나타냅니다.
	$("#slide").attr('val',val); //변경된 이미지영역의 번호를 부여합니다.
	setTimeout('imgslide()',1000); //1초 뒤에 다시 함수를 호출합니다.(숫자값 1000당 1초)
}
</script>
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>메인 화면</title>
</head>

<body>

<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>

<div class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light"> 
	<div class="container min-vh-100 pt-3 text-center">
		
		<div class="row row-content">
			<h3>반려동물과 오랫동안 함께하고 싶다면 </h3>
			<div class="row mb-3">
			 <div class="col-md-4">
		      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
		        <div class="col-auto d-none d-lg-block">
		         <img class="card-img-top" height="250px" src="<%=request.getContextPath()%>/resources/img/main/dogwithfruit.jpg">
		        </div>
		        <div class="col p-4 d-flex flex-column position-static">
		          <h5>반려동물은 이러한 과일을 좋아해요!</h5>
		        </div>
		      </div>
		    </div>
		    
		   <div class="col-md-4">
		      <div class="row no-gutters border rounded overflow-hidden mb-4 shadow-sm h-md-250 position-relative">
		        <div class="col-auto d-none d-lg-block">
		          <img class="card-img-top" width="200px" height="250px" src="<%=request.getContextPath()%>/resources/img/main/friends.jpg">
		        </div>
		        <div class="col p-4 d-flex flex-column position-static">
		          <h5>초보 반려인의 궁금한 점을 풀어드립니다.</h5>
		        </div>

		      </div>
		    </div>
		    <div class="col-md-4">
		      <div class="row no-gutters border overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
		        <div class="col-auto d-none d-lg-block">
		          <img class="card-img-top" width="200px" height="250px" src="<%=request.getContextPath()%>/resources/img/main/sadpug.jpg">
		        </div>
		        <div class="col p-4 d-flex flex-column position-static">
		          <h5>강아지가 아플 때 보내는 신호는 무엇일까?</h5>
		        </div>    
		      </div>
		    </div>
			</div>
		</div>
		
		<div class="row-doctor">
		<div class="row">
		<h3>전문의 찾기</h3>
		<div class="doctor-content">
		<div class="row row-doctor1">
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-dog.jpg"></a></div>
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-cat.jpg"></a></div>
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-ham.jpg"></a></div>
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-parrot.jpg"></a></div>
		</div>
		<div class="row row-doctor2">
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-bunny.jpg"></a></div>
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-hedgehog.jpg"></a></div>
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-dog.jpg"></a></div>
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-dog.jpg"></a></div>
		</div>	
		</div>
		<h5>클릭해서 내 반려동물을 잘 케어할 수 있는 전문의 선생님들의 리스트를 살펴보세요!</h5>
		</div>
		</div>
		
		<div class="row row-story -content">
		    <ul class="slider" var="1" max="6">
		      <c:forEach items="${fList}" var="fList">
		        <li id="img"><img src="${fullName}${fList.file_path}"></li>
			  </c:forEach>
		    </ul>
		</div>		


		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalSample">modal</button>
	
		<div class="modal fade" id="modalSample" tabindex="-1" role="dialog">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">Modal title</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <p>Modal body text goes here.</p>
		        <p>Modal body text goes here.</p>
		        <p>Modal body text goes here.</p>
		        <p>Modal body text goes here.</p>        
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary">Save changes</button>
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
				
	</div>
</div>
<script src="https://unpkg.com/swiper/js/swiper.min.js"></script>
</body>
</html>