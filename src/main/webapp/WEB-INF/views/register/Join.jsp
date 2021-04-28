<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="../include/head.jsp"></jsp:include>
<script src="js/mainpage/main_event.js"></script>
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
			<form action="<%= request.getContextPath() %>/Joinok.go" method="post" id="join">
				<h2 style="text-align: center"><b>회원가입</b></h2>
				
				<div class="regidiv">
				<p>이름</p>
				<input type="text" maxlength="10" id="name" name="name" placeholder="이름(실명)을 입력해주세요" onkeyup="enterkey()">
				<p class="tdname"></p>
				
				<p>이메일</p>
				<input type="text" maxlength="20" id="email" name="email" placeholder="bit@soomgo.com" onkeyup="enterkey()">
				<p class="tdmail"></p>
				
				<p>비밀번호</p>
				<input type="password" maxlength="15" id="pwd" name="pwd" placeholder="영문+숫자 조합 8자리 이상 입력해주세요" onkeyup="enterkey()">
				<p class="tdpw"></p>
				
				<p>주소</p>
				<input type="text" maxlength="50" id="adr" name="adr" placeholder="주소(클릭하세요)" onkeyup="enterkey()"> <br>
				<p></p>
				
				
				<input type="button" value="회원가입" class="button"> 
				</div>	
				
				
			</form>
		</div>
	</div>
</body>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
//이름 체크
var namecheck = /^[가-힣]{1,9}$/;
var nameck = false;
$('#name').blur(function() {
	if (namecheck.test($('#name').val())) {
		$(".tdname").html("");
		nameck = true;
	} else {
		$('.tdname').attr("style", "color:red; font-size:3px");
		$('.tdname').html("이름을 입력해주세요");
		nameck = false;
	}

})

//비밀번호 체크   
var passwoercheck = /^([A-Za-z])+([0-9])+([~!@#$%^&*()_+|<>?:{}])+$/;
var passck = false;
$('#pwd').blur(
		function() {
			if (passwoercheck.test($('#pwd').val())
					&& $("#pwd").val().length >= 8) {
				$('.tdpw').html("");
				passck = true;
			} else if (!passwoercheck.test($('#userPass').val())) {
				$('.tdpw').attr("style", "color:red; font-size:3px");
				$('.tdpw').html("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
				passck = false;

			}
		})
		
// 이메일 체크
var emailcheck = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;
var emailck = false;
$('#email').blur(function() {
	if (emailcheck.test($('#email').val())) {
		$('.tdmail').html("");
		emailck = true;
	} else {
		$('.tdmail').attr("style", "color:red; font-size:3px");
		$('.tdmail').html("이메일 형식이 맞지 않습니다");
		emailck = false;
	}

})		

// 버튼 선택 
function btnclick(){
	if (nameck == false || passck == false || emailck == false) {
		swal("빈칸을 모두 채워주세요." , "", "error");
		return;
	} else {
		confirm('',"가입하겠습니까?");
	}
}


$('.button').click(function() {
	btnclick();
})

var confirm = function(msg, title, resvNum) {
	swal({
		title : title,
		text : msg,
		type : "warning",
		showCancelButton : true,
		confirmButtonClass : "btn-danger",
		confirmButtonText : "예",
		cancelButtonText : "아니오",
		closeOnConfirm : false,
		closeOnCancel : true
	}, function(isConfirm) {
		if (isConfirm) {
			$('#join').submit();
			
		}else{
			return false;
		}

	});
}

function enterkey() { 
	if (window.event.keyCode == 13) { 
		
		btnclick();
	
	} 	
}		
	//우편번호로 주소찾기
	function sample4_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
						
						
						var addr = ''; // 주소 변수
		
		                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                    addr = data.jibunAddress;
            	 	    }
						// 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var roadAddr = data.roadAddress; // 도로명 주소 변수
						var extraRoadAddr = ''; // 참고 항목 변수

						// 법정동명이 있을 경우 추가한다. (법정리는 제외)
						// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
						if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
							extraRoadAddr += data.bname;
						}
						// 건물명이 있고, 공동주택일 경우 추가한다.
						if (data.buildingName !== '' && data.apartment === 'Y') {
							extraRoadAddr += (extraRoadAddr !== '' ? ', '
									+ data.buildingName : data.buildingName);
						}
						// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
						if (extraRoadAddr !== '') {
							extraRoadAddr = ' (' + extraRoadAddr + ')';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById("adr").value = addr;
						
						
					}
				}).open();
	}		
	$('#adr').focus(function() {
		sample4_execDaumPostcode();
		$('.button').focus();
	})
	
</script>
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>