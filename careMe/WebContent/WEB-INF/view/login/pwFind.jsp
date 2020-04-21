<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false" />
<title>비밀번호 찾기</title>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	function fn_pwfind() {
		var form = document.pwfind;
		var em_Chk = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
		var eFind = document.getElementById("member_email").value;

		if (form.member_email.value == "") {
			alert("이메일을 입력하세요.");
			form.member_email.focus();
			return false;
		}
		if (em_Chk.test(eFind) == false) {
			alert("이메일형식이 올바르지 않습니다.");
			form.member_email.focus();
			return false;
		}
		if (form.mailChk.value =='N') {
			alert("이메일 체크를 해주세요.")
			return false;
		}
	}

	/* 이메일 있는지 확인 */
	function fn_m_Chk() {
		$.ajax({
			url : "mailChk", //이메일 중복 확인 컨트롤러 사용
			type : "post",
			dataType : "json",
			data : {
				"member_email" : $("#member_email").val()
			},
			success : function(data) {
				if (data == 1) {
					alert("회원인 이메일 입니다.");
					$("#mailChk").attr("value", "Y");
				} else if (data == 0) {
					alert("해당이메일이 존재하지 않습니다");
				}
			}
		});
	}
</script>
</head>
<body>
<body>
	<br>
	<div class="#" align="center">
		<h3>비밀번호 찾기</h3>
	</div>
	<br>

	<form name="pwfind" class="#" action="find_pass"
		onsubmit="return fn_pwfind()" method="post">
		<div align="center">
			<label class="#"><h4>CARE ME!</h4></label> <br>
			<div class="#"
				style="padding: 15px; margin: 0 auto; max-width: 400px">
				<input type="text" id="member_email" name="member_email"
					class="form-control" maxlength="45" placeholder="이메일을 작성해주세요"><br>
				<button type="button" id="mailChk" name="mailChk" value="N"
					class="btn btn-dark btn-sm btn-block" onclick=" fn_m_Chk()">이메일
					체크</button>
				<input type="submit" class="btn btn-dark btn-sm btn-block"
					value="이메일 보내기">
			</div>
			<div class="#">
				<div align="center"
					style="border-top: 1px solid #888; width: 450px; margin: 20px auto; padding-top: 15px; font-size: 85%">
					가입하신 이메일로 임시비밀번호를 전송해드리겠습니다.</div>
			</div>
		</div>
		</div>
	</form>
</body>
</html>
