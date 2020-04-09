<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false" />
<title>로그인폼</title>

</head>
<body class="text-center">

	<script type="text/javascript">
	function Login()
	   {
	           var form = document.form1;

	                  //아이디에서 입력 필수 조건문
	                  if (form.member_id.value == "")
	                  {
	                          alert("아이디를 입력해야 합니다!");
	                          form.member_id.focus();//포커스를 id박스로 이동.
	                          return;
	                  }

	                  //아이디 입력 문자수를 4~12자로 제한하는 조건문
	                  if (form.member_id.value.length < 4 || form.member_id.value.length > 12)
	                  {
	                   alert("아이디는 4~12자 이내로 입력 가능합니다!");

	                   form.member_id.select();//입력한 문자를 선택 상태로 만듬.
	                   return;
	                  }

	            //입력된 문자의 길이만큼 루프를 돌면서 검사 
	            for (i=0; i<form.member_id.value.length; i++)
	            {
	                   var ch = form.member_id.value.charAt(i);//문자를 반환(정수형), 범위 검사 가능

	                   //입력된 문자를 검사

	                   if ( ( ch < "a" || ch > "z") && (ch < "A" || ch > "Z") && (ch < "0" || ch > "9" ) )
	                   {
	                    alert("아이디는 영문 소문자로만 입력 가능 합니다!");
	                    form.member_id.select();
	                    return;
	                   }
	            }

	            //입력된 첫번째 문자가 숫자인지 검사하는 조건문
	            if (!isNaN(form.member_id.value.substr(0,1)))

	            {
	                 alert("아이디는 숫자로 시작할 수 없습니다!");
	                 form.member_id.select();
	                 return;
	            }

	 

				//패스워드 검사
	            if (form.member_pass.value == "")
	            {
	                 alert("패스워드를 입력 해야 합니다!");
	                 form.member_pass.focus();//포커스를 Password박스로 이동.
	                 return;
	            }

	 

	            if (form.member_pass.value.length < 4 || form.member_pass.value.length > 12)
	            {
	                 alert("비밀번호는 4~12자 이내로 입력 가능 합니다!");

	                 form.member_pass.select();
	                 return;
	            }

	   form.submit();
	     
	   }

	</script>


	<div class="container-fluid" style="padding: 0;">
		<jsp:include page="/WEB-INF/view/include/header.jsp" flush="false" />
	</div>

	<div style="margin: 0 auto; margin-top: 100px;" class="text-center">
		<h1>로그인</h1>
	</div>
	<br>
	<div class="jumbotron"
		style="padding: 15px; margin: 0 auto; max-width: 500px">
		<br>
		<!-- 로그인 성공시 메인으로 돌아감 -->
		<form name="form1" action="../main">
			<table width="400" height="100" align="center" cellspacing="0">
				<tr height="10" align="center">
				</tr>
				
				<!-- 로그인 -->
				<tr>
					<td><b>ID:</b></td>
					<td><input type="text" style="width: 430px" id="id"
						name="member_id" maxlength="12" class="form-control" placeholder="Input ID"/></td>
				</tr>
				<tr>
					<td><b>PW:</b></td>
					<td><input type="password" style="width: 430px" id="pw"
						name="member_pass" maxlength="12" class="form-control" placeholder="Input PW"/></td>
				</tr>
			</table>
			<br>
			<!-- 버튼 -->
			<div>
				<input type="button" value="  로그인 " onclick="Login();" 
				class="btn btn-dark btn-sm btn-block"/> <input
					type="button" value="회원가입" onclick="location.href='signup'"
						 class="btn btn-dark btn-sm btn-block"/>

			</div>


			<!-- 아이디 : <input type="text" size="15" name="member_id" /><br /> 비밀번호 :
			<input type="password" size="15" name="member_pass" /><br /> <br />

			<input type="button" value="  로그인 " onclick="Login();" /> <input
				type="button" value="회원가입" onclick="location.href='signup'" /> -->
		</form>
	</div>
</body>

</html>


