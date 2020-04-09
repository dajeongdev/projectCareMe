<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false" />
<title>회원가입폼</title>
</head>
<body>


	<div class="container-fluid" style="padding: 0;">
		<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />
	</div>
	<br>
	<div class="container">
		<c:url var="signUpPath" value="/signup" />
		<form:form modelAttribute="MemberDto" action="${ signUpPath }"
			method="post"
			style="width: 100%; max-width: 400px; padding: 15px; margin: 0 auto; margin-top: 100px">

			<div class="jumbotron">
				<div class="text-center">
					<h1>
						<label>회원가입</label>
					</h1>
				</div>

				<div class="form-group">
					<label>아이디</label>
					<form:input path="member_id" class="form-control"
						placeholder="아이디입력" />
					<form:errors path="member_id" element="div"
						Class="alert text-danger" />
				</div>
				<div class="form-group">
					<label>비밀번호</label>
					<form:input path="member_pass" class="form-control"
						placeholder="비밀번호" />
					<form:errors path="member_pass" element="div"
						Class="alert text-danger" />
				</div>
				<label>비밀번호 확인</label><input type="password" name="member_pass"
					class="form-control" placeholder="비밀번호 확인"> <label>이메일</label><input
					type="text" name="member_email" class="form-control"
					placeholder="e-mail"> <label>핸드폰 번호</label><input
					type="text" name="member_phone" class="form-control"
					placeholder="핸드폰번호">


				<div class="form-group">
					<input type="hidden" name="" value="">
				</div>
				<div class="text-center">
					<a href="main" class="btn btn-success btn-lg">펫 등록하기</a>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>