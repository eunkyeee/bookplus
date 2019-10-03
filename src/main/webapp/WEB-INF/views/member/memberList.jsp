<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../header.jsp"/>

<script>
	window.addEventListener("load",init,false);
	function init(){
		let modifyBtn=document.getElementsByClassName("modifyBtn");
		let deleteBtn=document.getElementsByClassName("deleteBtn");
		
		Array.from(modifyBtn).forEach(function(item){
			item.addEventListener("click",modifyBtnf,false);
		});
		Array.from(deleteBtn).forEach(function(item){
			item.addEventListener("click",deleteBtnf,false);
		});
		
		function modifyBtnf(){
			document.location.href="${pageContext.request.contextPath }" + this.getAttribute("data-url");
			
		}
		
		function deleteBtnf(){
			if(confirm("탈퇴 하시겠습니까?")){
				document.location.href="${pageContext.request.contextPath }" + this.getAttribute("data-url");
				
			}
		}
	}
</script>

  <div class="container mt-5 mb-5">
  <div class="row">
  	<div class="col col-sm-12 h3 text-center mb-5">회원 정보 리스트</div>
  </div>
    <div class="row border">
      <div class="col col-md-2">아이디</div>
      <div class="col col-md-2">전화번호</div>
      <div class="col">닉네임</div>
      <div class="col">성별</div>
      <div class="col">지역</div>
      <div class="col col-md-1">생년월일</div>
      <div class="col col-md-1">가입일</div>
      <div class="col col-md-1">수정일</div>
      <div class="col">수정</div>
      <div class="col">삭제</div>
    </div>
    <c:forEach items="${memberList }" var="memberDTO">
    	<div class="row border">
	      <div class="col col-md-2">${memberDTO.id }</div>
	      <div class="col col-md-2">${memberDTO.tel }</div>
	      <div class="col">${memberDTO.nickname }</div>
	      <div class="col">${memberDTO.gender }</div>
	      <div class="col">${memberDTO.region }</div>
	      <div class="col col-md-1">${memberDTO.birth }</div>
	      <div class="col col-md-1">${memberDTO.cdate }</div>
	      <div class="col col-md-1">${memberDTO.udate }</div>
	      <div class="col col-md-1"><button class="btn-sm modifyBtn" data-url="/member/memberModifyForm/${memberDTO.id }">수정</button></div>
	      <div class="col col-md-1"><button class="btn-sm deleteBtn" data-url="/member/memberDelete/${memberDTO.id }">삭제</button></div>
    	</div>
 		</c:forEach>
  </div>
<jsp:include page="../footer.jsp"/>
  