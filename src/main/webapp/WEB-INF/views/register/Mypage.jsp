<%@page import="kr.or.team3.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../include/head.jsp"></jsp:include>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

	String id = (String)session.getAttribute("ID");

	if(id == null){
		response.sendRedirect(request.getContextPath()+"/main.go");
		return;
	}
	
	MemberDao memberDao = new MemberDao();
	
%>
<c:set var="member" value="<%=memberDao.getContent(id)%>" ></c:set>

<body>
	<div class="container loginForm">
		<div class="loginForm">
			<form action="<%= request.getContextPath() %>/EditOk.go?change=userinfo" method="post" id="join">
				
				<h2 style="text-align: center"><b>회원정보수정</b></h2>
				<div class="regidiv">
		
				<p>이름</p>
				<input type="text" maxlength="10" id="name" name="name" value=${ member.name }>
				<p class="tdname"></p>
				
				<p>이메일</p>
				<input type="text" maxlength="20" id="email" name="email" value=${ member.email } readonly>
				<p style="color:red; font-size:3px; text-align: center">이메일은 수정이 불가능합니다.</p>
				
				<p>주소</p>
				<input type="text" maxlength="50" id="adr" name="adr" value="${ member.adr }" > <br>
				<p></p>
				
				<p>비밀번호</p>
				<input type="password" maxlength="15" id="pwd" name="pwd" onkeyup="enterkey()" placeholder="정보를 수정할려면 비밀번호를 입력해주세요.">
				<p></p>
				
				<input type="button" value="정보수정" class="button"> 
				<p class="last_p"><a href="#" class="pwdChange">비밀번호를 변경하고 싶으신가요?</a></p>
					
				</div>	
				
				
			</form>
			
				<div class="userInfo_pwdChange" style="display:none">
					
					<form action="<%= request.getContextPath() %>/EditOk.go?change=pwd" method="post" id="pwdChangeForm">
					<h2 style="text-align: center"><b>비밀번호변경</b></h2>
					<div class="regidiv">
 					<p>기존 비밀번호</p>
 					<input type="text" maxlength="20" id="email" name="email" value=${ member.email } hidden>
 					<input type="password" maxlength="15" id="origin_pwd" name="origin_pwd" >
 					<p></p>
 					<p>변경 할 비밀번호</p>
 					<input type="password" maxlength="15" id="new_pwd" name="new_pwd" >
 					<p class="tdpw"></p>
 					<p>비밀번호확인</p>
 					<input type="password" maxlength="15" id="new_pwdck" name="new_pwdck">
 					<p class="tdpwch"></p>
 					<p></p>
 					<input type="button" value="비밀번호변경" class="pwd_Change_btn"> 
 					<p class="last_p"><a href="#" class="pwdChange_btn">창을 닫을려면 클릭하세요.</a></p>
 					</div>
 					</form>	
 				</div>
 			
 			
		</div>
	</div>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">

var pwd = "${ member.pwd }";

//이름 체크
var namecheck = /^[가-힣]{1,5}$/;
$('#name').change(function() {
	if (namecheck.test($('#name').val())) {
		$(".tdname").html("");
	} else {
		$('.tdname').attr("style", "color:red; font-size:3px");
		$('.tdname').html("이름을 입력해주세요");
	}

})

//비밀번호 체크 
var passck = false;
$('#pwd').change(function() {
			if ($('#pwd').val() != pwd){
				passck = false;
			} else {
				passck = true;
			}
		})
		

// 버튼 선택 
$('.button').click(function() {
	if(passck == false){
		swal("비밀번호를 확인해주세요.","","error");
		return false;
	}else{
			var cek = swal("수정하시겠습니까?","","info");
			if(cek == true){
				$('#join').submit();
			}else{
				return;
			}
		}
	})

				

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
$('#adr').click(function() {
	sample4_execDaumPostcode();
})


// 버튼 선택 
$('.button').click(function() {
	btnclick();
	})

function btnclick(){
	if(passck == false){
		swal("비밀번호를 확인해주세요","","error")
		return false;
	}else{
		confirm('','수정하시겠습니까?','userinfo');
	}
		
		
}	

//엔터키
function enterkey() { 
	if (window.event.keyCode == 13) { 
		
		btnclick();
	
	} 	
}		


// 스윗얼럿 선택창
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
			if(resvNum == "userinfo"){
				$('#join').submit();
			}else if(resvNum == "pwd"){
				$('#pwdChangeForm').submit();
			}
			
		}else{
			return false;
		}

	});
}


// ===============================================================================
// 비밀번호 변경폼 온오프
$('.pwdChange').click(function() {
	var toggle = $('.userInfo_pwdChange').attr('style');
	
	if(toggle == "display:none"){
		$('.userInfo_pwdChange').attr('style','');
	}else {
		$('.userInfo_pwdChange').attr('style','display:none');
	}
})

$('.pwdChange_btn').click(function() {
	var toggle = $('.userInfo_pwdChange').attr('style');
	
	if(toggle == "display:none"){
		$('.userInfo_pwdChange').attr('style','');
	}else {
		$('.userInfo_pwdChange').attr('style','display:none');
	}
})

//비밀번호 체크   
var origin_pwd = false;
$('#origin_pwd').change(function() {
			if ($('#origin_pwd').val() != pwd){
				origin_pwd = false;
			} else {
				origin_pwd = true;
			}
		})


var passwoercheck = /^([A-Za-z])+([0-9])+([~!@#$%^&*()_+|<>?:{}])+$/;
var new_pwd = false;
var new_pwdck = false;
$('#new_pwd').change(
		function() {
			if (passwoercheck.test($('#new_pwd').val())
					&& $("#new_pwd").val().length >= 8) {
				console.log("일치" + $('#new_pwd').val());
				$('.tdpw').html("");
				new_pwd = true;
			} else if (!passwoercheck.test($('#new_pwd').val())) {
				$('.tdpw').attr("style", "color:red; font-size:3px");
				$('.tdpw').html("8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.");
				new_pwd = false;

			}
		})

$('#new_pwdck').change(
		function() {
			if ($('#new_pwd').val() == $('#new_pwdck').val()) {
				console.log("일치");
				$('.tdpwch').html("");
				new_pwdck = true;
			} else if ($('#new_pwd').val() != $('#new_pwdck').val()
					&& $('#new_pwdck').val().length >= 3) {
				$('.tdpwch').attr("style", "color:red; font-size:3px");
				$('.tdpwch').html("비밀번호가 일치하지 않습니다.");
				new_pwdck = false;
			}
		})
		

// 비번변경버튼
$('.pwd_Change_btn').click(function() {
		if (origin_pwd == false || new_pwd == false || new_pwdck == false) {
			swal("입력값을 확인해주세요.", "" ,"error");
			return;
		} else {
			confirm('','변경하시겠습니까?','pwd');
		}
	
})


// ===============================================================================		
</script>		

<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>