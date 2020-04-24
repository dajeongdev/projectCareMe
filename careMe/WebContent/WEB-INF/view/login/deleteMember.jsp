<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:th="http://www.thymeleaf.org"
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity4">
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false" />
<title>회원 탈퇴</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	/* 유효성 검사 */
	function Delete() {
		var form = document.form8;
		if (!form.member_pass) {
			alert("비밀번호를 입력해야합니다!");
			form.member_pass.focus();
			return false;
		}if (form.member_pass.value =="")
        {
            alert("공백은 입력이 불가합니다!");
            form.member_pass.focus();//포커스를 Password박스로 이동.
            return false;
       }
       if (form.member_pass.value.length < 4 || form.member_pass.value.length > 12){
            alert("비밀번호는 4~12자 이내로 입력 가능 합니다!");
            form.member_pass.focus();
            return false;
       }
		form.submit();
	}
</script>
</head>
<body>
	<!-- 헤더 -->
	<div class="container-fluid" style="padding: 0;">
		<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />
	</div>
	<div style="margin: 0 auto; margin-top: 100px;" class="text-center">
		<h1>회원탈퇴</h1>
	</div>
	<br>
	<div style="padding: 15px; margin: 0 auto; max-width: 500px">

		<!-- 성공하면 로 감  -->
		<form name="form8" action="deleteMemberOk" onsubmit="return Delete()">

			<table width="400" height="100" align="center" cellspacing="0">
				<tr height="10" align="center"></tr>
				<tr>
					<!-- 아이디  -->
					<td style="padding-left: 20px;"><b>ID:</b></td>
					<td><input type="text" style="width: 430px" id="member_id"
						readonly="readonly" name="member_id" value="${sc.memberDto.member_id}"
						class="form-control" /></td>
				</tr>
				<tr>
					<!-- 비밀번호 입력 -->
					<td style="padding-left: 16px;"><b>PW:</b></td>
					<td><input type="password" style="width: 430px"
						id="member_pass" name="member_pass" maxlength="12"
						class="form-control" placeholder=" ※탈퇴하려면 비밀번호를 입력해주세요" /></td>
				</tr>
			</table>
			<!-- 버튼 -->
			<!-- 짧은 버튼 -->
			<br>
			<table width="300" height="30" align="center" cellspacing="0">
				<tbody>
					<tr>
						<td style="padding-left: 40px"><input type="submit"
							name="bye" value="탈퇴하기" class="btn btn-dark btn-sm btn-block"></td>
						<td style="padding-right: 40px"><input type="button"
							name="reset" value="취소하기" class="btn btn-dark btn-sm btn-block"
							onclick="history.go(-3)"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>