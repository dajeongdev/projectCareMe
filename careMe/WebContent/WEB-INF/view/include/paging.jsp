<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Page Numbering</title>
</head>
<body>


<div>
  <ul class="pagination pagination-lg pagination-dark">
  
  <!-- 왼쪽 화살표 -->  
    <li class="page-item disabled">
      <a class="page-link" href="#">&laquo;</a>
    </li>
    
    <c:forEach var="page" begin="${pages.startPage}" end="${pages.endPage}">
    	<c:choose>
    		<c:when test="${page eq pages.currentPage}">
    			<li class="page-item active">
      				<a class="page-link" href="#"><c:out value="${page}"/></a>
    			</li>	
    		</c:when>
    		<c:otherwise>
    			<li class="page-item active">
      				<a class="page-link" href="#"><c:out value="${page}"/></a>
    			</li>
    		</c:otherwise>
	</c:choose>
	</c:forEach>
    
  <!-- 오른쪽 화살표 -->
    <li class="page-item">
      <a class="page-link" href="#">&raquo;</a>
    </li>
  </ul>
</div>


</body>
</html>