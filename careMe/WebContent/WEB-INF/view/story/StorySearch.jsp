<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="Spring" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<% String fullName = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort() + "/careMe/"; %>
<c:set var="fullName" value="<%=fullName%>" />
<style>
@font-face { font-family: 'GmarketSansMedium'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff'); font-weight: normal; font-style: normal; }
@font-face { font-family: 'GmarketSansBold'; src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff'); font-weight: normal; font-style: normal; }
.storyMain { margin: 40px; font-family: 'GmarketSansMedium';}
h2, h4 { font-family: 'GmarketSansBold'; }
.card-text, .card-heart, .card-count { font-size: 15px; }
.part > div { float: left; }
.part:after { clear: both; content:''; display:block; }
.profile { margin-right: 10px;}
</style>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>스토리 메인</title>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>
<body>

<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>

<div class="storyTag">
<form action="/story/storyTagList" method="POST">
	<h2><strong>펫스토리</strong></h2>
	<hr>
	<div class="album py-5 bg-light">
    <div class="container">
    
    <h4><strong>${tagList.tag_name}</strong></h4>
    <hr>
    <c:forEach items="${tagList}" var="list" begin="${start_idx}" end="${start_idx + 11}">
	<div class="row"  onClick="location.href='storyTagList?story_board_idx=${tagList.story_board_idx}'">
         <div class="col-md-4">
          <div class="card mb-4 shadow-sm" onClick="document.location.href='storyDetail?story_board_idx=${list.story_board_idx}'">
           	<c:forEach items="${tagFileList}" var="fList" varStatus="f">
           	<c:if test="${fList.story_board_idx == list.story_board_idx}">
           	 <img width="100%" height="170" class="img" src="${fullName}${fList.file_path}">
           	</c:if>
            </c:forEach>
            <div class="card-body">
            <div class="part">
             <div class="profile">
              <svg class="bd-placeholder-img rounded-circle" width="40" height="40" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img"><rect width="100%" height="100%" fill="#777"/></svg>
              </div>
              <div class="content">
              <h5 class="card-text">${list.title}</h5>
              <span class="card-text">${list.member_id}</span>
              <i class="fas fa-heart"></i>&nbsp<span class="card-heart">${list.heart}</span>&nbsp&nbsp
              <i class="fas fa-eye"></i>&nbsp<span class="card-count">${list.view_count}</span>
              </div>
              </div>
              
              <div class="d-flex justify-content-between align-items-center">
              </div>
            </div>
          </div>
        </div>
        </div>
       </c:forEach>
      
        </div>
        </div>
     
	
	
	<div id="paging">
		<div class="row lg-3" >
			<div class="col-lg-12" align="center">
				<ul class="pagination pagination-lg pagination-dark">
  					
  					<li class="page-item disabled">
   		  				<a class="page-link" href="#">&laquo;</a>
   					 </li>
    				
   					<c:forEach var="page" begin="${paging.startPage}" end="${paging.endPage}">
						<c:choose>
                           <c:when test="${page eq paging.currentPage}"> 			
							 	<li class="page-item active" style="font-weight: bold;">
      								<a class="page-link" href="storyMain?currentPage=${page}">${page}</a>
    							</li>
    						</c:when>
   					 		<c:otherwise>
					   			<li class="page-item active">
		      						<a class="page-link" href="storyMain?currentPage=${page}">${page}</a>
		    					</li>
							</c:otherwise>
						</c:choose>
   					 </c:forEach>
    				
    				<li class="page-item">
						<a class="page-link" href="#">&raquo;</a>
    				</li>
    				
  				</ul>
			</div>
		</div>
		</div> 
	 </form>
	</div>
	
</body>
</html>