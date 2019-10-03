<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <style>
    	.n1{
    		margin: 20px 0 20px;
    	}
    	@media (min-width: 320px) {
	    	.img{
	    		display: none;
	    	}
    		.ebook{
    			width: 70px;
    			height: 30px;
    			font-size: 0.7em;
    		}
    	@media (min-width: 576px) {
	    	.img{
	    		display: inline;
	    	}
    		.ebook{
    			width: 150px;
    			height: 50px;
    			font-size: 1em;
    		}
    	}
    </style>
<main role="main" class="container">
  <div class="container">
    <div class="row">
        <div class="col-sm-12 col-lg-6 my-5 mt-5">
          <p class="secname"><strong>공지사항</strong></p>
          <div class="mx-5">
            <div class="n1 border-bottom"><a href="#" class="my-3" style="text-decoration: none; color:#164DB0; padding-bottom:10px;">[공지] 북플러스도서관 「2019 독서의 달」 운영 안내</a></div>
            <div class="n1 border-bottom"><a href="#" style="text-decoration: none; color:#164DB0; padding-bottom:10px;">[공지] 북플러스도서관 「하반기 북스타트프로그램」 운영 안내</a></div>
            <div class="n1 border-bottom"><a href="#" style="text-decoration: none; color:#164DB0; padding-bottom:10px;">[문화] 8월 문화가 있는 날 행사 안내 " 시간을 거슬러 흐르는 8.15"</a></div>
          </div>
        </div>
        <div class="col-sm-12 col-lg-6 my-5 mt-5">
          <p class="secname"><strong>전자도서관</strong></p>
          <div class="text-center justify-content-center">
          	<div class="ml-2 my-2 mb-4" style="color:#164DB0">
          		<img class="" src="${pageContext.request.contextPath}/resources/img/ebook.png" alt="ebook" width="200px" height="200px">
          	<div class="container">
					  <div class="row mt-4">
					    <div class="col mb-2">전자도서관 이용이 <br>처음이신가요?</div>
					    <div class="col mb-2">온라인 <br>이용하기</div>		  
					    <div class="w-100"></div>
					    <div class="col"><button class="btn btn-lg btn-outline-warning shadow-md ebook">Guide</button></div>
					    <div class="col"><button class="btn btn-lg btn-outline-warning shadow-md ebook">Online</button></div>
					  </div>
					</div>
          	</div>
       	  </div>
      </div>
      <div class="container mb-2 mt-5">
		  <div class="row text-center">
		    <div class="col border" style="background-color: #A9A9A9; border-top-left-radius:30px;">
		    	<a href="#" style="text-decoration: none; color: white;"><img src="${pageContext.request.contextPath}/resources/img/media.png" alt="" width="70px" height="70px" class="mr-2 img"/>미디어 예약</a>
		    </div>
		    <div class="col border" style="background-color: #F2EDD7; border-top-right-radius:30px;">
					<a href="#" style="text-decoration: none; color: gray;"><img src="${pageContext.request.contextPath}/resources/img/culture.png" alt="" width="70px" height="70px" class="mr-2 img"/>문화강좌 신청</a>
				</div>
		    <div class="w-100"></div>
		    <div class="col border" style="background-color: #F2EDD7; border-bottom-left-radius:30px;">
		    	<a href="#" style="text-decoration: none; color: gray;"><img src="${pageContext.request.contextPath}/resources/img/borrow.png" alt="" width="70px" height="70px" class="mr-2 img"/>대관 신청</a>
				</div>
		    <div class="col border" style="background-color: #A9A9A9; border-bottom-right-radius:30px;">
					<a href="#" style="text-decoration: none; color: white;"><img src="${pageContext.request.contextPath}/resources/img/study.png" alt="" width="70px" height="70px" class="mr-2 img"/>스터디룸 예약</a>
				</div>
		  </div>
		</div>
      
    </div>
 
  
  </div><!-- /.row -->

</main><!-- /.container -->