<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>스토리 메인</title>
</head>
<body>

<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
	
	<h1>First Story!</h1>
	<h2>인기글</h2>
	<div class="row">
		<div class="col-md-4">
			<div class="card mb-4 shadow-sm">
				{% include icons/placeholder.svg width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" %}
				<div class="card-body">
					<p class="card-text">text</p>
					<div class="d-flex justify-content-between align-items-center">
						<div class="btn-group">
							<button type="button" class="btn btn-sm btn-outline-secondary ">View</button>
							<button type="button" class="btn btn-sm btn-outline-secondary ">Edit</button>
						</div>
						<small class="text-muted">9 mins</small>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<div class="card mb-4 shadow-sm">
				{% include icons/placeholder.svg width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" %}
				<div class="card-body">
					<p class="card-text">text</p>
					<div class="d-flex justify-content-between align-items-center">
						<div class="btn-group">
							<button type="button" class="btn btn-sm btn-outline-secondary ">View</button>
							<button type="button" class="btn btn-sm btn-outline-secondary ">Edit</button>
						</div>
						<small class="text-muted">9 mins</small>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<div class="card mb-4 shadow-sm">
				{% include icons/placeholder.svg width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" %}
				<div class="card-body">
					<p class="card-text">text</p>
					<div class="d-flex justify-content-between align-items-center">
						<div class="btn-group">
							<button type="button" class="btn btn-sm btn-outline-secondary ">View</button>
							<button type="button" class="btn btn-sm btn-outline-secondary ">Edit</button>
						</div>
						<small class="text-muted">9 mins</small>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-4">
			<div class="card mb-4 shadow-sm">
				{% include icons/placeholder.svg width="100%" height="225" background="#55595c" color="#eceeef" class="card-img-top" text="Thumbnail" %}
				<div class="card-body">
					<p class="card-text">text</p>
					<div class="d-flex justify-content-between align-items-center">
						<div class="btn-group">
							<button type="button" class="btn btn-sm btn-outline-secondary ">View</button>
							<button type="button" class="btn btn-sm btn-outline-secondary ">Edit</button>
						</div>
						<small class="text-muted">9 mins</small>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>




</body>
</html>