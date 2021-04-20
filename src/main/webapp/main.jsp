<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<jsp:include page="WEB-INF/views/include/head.jsp"></jsp:include>
<link rel="stylesheet" href="css/Team3.css">
<body>
	<div class="container">
		<div class="wrapper">
			<div class="tabs">
				<div class="tab_select">

					<ul class="script_ul">
					<li><a class="sevice_btn" href="#">서비스 찾기</a></li>
					<li><a class="gosu_btn" href="#">고수 찾기</a></li>

					</ul>
					<div>
						<input type="text" id="search"><br>
						<a id="searchresult"></a>
					</div>
				</div>
			</div>
			<div class="main_image">
				<div class="image_upload">
				<img src="#" id="main_img">
				
				</div>

				<!-- 서비스 찾기 화면  -->
				<div class="service_search">
				<h2 class="tab-title"><b>어떤 서비스가 <br>
						필요하세요?</b></h2>
				
				<div class="service-searcher">
					<div class="input-group">
						<input type="text" placeholder="서비스를 검색하세요">
						<div class="input-search">
						<img src="images/search.svg">
						</div>
					</div>		
				</div>
				<ul class="category">
				<li>
				<a href="#">
				<img src="images/lesson.svg"><p>레슨1</p>
				</a>
				</li>
				<li>
				<a href="#">
				<img src="images/design-development.svg"><p>레슨1</p>
				</a>
				</li>
				<li>
				<a href="#">
				<img src="images/part-time-job.svg"><p>레슨1</p>
				</a>
				</li>
				<li>
				<a href="#">
				<img src="images/event.svg"><p>레슨1</p>
				</a>
				</li>
				<li>
				<a href="#">
				<img src="images/health-beauty.svg"><p>레슨1</p>
				</a>
				</li>
				<li>
				<a href="#">
				<img src="images/business.svg"><p>레슨1</p>
				</a>
				</li>
				</ul>
			</div>
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
	console.log($('script_ul > li'));
	
	$('script_ul').children("li").click(function(){
		console.log("클");
		$(this).attr('class','tab_selected');
	})
	
</script>
</html>