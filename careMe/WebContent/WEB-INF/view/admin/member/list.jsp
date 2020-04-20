<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.time.format.DateTimeFormatter"%>


<section class="content-header">
</section>
<section class="content">
	<div class="content-fluid">
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">회원목록</h3>
						<div class="card-tools">
							<form name="searchForm" action="member/search">
								<input type="hidden" name="page" value=1>
								<div class="input-group input-group-sm">
									<select class="form-control" name="searchType">
										<option value="id">Id</option>
										<option value="email">Email</option>
										<option value="phone">Phone</option>
										<option value="nick">Nick</option>
									</select> <input type="text" name="searchText"
										class="form-control float-right" placeholder="Search">

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
						<table class="table table-hover text-nowrap">
							<thead>
								<tr>
									<th>MemberIdx</th>
									<th>ID</th>
									<th>Email</th>
									<th>Phone</th>
									<th>Nick</th>
									<th>RegDate</th>
									<th>정보수정</th>
									<th>삭제</th>
								</tr>
							</thead>
							<tbody>
								<c:if test="${list != null}">
									<c:forEach var="member" items="${list}">
										<tr>
											<td>${member.member_idx}</td>
											<td>${member.member_id}</td>
											<td>${member.member_email}</td>
											<td><span class="tag tag-success">${member.member_phone}</span></td>
											<td><span class="tag tag-success">${member.member_nick}</span></td>
											<td>${member.reg_date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))}</td>
											<td>
												<button type="button" class="btn btn-block btn-info btn-xs"
													onclick="location.href='/careMe/admin/member/update?memberIdx=${member.member_idx}'">
													수정</button>
											</td>
											<td>
												<button type="button"
													class="btn btn-block btn-danger btn-xs">삭제</button>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>

					</div>
					<!-- /.card-body -->

					<div class="card-footer clearfix">
						<ul class="pagination pagination m-0"
							style="justify-content: center;">
							<li class="page-item"><a class="page-link" href="#">&laquo;</a></li>
							<li class="page-item"><a class="page-link" href="#">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">&raquo;</a></li>
						</ul>
					</div>

					<!-- /.card-footer -->
				</div>
				<!-- /.card -->
			</div>
		</div>
		<!-- /.row -->

	</div>
</section>
