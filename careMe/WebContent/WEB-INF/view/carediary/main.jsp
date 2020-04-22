<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<spring:url value="/resources/img/Tux.svg" var="default_image" />
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
	$(function () {
		$(".pagination").addClass("justify-content-end");
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
								<footer class="card-footer flexbox align-items-center">
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
				<div class="col-6 mx-auto mb-3">
					<form action="" class="form-inline text-right">
						<div class="input-group mb-3">
							<input type="text" class="form-control" placeholder="제목검색"
								id="searchText" name="searchText">
							<div class="input-group-append">
								<span class="input-group-text">검색</span>
							</div>
						</div>
					</form>
				</div>
				<div class="col-md-6">
					<jsp:include page="/WEB-INF/view/include/paging.jsp" flush="false" />
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
								<div class="col-md-12">
									<div class="people-nearby">

										<div class="nearby-user">
											<div class="row">
												<div class="col-md-2 col-sm-2">
													<img src="${default_image}" alt="user"
														class="profile-photo-lg">
												</div>
												<div class="col-md-7 col-sm-7">
													<h5>
														<a href="#" class="profile-link">강아지1</a>
													</h5>
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
													<img src="${default_image }" alt="user"
														class="profile-photo-lg">
												</div>
												<div class="col-md-7 col-sm-7">
													<h5>
														<a href="#" class="profile-link">강아지2</a>
													</h5>
													<p class="text-muted">2020/04/11 updated</p>
												</div>
												<div class="col-md-3 col-sm-3">
													<button class="btn btn-primary pull-right">선택</button>
												</div>
											</div>
										</div>

										<div class="row text-center">
											<div class="col-8 m-auto">
												<button class="btn btn-dark btn-sm">추가</button>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
						<div class="modal-footer text-center">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">닫기</button>
						</div>
					</div>
				</div>
			</div>

			<!--  E: 내용작성 -->
		</div>
	</div>

</body>
</html>