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
						<li><a class="tab_selected" href="#">서비스 찾기</a></li>
						<li><a class="" href="#">고수 찾기</a></li>
					</ul>
				</div>

				<!-- 서비스 찾기 화면  -->
				<div class="service_search">
					<h2 class="tab-title">
						<b>어떤 서비스가 <br> 필요하세요?</b>
					</h2>

					<div class="input-group">
						<input type="text" placeholder="서비스를 검색하세요" id="search">
						<div class="input-search">
							<img src="images/search.svg">
						</div>
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
					<li><a href="#"> <img src="images/lesson.svg">
						<p>코딩</p>
					</a></li>
					<li><a href="#"> <img src="images/design-development.svg">
						<p>요리</p>
					</a></li>
					<li><a href="#"> <img src="images/part-time-job.svg">
						<p>댄스</p>
					</a></li>
					<li><a href="#"> <img src="images/event.svg">
						<p>음악</p>
					</a></li>
					<li><a href="#"> <img src="images/health-beauty.svg">
						<p>운동</p>
					</a></li>
					<li><a href="#"> <img src="images/business.svg">
						<p>영어</p>
					</a></li>
				</ul>
			</div>

			<!-- 고수 찾기 화면  -->


			<!-- 메인화면 사진  -->
			<div class="main_image">
				<img id="main_img" src="images/3.png">

			</div>
		</div>
	</div>
	<script src="js/mainpage/main_image.js"></script>
	<script src="js/mainpage/searchdata.js"></script>
</body>
<script type="text/javascript">
	var imgtag = document.getElementById("main_img");
	var imgarr = [ "images/1.png", "images/2.png", "images/3.png" ];
	var imgindex = 0;
	function changeimg() {
		imgtag.setAttribute("src", imgarr[imgindex]);
		imgindex++;
		if (imgindex >= imgarr.length) {
			imgindex = 0;
		}
	}
	setInterval(changeimg, 5000);
	
	/* $(".sevice_btn").click(function(){
		$('.sevice_btn').attr('class','tab_selected');
	})
	 */
	
	$('.script_ul > li >a').click(function(){
		$('.script_ul > li >a').attr('class','');
		$(this).attr('class','tab_selected');
		
	})
	
</script>
</html>