<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp" />
<style>
	.page-link {
	  position: relative;
	  display: block;
	  padding: 0.5rem 0.75rem;
	  margin-left: -1px;
	  line-height: 1.25;
	  color: #000;
	  background-color: #fff;
	  /* border: 1px solid #dee2e6; */
	  border:none;
	}
	
	.page-link:hover {
	  z-index: 2;
	  color: #0056b3;
	  text-decoration: none;
	  background-color: #e9ecef;
	  border-color: #dee2e6;
	}
	
	.page-link:focus {
	  z-index: 2;
	  outline: 0;
	  box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
	}
	
	.page-item:first-child .page-link {
	  margin-left: 0;
	  border-top-left-radius: 0.25rem;
	  border-bottom-left-radius: 0.25rem;
	}
	
	.page-item:last-child .page-link {
	  border-top-right-radius: 0.25rem;
	  border-bottom-right-radius: 0.25rem;
	}
	
	.page-item.active .page-link {
	  z-index: 1;
	  color: #fff;
	  background-color: #007bff;
	  border-color: #007bff;
	}
	
	.page-item.disabled .page-link {
	  color: #6c757d;
	  pointer-events: none;
	  cursor: auto;
	  background-color: #fff;
	  border-color: #dee2e6;
	}
	 @media (min-width: 320px) {
			.hidden1{
				display: none;
			}
  	 }
	 @media (min-width: 768px) {
			.hidden1{
				display: block;
			}
  	 }
</style>
<script>

//case1)
/* 	$(document).on("ready",function(){
		
	}) */

	//case2)
	$(function(){
		//검색버튼 클릭 시
		$("#searchBtn").on("click",function(){
			
			//검색어 입력값이 없으면
			if($("#keyword").val().trim().length==0){
				alert("검색어를 입력하세요.");
				$("#keyword").focus();
				return false;
			}
			
			var $reqPage=${pc.rc.reqPage};
			var $searchType=$("#key1 option:selected").val();
			var $keyword=$("#keyword").val().trim();
			var $url  ="${pageContext.request.contextPath}/bbs/list/";
					$url += $reqPage+"/"+$searchType+"/"+$keyword;
			
 			$("form").attr("action",$url);
			$("form").submit(); 
		});
		
		//글쓰기 버튼 클릭 시
		$("#writeBtn").on("click",function(){
			var user="${sessionScope.user==null ? null:sessionScope.user.id}";
			
			//로그인 전이면 로그인화면으로 이동
			if(user==null || user==""){
					if(confirm('로그인 후 댓글작성이 가능합니다\n로그인 하시겠습니까?')){
						document.location.href='${pageContext.request.contextPath }/login/login';
					}
					return;
			}
			
			//로그인 후면 글쓰기화면으로 이동
			document.location.href="${pageContext.request.contextPath}/bbs/write";
			
		});
		
	});
</script>



