<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${path}/resources/csss/index.css" rel="stylesheet"/> 
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
   <div id="header">   
          <div class="logbox">
              <img class="logimg" src="${pageContext.request.contextPath}/resources/img/headerlogo.png">
          </div>
         <div class="col-md-10">
			<ul class="lower-navbar">
				<li><a href="">유심/eSIM</a></li>
			    <li><a href="#" id="toggleLink">휴대폰</a></li>
			    <li><a href="">상품</a></li>
				<li><a href="#">이벤트/혜택</a></li>
				<li><a href="#">공지사항</a></li>
				<li><a href="#">내 정보</a></li>
				<li><a href="#">유모바일</a></li>			
			</ul>
			<div class="submenu">
			  <div class="submenus">
			      <div class="submenuimg">
			         <a href="#"><img src="${pageContext.request.contextPath}/resources/img/img-gnb-lo.png"></a>
			      </div>
			      <ul class="submeul">
			        <li class="submeli"><a href="/phone/phoneproduct">가입신청</a></li>
			        <li class="submeli"><a href="#">모델별 지원금</a></li>
			      </ul>
			  </div>
			  <div class="submenus">
			      <div class="submenuimg">
			         <a href="#"><img src="${pageContext.request.contextPath}/resources/img/img-gnb-lo1.png"></a>
			      </div>
			      <ul class="submeul">
			        <li class="submeli"><a href="#">가입신청</a></li>
			     
			      </ul>
			  </div>
			</div>
		</div>
		
		<div class="top-util">
		    <div class="navlog">
		    <a href="#">
		     <img class="navimg" src="${pageContext.request.contextPath}/resources/img/navlog1.png">
		     </a>
		    </div>
		      <div class="navlog">
		       <a href="#">
		       <img class="navimg" src="${pageContext.request.contextPath}/resources/img/navlog2.png" id="togoll">
		       </a>
		           <div class="navsub">
		               <div class="info-basic">
              		<button type="button" id="btns" onclick="window.location.href = '/user/login';">로그인</button>
		                <ul class="find-join">
		                  <li><a href="#">ID/비밀번호찾기</a></li>
		                  <li><a href="user/join">회원가입</a></li>
		                </ul>
		               </div>
		              <div class="info-func">
		                <div class="func-btn">
		                 <ul>
		                 <img src="${pageContext.request.contextPath}/resources/img/log-doc.svg">
		                   <li><a href="#">신청내역조회</a></li>
		                 </ul>
		                 </div>
		              </div>
	               </div>
		    </div>
		      <div class="navlog">
		       <a href="#">
		       <img class="navimg" src="${pageContext.request.contextPath}/resources/img/navlog3.png">
		      </a>
		    </div>
		</div>
    </div>
    
    <!-- 헤더 끝 -->
</body>
</html>
<script>
<!--메뉴 펼침 -->

$(document).ready(function() {
    let isSubmenuVisible = false; // .submenu 표시 상태 변수

    $('#toggleLink').click(function() {
        isSubmenuVisible = !isSubmenuVisible; // 상태 토글
        if (isSubmenuVisible) {
            $('.submenu').show(); // 표시
        } else {
            $('.submenu').hide(); // 숨김
        }
    });
});


<!--메뉴 펼침 -->

<!-- 로그인 모달 -->
$(document).ready(function() {
	    $('#togoll').click(function() {
	        $('.navsub').toggle();  // .navsub의 표시/숨김을 토글
	    });
	});

<!-- 로그인 모달 -->

</script>