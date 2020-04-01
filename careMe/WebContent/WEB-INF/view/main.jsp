<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<spring:url value="/resources/css/bootstrap-4.4.1/bootstrap.css" var="bootstrapCSS" />
<spring:url value="/resources/js/jquery-3.4.1/jquery.min.js" var="bootstrapJS" />
<spring:url value="/resources/js/bootstrap-4.4.1/bootstrap.min.js" var="jqeury" />
<spring:url value="/resources/js/popper-1.16.0/umd/popper.min.js" var="popper" />
<link rel="stylesheet" href="${bootstrapCSS}">
<script type="text/javascript" src="${bootstrapJS}"></script>
<script type="text/javascript" src="${jqeury}"></script>
<script type="text/javascript" src="${bootstrapJS}"></script>

<title>메인 화면</title>

</head>
<body>
<nav class="navbar navbar-expand-md navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Care Me</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExample05" aria-controls="navbarsExample05" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarsExample05">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="#">전문가 상담<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">케어일기</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">전문의 찾기<span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">펫스토리</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">고민상담</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">마이페이지</a>
      </li>
    </ul>
    <form class="form-inline my-2 my-md-0">
      <input class="form-control" type="text" placeholder="Search">
    </form>
  </div>
</nav>


main world!

</body>
</html>