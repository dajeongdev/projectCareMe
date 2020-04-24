<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<% String hostname = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/careMe/"; %>
<c:set var="hostname" value="<%=hostname%>" />
<script>
var updateDoctorCert = function (doctor_idx) {
	var result = confirm('승인상태를 변경하시겠습니까?');

	if (result) {
		$.ajax({
		    url: "/careMe/admin/doctor/changeCert"
		    , type : "POST"
		    , dataType : "json"
		    , data : { "doctor_idx": doctor_idx }
			, success : function(data) {
				 location.reload();
		    }
		}).fail(function () {
			alert("실패");
		})
	}
	
}

</script>

<section class="content-header">
</section>
<section class="content">
	<div class="content-fluid">
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">${paging.currentPage} of ${paging.totalPage} pages , TotalCount : ${paging.totalCount} </h3>
						<div class="card-tools">
							<form name="searchForm" action="/careMe/admin/member/search">
								<input type="hidden" name="page" value=1>
								<div class="input-group input-group-sm">
									<select class="form-control" name="searchType">
										<option value="id">Id</option>
										<option value="email">Email</option>
										<option value="phone">Phone</option>
										<option value="nick">Nick</option>
									</select> <input type="text" name="searchText"
										class="form-control float-right" placeholder="Search" required>

									<div class="input-group-append">
										<button type="submit" class="btn btn-default">
											<i class="fas fa-search"></i>
										</button>
									</div>
								</div>
							</form>
						</div>

					</div>
					<!-- /.card-header -->
					<div class="card-body table-responsive p-0">
						<table class="table table-hover text-nowrap text-center">
							<thead>
								<tr>
									<th>DoctorIdx</th>
									<th>Name</th>
									<th>LicenseNumber</th>
									<th>HospitalName</th>
									<th>HospitalTel</th>
									<th>HospitalAddress</th>
									<th>승인요청일</th>
									<th>면허파일</th>
									<th>승인여부</th>
									<th>승인</th>
									<th>삭제</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${doctors != null}">
									<c:forEach var="doctor" items="${doctors}">
										<tr>
											<td>${doctor.doctor_idx}</td>
											<td>${doctor.doctor_name}</td>
											<td>${doctor.doctor_license}</td>
											<td>${doctor.doctor_hospital_name}</td>
											<td>${doctor.doctor_hospital_tel}</td>
											<td>${doctor.doctor_hospital_address}</td>
											<td>${doctor.reg_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))}</td>
											<c:if test="${doctor.certification_document == null}">
												<td>없음</td>
											</c:if>
											<c:if test="${doctor.certification_document != null}">
												<td>
													<button class="btn btn-dark btn-sm" 
														onclick="window.open('${hostname}${doctor.certification_document}')">
														파일보기
													</button>
												</td>
											</c:if>
											
											
											
											<c:if test="${doctor.is_certified == 'y'}">
												<td>승인됨</td>
												<td>
													<button class="btn btn-sm btn-danger" onclick="updateDoctorCert(${doctor.doctor_idx})">승인취소</button>
												</td>
											</c:if>
											<c:if test="${doctor.is_certified == 'n'}">
												<td>승인대기</td>
												<td>
													<button class="btn btn-sm btn-success" onclick="updateDoctorCert(${doctor.doctor_idx})">승인</button>
												</td>
											</c:if>
											<td>
												<button type="button" style="width:45px;"
													class="btn btn-block btn-danger btn-xs mx-auto"
													onclick=""
													>삭제</button>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>

					</div>
					<!-- /.card-body -->

					<div class="card-footer clearfix">
						<div class="row">
							<div class="col-md-6">
								<%@ include file="/WEB-INF/view/admin/include/paging.jspf" %>
							</div>
						</div>
					</div>

					<!-- /.card-footer -->
				</div>
				<!-- /.card -->
			</div>
		</div>
		<!-- /.row -->

	</div>
</section>
