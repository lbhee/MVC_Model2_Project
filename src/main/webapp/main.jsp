<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- header -->
<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
<body>
	<div class="container">
		<div class="wrapper">
			<div class="tabs">
				<div class="tab_select">
					<ul class="script_ul">
						<li><a class="tab_selected" href="#" id="search">서비스 찾기</a></li>
						
							<li><a class="gosumap" href="#" id="gosumap">고수 찾기</a></li>
					</ul>
				</div>

				<!-- 서비스 찾기 화면  -->
				<div class="service_search">
					<h2 class="tab-title">
						<b>어떤 서비스가 <br> 필요하세요?</b>
					</h2>
				
					<div class="input-group">
						<input type="text" placeholder="서비스를 검색하세요" id="searchbar">
						<input type="hidden" id="searchvalue">
						<div class="input-search">
							<img src="images/search.svg">
						</div>
					
					<!-- 검색결과 화면 -->
					<div class="searchBox" style="display: none">
						<div>
							<h1 id ="resultresearch"></h1>
						</div>
							<h2><a id="resultmove"></a></h2>
					</div>
					</div>
				
				<ul class="category">
					<li><a href="searchGosu.go?d_code=1&title='프로그래밍 / 코딩 레슨'"> <img src="images/lesson.svg">
						<p>코딩</p>
					</a></li>
					<li><a href="searchGosu.go?d_code=2&title='보컬 / 발성 레슨'"> <img src="images/event.svg">
						<p>음악</p>
					</a></li>
					<li><a href="searchGosu.go?d_code=3&title='퍼스널트레이닝(PT)'"> <img src="images/health-beauty.svg">
						<p>운동</p>
					</a></li>
					<li><a href="searchGosu.go?d_code=4&title='영어 / 토익 과외'"> <img src="images/business.svg">
						<p>영어</p>
					</a></li>
					<li><a href="searchGosu.go?d_code=5&title='댄스 레슨'"> <img src="images/part-time-job.svg">
						<p>댄스</p>
					</a></li>
					<li><a href="searchGosu.go?d_code=6&title='요리 / 조리 레슨'"> <img src="images/design-development.svg">
						<p>요리</p>
					</a></li>					
				</ul>
				</div>
				
			<!-- 고수 찾기 화면  -->
			<div class="Gosu_Map_Box">
			
			<div id="map" class="map"></div>
			<p> ⚠ 회원님의 현위치주변으로 검색이 됩니다.︎ </p>
			</div>
			</div>

			<!-- 메인화면 사진  -->
			<div class="main_image">
				<img id="main_img" src="images/3.png">

			</div>
		</div>
	</div>
	<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=53f9ac8e1b09677c674e020964ff22be&libraries=services"></script>
	<script src="js/mainpage/searchdata.js"></script>
	<script src="js/mainpage/main_js.js"></script>
</body>
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>