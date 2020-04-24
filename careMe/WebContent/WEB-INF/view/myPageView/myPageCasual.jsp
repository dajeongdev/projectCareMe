<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ taglib prefix="Spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="Form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		
	<div class="row">
		<div class="col-lg-4"></div>
		<div class="col-lg-4">
       			<svg class="bd-placeholder-img rounded-circle" width="140" height="140" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 140x140">
        		<rect width="100%" height="100%" fill="#777">
        		</rect><text x="50%" y="50%" fill="#777" dy=".3em">140x140</text></svg>
        
        	<h2><c:out value="${mlist.member_nick}"/></h2>
        
        	<p>환영합니다</p>

			<c:forEach var="item" items="${plist}">
			<div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        		<div class="col p-4 d-flex flex-column position-static">
          			<h3 class="mb-0"><c:out value="${item.name}"/></h3>
         			<div class="mb-1 text-muted">강아지 / 코카스파니엘</div>
          		</div>
      		</div>
      		</c:forEach>
    	</div>
		<div class="col-lg-4"></div>
		
		
		<!-- <div class="col-md-2"></div>
		
		<div class="col-md-5">
			
			<div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        		<div class="col p-4 d-flex flex-column position-static">
          			<strong class="d-inline-block mb-2 text-success">당신께 추천드리는 수의사</strong>
          			<h3 class="mb-0">ㅌㅌㅌ 병원</h3>
         			<div class="mb-1 text-muted">ㅌㅌㅌ 수의사</div>
          			<p class="mb-auto">여기에는 병원 주소가 들어감</p>
        		</div>
      		</div>
    	
    		<div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        		<div class="col p-4 d-flex flex-column position-static">
          			<strong class="d-inline-block mb-2 text-success">당신께 추천드리는 수의사</strong>
          			<h3 class="mb-0">ㅌㅌㅌ 병원</h3>
         			<div class="mb-1 text-muted">ㅌㅌㅌ 수의사</div>
          			<p class="mb-auto">여기에는 병원 주소가 들어감</p>
        		</div>
      		</div>
    	
    		<div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        		<div class="col p-4 d-flex flex-column position-static">
          			<strong class="d-inline-block mb-2 text-success">당신께 추천드리는 수의사</strong>
          			<h3 class="mb-0">ㅌㅌㅌ 병원</h3>
         			<div class="mb-1 text-muted">ㅌㅌㅌ 수의사</div>
          			<p class="mb-auto">여기에는 병원 주소가 들어감</p>
        		</div>
      		</div>
    	</div>
    </div> -->
    
    <hr>
    	
	<div class="row">
			<div class="col-md-5 mb3">
			<div class="table" style="width:540px; height: 540px;">
						<table class="table table-striped table-lg table-hover">
							<!-- 맨 윗 줄 -->
							<thead class="thead-dark">
								<tr>
									<th width="10%">번 호</th>
									<th align="left" width="30%">제목</th>
									<th width="20%">작성일자</th>
								</tr>
							</thead>	
							<tbody>
							<c:forEach var="brd" items="${bdlist}">	
								<tr onClick="location.href='casualBoardContent?question_table_idx=${brd.question_table_idx}'">
									<td><c:out value="${brd.question_table_idx}"/></td>
									<td><c:out value="${brd.title}"/></td>
									<td><c:out value="${brd.reg_date}"/></td>
								</tr>
							</c:forEach>
							</tbody>
						</table>
			</div>
			</div>
			<div class="col-md-2"></div>
			<div class="col-md-5 mb3">
			<div class="table" style="width:540px; height: 540px;">
						<table class="table table-striped table-lg table-hover">
							<!-- 맨 윗 줄 -->
							<thead class="thead-dark">
								<tr>
									<th width="10%">번 호</th>
									<th align="left" width="30%">제목</th>
									<th width="20%">작성일자</th>
									<th width="10%">조회</th>
								</tr>
							</thead>	
							<tbody>
								<c:forEach var="brd" items="${bdlist}">	
									<tr onClick="location.href='casualBoardContent?question_table_idx=${brd.question_table_idx}'">
										<td><c:out value="${brd.question_table_idx}"/></td>
										<td><c:out value="${brd.title}"/></td>
										<td><c:out value="${brd.reg_date}"/></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
			</div>
			</div>
	</div>
	
</div>
</div>
</body>
</html>
