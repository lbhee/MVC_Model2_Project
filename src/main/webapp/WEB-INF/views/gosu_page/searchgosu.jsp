<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<jsp:include page="../include/head.jsp"></jsp:include>

 
   <link rel="stylesheet" href="css/versions.css">
   <link rel="stylesheet" href="css/style.css">
<body class="host_version"> 
<script src="js/mainpage/searchdata.js"></script>
	
	<div class="all-title-box">
		<div class="container text-center">

			<h1 id="gsearch"></h1>
			<p>지금 <b style="color:#00c7ae">ㄱㅅㅊㄱ</b>와 함께 시작해보세요</p>
		</div>
	</div>
	<ul class="gosu_page_category">
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
    
      <div class="text-center">
      <h1>고수 리스트</h1>
      
      <div id="resultsearch"></div>
      
      </div>
	
		<div id="overviews" class="section lb">
        <div class="container">
            <div class="section-title text-center">
                <div class="col-md-8 offset-md-2">
                    <h1>나에게 딱! 맞는 고수를 선택하세요!</h1><br>
					<p class="lead">쨘! 도움을 줄 수 있는 고수를 무료로 찾았습니다.<br>
									고수의 개인페이지로 이동하여 고수의 정보를 더 얻을 수 있어요.</p>

                </div>
            </div>
          </div>
      </div>

    <!-- ALL JS FILES -->
    <script src="js/all.js"></script>
    <!-- ALL PLUGINS -->
    <script src="js/custom.js"></script>
    <script type="text/javascript">
    	var code = <%=request.getParameter("d_code")%>;
    	var h1 = document.getElementById("gsearch");

    	h1.innerHTML = <%=request.getParameter("title")%>


    	
    	function searchgosu() {
    		$.ajax({

    			url: "SearchGosu_A",
    			data: {d_code:code},
    			success:function(responsedata){
    				
    				$('#resultsearch').empty();
    				$('#resultsearch').append(responsedata);
    			}
    		});
    	}

    	$(function(){
    		searchgosu();
    	});
    	
    </script>

</body>
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>