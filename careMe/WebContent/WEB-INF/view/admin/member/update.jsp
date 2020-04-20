<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<spring:url value="/resources/admin/" var="adminResources" />

<section class="content-header"></section>
<section class="content px-5">
	<div class="content-fluid">
		<div class="card card-info">
			<div class="card-header">
				<h3 class="card-title">Horizontal Form</h3>
			</div>
			<!-- /.card-header -->
			<!-- form start -->
			<form class="form-horizontal">
				<div class="card-body">
					<div class="form-group row">
						<label for="member_id" class="col-sm-2 col-form-label">Id</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="member_id"
								placeholder="Email">
						</div>
					</div>
					<div class="form-group row">
						<label for="member_email" class="col-sm-2 col-form-label">Email</label>
						<div class="col-sm-10">
							<input type="email" class="form-control" id="member_email"
								placeholder="Email">
						</div>
					</div>
					<div class="form-group row">
						<label for="member_phone" class="col-sm-2 col-form-label">Phone</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="member_phone"
								placeholder="phone">
						</div>
					</div>
					<div class="form-group row">
						<label for="member_nick" class="col-sm-2 col-form-label">Nick</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="member_nick"
								placeholder="nickname">
						</div>
					</div>
					<div class="form-group row">
						<img class="img-fluid pad py-2" src="${adminResources}dist/img/photo2.png" alt="profile">
						<div class="input-group">
	                      <div class="custom-file">
	                        <input type="file" class="custom-file-input" id="exampleInputFile">
	                        <label class="custom-file-label" for="exampleInputFile">Choose file</label>
	                      </div>
	                    </div>
                    </div>

				</div>
				<!-- /.card-body -->
				<div class="card-footer">
					<button type="submit" class="btn btn-info">Submit</button>
					<button type="submit" class="btn btn-default float-right">Cancel</button>
				</div>
				<!-- /.card-footer -->
			</form>
		</div>
	</div>
</section>