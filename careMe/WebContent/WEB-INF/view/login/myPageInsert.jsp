<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false" />
<title>정보수정</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	
</script>
</head>
<body>
	<!-- 헤더 -->
	<div class="container-fluid" style="padding: 0;">
		<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />
	</div>

	<div style="margin: 0 auto; margin-top: 100px;" class="text-center">
		<h1>정보수정</h1>
	</div>
	<br>
	<div style="padding: 15px; margin: 0 auto; max-width: 700px">

		<!-- 성공하면 로 감  -->
		<form name="form2" action="" method="post"
			onsubmit="return myPageCasual()">

			<table width="685" height="400" align="center" cellspacing="0">
				<tr height="10" align="center">
				</tr>

				<tr>
					<!-- 아이디 입력 -->
					<td style="padding-left: 20px;"><b>ID:</b></td>
					<td><input type="text" style="width: 530px" id="member_id"
						name="member_id" maxlength="45" class="form-control" /></td>
				</tr>

				<tr>
					<!-- 비밀번호 입력 -->
					<td style="padding-left: 16px;"><b>PW:</b></td>
					<td><input type="password" style="width: 530px"
						id="member_pass" name="member_pass" maxlength="12"
						class="form-control" placeholder=" ※4-12자의 영문 대소문자와 숫자로만 입력" /></td>
				</tr>
				<tr>
					<!-- 비밀번호 확인 -->
					<td style="padding-left: 16px;"><b>PW:</b></td>
					<td><input type="password" style="width: 530px"
						id="member_pass2" name="member_pass2" maxlength="12"
						class="form-control" /></td>
				</tr>
				<tr>
					<!-- 닉네임 입력 -->
					<td style="padding-left: 10px;"><b>Nick:</b></td>
					<td><input type="text" style="width: 530px" id="member_nick"
						name="member_nick" class="form-control"/></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>