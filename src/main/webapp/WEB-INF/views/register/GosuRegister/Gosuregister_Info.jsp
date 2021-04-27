<%@page import="kr.or.team3.dao.GosuDao"%>
<%@page import="kr.or.team3.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="../../include/head.jsp"></jsp:include>
<%
String id = (String) session.getAttribute("ID");

if (id == null) {
	response.sendRedirect(request.getContextPath() + "/main.jsp");
	return;
}

MemberDao memberDao = new MemberDao();
GosuDao gosuDao = new GosuDao();
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="member" value="<%=memberDao.getContent(id)%>"></c:set>
<c:set var="gosu_info_basic"
	value="<%=gosuDao.getGosuInfoBasic_Content(id)%>"></c:set>
<c:set var="gosu_info_add"
	value="<%=gosuDao.getGosuInfoAdd_Content(id)%>"></c:set>
<c:set var="gosu_ServiceCode"
	value="<%=gosuDao.getGosuDetail_Join_Service_Content(id)%>"></c:set>


<c:set var="code" value='<%=request.getParameter("code")%>'></c:set>
<body>

	<div class="container">

		<div class="loginForm">
			<h2 style="text-align: center">
				<b>고수 프로필 관리</b>
			</h2>
			<div class="gosu_register_info_box">
				<form action="<%= request.getContextPath() %>/Gosuregister_Info_Edit.go" method="post" id="gosujoin" enctype="multipart/form-data">


					<div class="gosu_register">
						<div class="gosu_info_image_box">
						
						<c:choose>
							<c:when test="${ gosu_info_basic.photo == 'null'}">
								<img alt="" class="img_fileBox" src="images/default_img.svg">
							</c:when>
							
							<c:when test="${ gosu_info_basic.photo != null}">
								<img alt="" class="img_fileBox" src="upload/${ gosu_info_basic.photo }">
							</c:when>
						
						</c:choose>
						<input type="file" name="photo" class="img_file" hidden>
						<input tyep="text" name="photo_Defalut" value="${ gosu_info_basic.photo }" hidden>
						</div>
					</div>

					<div class="gosu_register">
						<p>
							<b>이름</b>
						</p>
						<input type="text" name="name" value="${ member.name }" readonly><br>
						<p style="color: red; font-size: 3px; text-align: center">이름은
							마이페이지를 통해서 수정해주세요.</p>
					</div>

					<div class="gosu_register">
						<p>
							<b>선택한 서비스분야</b>
						</p>
						<input type="text" name="d_code" id="d_code"
							value="${ gosu_ServiceCode.s_NAME }_${ gosu_ServiceCode.d_NAME }"
							readonly><br>
					</div>

					<div class="gosu_register">
						<p>
							<b>한줄 자기소개</b>
						</p>
						<input type="text" name="pr" id="pr" maxlength="30"
							value="${ gosu_ServiceCode.PR }" placeholder="자기소개를 입력해주세요."><br>
					</div>

					<div class="gosu_register">
						<p>
							<b>활동지역</b>
						</p>
						<input type="text" name="area" id="area" value="${ member.adr }"><br>
					</div>

					<div class="gosu_register">
						<p>
							<b>연락가능시간</b>
						</p>
						<select name="calltime_1" id="calltime_1" class="timeBox">
							<option value="${ gosu_info_basic.calltime_1 }" selected hidden >${ gosu_info_basic.calltime_1 }</option>
						 	<c:forEach var="i"  begin="9" end="24">
   							     <option value="${i}시">${i>9?i:'0'}${i>9?'':i}시</option>
   							</c:forEach>
						</select>
						~
						<select name="calltime_2" id="calltime_2" class="timeBox">
							<option value="${ gosu_info_basic.calltime_2 }" selected hidden >${ gosu_info_basic.calltime_2 }</option>
						 	<c:forEach var="i"  begin="9" end="24">
   							     <option value="${i}시">${i>9?i:'0'}${i>9?'':i}시</option>
						    </c:forEach>
						</select>
					</div>

					<div class="gosu_register">
						<p>
							<b>결제수단</b>
						</p>
						<select name="payment" id="payment">
						 	<option value="${ gosu_info_basic.payment }" selected hidden >${ gosu_info_basic.payment }</option>
							<option value="카드">카드</option>
   							 <option value="현금">현금</option>
						</select>
					</div>

					<div class="gosu_register">
						<p>
							<b>경력</b>
						</p>
						<input type="text" name="career" id="career" maxlength="10"
							value="${ gosu_info_add.career }" placeholder="경력을 적어주세요."><br>
					</div>

					<div class="gosu_register">
						<p>
							<b>자격증</b>
						</p>
						<input type="text" name="license" id="license" maxlength="50"
							value="${ gosu_info_add.license }" placeholder="자격증을 적어주세요."><br>
					</div>



					<input type="button" value="고수프로필수정하기" class="gosu_button"
						id="gosu_next_button">
				</form>
			</div>
		</div>
	</div>
</body>

<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
	function img_Return() {

	}

	$('.img_file').change(function(event) {
		var reader = new FileReader();

		reader.onload = function(event) {
			$('.img_fileBox').attr("src", event.target.result);

		}

		reader.readAsDataURL(event.target.files[0]);
	})

	// 가입하기 버튼
	$('#gosu_next_button').click(function() {
		confirm('','변경하시겠습니까?');
	});

	//우편번호로 주소찾기
	function sample4_execDaumPostcode() {
		new daum.Postcode({
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
				document.getElementById("area").value = addr;

			}
		}).open();
	}

	$('#area').click(function() {
		sample4_execDaumPostcode();
	})

	$('.img_fileBox').click(function() {
		$('.img_file').click();
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
		closeOnCancel : true}, 
		function(isConfirm) {
		if (isConfirm) {
			$('#gosujoin').submit();
		}else{
			return false;
		}

	});
}

</script>
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>