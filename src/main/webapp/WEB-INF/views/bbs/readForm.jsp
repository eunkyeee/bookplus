<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
		var l_id = "${sessionScope.user.id}"; //로긴아이디
		
		let errmsg=$(".errmsg").text();
		if(errmsg.length>0){
			changeRmode(false);			
		}else{
			
			changeRmode(true);
		}
		
		
		
		//답글 등록
		$("#replyBtn").on("click",function(e){
			e.preventDefault(); //등록 이벤트 제거 ex)등록을 누르면 페이지가 이동하는 이벤트를 제거
			let $rid = l_id;
			
			//로그인체크
			if($rid ==''){
				if(confirm('로그인 후 댓글작성이 가능합니다\n로그인 하시겠습니까?')){
					document.location.href='${pageContext.request.contextPath }/login/login';
				}
				return;
			}
				document.location.href="${pageContext.request.contextPath }/bbs/reply/${page}/${bbsDTO.bnum}";
		});
	
		//게시글 수정모드 전환
		$("#modifyBtn").on("click",function(e){
			e.preventDefault(); //등록 이벤트 제거 ex)등록을 누르면 페이지가 이동하는 이벤트를 제거
			console.log("수정모드전환");
			changeRmode(false);
		});
		//게시글 삭제
		$("#deleteBtn").on("click",function(e){
			e.preventDefault(); //등록 이벤트 제거
			console.log("삭제");
			if(confirm("삭제하시겠습니까?")){
				document.location.href="${pageContext.request.contextPath }/bbs/delete/${page}/${bbsDTO.bnum}";
			}
		});
	
		//목록으로 이동
		$("#listBtn, #listBtn2").on("click",function(e){
			e.preventDefault(); //등록 이벤트 제거
			console.log("목록");
			location.href="${pageContext.request.contextPath}/bbs/list/${page}";
		});
		
		//게시글 읽기모드 전환
		$("#cancelBtn").on("click",function(e){
			e.preventDefault(); //등록 이벤트 제거
			$("#bbsDTO").each(function(){
				this.reset();
			});
			changeRmode(true);
		});
		
		//게시글 수정 저장
		$("#saveBtn").on("click",function(e){
			e.preventDefault(); //등록 이벤트 제거
			console.log("수정");
			if(valChk()){
				$("form").submit();
			}
			$("form").submit();
		});
		
		
		function changeRmode(flag){
			//읽기모드
			if(flag){
				//1)제목변경 =>게시글 보기
				$("#title").text("게시글 보기");
				
				//2)제목,내용 필드를 readonly="true"
				$("#btitle").attr("readOnly","true"); //attr: 속성값 변경
				$("#bcontent").attr("readOnly","true"); //attr: 속성값 변경
				
				//3)읽기모드 버튼그룹만 display: true
				$("#rmode").css({"display":"block"});
				$("#umode").css({"display":"none"});
				
			}else{
				//수정모드
				
				//1)제목변경 =>게시글 수정
				$("#title").text("게시글 수정");
				//2)제목,내용 필드를 readonly="false"
				$("#btitle").removeAttr("readOnly");
				$("#bcontent").removeAttr("readOnly");
				//3)수정모드 버튼그룹만 display: true
				$("#umode").css({"display":"block"});
				$("#rmode").css({"display":"none"});
			}
		}
		
		
		
		
		
		//null값
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
<div class="row justify-content-center">
	<div class="col col-sm-12 col-md-8">
	<form:form modelAttribute="bbsDTO" action="${pageContext.request.contextPath }/bbs/modify/${page }" method="post">
	<form:hidden path="bnum" value="${bbsDTO.bnum }"/>
	<form:hidden path="bnickname" value="${bbsDTO.bnickname }"/>
	<form:hidden path="bid" value="${bbsDTO.bid }"/>
	<div class="form-group row justify-content-center my-3">
		<p class="h4" id="title"></p>
	</div>	
	<div class="form-group row text-right">
		<label class="col-form-label col-sm-12 col-md-2 px-0"></label>
				<label class="form-control col-md-12 form-control-plaintext">작성자: ${bbsDTO.bnickname } / 작성날짜: ${bbsDTO.bcdate }</label>
	</div>
 	<div class="form-group row">
		<%-- <form:label class="col-form-label col-sm-12 col-md-2 px-0" path="btitle">제 목</form:label> --%>
		<form:input class="form-control col-md-12" path="btitle"  placeholder="제목을 입력하세요." value="${bbsDTO.btitle }" readonly="true" />
		<div class="col-md-12"></div>
		<span id="err_1" class="errmsg"></span>
		<form:errors class="errmsg" path="btitle"></form:errors>
	</div>
	<div class="form-group row">
		<%-- <form:label class="col-form-label col-sm-12 col-md-2 px-0" path="bcontent">내 용</form:label> --%>
		<form:textarea class="form-control col-md-12" path="bcontent" rows="18" placeholder="내용을 입력하세요." value="${bbsDTO.bcontent }" readonly="true"/>
		<div class="col-md-12"></div>
		<span id="err_2" class="errmsg"></span>
		<form:errors class="errmsg" path="bcontent"></form:errors>
	</div>
	
	<!-- 게시글 보기 버튼 -->
	<div id="rmode">
	<div class="form-group row justify-content-end">
		<div class="col-sm-1 px-0 m-1">
		<button class="btn btn-sm btn-outline-success btn-block" id="replyBtn">답글</button>
		</div>
		
		<!-- 작성자만 수정, 삭제 가능 -->
		<c:if test="${bbsDTO.bid eq sessionScope.user.id }">
		<div class="col-sm-1 px-0 m-1">
		<button class="btn btn-sm btn-outline-warning btn-block" id="modifyBtn">수정</button>
		</div>
		<div class="col-sm-1 px-0 m-1">
		<button class="btn btn-sm btn-outline-info btn-block" id="deleteBtn">삭제</button>
		</div>
		</c:if>
		<div class="col-sm-1 px-0 m-1">
		<button class="btn btn-sm btn-outline-secondary btn-block" id="listBtn">목록</button>
		</div>
	</div>
	</div>	
	
	<!-- 게시글 수정 버튼 -->
	<div id="umode">
	<div class="form-group row justify-content-end">
		<div class="col-sm-1 px-0 m-1">
		<button class="btn btn-sm btn-outline-secondary btn-block" id="cancelBtn">취소</button>
		</div>
		<div class="col-sm-1 px-0 m-1">
		<button class="btn btn-sm btn-outline-secondary btn-block" id="saveBtn">저장</button>
		</div>
		<div class="col-sm-1 px-0 m-1">
		<button class="btn btn-sm btn-outline-secondary btn-block" id="listBtn2">목록</button>
		</div>
	</div>	
	</div>	
	</form:form>
<jsp:include page="../bbs/reReply.jsp"/>
