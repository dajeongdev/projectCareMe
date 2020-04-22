<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="pagination m-0">
	<c:if test="${paging.prevPage <= 0}">
		<li class="page-item disabled"><a class="page-link" href="#">&laquo;</a></li>
	</c:if>
	<c:if test="${paging.prevPage > 0}">
		<li class="page-item"><a class="page-link" href="${paging.path}${paging.prevPage}">&laquo;</a></li>
	</c:if>
	
	<c:if test="${paging.totalCount == 0}">
		<li class="page-item disabled"><a class="page-link" href="#">1</a></li>
	</c:if>
	
	<c:if test="${paging.totalCount > 0}">
		<c:forEach var="page" begin="${paging.startPage}" end="${paging.endPage}">
			<li class="page-item
				<c:if test="${paging.currentPage eq page}">active</c:if>">
				<a class="page-link" href="${paging.path}${page}">${page}</a></li>
		</c:forEach>
	</c:if>
	
	<c:if test="${paging.nextPage == 0}">
		<li class="page-item disabled"><a class="page-link" href="#">&raquo;</a></li>
	</c:if>
	<c:if test="${paging.nextPage > 0}">
		<li class="page-item"><a class="page-link" href="${paging.path}${paging.nextPage}">&raquo;</a></li>
	</c:if>
</ul>