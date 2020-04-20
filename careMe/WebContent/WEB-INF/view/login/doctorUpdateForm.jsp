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
<title>의사정보수정</title>
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false" />
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

	<tr>
		<!-- 아이디 입력 -->
		<td style="padding-left: 20px;"><b>ID:</b></td>
		<td><input type="text" style="width: 530px" id="member_id"
			name="member_id" maxlength="45" class="form-control" /></td>
	</tr>
</body>
</html>