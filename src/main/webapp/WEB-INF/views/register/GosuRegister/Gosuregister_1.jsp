<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="../../include/head.jsp"></jsp:include>
<% 
String id = (String)session.getAttribute("ID");

if(id == null){
	response.sendRedirect(request.getContextPath()+"/main.jsp");
	return;
}
%>
<body>
<div class="Gosu_image">
		
</div>
<div class="container">
			<div class="Gosu_contaniner">
			<h1>고수로 등록하고,<br>
				<b style="color:#00c7ae">ㄱㅅㅊㄱ</b>에서 고객을 만나보세요!</h1>
			<p>숨고는 국내 최대 규모의 생활서비스 플랫폼입니다. <br>
				가장 빠르고 간편하게 고객을 찾을 수 있습니다.</p>	
			
			<div class="loginForm">
				<div class="gosu_register_box">
					<h3>고수로 가입하실 분야를 선택하세요</h3>
					<ul class="gosu_register_category">
						<li><a href="<%= request.getContextPath() %>/Gosuregister_2.go?code=100"> <img src="images/lesson.svg">
							<p>코딩</p>
						</a></li>
						<li><a href="<%= request.getContextPath() %>/Gosuregister_2.go?code=200"> <img src="images/event.svg">
							<p>음악</p>
						</a></li>
						<li><a href="<%= request.getContextPath() %>/Gosuregister_2.go?code=300"> <img src="images/health-beauty.svg">
							<p>운동</p>
						</a></li>
						<li><a href="<%= request.getContextPath() %>/Gosuregister_2.go?code=400"> <img src="images/business.svg">
							<p>영어</p>
						</a></li>
						<li><a href="<%= request.getContextPath() %>/Gosuregister_2.go?code=500"> <img src="images/part-time-job.svg">
							<p>댄스</p>
						</a></li>	
						<li><a href="<%= request.getContextPath() %>/Gosuregister_2.go?code=600"> <img src="images/design-development.svg">
							<p>요리</p>
						</a></li>
					</ul>
				</div>		
			</div>	
		</div>
	</div>
</body>
<script type="text/javascript">

	$('#gosubtn').click(function(){
			$('#gosujoin').submit();
	});
	
</script>
</html>