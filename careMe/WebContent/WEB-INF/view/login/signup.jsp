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
<title>회원가입폼</title>
<script type="text/javascript">
	function validate() {
		var re = /^[a-zA-Z0-9]{4,12}$/ // 아이디와 패스워드가 적합한지 검사할 정규식
		var re2 = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
		// 이메일이 적합한지 검사할 정규식

		var id = document.getElementById("member_id");
		var pw = document.getElementById("member_pass");
		var email = document.getElementById("member_email");

		if (!check(re, member_id, "아이디는 4~12자의 영문 대소문자와 숫자로만 입력")) {
			return false;
		}

		if (!check(re, pw, "패스워드는 4~12자의 영문 대소문자와 숫자로만 입력")) {
			return false;
		}

		if (join.pw.value != join.checkpw.value) {
			alert("비밀번호가 다릅니다. 다시 확인해 주세요.");
			join.checkpw.value = "";
			join.checkpw.focus();
			return false;
		}

		if (email.value == "") {
			alert("이메일을 입력해 주세요");
			email.focus();
			return false;
		}

		if (!check(re2, email, "적합하지 않은 이메일 형식입니다.")) {
			return false;
		}
		alert("회원가입이 완료되었습니다.");
	}
	/*  function checkUserId(member_id) {
	        //Id가 입력되었는지 확인하기
	        if (!checkExistData(member_id, "아이디를"))
	            return false;
	 
	        var idRegExp = /^[a-zA-z0-9]{4,12}$/; //아이디 유효성 검사
	        if (!idRegExp.test(member_id)) {
	            alert("아이디는 영문 대소문자와 숫자 4~12자리로 입력해야합니다!");
	            form.userId.value = "";
	            form.userId.focus();
	            return false;
	        }
	        return true; //확인이 완료되었을 때
	    }
	 
	    function checkPassword(id, password1, password2) {
	        //비밀번호가 입력되었는지 확인하기
	        if (!checkExistData(password1, "비밀번호를"))
	            return false;
	        //비밀번호 확인이 입력되었는지 확인하기
	        if (!checkExistData(password2, "비밀번호 확인을"))
	            return false;
	 
	        var password1RegExp = /^[a-zA-z0-9]{4,12}$/; //비밀번호 유효성 검사
	        if (!password1RegExp.test(password1)) {
	            alert("비밀번호는 영문 대소문자와 숫자 4~12자리로 입력해야합니다!");
	            form.password1.value = "";
	            form.password1.focus();
	            return false;
	        }
	        //비밀번호와 비밀번호 확인이 맞지 않다면..
	        if (password1 != password2) {
	            alert("두 비밀번호가 맞지 않습니다.");
	            form.password1.value = "";
	            form.password2.value = "";
	            form.password2.focus();
	            return false;
	        }
	 
	    function checkMail(mail) {
	        //mail이 입력되었는지 확인하기
	        if (!checkExistData(mail, "이메일을"))
	            return false;
	 
	        var emailRegExp = /^[A-Za-z0-9_]+[A-Za-z0-9]*[@]{1}[A-Za-z0-9]+[A-Za-z0-9]*[.]{1}[A-Za-z]{1,3}$/;
	        if (!emailRegExp.test(mail)) {
	            alert("이메일 형식이 올바르지 않습니다!");
	            form.mail.value = "";
	            form.mail.focus();
	            return false;
	        }
	        return true; //확인이 완료되었을 때
	    } */
</script>

</head>
<body>
	<div class="container-fluid" style="padding: 0;">
		<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />
	</div>

	<div style="margin: 0 auto; margin-top: 100px;" class="text-center">
		<h1>회원가입</h1>
	</div>
	<br>
	<div class="jumbotron"
		style="padding: 15px; margin: 0 auto; max-width: 700px">

		<form name="join" onsubmit="return validate();" action=# method="post"
			enctype="text/plain">
			<table width="600" height="400" align="center" cellspacing="0">
				<tr height="10" align="center">
				</tr>

				<tr>
					<td><b>ID:</b></td>
					<td><input type="text" style="width: 530px" id="member_id"
						name="id" maxlength="12" class="form-control"
						placeholder="※4-12자의 영문 대소문자와 숫자로만 입력" /></td>
					<td><input type="button" class="btn btn-dark btn-sm btn-block"
						value="중복확인" onclick="overlapCheck(this.form)"></td>
				</tr>
				<tr>
					<td><b>PW:</b></td>
					<td><input type="password" style="width: 530px" id="pw"
						maxlength="12" class="form-control"
						placeholder=" ※4-12자의 영문 대소문자와 숫자로만 입력" /></td>
				</tr>
				<tr>
					<td><b>PW:</b></td>
					<td><input type="password" style="width: 530px" id="checkpw"
						maxlength="12" class="form-control" /></td>
				</tr>
				<tr>
					<td><b>email:</b></td>
					<td><input type="text" style="width: 530px" id="email"
						class="form-control" placeholder="ex)your@email.com" /></td>
				</tr>

				<tr>
					<td><b>phone:</b></td>
					<td><input type="text" style="width: 530px" id="phone"
						class="form-control" placeholder="000-0000-0000" /></td>
					<td><input type="button" class="btn btn-dark btn-sm btn-block"
						value="인증하기" onclick=""></td>
				</tr>

			</table>
			<br>
			<div class="text-center">
				<input type="submit" name="join" value="회원 가입"
					class="btn btn-dark btn-sm btn-block"> <input type="reset"
					name="reset" value="다시 입력" class="btn btn-dark btn-sm btn-block">
			</div>
		</form>
	</div>
</body>
</html>