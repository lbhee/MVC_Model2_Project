<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="../include/head.jsp"></jsp:include>

<%

	String id = (String)session.getAttribute("ID");

	if(id != null){
		response.sendRedirect(request.getContextPath()+"/main.go");
		return;
	}

%>

<body>
	<div class="container loginForm">
		<div class="loginForm">
			<form action="" method="post" id="Login" class="loginBox">
				<h2 style="text-align: center"><b>로그인</b></h2>
				
				<div class="regidiv">
				<p>이메일</p>
				<input type="text" maxlength="50" id="email" name="email" placeholder="bit@soomgo.com" onkeyup="enterkey()">
				<p class="tdmail"></p>
				
				<p>비밀번호</p>
				<input type="password" maxlength="16" id="pwd" name="pwd" placeholder="비밀번호를 입력해주세요" onkeyup="enterkey()">
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

})		

//비밀번호 체크   
var passck = false;
$('#pwd').change(
		function() {
			if ($("#pwd").val().length >= 3) {
				$('.tdpw').html("");
				passck = true;
			} else {
				$('.tdpw').attr("style", "color:red; font-size:3px");
				$('.tdpw').html("비밀번호를 입력해주세요.");
				passck = false;

			}
		})
		

function btnclick(){
	if (passck == false || emailck == false) {
		swal("빈칸을 모두 채워주세요." , "" ,"error");
		return;
	} else {
		$.ajax({
			   url:"Member_LoginOk_Service", 
			   data:{
				   email:$('#email').val(),
				   pwd:$('#pwd').val()	
			   },
			   dataType:"html",
			   success:function(data){
				   console.log(data);
				   if(data == "true"){
			   		   location.href = "main.jsp";
				   }else{
					   $('#pwd').blur();
					   swal("아이디 또는 비밀번호를 확인해주세요","","error")
					   
					   
				   }
			   },
			   error:function(xhr){
				   swal(xhr.status);
			   }
			})
	}
}		
		
		
// 버튼 선택 
$('.button').click(function() {
	btnclick();		
})


function enterkey() { 
	if (window.event.keyCode == 13) { 
		
		btnclick();
	
	} 	
}			
</script>
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>