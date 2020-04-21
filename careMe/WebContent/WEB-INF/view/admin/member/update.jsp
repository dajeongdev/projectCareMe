<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<spring:url value="/resources/admin/" var="adminResources" />
<% String hostname = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/careMe/"; %>
<c:set var="hostname" value="<%=hostname%>" />
<script>
	$(function() {
		$("#profileInputFile").change(function() {
	        readURL(this);
	    });

		function readURL(input) {
	        if (input.files && input.files[0]) {
	            var reader = new FileReader();
	            reader.onload = function(e) {
	                $('#previewImg').attr('src', e.target.result);
	            }
	            reader.readAsDataURL(input.files[0]);
	        }
	    }
	})
</script>
<section class="content-header"></section>
<section class="content px-5">
	<div class="content-fluid">
		<div class="card card-info">
			<div class="card-header">
				<h3 class="card-title">Horizontal Form</h3>
			</div>
			<!-- /.card-header -->
			<!-- form start -->
			<form class="form-horizontal" method="POST" enctype="multipart/form-data">
				<input type="hidden" name="member_idx" value="${member.member_idx}">
				<div class="card-body">
					<div class="form-group row">
						<label for="member_id" class="col-sm-2 col-form-label">Id</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="member_id" name="member_id"
								placeholder="Email" value="${member.member_id}">
						</div>
					</div>
					<div class="form-group row">
						<label for="member_email" class="col-sm-2 col-form-label">Email</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" id="member_email" name="member_email"
								placeholder="Email" value="${member.member_email}">
						</div>
					</div>
					<div class="form-group row">
						<label for="member_phone" class="col-sm-2 col-form-label">Phone</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="member_phone" name="member_phone"
								placeholder="phone" value="${member.member_phone}">
						</div>
					</div>
					<div class="form-group row">
						<label for="member_nick" class="col-sm-2 col-form-label">Nick</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="member_nick" name="member_nick"
								placeholder="nickname" value="${member.member_nick}">
						</div>
					</div>
					<div class="form-group row">
						<img class="img-fluid pad py-2" src="${hostname}${member.member_profile}" id="previewImg" alt="profile">
						<div class="input-group">
	                      <div class="custom-file">
	                        <input type="file" class="custom-file-input" id="profileInputFile" name="profileImg">
	                        <label class="custom-file-label" for="profileInputFile">Choose file</label>
	                      </div>
	                    </div>
                    </div>

				</div>
				<!-- /.card-body -->
				<div class="card-footer">
					<button type="submit" class="btn btn-info">Submit</button>
					<a href="/careMe/admin/member" class="btn btn-default float-right">Cancel</a>
				</div>
				<!-- /.card-footer -->
			</form>
		</div>
	</div>
</section>