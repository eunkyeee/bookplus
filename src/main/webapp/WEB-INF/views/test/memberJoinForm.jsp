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
 	window.addEventListener("load",init,false);
	function init(){
		let joinBtn=document.getElementById("joinBtn");
		joinBtn.addEventListener("click",joinBtnF,false);
		
		function joinBtnF(){
			//유효성체크
			if(!validationChk()){
				document.getElementById("memberDTO").submit();
				
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
<style>
	.errMsg{
		font-color: red;
		font-size: 0.8em;
		font-weight: bold;
	}
</style>
</head>
<body>
	<form:form modelAttribute="memberDTO" action="${pageContext.request.contextPath }/test/memberJoin">
	
		아이디: <form:input type="text" path="id"/><form:errors path="id" cssClass="errMsg"></form:errors><br>
		별칭: <form:input type="text" path="nickname"/><form:errors path="nickname" cssClass="errMsg"></form:errors><br>
		나이: <form:input type="text" path="age"/><form:errors path="age" cssClass="errMsg"></form:errors><br>
		
		<form:select path="region">
			<option value="">--지역선택--</option>
			<form:options items="${region }" itemValue="code" itemLabel="label"/>
		</form:select>
		<button type="button" id="joinBtn">가입하기</button>
	</form:form>
</body>
</html>