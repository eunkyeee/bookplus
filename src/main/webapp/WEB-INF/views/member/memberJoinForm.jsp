<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:include page="../header.jsp"/>
<head>
<script>
(function(){
	window.addEventListener("load", init, false);
	function init() {
		let idTag 			= document.getElementById("id");
		let pwTag 			= document.getElementById("pw");
		let pwchkTag 		= document.getElementById("pwChk");
		let telTag 			= document.getElementById("tel");
		let regionTag 	= document.getElementById("region");
		let genderTag 	= document.getElementsByName("gender");
		let nicknameTag = document.getElementById("nickname");
		let joinBtn     = document.getElementById("joinBtn");

		idTag.addEventListener("blur"				,checkId,false);		
		idTag.addEventListener("change"			,checkId,false);
		idTag.addEventListener("keydown"		,checkId,false);
		
		pwTag.addEventListener("blur"				,checkPw,false);		
		pwTag.addEventListener("change"			,checkPw,false);
		pwTag.addEventListener("keydown"		,checkPw,false);
				
		pwchkTag.addEventListener("blur"		,checkPwchk,false);
 		pwchkTag.addEventListener("change"	,checkPwchk,false);
		pwchkTag.addEventListener("keydown"	,checkPwchk,false);
		
		telTag.addEventListener("blur"			,checkTel,false);
		telTag.addEventListener("change"		,checkTel,false);
		telTag.addEventListener("keydown"		,checkTel,false);
		
		regionTag.addEventListener("blur"		,checkRegion,false);
		regionTag.addEventListener("change"	,checkRegion,false);
		regionTag.addEventListener("keydown",checkRegion,false);
		
		Array.from(genderTag).forEach(function(item) {
			item.addEventListener("blur"	,checkGender,false);
			item.addEventListener("change",checkGender,false);
		});
		
		nicknameTag.addEventListener("blur"		,checkNickname,false);
		nicknameTag.addEventListener("change"	,checkNickname,false);
		nicknameTag.addEventListener("keydown",checkNickname,false);
		
		joinBtn.addEventListener("click",joinBtnf,false);
				
		function validFeedback(obj, result, msg) {
			//console.log(obj.id);
			if(result){
				if(!obj.classList.contains("is-valid")) {
					obj.classList.add("is-valid");
					obj.classList.remove("is-invalid")
					obj.nextElementSibling.innerHTML = msg;
					if(!obj.classList.contains("valid-feedback")) {
						obj.nextElementSibling.classList.add("valid-feedback");
						obj.nextElementSibling.classList.remove("invalid-feedback");
					}
				}				

			}else{
				if(!obj.classList.contains("is-invalid")) {
					obj.classList.add("is-invalid");
					obj.classList.remove("is-valid")
					obj.nextElementSibling.innerHTML = msg;
					if(!obj.classList.contains("invalid-feedback")) {
						obj.nextElementSibling.classList.add("invalid-feedback");
						obj.nextElementSibling.classList.remove("valid-feedback");
					}
				}
				if(obj.id != "pwchk") {
					obj.focus();
				}
			}
		}
		
		//이메일 주소 체크   
		function checkId() {
			let emailReg = /^((?!\.)[\w-_.]*[^.])(@\w+)(\.\w+(\.\w+)?[^.\W])$/;
			let status = emailReg.test(idTag.value);
			let msg = "";
			if(status) {
				validFeedback(idTag,status,"유효성 통과");
			}else{
				validFeedback(idTag,status,"이메일 정보가 잘못되었습니다.");
			}
			return status;
		}

    // 비밀번호 체크 : 6~10이하, 소문자,숫자,대문자,특수문자가 들어가는 비밀번호		
		function checkPw() {
		  let pwdReg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{6,10}$/;
			let status = pwdReg.test(pwTag.value);
			let msg = "";
			if(status) {
				validFeedback(pwTag,status,"유효성 통과");
			}else{
				validFeedback(pwTag,status,"비밀번호가 잘못되었습니다.");
			}
			return status;
		}
		// 비밀번호 확인체크
		function checkPwchk() {
      let pwdReg = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])[A-Za-z\d$@$!%*?&]{6,10}$/;
			let status = pwdReg.test(pwchkTag.value) && pwTag.value == pwchkTag.value;
			let msg = "";
			//console.log(pwTag.value, pwchkTag.value);
			if(status) {
				validFeedback(pwchkTag,status,"유효성 통과");
			}else{
				validFeedback(pwchkTag,status,"비밀번호가 잘못되었습니다.");
			}
			return status;
		}
		
		//전화번호 체크
		function checkTel() {
			let phoneReg = /(\d{3})-(\d{3,4})-(\d{4})/;
			let status = phoneReg.test(telTag.value);
			let msg = "";
			if(phoneReg.test(telTag.value)) {
				validFeedback(telTag,status,"유효성 통과");
			}else {
				validFeedback(telTag,status,"전화번호가 잘못되었습니다.");
			}
			return status;
		}
		
		//지역 체크
		function checkRegion() {
		  let regionText = regionTag.options[regionTag.selectedIndex].text;
		  let regionValue = regionTag.options[regionTag.selectedIndex].value;
		  
      let status = regionValue != 0 ? true : false;
      if(status) {
    	  validFeedback(regionTag,status,"유효성 통과");
      }else{
    	  validFeedback(regionTag,status,"지역을 선택하세요.");
      }	
      return status;
		}
		
		//성별 체크
		function checkGender() {
			let status = false;
			Array.from(genderTag).forEach(function(item){
				if(item.checked) {
					status = true;
				}
			});
      if(status) {
    	  validFeedback(genderTag[0].parentElement.parentElement,status,"유효성 통과");
      }else{
    	  validFeedback(genderTag[0].parentElement.parentElement,status,"성별 선택하세요.");
      }		

      return status;
		}
		
		//별칭 체크
		function checkNickname() {
      let status = nicknameTag.value.trim().length != 0;
      //console.log(nicknameTag.value, status);
      if(status) {
    	  validFeedback(nicknameTag,status,"유효성 통과");
      }else{
    	  validFeedback(nicknameTag,status,"닉네임을 입력하세요.");
      }			
      return status;
		}
				
		//가입하기 버튼 클릭시
		function joinBtnf(e) {
			e.preventDefault();
			
			let result = checkId() && checkPw() && 
									 checkPwchk() && checkTel() && 
									 checkRegion() && checkGender() && checkNickname();
			if(result) {
      	alert('회원 가입이 정상적으로 처리되었습니다.');
      	document.getElementById("mdto").submit();
			}
		}				
	}	
})();

  </script>
 </head> 
	<div class="mt-5 mb-5">
    <form:form class="formTotal" modelAttribute="mdto" action="${pageContext.request.contextPath }/member/memberJoin">
    <div class="form-row">
    	<div class="col-md-4 mb-5 mx-auto text-center h2">회원가입</div>
    </div>
      <div class="form-row">
        <div class="col-md-4 mb-3 mx-auto">
          <form:label path="id">아이디</form:label>
          <form:input path="id" type="text" cssClass="form-control" placeholder="ID@test.com" required="required"/>
          <form:errors path="id" cssClass="errMsg"></form:errors>
          <div class=""></div>
        </div>
      </div>
      <div class="form-row">
        <div class="col-md-4 mb-3 mx-auto">
          <form:label path="pw">비밀번호</form:label>
          <form:password cssClass="form-control" path="pw" placeholder="6자리 이상 영문/숫자 포함" required="required"/>
          <form:errors path="pw" cssClass="errMsg"></form:errors>
          <div class=""></div>
        </div>
      </div>
      <div class="form-row">
        <div class="col-md-4 mb-3 mx-auto">
          <label for="pwChk">비밀번호 확인</label>
          <input type="password" class="form-control" id="pwChk" name="pwChk" placeholder="6자리 이상 영문/숫자 포함" required>
          <div class=""></div>
        </div>
      </div>
      <div class="form-row">
        <div class="col-md-4 mb-3 mx-auto">
          <form:label path="tel">전화번호</form:label>
          <form:input type="text" cssClass="form-control" path="tel" placeholder="'-'를 포함하여 입력해주세요." required="required"/>
          <form:errors path="tel" cssClass="errMsg"></form:errors>
          <div class=""></div>
        </div>
      </div>
      <div class="form-row" >
        <div class="col-md-4 mb-3 mx-auto">
          <form:label path="region">지역</form:label>
          <form:select cssClass="custom-select" path="region">
              <option value="0">==지역선택==</option> 
