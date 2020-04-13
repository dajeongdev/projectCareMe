<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<spring:url value="/resources/css/carediary/main.css" var="mainCSS" />
<link rel="stylesheet" href="${mainCSS}">
<title>메인 화면</title>
</head>
<body>

<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>

<div class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light"> 
	<div class="container min-vh-100 pt-3">
		<!--  S: 내용작성 -->
		
		<div class="row mb-3 text-center">
			<div class="col-md-12">				
				<img alt="" src="https://images.theconversation.com/files/319652/original/file-20200310-61148-vllmgm.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=754&h=503&fit=crop&dpr=1" style="width:200px;border-radius:50%;">
				<h1>강아지1</h1>
			</div>
			
		
		</div>
		
		<div class="row mb-2 col-md-12">
		
			<div class="col-2 text-left">
				<button class="btn btn-success">일기작성</button>
			</div>
			
			<div class="col-10">
				
			</div>
		
		</div>
		
		<!-- S:diary -->
		<div class="col-md-12">
		
		    <div class="card b-1 hover-shadow mb-20">
		    	<footer class="card-footer flexbox align-items-center">
		            <div>
		                <strong>오늘의 일기 : </strong>
		                <span>21 Jan, 2018</span>
		            </div>
		            <div class="card-hover-show">
		                <a class="btn btn-xs fs-10 btn-bold btn-info" href="#">수정</a>
		                <a class="btn btn-xs fs-10 btn-bold btn-primary" href="#" data-toggle="modal" data-target="#modal-contact">질문하기</a>
		                <a class="btn btn-xs fs-10 btn-bold btn-warning" href="#">열기</a>
		            </div>
		        </footer>
		        
		        <div class="media card-body table-responsive">
		        	<div class="row">
		        	
			        	<div class="col-md-6 p-2">
			        		<div class="card-body">
				        		<table class="table-sm w-100 text-center">
					        		<thead class="border-bottom">
					        			<tr>
					        				<th>날짜</th>
					        				<th>산책</th>
					        				<th>소변</th>
					        				<th>대변</th>
					        				<th>몸무게</th>
					        			</tr>
					        		</thead>
					        		<tbody>
						        		<tr>
					        				<td>2020/03/26</td>
					        				<td>50/m</td>
					        				<td>
					        					<div class="color-circle"></div>
											</td>
					        				<td>무름</td>
					        				<td>20/kg</td>
					        			</tr>
					        		</tbody>
				        		</table>
			        		</div>
			        	</div>
			        	
			        	
			        	<div class="col-md-6 p-2">
			        		<div class="card-body">
				        		<p>
				        			오늘의 일기 오늴의일기 오늘의 일기 오늴의일기 오늘의 일기 오늴의일기
				        			오늘의 일기 오늴의일기  오늘의 일기 오늴의일기
				        		</p>
		        			</div>
			        	</div>
		        	</div>
		        </div>
		    </div>
		
		    <div class="card b-1 hover-shadow mb-20">
		        <footer class="card-footer flexbox align-items-center">
		            <div>
		                <strong>산책일기 : </strong>
		                <span>18 Jan, 2017</span>
		            </div>
		            <div class="card-hover-show">
		                <a class="btn btn-xs fs-10 btn-bold btn-info" href="#">Download CV</a>
		                <a class="btn btn-xs fs-10 btn-bold btn-primary" href="#" data-toggle="modal" data-target="#modal-contact">Contact</a>
		                <a class="btn btn-xs fs-10 btn-bold btn-warning" href="#">Delete</a>
		            </div>
		        </footer>
		        
		        <div class="media card-body">
		            <div class="media-left pr-12">
		                <img class="avatar avatar-xl no-radius" src="https://bootdey.com/img/Content/avatar/avatar2.png" alt="...">
		            </div>
		            <div class="media-body">
		                <div class="mb-2">
		                    <span class="fs-20 pr-16">Maryam Amiri</span>
		                </div>
		                <small class="fs-16 fw-300 ls-1">Designer</small>
		            </div>
		            <div class="media-right text-right d-none d-md-block">
		                <p class="fs-14 text-fade mb-12"><i class="fa fa-map-marker pr-1"></i> Fairfield, IA</p>
		                <span class="text-fade"><i class="fa fa-money pr-1"></i> $45 per hour</span>
		            </div>
		        </div>
		    </div>
		    
		    <div>
		    	<form action="" class="form-inline text-right">
					<div class="w-100">
						<input type="date" class="form-control mr-sm-2">
						<input type="text" class="form-control mr-sm-2">
			    		<button class="btn btn-secondary">검색</button>						
					</div>
				</form>
		    	
		    </div>
		
		    <nav>
		        <ul class="pagination justify-content-center">
		            <li class="page-item active">
		                <a class="page-link" href="#">1</a>
		            </li>
		            <li class="page-item"><a class="page-link" href="#">2</a></li>
		            <li class="page-item"><a class="page-link" href="#">3</a></li>
		            <li class="page-item"><a class="page-link" href="#">4</a></li>
		            <li class="page-item"><a class="page-link" href="#">5</a></li>
		        </ul>
		    </nav>
		    <br>
		    
		</div>
		<!-- E:diary -->

		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalSample">modal</button>
	
		<div class="modal fade" id="modalSample" tabindex="-1" role="dialog">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">나의 반려동물</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		      	<ul>
		      		<li>강아지1</li>
		      		<li>강아지2</li>
		      		<li>강아지3</li>
		      		<li>강아지4</li>
		      	</ul>
		      </div>
		      <div class="modal-footer text-center">		        
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
		      </div>
		    </div>
		  </div>
		</div>
			
		<!--  E: 내용작성 -->	
	</div>
</div>

</body>
</html>