<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>메인 화면</title>
</head>
<body>

<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>

<div class="cover-container d-flex w-100 h-100 mx-auto flex-column bg-light"> 
	<div class="container min-vh-100 pt-3 text-center">
		<!--  S: 내용작성 -->
		
		<div class="row mb3 jumbotron">
			<div class="col-md-4">게시판 1</div>
			<div class="col-md-4">게시판 2</div>
			<div class="col-md-4">게시판 3</div>
		</div>
		
		<div class="row mb3 jumbotron">
			<div class="col-md-2">왼쪽</div>
			<div class="col-md-8">의사 정보</div>
			<div class="col-md-2">오른쪽</div>
		</div>
		
		<div class="row mb3 jumbotron">
			<div class="col-md-2">왼쪽</div>
			<div class="col-md-8">스토리 사진들</div>
			<div class="col-md-2">오른쪽</div>
		</div>		

		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#modalSample">modal</button>
	
		<div class="modal fade" id="modalSample" tabindex="-1" role="dialog">
		  <div class="modal-dialog modal-lg" role="document">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title">Modal title</h5>
		        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
		          <span aria-hidden="true">&times;</span>
		        </button>
		      </div>
		      <div class="modal-body">
		        <p>Modal body text goes here.</p>
		        <p>Modal body text goes here.</p>
		        <p>Modal body text goes here.</p>
		        <p>Modal body text goes here.</p>        
		      </div>
		      <div class="modal-footer">
		        <button type="button" class="btn btn-primary">Save changes</button>
		        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
		      </div>
		    </div>
		  </div>
		</div>
			
		<!--  E: 내용작성 -->	
	</div>
</div>

</body>
</html>