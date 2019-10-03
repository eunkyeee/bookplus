<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header.jsp"/>

<style>
	.errmsg{
		color: red;
		font-weight: bold;
		font-size: 0.8em;
	}
</style>

<script>
	$(function(){
		$("#writeBtn").on("click",function(e){
			e.preventDefault(); //등록 이벤트 제거 ex)등록을 누르면 페이지가 이동하는 이벤트를 제거
			if(valChk()){
				$("#bbsDTO").submit();				
			}
		});
		$("#cancelBtn").on("click",function(e){
			e.preventDefault(); //등록 이벤트 제거
			$("#bbsDTO").each(function(){
				this.reset();
			});
		});
		$("#listBtn").on("click",function(e){
			e.preventDefault(); //등록 이벤트 제거
			location.href="${pageContext.request.contextPath}/bbs/list/${page}";
		});
		
		function valChk(){
			//제목 입력값이 없을 경우
			if($("#btitle").val().length==0){
				$("#err_1").text("제목을 입력하세요.");
				$("#btitle").focus();
				return false;
			}
			
			//내용 입력값이 없을 경우
			if($("#bcontent").val().length==0){
				$("#err_2").text("내용을 입력하세요.");
				$("#bcontent").focus();
				return false;

			}
			return true;
		}
	});
	
	
</script>

<div class="container mt-5 mb-5">
<div class="row justify-content-center border border-secondary">
	<div class="col col-sm-12 col-md-8">
	<form:form modelAttribute="bbsDTO" action="${pageContext.request.contextPath }/bbs/reply">
	<form:hidden path="bid" value="${sessionScope.user.id }"/>
	<form:hidden path="bnickname" value="${sessionScope.user.nickname }"/>
	<form:hidden path="bgroup" value="${bbsDTO.bgroup }"/>
	<form:hidden path="bstep" value="${bbsDTO.bstep }"/>
	<form:hidden path="bindent" value="${bbsDTO.bindent }"/>
	<div class="form-group row justify-content-center my-3">
		<p class="h4"> 답글 작성 </p>
	</div>	
	<div class="form-group row text-right">
		<label class="form-control col-md-12 form-control-plaintext">${sessionScope.user.nickname }(${sessionScope.user.id })</label>
	</div>
 	<div class="form-group row">
		<%-- <form:label class="col-form-label col-sm-12 col-md-2 px-0" path="btitle">제 목</form:label> --%>
		<form:input class="form-control col-md-12" path="btitle"  placeholder="제목을 입력하세요." value="[답글] ${bbsDTO.btitle }"/>
		<div class="col-md-12"></div>
		<span id="err_1" class="errmsg"></span>
		<form:errors class="errmsg" path="btitle"></form:errors>
	</div>
	<div class="form-group row">
		<%-- <form:label class="col-form-label col-sm-12 col-md-2 px-0" path="bcontent">내 용</form:label> --%>
		<form:textarea class="form-control col-md-12" path="bcontent" rows="18" placeholder="내용을 입력하세요."
									 value="[답글] ${bbsDTO.bcontent }"/>
		<div class="col-md-12"></div>
		<span id="err_2" class="errmsg"></span>
		<form:errors class="errmsg" path="bcontent"></form:errors>
	</div>
	<div class="form-group row justify-content-end">
		<div class="col-sm-2 px-0 m-1">
		<button class="btn btn-sm btn-outline-success btn-block" id="writeBtn">등록</button>
		</div>
		<div class="col-sm-2 px-0 m-1">
		<button class="btn btn-sm btn-outline-info btn-block" id="cancelBtn">취소</button>
		</div>
		<div class="col-sm-2 px-0 m-1">
		<button class="btn btn-sm btn-outline-secondary btn-block" id="listBtn">목록</button>
		</div>
	</div>	
	</form:form>
	</div>
</div>
</div>

<jsp:include page="../footer.jsp"/>