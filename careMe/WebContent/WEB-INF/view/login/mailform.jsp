<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="/WEB-INF/view/include/sources.jsp" flush="false" />
<title>이메일</title>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">

	//확인버튼 누르면 실행
	function fn_close() {
		var form = document.form3;

		//입력한 인증번호
		//var num = document.getElementById('chkNum').value;

		//인증번호가 비어있다면
		if(form.chkNum.value ==""){
			alert("인증번호를 입력해야합니다!");
			form.chkNum.focus();
			return false;
			}
	/* 	//인증번호가 같지 않으면
		if(form.chkNum.value != ${result}){
			alert("인증번호가 틀립니다!");
			form.chkNum.focus();
			return false;
	}
		//인증번호가 같다면
		if(from.chkNum.value == ${result}){ */
	
		//부모창 인증번호 = 자식창 인증번호 입력한것 (여기 입력한거를 들고 부모창으로 가기)
		/* opener.document.getElementById('getnumber').value = windowObj.document
				.getElementById('chkNum').value
		num = opener.document.getElementById('getnumber').value;
		console.log('chkNum');
		//창 닫기
		self.close(); */
	 
}

	//이메일 보내기 컨트롤러 호출
	function fn_sendmail() {
		var result = "";
		
		alert($("#getemail").val());
		 $.ajax({
			url : "sendMail",
			type : "post",
			dataType : "json",
			async:false,
			data : "getemail="+$("#getemail").val(),
			error : function(e) {
					alert("실패했습니다");
					console.log(e);
				},
			success: function(data){
					alert("메일을 확인해주세요.");
					result = data;
				}
			});
			
			return result; 
		} 
	
</script>
</head>
<body class="text-center">
	<div style="margin: 0 auto; margin-top: 100px;" class="text-center">
		<h1>메일인증</h1>
	</div>
	<br>

	<div style="padding: 15px; margin: 0 auto; max-width: 500px">
		<br>
		<!-- 인증번호 보내기 하면 이메일 보냄 -->
		<form name="form3" action="sendMail">
			<table width="400" height="100" align="center" cellspacing="0">
				<tr height="10" align="center">
				</tr>

				<!-- 이메일 -->
				<tr>
					<td><b>Email:</b></td>
					<td><input type="text" style="width: 300px" id="getemail"
						name="getemail" maxlength="45" class="form-control"
						value="${param.email}" /></td>
				</tr>
				<tr>
					<td><b>인증번호:</b></td>
					<td><input type="text" style="width: 300px" id="chkNum"
						name="chkNum" maxlength="12" class="form-control"
						placeholder="인증번호를 입력해주세요" /></td>
						<%-- <td><input type="hidden" value="#{result}"></td> --%>
				</tr>
			</table>
			<br>
			<!-- 버튼 -->
			<!-- 짧은버튼 -->
			<table width="250" height="30" align="center" cellspacing="0">
				<tbody>
					<tr>
						<td style="padding-left: 30px">
							<button type="button" onclick="fn_sendmail()"
								class="btn btn-dark btn-sm btn-block">인증번호 받기</button>
						</td>
						<td><input type="button" value="확인" onclick="fn_close()"
							class="btn btn-dark btn-sm btn-block"></td>
					</tr>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>