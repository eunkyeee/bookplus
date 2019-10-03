<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	@media (min-width: 320px) {
			.book{
				width: 200px;
				height: 250px;
			}
		}
	@media (min-width: 992px) {
			.book{
				width: 150px;
				height: 250px;
			}
		}
	@media (min-width: 1440px) {
			.book{
				width: 200px;
				height: 250px;
			}
		}
</style>
    <div class="jumbotron p-4 p-md-5 text-white rounded bg-dark" style="background-image: url('${pageContext.request.contextPath}/resources/img/booknail.jpg')">
    <div class="px-0">
    <div class="" style="margin-bottom: 150px"></div>
      <form class="form-inline my-2 my-lg-0 justify-content-center">
      <input class="form-control mr-sm-2" type="search" placeholder="도서관 자료를 검색할 수 있습니다." aria-label="Search" style="width:400px">
      <button class="btn my-2 my-sm-0" type="submit" style="background-color:#F2EDD7">검색</button>
    </form>
    </div>
  </div>
  
   <div class="row mb-2">
   <div class="col-12 font-weight-bold my-2">
   	<h3>대출 베스트</h3>
   </div>
    <div class="col-md-6 col-lg-3">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-400 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
        <strong class="d-inline-block mb-2 text-danger">No.1</strong>
          <div class="d-inline-block mb-2 text-center"><img class="book" alt="당신은 뇌를 고칠 수 있다" src="${pageContext.request.contextPath}/resources/img/book1.jpg" width="150px" height="250px"></div>
          <h5 class="mb-3 text-center font-weight-bold">당신은 뇌를 고칠 수 있다</h5>
          <div class="small mb-1">매주 1시간 투자하여 최상의 기억력, 생산성, 수면을 얻는 법</div>
          <p class="small mb-auto mb-1">저자- 브론스테인 지음</p>
          <a href="#" class="stretched-link small">상세정보</a>
        </div>
      </div>
    </div>
       <div class="col-md-6 col-lg-3">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-400 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
        <strong class="d-inline-block mb-2 text-danger">No.2</strong>
          <div class="d-inline-block mb-2 text-center"><img class="book" alt="반일 종족주의" src="${pageContext.request.contextPath}/resources/img/book2.jpg" width="200px" height="250px"></div>
          <h5 class="mb-3 text-center font-weight-bold">반일 종족주의</h5>
          <div class="small mb-1">대한민국 위기의 근원</div>
          <p class="small mb-auto mb-1">저자- 이영훈 지음</p>
          <a href="#" class="stretched-link small">상세정보</a>
        </div>
      </div>
    </div>
       <div class="col-md-6 col-lg-3">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-400 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
        <strong class="d-inline-block mb-2 text-danger">No.3</strong>
          <div class="d-inline-block mb-2 text-center"><img class="book" alt="90년생이 온다" src="${pageContext.request.contextPath}/resources/img/book3.jpg" width="200px" height="250px"></div>
          <h5 class="mb-3 text-center font-weight-bold">90년생이 온다</h5>
          <div class="small mb-1">간단함, 병맛, 솔직함으로 기업의 흥망성쇠를 좌우하는</div>
          <p class="small mb-auto mb-1">저자- 임홍택 지음</p>
          <a href="#" class="stretched-link small">상세정보</a>
        </div>
      </div>
    </div>
       <div class="col-md-6 col-lg-3">
      <div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-400 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
        <strong class="d-inline-block mb-2 text-danger">No.4</strong>
          <div class="d-inline-block mb-2 text-center"><img class="book" alt="여행의 이유(바캉스 에디션) " src="${pageContext.request.contextPath}/resources/img/book4.jpg" width="200px" height="250px"></div>
          <h5 class="mb-3 text-center font-weight-bold">여행의 이유(바캉스 에디션) </h5>
          <div class="small mb-1">김영하 산문</div>
          <p class="small mb-auto mb-1">저자- 김영하 지음</p>
          <a href="#" class="stretched-link small">상세정보</a>
        </div>
      </div>
    </div>
  </div>
  </div>