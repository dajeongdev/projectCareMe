<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@ taglib prefix="Spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="Form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="EUC-KR">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false"/>
<title>�Խ���</title>
</head>
<body>
<div class="container-fluid" style="padding:0;">
	<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false"/>
</div>
<p>���� �Խ���(��ü ��: <c:out value="${countPro}"/> )</p>

<table class="con" id="posts"> 
<!-- �� �� �� -->
    <tr height="30"> 
      <td align="center"  width="50"  >�� ȣ</td> 
      <td align="center"  width="250" >��   ��</td> 
      <td align="center"  width="100" >�ۼ���</td>
      <td align="center"  width="150" >�ۼ���</td> 
      <td align="center"  width="50" >�� ȸ</td> 
    </tr>
   
<!-- �� ���� �� -->
	<c:forEach var="item" items="${listPro}">

	 <tr>
    	<td align="center"  width="50"  ><c:out value="${item.question_table_idx}"/></td> 
      	<td align="left"    width="250" ><a href="">"${item.title}"</a></td> 
     	<td align="center"  width="100" ><c:out value="${item.member_id}"/></td>
     	<td align="center"  width="150" ><c:out value="${item.brd_reg_date}"/></td> 
      	<td align="center"  width="50" ><c:out value="${item.views}"/></td> 	
     </tr>

	</c:forEach>
<!-- �Խ��� �۾��� -->
	<tr>
	  <td align="right"><a href="writeForm">�۾���</a></td>
	  <td colspan="4" width="50" align="center">number</td>
	</tr>
</table>

<!-- �Խ��� �˻� -->
<form action="searchPro">
	<select name="searchn">
		<option value="0">�ۼ���</option>
		<option value="1">����</option>
		<option value="2">����</option>
	</select>

	<input type="text" name="searchKeyword" size="15" maxlength="50" /> 
	<input type="submit" value="�˻�" />
</form>
</body>
</html>