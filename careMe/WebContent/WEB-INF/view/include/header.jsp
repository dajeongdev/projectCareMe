<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<% String uri = request.getRequestURI().toString(); %>
<spring:url value="/resources/img/dog.jpg" var="profile" />
<c:set var="uri" value="<%=uri%>" />
${uri}

<header class="blog-header py-3" style="padding:1rem;">
	<div class="row flex-nowrap justify-content-between align-items-center">
		<div class="col-4 pt-1">
			<a class="text-muted" href="#">Subscribe</a>
		</div>
		<div class="col-4 text-center">
			<a class="blog-header-logo text-dark" href="#">LOGO</a>
		</div>
		<div class="col-4 d-flex justify-content-end align-items-center">
			<img src="${profile}" style="padding:5px;width:60px;">
			<a class="btn btn-sm btn-outline-secondary" href="#">Sign up</a>
		</div>
	</div>
</header>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
	<a class="navbar-brand" href="#">CARE ME</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbars" aria-controls="navbars" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>
	
	<div class="collapse navbar-collapse" id="navbars">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a class="nav-link" href="view/doctorBoardView/doctorBoard">전문가 상담<span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">케어일기</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">전문의 찾기</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">펫스토리</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="#">고민상담</a>
			</li>
		</ul>
		<form class="form-inline my-2 my-md-0">
			<input class="form-control" type="text" placeholder="Search">
		</form>
	</div>
</nav>

