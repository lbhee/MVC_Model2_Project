<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="../../include/head.jsp"></jsp:include>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="code" value='<%=request.getParameter("code")%>'></c:set>
<c:set var="id" value='<%=session.getAttribute("ID")%>' ></c:set>
<body>
<c:choose>
	<c:when test="${fn:substring(code,0,1)==1}">
		<c:set var="code_1" value="프론트"></c:set>
		<c:set var="code_2" value="백엔드"></c:set>
	</c:when>
	<c:when test="${fn:substring(code,0,1)==2}">
		<c:set var="code_1" value="가요"></c:set>
		<c:set var="code_2" value="팝"></c:set>
	</c:when>
	<c:when test="${fn:substring(code,0,1)==3}">
		<c:set var="code_1" value="유산소"></c:set>
		<c:set var="code_2" value="웨이트"></c:set>
	</c:when>
	<c:when test="${fn:substring(code,0,1)==4}">
		<c:set var="code_1" value="토익"></c:set>
		<c:set var="code_2" value="회화"></c:set>
	</c:when>
	<c:when test="${fn:substring(code,0,1)==5}">
		<c:set var="code_1" value="스트릿댄스"></c:set>
		<c:set var="code_2" value="폴댄스"></c:set>
	</c:when>
	<c:when test="${fn:substring(code,0,1)==6}">
		<c:set var="code_1" value="한식"></c:set>
		<c:set var="code_2" value="양식"></c:set>
	</c:when>
</c:choose>
<div class="container">
			
		<div class="loginForm">
			<div class="gosu_register_box">
				<p></p>
				<h3>어떤 서비스를 제공할 수 있나요?</h3>
				<div class="gosu_register2_category">
					<form action="<%= request.getContextPath() %>/GosuregisterOk.go" method= "post" id="gosujoin" >
					<input type="text" maxlength="20" id="email" name="email" value="${id}" hidden>
					<div class="gosu_code2">
						<input type="radio" name="code" value="${code + 1}" id="code_1">
						<label class="code_select" for="code_1">${code_1}</label>
					</div>
					<div class="gosu_code2">
						<input type="radio" name="code" value="${code + 2}" id="code_2">
						<label class="code_select" for="code_2">${code_2}</label>
					</div>
					<input type="button" value="다음" class="gosu_button" id="gosu_next_button"> 
					</form>
				</div>
			</div>		
		</div>	
	</div>

</body>
<script type="text/javascript">
	
	// 다음 버튼
	$('#gosu_next_button').click(function(){
		var code = $('input[name=code]:checked').val();
		console.log(code);
		if($('#code_1').is(':checked') || $('#code_2').is(':checked')){	
			confirm('',"해당 고수로 등록하시겠습니까?\n등록후엔 변경이 불가능합니다.");
			
		}else{
			swal("제공 할 서비스를 선택해주세요.");
		}
		
		
	});
	
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
				swal('', '고수 가입을 환영합니다~', "success");
				$('#gosujoin').submit();
				
			}else{
				return false;
			}

		});
	}

	
</script>
</html>