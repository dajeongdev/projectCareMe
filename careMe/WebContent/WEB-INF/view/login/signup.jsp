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
	/* 아이디 중복체크 */
function fn_idChk(){
			$.ajax({
				url : "login/idChk",
				type : "post",
				dataType : "json",
				data : {"member_id" : $("#member_id").val()},
				success : function(data){
					if(data == 1){
						alert("중복된 아이디입니다.");
					}else if(data == 0){
						$("#idChk").attr("value", "Y");
						alert("사용가능한 아이디입니다.");
					}
				}
			})
		}
	
	//$(function(){
//아이디 중복체크


	 function checkValue()
        {
            var form = document.form2;
        
            if(!form.id.value){
                alert("아이디를 입력하세요.");
                return false;
            }
            
            if(form.idDuplication.value != "idCheck"){
                alert("아이디 중복체크를 해주세요.");
                return false;
            }
            
            if(!form.password.value){
                alert("비밀번호를 입력하세요.");
                return false;
            }
            
            // 비밀번호와 비밀번호 확인에 입력된 값이 동일한지 확인
            if(form.password.value != form.password2.value ){
                alert("비밀번호를 동일하게 입력하세요.");
                return false;
            }    
            // 취소 버튼 클릭시 첫화면으로 이동
            function goFirstForm() {
                location.href="'signup'";
            }    
            
	/* 유효성 검사 */
	/* function validate() {
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
	 */
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
	<div style="padding: 15px; margin: 0 auto; max-width: 700px">

		<form name="form2" onsubmit="return validate();" action=#
			method="post" enctype="text/plain">
			<table width="600" height="400" align="center" cellspacing="0">
				<tr height="10" align="center">
				</tr>

				<tr>
					<!-- 아이디 입력 -->
					<td><b>ID:</b></td>
					<td><input type="text" style="width: 530px" id="member_id"
						name="id" maxlength="12" class="form-control"
						onkeydown="InputIdChk" placeholder="※4-12자의 영문 대소문자와 숫자로만 입력" /></td>

					<!-- 아이디 중복확인 버튼 -->
					<td><button type="button" class="btn btn-dark btn-sm btn-block"
						 value="N" onclick="fn_idChk()" id="idChk">중복확인</button></td>

				</tr>

				<tr>
					<!-- 비밀번호 입력 -->
					<td><b>PW:</b></td>
					<td><input type="password" style="width: 530px"
						id="member_pass" name="password" maxlength="12"
						class="form-control" placeholder=" ※4-12자의 영문 대소문자와 숫자로만 입력" /></td>
				</tr>
				<tr>
					<!-- 비밀번호 확인 -->
					<td><b>PW:</b></td>
					<td><input type="password" style="width: 530px"
						id="member_pass" name="passowrd2" maxlength="12"
						class="form-control" /></td>
				</tr>
				<tr>
					<!-- 이메일 선택 -->
					<td><b>email:</b></td>
					<td><input type="text" style="width: 530px" id="member_email"
						name="email" class="form-control" placeholder="ex)your@email.com" /></td>
				</tr>

				<tr>
					<!-- 연락처 -->
					<td><b>phone:</b></td>
					<td><input type="text" style="width: 530px" id="member_phone"
						name="phone" class="form-control" placeholder="000-0000-0000" /></td>
					<!-- 연락처 인증 -->
					<td><input type="button" class="btn btn-dark btn-sm btn-block"
						value="인증하기" onclick=""></td>
				</tr>

			</table>
			<br>
			<!-- 버튼 -->
			<!-- 짧은 버튼 -->
			<table width="400" height="50" align="center" cellspacing="0">
				<tbody>
					<tr height="10" align="center">
					</tr>

					<tr>
						<td><input type="submit" name="join" value="회원 가입"
							class="btn btn-dark btn-sm btn-block"></td>
						<td><input type="reset" name="reset" value="다시 입력"
							class="btn btn-dark btn-sm btn-block"></td>
					</tr>
				</tbody>
			</table>

			<!-- 긴거 -->
			<!-- <div class="text-center">
				<input type="submit" name="join" value="회원 가입"
					class="btn btn-dark btn-sm btn-block"> <input type="reset"
					name="reset" value="다시 입력" class="btn btn-dark btn-sm btn-block"> -->
		</form>
	</div>
</body>
</html>