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
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script type="text/javascript">
	/* 아이디 중복체크 */
//$(function(){
	/* 유효성 검사 */
	function Signup(){
	           var form = document.form2;
	                  //아이디에서 입력 필수 조건문
	                  if (form.member_id.value == "")  {
	                          alert("아이디를 입력해야 합니다!");
	                          form.member_id.focus();//포커스를 id박스로 이동.
	                          return false;
	                  }
	                  //아이디 입력 문자수를 4~12자로 제한하는 조건문
	                  if (form.member_id.value.length < 4 || form.member_id.value.length > 45)
	                  {
	                   alert("아이디는 4글자 이상으로 입력 가능합니다!");

	                   form.member_id.focus();//입력한 문자를 선택 상태로 만듬.
	                   return false;
	                  }
	            
	            //입력된 첫번째 문자가 숫자인지 검사하는 조건문
	            if (!isNaN(form.member_id.value.substr(0,1)))
	            {
	                 alert("아이디는 숫자로 시작할 수 없습니다!");
	                 form.member_id.focus();
	                 return false;
	            }
	            //아이디 중복검사 했는지
	            if(form.idChk.value=='N'){
		            alert("아이디 중복검사를해야합니다!");
					return false;
		            }
				//패스워드 검사
	            if (!form.member_pass.value)
	            {
	                 alert("패스워드를 입력 해야 합니다!");
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
	          	//닉네임 입력 했는지
				 if (form.member_nick.value == "")  {
	                          alert("닉네임을 입력해야 합니다!");
	                          form.member_nick.focus();//포커스를 nick박스로 이동.
	                          return false;
				 }
	          	//닉네임 중복검사 했는지
	            if(form.nickChk.value=='N'){
		            alert("닉네임 중복검사를해야합니다!");
					return false;
		            }
	          	//이메일 입력 했는지
		         if(form.member_email.value == ""){
			         alert("이메일을 입력해야합니다!");
					form.member_email.focus();
					return false;
			      }
			      //이메일 중복검사를 했는지
			      if(form.mailChk.value=='N'){
					      alert("이메일 중복검사를 해야합니다!");
					      return false;
					      }
			      
			     //이메일 인증받기 했는지
		         if(form.sendMail.value=='N'){
			            alert("이메일 인증하기를 눌러주세요!");
						return false; 
			            }
		            //인증번호를 입력했는지
		            if(form.getnumber.value==""){
			            alert("인증번호를 입력해주세요!")
			            form.mailNum.focus();
			            return false;
			            }
		            //인증번호 확인을 눌렀는지
		            if(form.emailNChk.value=='N'){
			            alert("인증번호 확인을 눌러주세요!")
			            return false;
			            }
		           
	  form.submit();
	     
	   }
	
	//아이디 중복확인   
	function fn_idChk(){
		$.ajax({
			url : "idChk",
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
		});
	}
	//닉네임 중복확인   
	function fn_nChk(){
		$.ajax({
			url : "nickChk",
			type : "post",
			dataType : "json",
			data : {"member_nick" : $("#member_nick").val()},
			success : function(data){
				if(data == 1){
					alert("중복된 닉네임입니다.");
				}else if(data == 0){
					$("#nickChk").attr("value", "Y");
					alert("사용가능한 닉네임입니다.");
				}
			}
		});
	}

	//이메일 중복 체크
	function fn_mmChk(){
		$.ajax({
			url : "mailChk",
			type : "post",
			dataType : "json",
			data : {"member_email" : $("#member_email").val()},
			success : function(data){
				if(data == 1){
					alert("중복된 이메일 입니다.");
				}else if(data == 0){
					$("#mailChk").attr("value", "Y");
					alert("사용가능한 이메일입니다.");
				}
			}
		});
	} 

	//이메일 보냄

function fn_sendChk(){
<%--  window.open("<%=request.getContextPath()%>/login/mailform", "인증받기", "width=600,height=600" ); --%>
	/* var win = window.open("sendMail", "sendMail", "width=150,height=150");
		 win.document.write("<p>이메일을 확인해 주세요!!</p><br><div style='center'><button class='btn btn-dark btn-sm btn-block' onclick='self.close();'>닫기</button></div>"); */
			 var windowObj;
	         var email = document.getElementById('member_email').value;
			 
			 var settings ='toolbar=yes,directories=yes,status=yes,menubar=no,scrollbars=auto,resizable=no,height=600,width=600,left=0,top=0';
	        // 자식창을 열고 자식창의 window 객체를 windowObj 변수에 저장
	        windowObj = window.open("<%=request.getContextPath()%>/login/mailform?email="+email,"인증받기",settings);

	}
	/* 펑션띄우기
		function fn_sendChk() {
		  setTimeout(function(){ alert("이메일을 확인해 주세요!!"); }, 1000);
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
	<div style="padding: 15px; margin: 0 auto; max-width: 700px">

		<!-- 성공하면 insertok로 감  -->
		<form name="form2" action=insertok method="get"
			onsubmit="return Signup()">
			<table width="685" height="400" align="center" cellspacing="0">
				<tr height="10" align="center">
				</tr>

				<tr>
					<!-- 아이디 입력 -->
					<td style="padding-left: 20px;"><b>ID:</b></td>
					<td><input type="text" style="width: 530px" id="member_id"
						name="member_id" maxlength="45" class="form-control"
						placeholder="※4-12자의 영문 대소문자와 숫자로만 입력" /></td>

					<!-- 아이디 중복확인 버튼 -->
					<td><button type="button"
							class="btn btn-dark btn-sm btn-block" name="idChk" value="N"
							id="idChk" onclick="fn_idChk();">중복확인</button></td>

				</tr>

				<tr>
					<!-- 비밀번호 입력 -->
					<td style="padding-left: 16px;"><b>PW:</b></td>
					<td><input type="password" style="width: 530px"
						id="member_pass" name="member_pass" maxlength="12"
						class="form-control" placeholder=" ※4-12자의 영문 대소문자와 숫자로만 입력" /></td>
				</tr>
				<tr>
					<!-- 비밀번호 확인 -->
					<td style="padding-left: 16px;"><b>PW:</b></td>
					<td><input type="password" style="width: 530px"
						id="member_pass2" name="member_pass2" maxlength="12"
						class="form-control" /></td>
				</tr>

				<tr>
					<!-- 닉네임 입력 -->
					<td style="padding-left: 10px;"><b>Nick:</b></td>
					<td><input type="text" style="width: 530px" id="member_nick"
						name="member_nick" class="form-control" placeholder="ex)petlove" /></td>

					<!-- 닉네임 중복확인 버튼 -->
					<td><button type="button"
							class="btn btn-dark btn-sm btn-block" name="nickChk" value="N"
							id="nickChk" onclick="fn_nChk();">중복확인</button></td>
				</tr>

				<tr>
					<!-- 이메일 입력 -->
					<td style="padding-left: 8px;"><b>Email:</b></td>
					<td><input type="text" style="width: 530px" id="member_email"
						name="member_email" class="form-control"
						placeholder="ex)your@email.com" /></td>

					<!-- 이메일 중복 확인 -->
					<td><button type="button"
							class="btn btn-dark btn-sm btn-block" name="mailChk" id="mailChk"
							onclick="fn_mmChk();" value="N">중복확인</button></td>
					<!-- value="N" onclick="fn_mmChk();" -->
				</tr>

				<tr>
					<!--이메일 인증 -->
					<td><button type="button"
							class="btn btn-dark btn-sm btn-block" name="sendMail" value="N"
							id="sendMail" onclick="fn_sendChk();">인증받기</button></td>

					<!-- onclick="fn_sendChk();" /onclick="window.open('sendMail','sendMail','width=300,height=300');" -->
					<td><input type="text" style="width: 530px" id="getnumber"
						name="getnumber" class="form-control" /></td>

					<!-- 인증번호 확인 버튼 -->
					<!-- 	<td><button type="button"
							class="btn btn-dark btn-sm btn-block" name="enChk" value="N"
							id="enChk" onclick="fn_endChk();">확 인</button></td> -->
				</tr>


				<!-- <tr>
					연락처
					<td><b>phone:</b></td>
					<td><input type="text" style="width: 530px" id="member_phone"
						name="member_phone" class="form-control"
						placeholder="000-0000-0000" /></td>

				</tr>
 -->
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