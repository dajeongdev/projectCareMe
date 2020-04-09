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

</head>
<body class="text-center">
	<script type="text/javascript">

		function Signup()
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
	<div class="container">
		<div class="row">
			<div class="col-lg-4"
				style="padding: 15px; margin: 0 auto; margin-top: 100px">
				<form action="signup" method="post">
					<div>
						<h1>회원가입</h1>
					</div>
					<br>
					<!-- 아이디 -->
					<div class="input-group-prepend">
						<span id="member_id" class="input-group-text">아이디</span> <input
							type="text" name="member_id" class="form-control"
							placeholder="아이디를 입력하세요" required /> <input type="button"
							value="중복확인" />
					</div>
					<!-- 비밀번호 -->
					<div class="input-group-prepend">
						<span id="member_pass" class="input-group-text">비밀번호</span> <input
							type="password" name="member_pass" class="form-control"
							placeholder="비밀번호를 입력하세요" required />
					</div>
					<!-- 비밀번호 확인 -->
					<div class="input-group-prepend">
						<span id="member_pass2" class="input-group-text">비밀번호</span> <input
							type="password" name="member_pass2" class="form-control"
							placeholder="비밀번호를 다시 입력해주세요" required />
					</div>
					<!-- 핸드폰 -->
					<div class="input-group-prepend">
						<span id="member_phone" class="input-group-text">핸드폰</span> <input
							type="text" name="member_phone" class="form-control"
							pattern="\d{3}\-\d{4}-\d{4}" placeholder="000-0000-0000" required />
					</div>
					<!-- 이메일 -->
					<div class="input-group-prepend">
						<span id="member_email" class="input-group-text">이메일</span> <input
							type="text" name="member_email" class="form-control"
							placeholder="your@email.com" />
					</div>
				</form>
				<br>
				<!-- 가입완료버튼 -->
				<div>
					<input type="submit" value="가입하기"
						class="btn btn-dark btn-sm btn-block" />
				</div>
			</div>
		</div>
	</div>
</body>
</html>