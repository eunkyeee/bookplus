<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header.jsp"/>
<style>
	.errMsg{
		color: red;
		font-size: 0.8em;
	}
</style>

<script>
	$(function(){
		//확인버튼 클릭시
		$("#okBtn").on("click",function(e){
			//해당요소의 기본 이벤트 막기
			e.preventDefault(); 
			if($("#fromPW").val().length == 0){
				$("#error_1").text("비밀번호를 입력하세요.");
				$("#fromPW").focus();
				return false;
			};
			if($("#toPW").val().length == 0){
				$("#error_2").text("새 비밀번호를 입력하세요.");
				$("#toPW").focus();
				return false;
			};
			if($("#toPWChk").val().length == 0){
				$("#error_3").text("새 비밀번호를 입력하세요.");
				$("#toPWChk").focus();
				return false;
			};
			
			if($("#toPW").val() != $("#toPWChk").val()){
				$("#toPWChk.errors").text("비밀번호가 일치하지 않습니다.");
				$("#toPWChk").focus();
				return false;
			};
			if($("#fromPW").val() == $("#toPW").val()){
				$("#error_3").text("현재비밀번호과 다른 비밀번호를 입력해주세요.");
				$("#toPW").focus();
				return false;
			};
	
			$("#passwdDTO").submit();
			
		});
		
		//취소버튼 클릭시
		$("#cancelBtn").on("click",function(e){
			//해당 요소의 기본 이벤트 막기
			e.preventDefault();
			$("#passwdDTO").each(function(){
				this.reset();
			})
		});
		
		
	});
	
</script>

<div class="container-fluid">
<div class="row justify-content-center my-5">
<div class="col-sm-4 col-md-6 col-lg-5 col-xl-5">
	<form:form class="form-signin" modelAttribute="passwdDTO" action="${pageContext.request.contextPath }/member/changePw" method="post">
		<div class="text-center mb-4">
			
			<h1 class="h3 mb-3 font-weight-nomal">비밀번호변경</h1>
		</div>

		<div class="form-label-group mb-3">
			<form:hidden path="id" value="${sessionScope.user.id}"/>
		</div>
		<div class="form-label-group">
			<form:password class="form-control" path="fromPW" placeholder="현재 비밀번호"></form:password>
		</div>
		<div class="form-label-group mb-3">
			<form:errors path="fromPW" class="errMsg"></form:errors>
			<span id="error_1" class="errMsg"></span>
		</div>
		<div class="form-label-group">
			<form:password class="form-control" path="toPW" placeholder="새 비밀번호"></form:password>
		</div>
		<div class="form-label-group mb-3">
			<form:errors path="toPW" class="errMsg"></form:errors>
			<span id="error_2" class="errMsg"></span>
		</div>
		<div class="form-label-group mb-3">
			<input class="form-control" type="password" id="toPWChk" placeholder="새 비밀번호 확인"/>
		</div>
		<div class="form-label-group mb-3">
			<div class="errMsg"></div>
			<span id="error_3" class="errMsg"></span>
		</div>

		<button class="btn btn-md btn-outline-success btn-block" id="okBtn" type="submit">확 인</button>
		<button class="btn btn-md btn-outline-dark btn-block" id="cancelBtn" type="submit">취 소</button>

	</form:form>
</div>
</div>
</div>


<jsp:include page="../footer.jsp"/>