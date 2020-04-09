<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="Spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="Form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false" />
<title>메인 화면</title>
</head>
<body>

	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />

	<div
		class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light">
		<div class="container min-vh-100 pt-3 text-center">


			<h2 align="left">자주 묻는 질문</h2>
			<p align="left"></p>
			
			<div class="row mb-2">
				<div class="col-md-6">
					<div
						class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<div class="col p-4 d-flex flex-column position-static">
							<strong class="d-inline-block mb-2 text-primary">World</strong>
							<h3 class="mb-0">Featured post</h3>
							<div class="mb-1 text-muted">Nov 12</div>
							<p class="card-text mb-auto">This is a wider card with
								supporting text below as a natural lead-in to additional
								content.</p>
							<a href="#" class="stretched-link">Continue reading</a>
						</div>
						<div class="col-auto d-none d-lg-block">{% include
							icons/placeholder.svg width="200" height="250"
							background="#55595c" color="#eceeef" text="Thumbnail" %}</div>
					</div>
				</div>
				<div class="col-md-6">
					<div
						class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<div class="col p-4 d-flex flex-column position-static">
							<strong class="d-inline-block mb-2 text-success">Design</strong>
							<h3 class="mb-0">Post title</h3>
							<div class="mb-1 text-muted">Nov 11</div>
							<p class="mb-auto">This is a wider card with supporting text
								below as a natural lead-in to additional content.</p>
							<a href="#" class="stretched-link">Continue reading</a>
						</div>
						<div class="col-auto d-none d-lg-block">{% include
							icons/placeholder.svg width="200" height="250"
							background="#55595c" color="#eceeef" text="Thumbnail" %}</div>
					</div>
				</div>
			</div>



		</div>
	</div>
</body>
</html>
