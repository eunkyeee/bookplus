<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Jekyll v3.8.5">
    <title>BOOKPLUS</title>

    <!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/css/bootstrap.css" rel="stylesheet">
<!-- fontawesome -->
<link href="${pageContext.request.contextPath}/webjars/font-awesome/5.9.0/css/all.css" rel="stylesheet">

  <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="${pageContext.request.contextPath}/webjars/jquery/3.4.1/dist/jquery.js" ></script>
    <script src="${pageContext.request.contextPath}/webjars/popper.js/1.14.7/dist/umd/popper.js"></script>
    <script src="${pageContext.request.contextPath}/webjars/bootstrap/4.3.1/js/bootstrap.js"></script>

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->
    <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath }/resources/css/blog.css" rel="stylesheet">
  </head>
  <body>
  <div class="container bg-light">
    <div class="container">
  <header class="blog-header">
    <div class="row flex-nowrap justify-content-between align-items-center" style="height:150px">
 <%--      <div class="col-2 pt-1">
        <a class="text-muted" href="${pageContext.request.contextPath}"><i class="fas fa-home fa-lg" style="color: gray"></i></a>
      </div> --%>
      <div class="col-md-8">
        <a class="blog-header-logo text-dark" href="${pageContext.request.contextPath}">BOOK+ Library</a>
      </div>
      <div class="col-md-4 d-flex justify-content-end align-items-center">
       <c:choose>
        	<c:when test="${sessionScope.user eq null}">
        		<a class="btn btn-sm btn-outline-secondary mr-2" href="${pageContext.request.contextPath}/login/login">로 그 인</a>
        		<a class="btn btn-sm btn-outline-secondary" href="${pageContext.request.contextPath}/member/memberJoinForm">회원가입</a>
        	</c:when>

        	<c:otherwise>
        		<div class="btn-group">
        			<button class="btn btn-sm btn-outline-secondary dropdown-toggle mr-2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        				${sessionScope.user.nickname}님
        			</button>
        			<div class="dropdown-menu">
        				<a href="${pageContext.request.contextPath}/member/memberModifyForm/${sessionScope.user.id}" class="dropdown-item modifyBtn">내정보수정</a>
        				<a href="${pageContext.request.contextPath}/member/changePwForm" class="dropdown-item">비밀번호 변경</a>
        				<a href="${pageContext.request.contextPath}/member/memberDeleteForm/${sessionScope.user.id}" class="dropdown-item">탈퇴</a>
        			</div>
        			<a href="${pageContext.request.contextPath}/login/logout" class="btn btn-sm btn-outline-secondary">로그아웃</a>
        		</div>
        	</c:otherwise>
        </c:choose>
      </div>
    </div>
  </header>

  <div class="nav-scroller py-1 mb-2">
    <nav class="nav d-flex justify-content-between">
      <a class="p-2 text-muted" href="#">전자도서관</a>
      <a class="p-2 text-muted" href="#">자료검색</a>
      <a class="p-2 text-muted" href="#">문화</a>
      <a class="p-2 text-muted" href="${pageContext.request.contextPath}/bbs/list">자유게시판</a>
      <a class="p-2 text-muted" href="#">도서관 소개</a>
      <a class="p-2 text-muted" href="#">문의</a>

    </nav>
  </div>