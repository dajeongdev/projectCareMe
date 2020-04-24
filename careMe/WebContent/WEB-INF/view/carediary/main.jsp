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
<title>케어다이어리</title>
<script>
	$(function() {
		$(".pagination").addClass("justify-content-start");

		$("#additional").on("hide.bs.collapse", function () {
			$(".bi-caret-down-fill").show();
			$(".bi-caret-up-fill").hide();
		}).on('show.bs.collapse', function () {
			$(".bi-caret-down-fill").hide();
			$(".bi-caret-up-fill").show();
		});
	})
	
	var toPetUpdateForm = function (petIdx) {
		var result = confirm("펫 정보 수정창으로 이동하시겠습니까?");
		if (result) {
			location.href = "/careMe/pet/update?p=" + petIdx;
		}
	}

	var selectPet = function (petIdx) {
		location.href = "/careMe/carediary/" + petIdx;
	}

	var registPet = function () {
		location.href = "/careMe/pet/regist";
	}
	
</script>
</head>
<body>
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />
	<c:set var="isOwnPet" value="n" />
	<c:if test="${pet.member_idx eq sc.memberDto.member_idx}">
		<c:set var="isOwnPet" value="y" />
	</c:if>
	<div
		class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light">
		<div class="container min-vh-100 pt-3">
			<!--  S: 내용작성 -->

			<div class="row mb-3 text-center">
				<div class="col-md-12">
					<img alt="" src="${hostname}${pet.profile_image_file_path}"
						style="width: 200px; border-radius: 50%;">
					<h1>${pet.name}</h1>
					<c:if test="${isOwnPet eq 'y'}">
						<div>
						<button class="btn btn-dark btn-sm"
							onclick="toPetUpdateForm(${petIdx})">수정</button>
						</div>
					</c:if>
					
					<c:if test="${isOwnPet eq 'n'}">
						<div>
						<button type="button" class="btn btn-dark btn-sm"
							data-toggle="modal" data-target="#modalPetInfo">정보조회</button>
						</div>
					</c:if>
				</div>

			</div>

			<c:if test="${isOwnPet eq 'y'}">
			<div class="row mb-2">
				<div class="col-2 text-left">
					<button class="btn btn-dark btn-sm" onclick="location.href='write'">일기작성</button>
				</div>
				

				<div class="col-10 text-right">
					<button type="button" class="btn btn-dark btn-sm"
						data-toggle="modal" data-target="#modalSample">반려동물 목록</button>
				</div>
			</div>
			</c:if>

			<!-- S:diary list box -->
			<div class="row">
				<div class="col-md-12">
					<!-- S:diary -->
					<c:if test="${articles.size() eq 0}">
						<div class="card b-1 hover-shadow mb-20">
							<div class="media card-body table-responsive row">
								<div class="col-md-8">
									<img alt=""
										src="https://www.hostinger.com/assets/images/404-3a53e76ef1.png">
								</div>
								<div class="col-md-4">
									<p>아직 작성한 일기가 없습니다.</p>
									<p>일기를 작성 해주세요.</p>
									<c:if test="${isOwnPet eq 'y'}">
									<button class="btn btn-dark btn-sm"
										onclick="location.href='write'">일기작성</button>
									</c:if>
								</div>
							</div>
						</div>
					</c:if>

					<c:if test="${articles.size() > 0}">
						<c:forEach var="article" items="${articles}" varStatus="status">
							<div class="card b-1 hover-shadow mb-20">
								<header class="card-header flexbox align-items-center">
									<div>
										<strong>${article.diary.title} </strong>
									</div>
									<div class="card-hover-show">
										<c:if test="${isOwnPet eq 'y'}">
											<a class="btn btn-xs fs-10 btn-dark btn-sm"
												href="update?d_id=${article.diary.pet_care_idx}">
												수정
											</a> 
											<a class="btn btn-xs fs-10 btn-bold btn-dark btn-sm"
												href="/careMe/view/casualBoardView/casualWriteForm?carediary=${article.diary.pet_care_idx}&pet=${petIdx}">
												질문하기
											</a>
										</c:if>
										<button type="button" class="btn btn-xs fs-10 btn-dark btn-sm"
											data-toggle="collapse" data-target="#image${status.index}"
											aria-expanded="false" aria-controls="image${status.index}">
											열기
										</button>
									</div>
								</header>

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
								<button class="btn btn-dark btn-sm"
									onclick="location.href='/careMe/pet/regist'">추가</button>
							</div>
						</div>
					</div>
				</div>
				<!--  E: 내용작성 -->
			</div>


			<!-- S: 펫 정보조회 모달 -->
			<div class="modal fade" id="modalPetInfo" tabindex="-1" role="dialog">
				<div class="modal-dialog modal-md" role="document">
					<div class="modal-content">
					<div class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light">
						<div class="container min-vh-100 pt-3" style="max-width: 720px">

								<!-- S: 필수 -->
								<div class="my-3 p-3 bg-white rounded shadow-sm">
									<div class="row mb-3 col-12" style="font-size: 20px">
										<strong>${pet.name}</strong>
									</div>
									<hr>

									<div class="row mb-3">
										<div class="col-12">
											<label for="name" for="previewImg">프로필 사진</label> <img
												id="previewImg" class="w-100" src="${default_image}">
										</div>
									</div>

									<div class="row mb-3">
										<div class="col-12">
											<label for="name">이름</label> 
											<input type="text"
												class="form-control" id="name" name="name" placeholder="" value="${pet.name}"
												max="20" disabled>
										</div>
									</div>

									<div class="row">
										<div class="col-md-6  mb-3">
											<label for="petSpecies1">동물의 종류(대분류)</label>
											<input type="text" value="${petSpecName.ance_name}" class="form-control" disabled>
										</div>
										<div class="col-md-6  mb-3">
										<label for="petSpecies1">동물의 종류(소분류)</label>
											<input type="text" value="${petSpecName.spec_name}" class="form-control" disabled>
										</div>
									</div>

									<div class="row mb-3">
										<div class="col-12">
											<p class="mb-1">중성화 여부</p>
											
											<div class="d-block mb-3">
												<c:if test="${pet.neutralized eq 'y'}">
												<div class="custom-control custom-radio">
													<input id="neutY" name="neutralized" type="radio"
														class="custom-control-input" value="y" checked> <label
														class="custom-control-label" for="neutY">예</label>
												</div>
												</c:if>
												
												<c:if test="${pet.neutralized eq 'n'}">
												<div class="custom-control custom-radio">
													<input id="neutN" name="neutralized" type="radio"
														class="custom-control-input" value="n" required> <label
														class="custom-control-label" for="neutN">아니오</label>
												</div>
												</c:if>
												<c:if test="${pet.neutralized eq 'd'}">
												<div class="custom-control custom-radio">
													<input id="neutD" name="neutralized" type="radio"
														class="custom-control-input" value="d" required> <label
														class="custom-control-label" for="neutD">모름</label>
												</div>
												</c:if>
											</div>
										</div>
									</div>

								</div>
								<!-- E: 필수 -->


								<!-- S: 추가 -->
								<div class="my-3 p-3 bg-white rounded shadow-sm">

									<div class="row">
										<div class="col-6">
											<div class="h-100 pt-2" style="font-size: 20px;">
												<strong>추가정보</strong>
											</div>
										</div>
										<div class="col-6 text-right">
											<button class="btn btn-white" type="button"
												data-toggle="collapse" data-target="#additional"
												aria-expanded="false" aria-controls="additional">
												<svg class="bi bi-caret-down-fill" width="2em" height="2em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg">
													<path d="M7.247 11.14L2.451 5.658C1.885 5.013 2.345 4 3.204 4h9.592a1 1 0 01.753 1.659l-4.796 5.48a1 1 0 01-1.506 0z" />
												</svg>
												<svg class="bi bi-caret-up-fill" width="2em" height="2em" viewBox="0 0 16 16" fill="currentColor" xmlns="http://www.w3.org/2000/svg" style="display: none;">
						  							<path d="M7.247 4.86l-4.796 5.481c-.566.647-.106 1.659.753 1.659h9.592a1 1 0 00.753-1.659l-4.796-5.48a1 1 0 00-1.506 0z" />
												</svg>
											</button>
										</div>
									</div>

									<div class="collapse" id="additional">
										<hr class="mb-4">

										<div class="row mb-3">
											<div class="col-12">
												<label for="birth">생년월일</label>
												<input type="date" class="form-control" id="birth" name="birth" value="${pet.birth}" disabled>
											</div>
										</div>

										<div class="row mb-3">
											<div class="col-12">
												<p class="mb-1">성별</p>
												<div class="d-block">
													<c:if test="${pet.gender eq 'm'}">
													<div class="custom-control custom-radio">
														<input id="genM" name="gender" type="radio"
															class="custom-control-input" value="m" checked>
															<label class="custom-control-label" for="genM">남</label>
													</div>
													</c:if>
													<c:if test="${pet.gender eq 'f'}">
													<div class="custom-control custom-radio">
														<input id="genF" name="gender" type="radio"
															class="custom-control-input" value="f" checked> 
															<label class="custom-control-label" for="genF">여</label>
													</div>
													</c:if>
													<c:if test="${pet.gender eq 'n'}">
													<div class="custom-control custom-radio">
														<input id="genN" name="gender" type="radio"
															class="custom-control-input" value="n" checked> 
															<label class="custom-control-label" for="genN">기타</label>
													</div>
													</c:if>
													<c:if test="${pet.gender == null}">
														<div class="custom-control custom-radio">
														<input id="genN" name="gender" type="radio"
															class="custom-control-input" value="" checked> 
															<label class="custom-control-label" for="genN" >정보없음</label>
														</div>
													</c:if>
												</div>
											</div>
										</div>

										<div class="row mb-3">
											<div class="col-12">
												<label for="weight">몸무게(kg)</label>
												<input type="number" value="${pet.weight}"
													class="form-control" id="weight" name="weight"
													placeholder="" disabled>
											</div>
										</div>

										<div class="row mb-3">
											<div class="col-12">
												<p class="mb-1">기초 예방접종</p>
												<div class="d-block">
													<c:if test="${pet.vaccination eq 'y'}">
													<div class="custom-control custom-radio">
														<input id="vaccY" name="vaccination" type="radio"
															class="custom-control-input" value="y" checked> 
															<label class="custom-control-label" for="vaccY">예</label>
													</div>
													</c:if>
													<c:if test="${pet.vaccination eq 'n'}">
													<div class="custom-control custom-radio">
														<input id="vaccN" name="vaccination" type="radio"
															class="custom-control-input" value="n" checked> 
															<label class="custom-control-label" for="vaccN">아니오</label>
													</div>
													</c:if>
													<c:if test="${pet.vaccination eq 'd'}">
													<div class="custom-control custom-radio">
														<input id="vaccD" name="vaccination" type="radio"
															class="custom-control-input" value="d" checked> 
															<label class="custom-control-label" for="vaccD">모름</label>
													</div>
													</c:if>
													<c:if test="${pet.vaccination == null}">
													<div class="custom-control custom-radio">
														<input id="vaccD" name="vaccination" type="radio" 
														  class="custom-control-input" value="" checked> 
															<label class="custom-control-label" for="vaccD">정보없음</label>
													</div>
													</c:if>
													
												</div>
											</div>
										</div>

										<div class="row mb-3">
											<div class="col-12">
												<label for="bloodType">혈액형</label> 
												<input type="text" class="form-control" id="bloodType" 
												  name="bloodType" placeholder="" value="${pet.blood_type}" disabled>
											</div>
										</div>

										<div class="row mb-3">
											<div class="col-12">
												<label for="registrationNumber">등록번호</label> 
												<input type="text" class="form-control" id="registrationNumber"
													name="registrationNumber" placeholder="" value="${pet.registration_number}" disabled>
											</div>
										</div>

										<div class="row mb-3">
											<div class="col-12">
												<label for="memo">기타 특이사항</label>
												<textarea class="form-control" id="memo" name="memo"
													placeholder="" disabled>${pet.memo}</textarea>
											</div>
										</div>

									</div>
								</div>

								<!-- E: 추가 -->

						</div>
					</div>
				</div>
				</div>
			</div>
			<!-- E: 펫 정보조회 모달 -->
		</div>
	</div>
</body>
</html>