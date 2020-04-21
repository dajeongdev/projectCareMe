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
<title>의사 등록 완료</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript"></script>
</head>
<body>
	<% response.sendRedirect("mypage"); %>

 
	<!-- 짧은 버튼 -->
<!-- 	<table width="400" height="50" align="center" cellspacing="0">
		<tbody>
			<tr height="10" align="center"></tr>

			<tr>
				<td><input type="submit" name="d_in" value="등록하기"
					class="btn btn-dark btn-sm btn-block"></td>
				<td><input type="reset" name="reset" value="다시 입력"
					class="btn btn-dark btn-sm btn-block"></td>
			</tr>
		</tbody>
	</table> -->
</body>
</html>