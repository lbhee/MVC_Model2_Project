<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<jsp:include page="WEB-INF/views/include/head.html"></jsp:include>
<link rel="stylesheet" href="css/Team3.css">
<body>
	<div class="container">
		<div class="wrapper">
			<div class="tabs">
				<div class="tab_select">
					<ul>
						<li>서비스 찾기</li>
						<li>고수 찾기<hr></li>
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
			</div>
		</div>
	</div>
	<script src="js/mainpage/main_image.js"></script>
	<script src="js/mainpage/searchdata.js"></script>
</body>
</html>

<!-- 
<ul>
							<li class="dd"><a href = "#">프로그래밍 코딩 레슨</a></li>
							<li class="dd"><a href = "#">보컬 레슨</a></li>
							<li class="dd"><a href = "#">퍼스널트레이닝</a></li>
							<li class="dd"><a href = "#">요리/조리 레슨</a></li>
							<li class="dd"><a href = "#">댄스 레슨</a></li>
							<li class="dd"><a href = "#">영어과외</a></li>
						</ul>
 -->