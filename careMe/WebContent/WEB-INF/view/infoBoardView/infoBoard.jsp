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



			<div class="row mb-2">
				<div class="col-md-6">
					<div
						class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<div class="col p-4 d-flex flex-column position-static">
							<strong class="d-inline-block mb-2 text-primary">건강/질병</strong>
							<h3 class="mb-0">당신 강아지의 피부는?</h3>
							<div class="mb-1 text-muted">April 3</div>
							<p class="card-text mb-auto">견종에 따라 피부내 기름양에 따라 피부병이 발생할 수 있습니다.</p>
							<a href="#" class="stretched-link">더보기</a>
						</div>
						<div class="col-auto d-none d-lg-block">
							<img src="<%=request.getContextPath()%>/resources/img/infoBoardThumbnail.jpg" width="200" height="250"/>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div
						class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<div class="col p-4 d-flex flex-column position-static">
							<strong class="d-inline-block mb-2 text-success">행동/교육</strong>
							<h3 class="mb-0">앵무새의 습성</h3>
							<div class="mb-1 text-muted">April 5</div>
							<p class="mb-auto">앵무새가 흔히 사람만을 따라한다고 하죠. 그 진실은?</p>
							<a href="#" class="stretched-link">더보기</a>
						</div>
						<div class="col-auto d-none d-lg-block">
							<img src="<%=request.getContextPath()%>/resources/img/infoBoardThumbnail.jpg" width="200" height="250"/>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div
						class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<div class="col p-4 d-flex flex-column position-static">
							<strong class="d-inline-block mb-2 text-success">제품/사료</strong>
							<h3 class="mb-0">여물은 선택은 이렇게</h3>
							<div class="mb-1 text-muted">Nov 11</div>
							<p class="mb-auto">당신의 소는 무엇을 먹고 있습니까.</p>
							<a href="#" class="stretched-link">더보기</a>
						</div>
						<div class="col-auto d-none d-lg-block">
							<img src="<%=request.getContextPath()%>/resources/img/infoBoardThumbnail.jpg" width="200" height="250"/>
						</div>
					</div>
				</div>
				<div class="col-md-6">
					<div
						class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
						<div class="col p-4 d-flex flex-column position-static">
							<strong class="d-inline-block mb-2 text-success">기 타</strong>
							<h3 class="mb-0">파충류를 키울 때 주의점</h3>
							<div class="mb-1 text-muted">April 10</div>
							<p class="mb-auto">어떤 파충류를 키우는가에 따라 환경에 많은 신경써야합니다.</p>
							<a href="#" class="stretched-link">더보기</a>
						</div>
						<div class="col-auto d-none d-lg-block">
							<img src="<%=request.getContextPath()%>/resources/img/infoBoardThumbnail.jpg" width="200" height="250"/>
						</div>
					</div>
				</div>
			</div>



		</div>
	</div>
</body>
</html>
