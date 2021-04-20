<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="../include/head.jsp"></jsp:include>
<body>
	<div class="container loginForm">
		<div class="loginForm">
			<form action="<%= request.getContextPath() %>/Loginok.go" method="post" id="Login">
				<h2 style="text-align: center"><b>로그인</b></h2>
				
				<div class="regidiv">
				<p>이메일</p>
				<input type="text" maxlength="50" id="email" name="email" placeholder="bit@soomgo.com">
				<p class="tdmail"></p>
				
				<p>비밀번호</p>
				<input type="password" maxlength="16" id="pwd" name="pwd" placeholder="비밀번호를 입력해주세요">
				<p class="tdpw"></p>
				
				
				<input type="button" value="로그인" class="button"> 
				
				<p><a href="<%= request.getContextPath() %>/Join.go">계정이 없으신가요?</a></p>
				</div>
				
				
				
				
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
		
// 이메일 체크
var emailcheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
var emailck = false;
$('#email').blur(function() {
	if (emailcheck.test($('#email').val())) {
		$('.tdmail').html("");
		emailck = true;
	} else {
		$('.tdmail').attr("style", "color:red; font-size:3px");
		$('.tdmail').html("이메일 주소를 입력 해주세요.");
		emailck = false;
	}

<<<<<<< HEAD
	<form action="login.go" method="post">
		<label for="email">EMAIL&nbsp;&nbsp;&nbsp;</label>
		<input type="text" placeholder="email을 입력하세요" name="email" id="email"><br>
		<label for="pwd">비밀번호</label>
		<input type="password" placeholder="비밀번호를 입력하세요" name="pwd" id="pwd"><br>	
		<input type="submit" value="로그인">
		<input type="submit" value="회원가입">
	</form>
=======
})		
>>>>>>> 871714e32a06625d73bf7430fc0d0b8058fbb3ad

//비밀번호 체크   
var passwoercheck = /^([A-Za-z])+([0-9])+([~!@#$%^&*()_+|<>?:{}])+$/;
var passck = false;
$('#pwd').blur(
		function() {
			if (passwoercheck.test($('#pwd').val())
					&& $("#pwd").val().length >= 8) {
				console.log("일치" + $('#userPass').val());
				$('.tdpw').html("");
				passck = true;
			} else if (!passwoercheck.test($('#userPass').val())) {
				$('.tdpw').attr("style", "color:red; font-size:3px");
				$('.tdpw').html("비밀번호를 입력해주세요.");
				passck = false;

			}
		})
		
		
		
// 버튼 선택 
$('.button').click(
		function() {
			if (passck == false || emailck == false) {
				alert("형식이 맞지 않습니다.");
				return;
			} else {
				$('#Login').submit();
			}
		})
		
</script>
</html>