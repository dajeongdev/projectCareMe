<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.careme.model.dto.MemberDto"%>
<%
	String hostname = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ "/careMe/";
%>
<c:set var="hostname" value="<%=hostname%>" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false" />
<spring:url value="/resources/css/carediary/main.css" var="mainCSS" />
<link rel="stylesheet" href="${mainCSS}">
<title>전문의 찾기</title>

</head>
<body>
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />

</body>
</html>