<!--               <option value="서울">서울</option>
              <option value="경기">경기</option>
              <option value="대구">대구</option>
              <option value="울산">울산</option>
              <option value="기타">기타</option> -->
              <form:options path="region" items="${region }" itemValue="code" itemLabel="label"/>
            </form:select>
            <form:errors path="region" cssClass="errMsg"></form:errors>
          <div class=""></div>
        </div>
      </div>
      <div class="form-row">
        <div class="col-md-4 mb-3 mx-auto">
            <form:label path="gender">성별</form:label>
            <div class="form-control">
              <form:radiobuttons items="${gender }" cssClass="form-check-label ml-2" path="gender" itemValue="code" itemLabel="label"/>
            </div>
            <form:errors path="gender" cssClass="errMsg"></form:errors>
            <div class=""></div>
          </div>
        </div>
        <div class="form-row">
        <div class="col-md-4 mb-3 mx-auto">
          <form:label path="nickname">닉네임</form:label>
          <form:input type="text" cssClass="form-control" path="nickname" placeholder="Nickname" required="required"/>
          <form:errors path="nickname" cssClass="errMsg"></form:errors>
          <div class=""></div>
      </div >
    </div>
    <div class="form-row ">
    <div class="mx-auto">
    	<input class="btn btn-outline-primary btn-block" id="joinBtn" type="button" value="가입하기">
    </div> 
     </div>
    </form:form>
  </div>
    <jsp:include page="../footer.jsp"/>