<%@page import="kr.or.team3.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>

<%

	String M_email = (String)session.getAttribute("ID");
	String G_email = request.getParameter("email");
	
	MemberDao memberDao = new MemberDao();
%>

<c:set var = "code" value ="${ param.code }" />
<c:set var = "M_email" value = "<%=M_email%>"/>
<c:set var = "G_gosu" value = "<%=memberDao.getContent(G_email)%>"/>

<div class="RQ_WriteImgBox">
		
</div>
<div class="container loginForm">
		<div class="Gosu_contaniner">
			<h1>${code} <br>
				${G_gosu.name} 고수님에게 요청 ✉</h1>
		</div>	
		<div class="loginForm">
			<form action = "SendRQOk.go" method = "POST" class = "checkdomain form-inline">
				<div class="RQ_writeBox">
				<div class="regidiv">
				<p>제목</p>
					<input type = "text" class = "form-control" id="title" name = "title" placeholder="요청서 제목을 입력해주세요.">
				<p class="tdname"></p>
				
				<p>내용</p>
					<input type = "text" class = "form-control" id="content" name = "content" placeholder="요청서 내용을 입력해주세요.">
				<p></p>
				
				<p>요청날짜</p>
					<input type = "date" class = "form-control" id="hopedate" name = "hopedate" min="2021-4-27">
				<p></p>
				
				<p>전화번호</p>
					<input type = "text" class = "form-control" id="phone" name = "phone" placeholder="연락받을 번호를 입력해주세요.">
				<p></p>
				
				
				<div class = "regidiv" style = " display :none">
						<input type = "text" class = "form-control" id = "g_email" name = "g_email" value = "${G_gosu.email}">
				</div>
				<div class = "regidiv" style = " display :none">
					<input type = "text" class = "form-control" id = "g_code" name = "g_code" value = "10000">
				</div>
				<div class = "regidiv" style = " display :none">
						<input type = "text" class = "form-control" id = "m_email" name = "m_email" value = "${M_email}">
				</div>	
					
				<input type="button" value="요청서보내기️" class="button"> 
				</div>		
				</div>	
			</form>	
			
		</div>
	</div>
	<p></p>
	<div class="Rq_Wrtie_category">
	<h4>요청서를 보낸 후 어떻게 진행되는지 알려드릴게요.</h4>
	<ul>
		<li><img src="images/Rqwrite_1.svg"><p>요청서 작성</p><p>도움이 필요한 서비스의 요청서를<br> 상세히 작성하고 고수를 소개받으세요</p></li>
		<li><img src="images/Rqwrite_2.svg"><p>견적 비교</p><p>최대 48시간 이내에 고수들이 제공하는<br> 견적을 받아보고 꼼꼼히 비교해 보세요</p></li>
		<li><img src="images/Rqwrite_3.svg"><p>연락 및 거래<p><p>원하는 고수와 전화를 <br>통해 거래하세요</p></li>
		<li><img src="images/Rqwrite_4.svg"><p>리뷰 쓰기</p><p>거래가 잘 마무리 되었다면 서비스에<br> 대한 리뷰를 남겨주세요</p></li>
	</ul>	
	</div>	
	<script type="text/javascript">
	
		$('.button').click(function(){
			
			if ($('#title').val() == '' || $('#content').val() == '' || $('#hopedate').val() == '' || $('#phone').val() == '') {
				swal("빈칸을 모두 채워주세요." , "", "error");
				return;
			} else {
				confirm('',"요청 하시겠습니까?");
			}
			
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
					$('.checkdomain').submit();
					
				}else{
					return false;
				}

	});
}

	
	function date(){
		var date = new Date();
		return date.getFullYear() + "-" + (date.getMonth()+1) + "-" + date.getDate();
	};
	
	function date2(){
		var date = new Date();
		return date.getFullYear() + "-" + (date.getMonth()+2) + "-" + date.getDate();
	};
	
	$('#hopedate').attr("min",date()); 
	$('#hopedate').attr("max",date2()); 
	</script>
</html>	