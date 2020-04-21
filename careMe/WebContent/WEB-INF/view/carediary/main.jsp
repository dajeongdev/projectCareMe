<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.careme.model.dto.MemberDto"%>
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
<title>메인 화면</title>
<script>
	$(function() {
		$(".pagination").addClass("justify-content-start");
	})
	
</script>
</head>
<body>

	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />

	<div
		class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light">
		<div class="container min-vh-100 pt-3">
			<!--  S: 내용작성 -->

			<div class="row mb-3 text-center">
				<div class="col-md-12">
					<img alt=""
						src="https://images.theconversation.com/files/319652/original/file-20200310-61148-vllmgm.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=754&h=503&fit=crop&dpr=1"
						style="width: 200px; border-radius: 50%;">
					<h1>강아지1</h1>
					<div>
						<button class="btn btn-dark btn-sm">수정</button>
					</div>
				</div>


			</div>

			<div class="row mb-2">
				<div class="col-2 text-left">
					<button class="btn btn-dark btn-sm" onclick="location.href='write'">일기작성</button>
				</div>

				<div class="col-10 text-right">
					<button type="button" class="btn btn-dark btn-sm"
						data-toggle="modal" data-target="#modalSample">목록</button>
				</div>
			</div>

			<!-- S:diary list box -->
			<div class="row">
				<div class="col-md-12">
					<!-- S:diary -->
					<c:if test="${articles.size() > 0}">
						<c:forEach var="article" items="${articles}" varStatus="status">
							<div class="card b-1 hover-shadow mb-20">
								<footer class="card-header flexbox align-items-center">
									<div>
										<strong>${article.diary.title} : </strong> <span>${article.diary.reg_date}</span>
									</div>
									<div class="card-hover-show">
										<a class="btn btn-xs fs-10 btn-dark btn-sm"
											href="update?d_id=${article.diary.pet_care_idx}">수정</a> <a
											class="btn btn-xs fs-10 btn-bold btn-dark btn-sm" href="#"
											data-toggle="modal" data-target="#modal-contact">질문하기</a>
										<button type="button" class="btn btn-xs fs-10 btn-dark btn-sm"
											data-toggle="collapse" data-target="#image${status.index}"
											aria-expanded="false" aria-controls="image${status.index}">열기</button>
										<!-- <a class="btn btn-xs fs-10 btn-bold btn-warning" href="#">열기</a> -->
									</div>
								</footer>

								<div class="media card-body table-responsive row">

									<div class="row mb-2 w-100">
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
															<td>${article.diary.diary_date}</td>
															<td>${article.diary.exercise}/m</td>
															<td>
																<div class="color-circle"
																	style="background-color:${article.urineContent};"></div>
															</td>
															<td>${article.fecesContent}</td>
															<td>${article.diary.weight}111/kg</td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
										<div class="col-md-6 p-2">
											<div class="card-body">
												<p>${article.diary.memo}</p>
											</div>
										</div>
									</div>

									<div class="row">
										<div class="col-md-12 p-2 collapse" id="image${status.index}">
											<div class="card-body">
												<div class="row">
													<c:if test="${article.files.size() > 0}">
														<c:forEach var="image" items="${article.files}">
															<div class="col-md-3">
																<img src="${hostname}${image.file_path}">
															</div>
														</c:forEach>
													</c:if>
												</div>
											</div>
										</div>
									</div>

								</div>
							</div>
							<!-- E:diary -->
						</c:forEach>
					</c:if>

				</div>
			</div>
			<!-- E:diary list box -->

			<div class="row">
				<div class="col-md-6">
					<jsp:include page="/WEB-INF/view/include/paging.jsp" flush="false" />
				</div>
				<div class="col-6 mx-auto mb-3">
					<form action="" class="form-inline justify-content-end">
						<input type="hidden" name="page" value="${paging.currentPage}">
						<div class="input-group mb-3">
							<input type="text" class="form-control" placeholder="제목검색"
								id="searchText" name="searchText">
							<div class="input-group-append">
								<span class="input-group-text">검색</span>
							</div>
						</div>
					</form>
				</div>
			</div>



			<div class="modal fade" id="modalSample" tabindex="-1" role="dialog">
				<div class="modal-dialog modal-md" role="document">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title">나의 반려동물</h5>
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
						</div>
						<div class="modal-body">
							<div class="row">
								<c:if test="${pets.size() > 0}">
									<c:forEach var="pet" items="${pets}">
										<div class="col-md-4 mb-3">
											<div class="image" style="height: 100px;">
												<img src="${hostname}${pet.profile_image_file_path}"
													class="h-100" />
											</div>
											<div class="card-inner">
												<div class="header text-center py-1">
													<strong>${pet.name}</strong>
												</div>
												<div class="content text-center">
													<button class="btn btn-dark btn-sm"
														onclick="selectPet(${pet.pet_idx})">선택</button>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:if>
							</div>
							<div class="modal-footer justify-content-center">
								<button class="btn btn-dark btn-sm">추가</button>
							</div>
						</div>
					</div>
				</div>
				<!--  E: 내용작성 -->
			</div>
		</div>
	</div>
</body>
</html>