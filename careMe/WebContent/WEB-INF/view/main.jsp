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
.p-4 > h4 { padding-bottom: 5px;}
.no-gutters { background-color: #fff;}
.row-content { padding-top: 50px; }
.row-doctor1 > .col-sm-3 { width: 960px; padding-right: 0 !important; padding-left: 0 !important;}
.row-doctor2 > .col-sm-3 { width: 960px; padding-right: 0 !important; padding-left: 0 !important;}
.row-story > .col-sm-3 { width: 960px; padding-right: 0 !important; padding-left: 0 !important;}
.row-story, .row-doctor { width: 960px;}
.row-doctor > h3 { float:left; }
</style>
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
		<div class="row row-doctor1">
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="200px" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-dog.jpg"></a></div>
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="200px" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-cat.jpg"></a></div>
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="200px" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-ham.jpg"></a></div>
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="200px" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-parrot.jpg"></a></div>
		</div>
		<div class="row row-doctor2">
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="200px" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-bunny.jpg"></a></div>
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="200px" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-hedgehog.jpg"></a></div>
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="200px" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-dog.jpg"></a></div>
			<div class="col-sm-3 col-auto"><a href="#"><img width="100%" height="200px" class="img" src="<%=request.getContextPath()%>/resources/img/main/doctor-dog.jpg"></a></div>
		</div>
		<h5>클릭해서 내 반려동물을 잘 케어할 수 있는 전문의 선생님들의 리스트를 찾아보세요!</h5>
		</div>
		</div>
		
		<div class="row row-story">
		<form action="main" name="mainImage">
		 <div class="swiper-container">
    		<div class="swiper-wrapper">
    			
    			<c:forEach items="${fList}" var="fList">
      				<div class="swiper-slide"><img width="100%" height="200" class="img" src="${fullName}${fList.file_path}"></div>
      			</c:forEach>
      			
    		</div>
    <!-- Add Arrows -->
   			<div class="swiper-button-next"></div>	
    		<div class="swiper-button-prev"></div>
  		</div>
  		</form>
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