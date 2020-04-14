<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/img/Tux.svg" var="default_image" />
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
		
		<div class="row mb-2">
		
			<div class="col-2 text-left">
				<button class="btn btn-success" onclick="location.href='carediary/write'">일기작성</button>
			</div>
			
			<div class="col-10 text-right">
				<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalSample">목록</button>
			</div>
		
		</div>
		
		<!-- S:diary -->
		<div class="row">
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
			                <button type="button" class="btn btn-xs fs-10 btn-bold btn-warning" data-toggle="collapse" data-target="#images" aria-expanded="false" aria-controls="images">열기</button>
			                <!-- <a class="btn btn-xs fs-10 btn-bold btn-warning" href="#">열기</a> -->
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
				        	
				        	<div class="col-md-12 p-2 collapse" id="images">
				        		<div class="card-body">
				        			<div class="row">
				        				<div class="col-md-3">
				        					<img src="https://images.theconversation.com/files/319652/original/file-20200310-61148-vllmgm.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=754&h=503&fit=crop&dpr=1">
					        			</div>
					        			<div class="col-md-3">
						        			<img src="https://images.theconversation.com/files/319652/original/file-20200310-61148-vllmgm.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=754&h=503&fit=crop&dpr=1">
					        			</div>
					        			<div class="col-md-3">
						        			<img src="https://images.theconversation.com/files/319652/original/file-20200310-61148-vllmgm.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=754&h=503&fit=crop&dpr=1">
					        			</div>
				        			</div>
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
			    
			</div>
		</div>
		<!-- E:diary -->
		
		<div class="row">
		    <div class="col-8 mx-auto mb-3">
		    	<form action="" class="form-inline text-right">
					<div class="w-100">
						<input type="date" class="form-control mr-sm-2">
						<input type="text" class="form-control mr-sm-2">
			    		<button class="btn btn-secondary">검색</button>						
					</div>
				</form>
		    </div>
		
			<div class="col-8 mx-auto">
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
		    
		    
		</div>
	
		

		
	
		<div class="modal fade" id="modalSample" tabindex="-1" role="dialog">
		  <div class="modal-dialog modal-md" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">나의 반려동물</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		      	<div class="row">
		      		<div class="col-md-12">
		      			<div class="people-nearby">

		      				<div class="nearby-user">
		      					<div class="row">
		      						<div class="col-md-2 col-sm-2">
		      							<img src="${default_image}" alt="user" class="profile-photo-lg">
		      						</div>
		      						<div class="col-md-7 col-sm-7">
			      						<h5><a href="#" class="profile-link">강아지1</a></h5>
			      						<p class="text-muted">2020/04/12 updated</p>
			      					</div>
			      					<div class="col-md-3 col-sm-3">
			      						<button class="btn btn-primary pull-right">선택</button>
		      						</div>
		      					</div>
		      				</div>
		      				
		      				<div class="nearby-user">
		      					<div class="row">
		      						<div class="col-md-2 col-sm-2">
		      							<img src="${default_image }" alt="user" class="profile-photo-lg">
		      						</div>
		      						<div class="col-md-7 col-sm-7">
			      						<h5><a href="#" class="profile-link">강아지2</a></h5>
			      						<p class="text-muted">2020/04/11 updated</p>
			      					</div>
			      					<div class="col-md-3 col-sm-3">
			      						<button class="btn btn-primary pull-right">선택</button>
		      						</div>
		      					</div>
		      				</div>
		      				
	      					<div class="row text-center">
	      						<div class="col-8 m-auto">
	      							<button class="btn btn-success">추가</button>
	      						</div>
	      					</div>
		      				
		      			</div>
		      		</div>
		      	</div>
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