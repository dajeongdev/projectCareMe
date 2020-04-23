<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.careme.model.dto.MemberDto"%>
<spring:url value="/resources/admin/" var="adminResources" />
<%
	String hostname = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ "/careMe/";
%>
<c:set var="hostname" value="<%=hostname%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false" />
<spring:url value="/resources/css/carediary/main.css" var="mainCSS" />
<link rel="stylesheet" href="${mainCSS}">

<!-- Font Awesome -->
<link rel="stylesheet"
	href="${adminResources}plugins/fontawesome-free/css/all.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="${adminResources}dist/css/adminlte.min.css">
<!-- Google Font: Source Sans Pro -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">

<script>
	$(function() {
		$(".pagination").addClass("justify-content-center");
	})
</script>

<title>전문의 찾기</title>

</head>
<body>
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />
	<!-- S:인기의사 -->
	<div class="container-lg">
		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>인기</h1>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->
		</section>

		<!-- Main content -->
		<section class="content">

			<!-- Default box -->
			<div class="card card-solid">
				<div class="card-body pb-0">
					<div class="row d-flex align-items-stretch">
						<c:forEach var="doctor" items="${popularDoctors}">
							<div class="col-12 col-sm-6 col-md-4 d-flex align-items-stretch">
								<div class="card bg-light">
									<div class="card-header text-muted border-bottom-0">
										Digital Strategist</div>
									<div class="card-body pt-0">
										<div class="row">
											<div class="col-7">
												<h2 class="lead">
													<b>${doctor.doctorDto.doctor_name}</b>
												</h2>
												<p class="text-muted text-sm">
													<b>전문분야: </b>
													<c:forEach var="major" items="${doctor.doctorMajor}">
														${major.pet_species_name}
													</c:forEach>
												</p>
												<ul class="ml-4 mb-0 fa-ul text-muted">
													<li class="small"><span class="fa-li">
														<i class="fas fa-lg fa-building"></i></span> 병원: ${doctor.doctorDto.doctor_hospital_name}
													</li>
													<li class="small"><span class="fa-li">
														<i class="fas fa-lg fa-phone"></i></span> Phone #: ${doctor.doctorDto.doctor_hospital_tel}
													</li>
												</ul>
											</div>
											<div class="col-5 text-center">
												<img src="${adminResources}/dist/img/user1-128x128.jpg" alt="" class="img-circle img-fluid">
											</div>
										</div>
									</div>
									<div class="card-footer">
										<div class="text-right">
											<a href="#" class="btn btn-sm bg-teal"><i class="fas fa-comments"></i></a> 
											<a href="#" class="btn btn-sm btn-primary"><i class="fas fa-user"></i> View Profile</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- /.card-body -->
			</div>
			<!-- /.card -->

		</section>
		<!-- /.content -->
	</div>
	<!-- E:인기의사 -->


	<!-- Site wrapper -->
	<div class="container-lg">

		<!-- Content Header (Page header) -->
		<section class="content-header">
			<div class="container-fluid">
				<div class="row mb-2">
					<div class="col-sm-6">
						<h1>Contacts</h1>
					</div>
				</div>
				
				<div class="card card-solid">
					<div class="card-body pb-0">
						<div class="row d-flex align-items-stretch">
							고양이, 강아지, 거북이, 앵무새, 말, 소
						</div>
					</div>
				</div>
			</div>
			<!-- /.container-fluid -->
		</section>

		<!-- Main content -->
		<section class="content">

			<!-- Default box -->
			<div class="card card-solid">
				<div class="card-body pb-0">
					<div class="row d-flex align-items-stretch">
						<c:forEach var="doctor" items="${doctors}">
							<div class="col-12 col-sm-6 col-md-4 d-flex align-items-stretch">
								<div class="card bg-light">
									<div class="card-header text-muted border-bottom-0">
										Digital Strategist</div>
									<div class="card-body pt-0">
										<div class="row">
											<div class="col-7">
												<h2 class="lead">
													<b>${doctor.doctorDto.doctor_name}</b>
												</h2>
												<p class="text-muted text-sm">
													<b>전문분야: </b>
													<c:forEach var="major" items="${doctor.doctorMajor}">
														${major.pet_species_name}
													</c:forEach>
												</p>
												<ul class="ml-4 mb-0 fa-ul text-muted">
													<li class="small"><span class="fa-li">
														<i class="fas fa-lg fa-building"></i></span> 병원: ${doctor.doctorDto.doctor_hospital_name}
													</li>
													<li class="small"><span class="fa-li">
														<i class="fas fa-lg fa-phone"></i></span> Phone #: ${doctor.doctorDto.doctor_hospital_tel}
													</li>
												</ul>
											</div>
											<div class="col-5 text-center">
												<img src="${adminResources}/dist/img/user1-128x128.jpg" alt="" class="img-circle img-fluid">
											</div>
										</div>
									</div>
									<div class="card-footer">
										<div class="text-right">
											<a href="#" class="btn btn-sm bg-teal"><i class="fas fa-comments"></i></a> 
											<a href="#" class="btn btn-sm btn-primary"><i class="fas fa-user"></i> View Profile</a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</div>
				<!-- /.card-body -->
				<div class="card-footer">
					<nav aria-label="Contacts Page Navigation">
						<jsp:include page="/WEB-INF/view/include/paging.jsp" flush="false" />
					</nav>
				</div>
				<!-- /.card-footer -->
			</div>
			<!-- /.card -->

		</section>
		<!-- /.content -->

	</div>
	<!-- ./wrapper -->

</body>
</html>