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
							<%@ include file="/WEB-INF/view/findDoctor/doctorCard.jspf" %>
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
					<div class="card-body p-1 pl-3">
						<div class="row d-flex align-items-stretch">
							<a href="findDoctor" class="mx-1">
								<small class="badge
									<c:if test="${petSpec == '0'}">badge-primary</c:if>
									<c:if test="${petSpec != null}">badge-secondary</c:if> ">
									전체
								</small>
							</a>
							<c:forEach var="spec" items="${petSpecs}">
								<a href="findDoctor?petSpec=${spec.pet_species_idx}" class="mx-1">
									<small class="badge
										<c:if test="${petSpec == spec.pet_species_idx}">badge-primary</c:if>
										<c:if test="${petSpec != spec.pet_species_idx}">badge-secondary</c:if> ">
										${spec.pet_species_name}
									</small>
								</a>
							</c:forEach>							
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
							<%@ include file="/WEB-INF/view/findDoctor/doctorCard.jspf" %>
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