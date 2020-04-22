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
						<div class="col-12 col-sm-6 col-md-4 d-flex align-items-stretch">
							<div class="card bg-light">
								<div class="card-header text-muted border-bottom-0">
									Digital Strategist</div>
								<div class="card-body pt-0">
									<div class="row">
										<div class="col-7">
											<h2 class="lead">
												<b>Nicole Pearson</b>
											</h2>
											<p class="text-muted text-sm">
												<b>About: </b> Web Designer / UX / Graphic Artist / Coffee
												Lover
											</p>
											<ul class="ml-4 mb-0 fa-ul text-muted">
												<li class="small"><span class="fa-li"><i
														class="fas fa-lg fa-building"></i></span> Address: Demo Street
													123, Demo City 04312, NJ</li>
												<li class="small"><span class="fa-li"><i
														class="fas fa-lg fa-phone"></i></span> Phone #: + 800 - 12 12 23
													52</li>
											</ul>
										</div>
										<div class="col-5 text-center">
											<img src="../../dist/img/user1-128x128.jpg" alt=""
												class="img-circle img-fluid">
										</div>
									</div>
								</div>
								<div class="card-footer">
									<div class="text-right">
										<a href="#" class="btn btn-sm bg-teal"> <i
											class="fas fa-comments"></i>
										</a> <a href="#" class="btn btn-sm btn-primary"> <i
											class="fas fa-user"></i> View Profile
										</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-12 col-sm-6 col-md-4 d-flex align-items-stretch">
							<div class="card bg-light">
								<div class="card-header text-muted border-bottom-0">
									Digital Strategist</div>
								<div class="card-body pt-0">
									<div class="row">
										<div class="col-7">
											<h2 class="lead">
												<b>Nicole Pearson</b>
											</h2>
											<p class="text-muted text-sm">
												<b>About: </b> Web Designer / UX / Graphic Artist / Coffee
												Lover
											</p>
											<ul class="ml-4 mb-0 fa-ul text-muted">
												<li class="small"><span class="fa-li"><i
														class="fas fa-lg fa-building"></i></span> Address: Demo Street
													123, Demo City 04312, NJ</li>
												<li class="small"><span class="fa-li"><i
														class="fas fa-lg fa-phone"></i></span> Phone #: + 800 - 12 12 23
													52</li>
											</ul>
										</div>
										<div class="col-5 text-center">
											<img src="../../dist/img/user2-160x160.jpg" alt=""
												class="img-circle img-fluid">
										</div>
									</div>
								</div>
								<div class="card-footer">
									<div class="text-right">
										<a href="#" class="btn btn-sm bg-teal"> <i
											class="fas fa-comments"></i>
										</a> <a href="#" class="btn btn-sm btn-primary"> <i
											class="fas fa-user"></i> View Profile
										</a>
									</div>
								</div>
							</div>
						</div>
						<div class="col-12 col-sm-6 col-md-4 d-flex align-items-stretch">
							<div class="card bg-light">
								<div class="card-header text-muted border-bottom-0">
									Digital Strategist</div>
								<div class="card-body pt-0">
									<div class="row">
										<div class="col-7">
											<h2 class="lead">
												<b>Nicole Pearson</b>
											</h2>
											<p class="text-muted text-sm">
												<b>About: </b> Web Designer / UX / Graphic Artist / Coffee
												Lover
											</p>
											<ul class="ml-4 mb-0 fa-ul text-muted">
												<li class="small"><span class="fa-li"><i
														class="fas fa-lg fa-building"></i></span> Address: Demo Street
													123, Demo City 04312, NJ</li>
												<li class="small"><span class="fa-li"><i
														class="fas fa-lg fa-phone"></i></span> Phone #: + 800 - 12 12 23
													52</li>
											</ul>
										</div>
										<div class="col-5 text-center">
											<img src="${adminResources}dist/img/user1-128x128.jpg" alt=""
												class="img-circle img-fluid">
										</div>
									</div>
								</div>
								<div class="card-footer">
									<div class="text-right">
										<a href="#" class="btn btn-sm bg-teal"> <i
											class="fas fa-comments"></i>
										</a> <a href="#" class="btn btn-sm btn-primary"> <i
											class="fas fa-user"></i> View Profile
										</a>
									</div>
								</div>
							</div>
						</div>
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
													<c:forEach var="major" items="${doctor.doctorMajorDto}">
														${major.pet_species_idx}
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
						<ul class="pagination justify-content-center m-0">
							<li class="page-item active"><a class="page-link" href="#">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">4</a></li>
							<li class="page-item"><a class="page-link" href="#">5</a></li>
							<li class="page-item"><a class="page-link" href="#">6</a></li>
							<li class="page-item"><a class="page-link" href="#">7</a></li>
							<li class="page-item"><a class="page-link" href="#">8</a></li>
						</ul>
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