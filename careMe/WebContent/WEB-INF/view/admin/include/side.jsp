<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<spring:url value="/resources/admin/" var="adminResources" />

<%
	String uri, currentMenu;
	uri = (String) request.getAttribute("javax.servlet.forward.request_uri");
	uri = uri.substring(uri.indexOf("/admin") + 6);
	
	//out.print("uri admin 때면" + uri);
	//out.print("uri.lastIndexOf(/)::" + uri.lastIndexOf("/"));
	
	if (uri.lastIndexOf("/") > 1) {
		uri = uri.substring(1);
		currentMenu = uri.substring(0, uri.indexOf("/"));
	} else if (uri.lastIndexOf("/") == 0) {
		currentMenu = uri.substring(1);
	} else {
		currentMenu = "main";
	}
%>
<c:set var="currentMenu" value="<%=currentMenu%>" />

<!-- Main Sidebar Container -->
<aside class="main-sidebar sidebar-dark-primary elevation-4">
  <!-- Brand Logo -->
  <a href="/careMe/admin" class="brand-link">
    <img src="${adminResources}dist/img/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
         style="opacity: .8">
    <span class="brand-text font-weight-light">CareMe Admin</span>
  </a>

  <!-- Sidebar -->
  <div class="sidebar">
    <!-- Sidebar user panel (optional) -->
    <div class="user-panel mt-3 pb-3 mb-3 d-flex">
      <div class="image">
        <img src="${adminResources}dist/img/husky_PNG19.png" class="img-circle elevation-2" alt="User Image">
      </div>
      <div class="info">
        <a href="#" class="d-block">관리자</a>
      </div>
    </div>

    <!-- Sidebar Menu -->
    <nav class="mt-2">
      <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
        <!-- Add icons to the links using the .nav-icon class
             with font-awesome or any other icon font library -->
        <li class="nav-item has-treeview menu-open">
          <a href="#" class="nav-link active">
            <i class="nav-icon fas fa-copy"></i>
            <p>
              회원정보
              <i class="right fas fa-angle-left"></i>
            </p>
          </a>
          <ul class="nav nav-treeview">
            <li class="nav-item">
              <a href="/careMe/admin/member" class="nav-link
	              <c:if test="${currentMenu == 'member'}">active	</c:if> 
              ">
                <i class="far fa-circle nav-icon"></i>
                <p>일반회원</p>
              </a>
            </li>
            <li class="nav-item">
              <a href="/careMe/admin/doctor" class="nav-link
              	<c:if test="${currentMenu == 'doctor'}">active</c:if> 
              ">
                <i class="careMe/admin/doctor"></i>
                <i class="far fa-circle nav-icon"></i>
                <p>의사회원</p>
              </a>
            </li>
            <li class="nav-item">
              <a href="#" class="nav-link">
                <i class="far fa-circle nav-icon"></i>
                <p>하나뭐였지</p>
              </a>
            </li>
          </ul>
        </li>
      </ul>
    </nav>
    <!-- /.sidebar-menu -->
  </div>
  <!-- /.sidebar -->
</aside>