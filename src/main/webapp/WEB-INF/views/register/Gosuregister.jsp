<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="../include/head.jsp"></jsp:include>
<body>
<div class="container loginForm">
		<div class="loginForm">
			<form action="<%= request.getContextPath() %>/GosuregisterOk.go" method = "post" id="gosujoin">
				<h2 style="text-align: center"><b>고수가입</b></h2>

				<div class="regidiv">
				<p>이메일</p>
				<input type="text" name="email" value="<%=session.getAttribute("ID")%>" readonly><br>
				<p class="tdmail"></p>
				
				<p>자기소개</p>
				<input type="text" name="pr" id="pr" value="" placeholder="자기소개를 입력해주세요."><br>
				<p class="tdmail"></p>

				<p>서비스분야</p>
				<div class="radio_wrap">
					<input type="radio" class = "radio" name="d_code" value="101" checked><span>프론트</span>
					<input type="radio" class = "radio" name="d_code" value="102"><span>백앤드</span><br>
					<input type="radio" class = "radio" name="d_code" value="201"><span>가요</span>
					<input type="radio" class = "radio" name="d_code" value="202"><span>팝</span><br>
					<input type="radio" class = "radio" name="d_code" value="301"><span>PT</span>
					<input type="radio" class = "radio" name="d_code" value="302"><span>유산소</span><br>
					<input type="radio" class = "radio" name="d_code" value="401"><span>토익</span>
					<input type="radio" class = "radio" name="d_code" value="402"><span>회화</span><br>
					<input type="radio" class = "radio" name="d_code" value="501"><span>스트릿</span>
					<input type="radio" class = "radio" name="d_code" value="502"><span>폴</span><br>
					<input type="radio" class = "radio" name="d_code" value="601"><span>한식</span>
					<input type="radio" class = "radio" name="d_code" value="602"><span>양식</span><br>
					<p class="tdmail"></p>
				</div>
				<input type="button" value ="고수가입" id="gosubtn">
				</div>	
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">

	$('#gosubtn').click(function(){
			$('#gosujoin').submit();
	});
	
</script>
</html>
