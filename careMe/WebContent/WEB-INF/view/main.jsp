<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<% String fullName = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/careMe/"; %>
<c:set var="fullName" value="<%=fullName%>" />
<meta charset="UTF-8">	
<style>
@font-face { font-family: 'GmarketSansMedium'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'GmarketSansBold'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff'); font-weight: normal; font-style: normal; }
.main {margin: 40px; width: 100%;}
.cover-container, p ,h5 { font-family: 'GmarketSansMedium'; }
h3 { font-family: 'GmarketSansBold'; }
.row-doctor > p { margin-bottom: 0 !important; padding-left: 40px;}
.container, .row-doctor1, .row-doctor2 {width: 1140px;}
.p-4 > h4 { padding-bottom: 5px;}
.row-content { padding-bottom: 20px; }
.row-doctor1, .row-doctor2 { margin-left: 20px;}
.row-doctor1 > .col-sm-3, .row-doctor2 > .col-sm-3, .row-story > .col-sm-3 { width:285px; margin-left: 0 !important; padding-right: 0 !important; padding-left: 0 !important;}
.row-story > .col-sm-3 { padding-right: 0 !important; padding-left: 0 !important;}
.row-doctor2 > .col-sm-3 { padding-bottom: 10px;}
.row-doctor { margin:0 auto !important; padding: 0 !important; padding-bottom: 20px;}}
.row-doctor > h3 { float:left; }
.doctor-content { margin: 0 auto;width: 1140px; }
hr { width: 1140px; margin-top: 0!important; }
</style>

<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>메인 화면</title>
</head>
<body>
<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>
	<div class="main">
	<div class="container min-vh-100 pt-3 text-center">		
		<div class="row row-content">
			<h3>반려동물과 오랫동안 함께하고 싶다면 </h3>
			<hr>
			<div class="row mb-3">
			 <div class="col-md-4">
		      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
		        <div class="col-auto d-none d-lg-block">
		         <img class="card-img-top" height="250px" src="<%=request.getContextPath()%>/resources/img/main/dogwithfruit.jpg">
		        </div>
		        <div class="col p-4 d-flex flex-column position-static">
		          <h5>반려동물은 이런 과일을 좋아해요!</h5>
		          <br>
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
		
		<div class="row row-doctor">
		<h3>전문의 찾기</h3>
		<p>클릭해서 내 반려동물을 잘 케어할 수 있는 전문의 선생님들의 리스트를 살펴보세요!</p>
		<hr>
		<div class="doctor-content">
			<div class="row row-doctor1">
				<div class="col-sm-3 col-auto"><a href="/findDoctor?petSpec=1"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-dog.jpg"></a></div>
				<div class="col-sm-3 col-auto"><a href="/findDoctor?petSpec=2"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-cat.jpg"></a></div>
				<div class="col-sm-3 col-auto"><a href="/findDoctor?petSpec=6"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-ham.jpg"></a></div>
				<div class="col-sm-3 col-auto"><a href="/findDoctor?petSpec=3"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-parrot.jpg"></a></div>
			</div>
			<div class="row row-doctor2">
				<div class="col-sm-3 col-auto"><a href="/findDoctor?petSpec=4"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-turtles.jpg"></a></div>
				<div class="col-sm-3 col-auto"><a href="/findDoctor?petSpec=5"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-raccoon.jpg"></a></div>
				<div class="col-sm-3 col-auto"><a href="/findDoctor?petSpec=7"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-horse.jpg"></a></div>
				<div class="col-sm-3 col-auto"><a href="/findDoctor?petSpec=9"><img width="100%" height="220" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-fish.jpg"></a></div>
			</div>	
		</div>
		</div>
		
		<div class="row row-story">
			
			          
		</div>

	</div>
	</div>
</body>
</html>