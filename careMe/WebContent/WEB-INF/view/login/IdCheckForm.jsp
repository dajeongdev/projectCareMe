<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 중복체크</title>

  <script type="text/javascript">
    
        var httpRequest = null;
        
        // httpRequest 객체 생성
        function getXMLHttpRequest(){
            var httpRequest = null;
        
            if(window.ActiveXObject){
                try{
                    httpRequest = new ActiveXObject("Msxml2.XMLHTTP");    
                } catch(e) {
                    try{
                        httpRequest = new ActiveXObject("Microsoft.XMLHTTP");
                    } catch (e2) { httpRequest = null; }
                }
            }
            else if(window.XMLHttpRequest){
                httpRequest = new window.XMLHttpRequest();
            }
            return httpRequest;    
        }
        
        
        // 회원가입창의 아이디 입력란의 값을 가져온다.
        function pValue(){
            document.getElementById("member_id").value = opener.document.form2.id.value;
        }
        
        // 아이디 중복체크
        function idCheck(){
 
            var id = document.getElementById("member_id").value;
 
            if (!id) {
                alert("아이디를 입력해주세요");
                return false;
            } 
            else if((id < "0" || id > "9") && (id < "A" || id > "Z") && (id < "a" || id > "z")){ 
                alert("한글 및 특수문자는 사용할 수 없습니다.");
                return false;
            }
            else
            {
                var param="id="+id
                httpRequest = getXMLHttpRequest();
                httpRequest.onreadystatechange = callback;
                httpRequest.open("POST", "MemberController", true);    
                httpRequest.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); 
                httpRequest.send(param);
            }
        }
        
        function callback(){
            if(httpRequest.readyState == 4){
                // 결과값을 가져온다.
                var resultText = httpRequest.responseText;
                if(resultText == 0){
                    alert("사용할수없는 아이디입니다.");
                    document.getElementById("noButton").style.visibility='visible';
                    document.getElementById("useButton").style.visibility='hidden';
                    document.getElementById("message").innerHTML ="";
                } 
                else if(resultText == 1){ 
                    document.getElementById("noButton").style.visibility='hidden';
                    document.getElementById("useButton").style.visibility='visible';
                    document.getElementById("message").innerHTML = "사용 가능한 아이디입니다.";
                }
            }
        }
        
        // 사용하기 클릭 시 부모창으로 값 전달 
        function sendCheckValue(){
            // 중복체크 결과인 idCheck 값을 전달한다.
            opener.document.form2.idDuplication.value ="idCheck";
            // 회원가입 화면의 ID입력란에 값을 전달
            opener.document.form2.id.value = document.getElementById("member_id").value;
            
            if (opener != null) {
                opener.chkForm = null;
                self.close();
            }    
        }    
   </script>

</head>

<body onload="pValue()">
<div id="wrap">
    <br>
    <b><font size="4" color="gray">아이디 중복체크</font></b>
    <hr size="1" width="460">
    <br>
    <div id="chk">
        <form id="checkForm">
            <input type="text" name="input" id="member_id">
            <input type="button" value="중복확인" onclick="idCheck()">
        </form>
        <div id="message"></div>
        <br>
        <!-- 버튼 -->
        <input id="noButton" type="button" value="취소" onclick="window.close()"><br>
        <input id="useButton" type="button" value="사용하기" onclick="sendCheckValue()">
    </div>
</div>    

</body>
</html>