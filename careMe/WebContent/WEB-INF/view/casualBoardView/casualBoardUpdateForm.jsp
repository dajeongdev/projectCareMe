<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">	
<title>Insert title here</title>

</head>
<body>

<form action="casualBoardUpdateAdd" method="post" name="updateInfo">

	����<input name="title" type="text"><br>
		<input name="question_table_idx" type="hidden" value="${idx}"/>
		<input name="doctor_idx" type="hidden" value="1"/>
		<input name="pet_idx" type="hidden" value="1"/>
		<input name="question_type" type="hidden" value="n"/>
		<input name="is_private" type="hidden" value="n"/>
	
		�Һз�<select name="pet_species_idx">
			<c:forEach var="specs" items="${specs}">
				<option value="${specs.pet_species_idx}">${specs.pet_species_idx}</option>
			</c:forEach>
			</select>
	
	<!-- MyPet<select name="pet_idx"><br>
			<option value="1">Pet 1</option>
			<option value="2">Pet 2</option>
			</select><br> -->	
		
		����<br>
		<textarea name="content" rows="13" cols="40"></textarea><br>
	
		<input type="submit" value="����">
		<input type="reset" value="�ٽþ���">
		<input type="button" value="�������" OnClick="window.location='casualBoard'">

</form>



</body>
</html>