<!-- 게시글 목록 Grid -->
<div class="container">
	<div class="row justify-content-center">
		<div class="col">
			<div class="row justify-content-center my-5">
				<p class="h4">게시글 목록</p>
			</div>
		<div class="">
			<div class="row py-4 font-weight-bold border" style="color: #521011; background-color: #F8E0D4">
				<div class="col col-md-1 text-center ">번호</div>
				<div class="col col-md-6 text-center">제목</div>
				<div class="col col-md-2 text-center hidden1">작성자</div>
				<div class="col col-md-2 text-center hidden1">작성일</div>
				<div class="col col-md-1 text-center hidden1">조회수</div>
			</div>
			<c:forEach var="rec" items="${list}">
			<div >
			<div class="row py-2 border-top border-bottom ">
				<div class="col col-md-1 text-center small">${rec.bnum }</div>
				<div class="col col-md-6">
				<c:forEach begin="1" end="${rec.bindent }">&nbsp;&nbsp;</c:forEach>
				<c:if test="${rec.bindent>0 }">
					<img src="${pageContext.request.contextPath}/resources/img/icon_reply.gif"/>
				</c:if>
					<a class="text-secondary" style="text-decoration:none" href="${pageContext.request.contextPath}/bbs/read/${pc.rc.reqPage}/${rec.bnum}"> ${rec.btitle }</a>
				</div>
				<div class="col col-md-2 text-center small hidden1">${rec.bnickname }</div>
				<div class="col col-md-2 text-center small hidden1">${rec.bcdate }</div>
				<div class="col col-md-1 text-center small hidden1">${rec.bhit }</div>
			</div>
			</div>
			</c:forEach>
			</div>
			<!-- 글쓰기 버튼 -->
			<div class="row justify-content-end my-3">
				<button type="button" class="btn btn-outline-secondary btn-sm" id="writeBtn">글쓰기</button>
			</div>
			
			<!-- 페이징 Pagination -->
			<div class="row justify-content-center my-3">
				<nav aria-label="...">
				  <ul class="pagination pagination-sm">
				  
				  
				<!-- 처음/이전 페이지 이동 -->
				  <c:choose>
				  <c:when test="${pc.prev }">
				    <li class="page-item">
				      <a class="page-link" href="${pageContext.request.contextPath}/bbs/list/1" tabindex="-1" aria-disabled="true">◀</a>
				    </li>

				    <li class="page-item">
				      <a class="page-link" href="${pageContext.request.contextPath}/bbs/list/${pc.startPage-1}" tabindex="-1" aria-disabled="true">◁</a>
				    </li>
				  </c:when>
				    <c:otherwise>
				    	<li class="page-item disabled">
				      	<a class="page-link" href="#" tabindex="-1" aria-disabled="true">◀</a>
				    	</li>

				    	<li class="page-item disabled">
				      	<a class="page-link" href="#" tabindex="-1" aria-disabled="true">◁</a>
				    	</li>
				    </c:otherwise>
				  </c:choose>
				    
				    <c:forEach var="pageNum" begin="${pc.startPage }" end="${pc.endPage }">
				    <c:if test="${pc.rc.reqPage != pageNum }">
				    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/bbs/list/${pageNum}/${pc.searchType}/${pc.keyword}">${pageNum }</a></li>
				    </c:if>
				    <c:if test="${pc.rc.reqPage == pageNum }">
				    <li class="page-item active" aria-current="page">
				      <a class="page-link" href="${pageContext.request.contextPath}/bbs/list/${pageNum}/${pc.searchType}/${pc.keyword}">${pageNum }<span class="sr-only">(current)</span></a>
				    </li>
				    </c:if>
				    </c:forEach>
				    
				    <!-- 다음/끝 페이지 이동 -->
				    <c:choose>
				  	<c:when test="${pc.next }">
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/bbs/list/${pc.endPage+1}">▷</a>
					    </li>
					    
					    <li class="page-item">
					      <a class="page-link" href="${pageContext.request.contextPath}/bbs/list/${pc.finalEndPage}">▶</a>
					    </li>
				  	</c:when>
				  	<c:otherwise>
					  	<li class="page-item disabled">
					      <a class="page-link" href="#">▷</a>
					    </li>
					    
					    <li class="page-item disabled">
					      <a class="page-link" href="#">▶</a>
					    </li>
				  	</c:otherwise>
				    </c:choose>
				  </ul>
				</nav>
		</div>
			<!-- 검색 select-->	
			<div class="row justify-content-center mb-4">
				<form>
				<div class="form-group row">
					<div class="col col-sm-4 px-0">
					<select id="key1" name="searchType" class="form-control form-control-sm">
						<option value="TC"
							<c:out value="${pc.searchType == 'TC' ? 'selected' : ''}"/>>제목+내용</option>
						<option value="T"
							<c:out value="${pc.searchType == 'T' ? 'selected' : ''}"/>>제목</option>
						<option value="C"
							<c:out value="${pc.searchType == 'C' ? 'selected' : ''}"/>>내용</option>
						<option value="N"
							<c:out value="${pc.searchType == 'N' ? 'selected' : ''}"/>>작성자</option>
						<option value="I"
							<c:out value="${pc.searchType == 'I' ? 'selected' : ''}"/>>아이디</option>
					</select>
					</div>
					<div class="col col-sm-6 px-1">
					<input type="text" name="keyword" id="keyword" class="form-control form-control-sm"
								 value="${pc.keyword }" placeholder="검색어를 입력하세요!"/>
					</div>
					<div class="col col-sm-2 px-0">							 
					<button class="btn btn-sm btn-outline-secondary align-baseline" type="button" id="searchBtn">검색</button>
					</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<jsp:include page="../footer.jsp" />