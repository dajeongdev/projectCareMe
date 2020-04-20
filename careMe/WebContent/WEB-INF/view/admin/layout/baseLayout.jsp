<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<!DOCTYPE html>
<html>
<head>
<tiles:insertAttribute name="head" />
<title><tiles:getAsString name="title" /></title>
</head>

<body class="hold-transition sidebar-mini">
<div class="wrapper">

	<tiles:insertAttribute name="nav" />
	<tiles:insertAttribute name="side" />
	<tiles:insertAttribute name="body" />
	
</div>	
</body>

</html>