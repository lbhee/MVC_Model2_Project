<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<jsp:include page="WEB-INF/views/include/head.jsp"></jsp:include>
<body class="host_version"> 
<script src="js/mainpage/searchdata.js"></script>
	
	<div class="all-title-box">
		<div class="container text-center">
			<h1 id="gsearch"><span class="m_1">지금 숨고와 함께 시작해보세요.</span></h1>
		</div>
	</div>
	
    <div id="overviews" class="section lb">
        <div class="container">
            <div class="section-title row text-center">
                <div class="col-md-8 offset-md-2">
                    <h1>나에게 딱! 맞는 고수를 선택하세요!</h1><br>
					<p class="lead">쨘! 도움을 줄 수 있는 고수를 무료로 찾았습니다.<br>
									직접 연락을 통해 고수의 정보를 더 얻을 수 있어요.</p>
                </div>
            </div>
          </div>
      </div>
      
      <div id = "resultsearch">
      
      </div>

            

    <!-- ALL JS FILES -->
    <script src="js/all.js"></script>
    <!-- ALL PLUGINS -->
    <script src="js/custom.js"></script>
    <script type="text/javascript">
    	var h1 = document.getElementById("gsearch");
    	h1.innerHTML = localStorage.getItem("고수분야");

    	$(function(){
    		searchgosu();

    	});
    	
    	function searchgosu() {
    		$.ajax({
    			url: "searchgosu.go",
    			data: {d_code:$('#searchvalue').val()},
    			success:function(responsedata){
    				
    				
    				$('#resultsearch').append(responsedata);
    					console.log(responsedata);
    				
    					
    				
    				/*
    				<div class="col-md-4 col-sm-6 col-xs-12">
                        <div class="icon-wrapper wow fadeIn" data-wow-duration="1s" data-wow-delay="0.6s">
                            <i class="flaticon-cloud-computing-1 global-radius effect-1 alignleft"></i>
                            <h3>코딩고수 - 이00</h3>
                            <p>웹개발 정보처리기사 컴활 웹디자인기능사 SpringBoot + AWS C/JAVA 프로그래밍
    						<small class="readmore">
    							<a href="#">개인페이지</a>
    						</small>
    						</p>
                        </div><!-- end icon-wrapper -->
                    </div>
    				*/
    			}
    		});
    	}
    </script>

</body>
</html>