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
<title>정보수정</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
/* 유효성 검사 */
function Update(){
           var form = document.form5;
			//패스워드 검사
            if (!form.member_pass.value)
            {
                 alert("패스워드를 입력 해야 합니다!");
                 form.member_pass.focus();//포커스를 Password박스로 이동.
                 return false;
            }
            if (form.member_pass.value =="")
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
            if (form.member_pass.value != form.member_pass2.value){
	            alert("비밀번호를 같게 입력해주세요!");
	           	form.member_pass.focus();
	           	return false;
	         }
  form.submit();
     
   }

function godelete(){
	 var result = confirm("정말 탈퇴하겠습니까?");

		 if(result) {
			 location.href='deleteMember';
     } else{
    	 return;
     }
}

</script>

</head>
<body>
	<!-- 헤더 -->
	<div class="container-fluid" style="padding: 0;">
		<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />
	</div>

	<div style="margin: 0 auto; margin-top: 100px;" class="text-center">
		<h1>정보수정</h1>
	</div>
	<br>
	<div style="padding: 15px; margin: 0 auto; max-width: 700px">

		<!-- 성공하면 로 감  -->
		<form name="form5" action="update" method="post"
			onsubmit="return Update()">

			<table width="685" height="400" align="center" cellspacing="0">
				<tr height="10" align="center">
				</tr>

				<tr>
					<!-- 아이디 입력 -->
					<td style="padding-left: 20px;"><b>ID:</b></td>
					<td><input type="text" style="width: 530px" id="member_id"
						readonly="readonly" name="member_id" value="${sc.memberDto.member_id}"
						class="form-control" /></td>
				</tr>
				<tr>
					<!-- 비밀번호 입력 -->
					<td style="padding-left: 16px;"><b>PW 변경:</b></td>
					<td><input type="password" style="width: 530px"
						id="member_pass" name="member_pass" maxlength="12"
						class="form-control" placeholder=" ※4-12자의 영문 대소문자와 숫자로만 입력" /></td>
				</tr>
				<tr>
					<!-- 비밀번호 확인 -->
					<td style="padding-left: 16px;"><b>PW 확인:</b></td>
					<td><input type="password" style="width: 530px"
						id="member_pass2" name="member_pass2" maxlength="12"
						class="form-control" /></td>
				</tr>
				<tr>
					<!-- 닉네임 입력 -->
					<td style="padding-left: 10px;"><b>Nick:</b></td>
					<td><input type="text" style="width: 530px" id="member_nick"
						readonly="readonly" name="member_nick" value="${sc.memberDto.member_nick}"
						class="form-control" /></td>
				</tr>
				<tr>
					<!-- 이메일 입력 -->
					<td style="padding-left: 10px;"><b>Email:</b></td>
					<td><input type="text" style="width: 530px" id="member_email"
						readonly="readonly" name="member_email" value="${sc.memberDto.member_email}"
						class="form-control" /></td>
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
						<td><input type="submit" name="join" value="수정하기"
							class="btn btn-dark btn-sm btn-block"></td>
						<td><input type="button" name="reset" value="취소하기"
							class="btn btn-dark btn-sm btn-block" onclick="history.go(-1)"></td>
					</tr>
				</tbody>
			</table>
			<!-- 탈퇴하기 -->
			<table width="100" height="50" align="center" cellspacing="0">
				<tbody>
					<tr height="10" align="center">
					</tr>
					<tr>
						<td><input type="button" name="bye" value="탈퇴하기"
							onclick="godelete()" class="btn btn-dark btn-sm btn-block"></td>
						<!-- onclick="location.href='deleteMember'" -->
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>