<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<meta name="viewport" content="width=device-width, initial-scale=1">
<spring:url value="/resources/css/bootstrap-4.4.1/bootstrap.min.css" var="bootstrapCSS" />
<spring:url value="/resources/js/jquery-3.4.1/jquery.min.js" var="bootstrapJS" />
<spring:url value="/resources/js/bootstrap-4.4.1/bootstrap.min.js" var="jqeury" />
<spring:url value="/resources/js/popper-1.16.0/umd/popper.min.js" var="popper" />
<link rel="stylesheet" href="${bootstrapCSS}">
<script type="text/javascript" src="${bootstrapJS}"></script>
<script type="text/javascript" src="${jqeury}"></script>
<script type="text/javascript" src="${popper}"></script>