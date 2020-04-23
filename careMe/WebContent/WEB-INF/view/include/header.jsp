<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page import="com.careme.model.command.SessionCommand"%>
<%@ page import="com.careme.model.command.LoginCommand"%>
<style>
body { font-family: 'GmarketSansMedium'; }

@font-face { font-family: 'GmarketSansMedium'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'GmarketSansBold'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff'); font-weight: normal; font-style: normal; }
</style>
<%
	String uri, currentMenu;
	uri = (String) request.getAttribute("javax.servlet.forward.request_uri");
	uri = uri.substring(uri.indexOf("/careMe") + 8);
	if (uri.indexOf("/") > 0)
		currentMenu = uri.substring(uri.indexOf("/"), uri.length() - 1);
	else
		currentMenu = uri;
	
	//세션에 저장된 값
	//SessionCommand sc = (SessionCommand)session.getAttribute("MINFO");

	/* if (session.getAttribute("MINFO") == null) {
		//out.println("<a href='loginform.jsp'>Sign up</a>");

	} else {
		String member_nick = (String) session.getAttribute("MINFO");
		//out.println("<a href='login/logoutok'>Logout"+member_nick+"</a>");
	} */
%>
<spring:url value="/resources/img/dog.jpg" var="profile" />
<c:set var="currentMenu" value="<%=currentMenu%>" />

<header class="blog-header py-3" style="padding: 1rem;">

	<!-- 비로그인 상태 -->
	<c:if test="${empty sessionScope.MINFO}">
		<div
			class="row flex-nowrap justify-content-between align-items-center">
			<div class="col-12 text-center">
				<a class="blog-header-logo text-dark" href="#">LOGO</a>
			</div>

			<div
				class="col-12 d-flex justify-content-end align-items-center position-absolute">
				<a class="btn btn-sm btn-outline-secondary"
					href="/careMe/login/loginform">Sign up</a>
			</div>
		</div>
	</c:if>
	<!-- 로그인 상태 -->
	<c:if test="${not empty sessionScope.MINFO}">
		<div
			class="row flex-nowrap justify-content-between align-items-center">
			<div class="col-12 text-center">
				<a class="blog-header-logo text-dark" href="#">LOGO</a>
			</div>

			<div
				class="col-12 d-flex justify-content-end align-items-center position-absolute">
				<img src="${profile}" style="padding: 5px; width: 60px;">
				
					
				<a class="btn btn-sm btn-outline-secondary"
					href="/careMe/login/mypage">정보수정</a>
				<a class="btn btn-sm btn-outline-secondary"
					href="/careMe/login/logoutok">${MINFO} Logout</a>
				
			</div>
		</div>
	</c:if>
</header>

<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
	<a class="navbar-brand"></a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbars" aria-controls="navbars" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbars">
		<ul class="navbar-nav mr-auto">
			<c:if test="${currentMenu == 'main'}">
				<li class="nav-item active">
			</c:if>
			<c:if test="${currentMenu != 'main'}">
				<li class="nav-item">
			</c:if>
			<!-- <li><a class="nav-link" href="/careMe/main">Main</a></li> -->

			<li class="nav-item"><a class="nav-link"
				href="/careMe/view/doctorBoardView/doctorBoard?currentPage=1">전문가 상담</a></li>

			<li class="nav-item"><a class="nav-link"
				href="/careMe/carediary">케어일기</a></li>

			<li class="nav-item"><a class="nav-link" href="/careMe/findDoctor">전문의 찾기</a></li>

			<li class="nav-item"><a class="nav-link" href="/careMe/view/story/storyMain?currentPage=1">펫스토리</a></li>

			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="dropdown04"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Community</a>
				<div class="dropdown-menu" aria-labelledby="dropdown04">
					<a class="dropdown-item"
						href="/careMe/view/myPageView/myPageDoctor">의사 마이페이지 (임시)</a> <a
						class="dropdown-item" href="/careMe/view/myPageView/myPageCasual">일반
						마이페이지 (임시)</a> <a class="dropdown-item"
						href="/careMe/view/casualBoardView/casualBoard?currentPage=1">고민상담</a>
				</div></li>
		</ul>
	</div>
</nav>

