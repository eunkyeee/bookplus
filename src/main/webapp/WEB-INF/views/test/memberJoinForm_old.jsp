<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<script>
	window.addEventlistener("load",init,false);
	function init(){
		let joinBtn=document.getElementById("joinBtn");
		joinBtn.addEventListener("click",joinBtnF,false);
		
		function joinBtnF(){
			//유효성체크
			if(!validationChk()){
				document.getElementById("joinForm").submit();
				
			}
		}
		
		function validationChk(){
			
			if(document.getElementById("id").value.trim().length==0){
				document.getElementById("id").focus();
				return false;
			}
			if(document.getElementById("nickname").value.trim().length==0){
				document.getElementById("nickname").focus();
				return false;
			}
			if(document.getElementById("age").value.trim().length==0){
				document.getElementById("age").focus();
				return false;
			}
			return true;
		}
	}
</script>
</head>
<body>
<form id="joinForm" action="${pageContext.request.contextPath }/test/memberJoin" method="post">
	아이디: <input type="text" id="id" name="id" value="${memberDTO.id }"/> <br>
	별칭: <input type="text" id="nickname" name="nickname" value="${memberDTO.nickname }"/> <br>
	나이: <input type="text" id="age" name="age" value="${memberDTO.age }"/> <br>
	<button id="joinBtn">가입하기</button>
</form>
</body>
</html>