<%@page import="kr.or.team3.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- header -->
<jsp:include page="../../include/head.jsp"></jsp:include>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

	String id = (String)session.getAttribute("ID");

	if(id == null){
		response.sendRedirect(request.getContextPath()+"/main.jsp");
		return;
	}
	
	MemberDao memberDao = new MemberDao();
	
%>
<c:set var="member" value="<%=memberDao.getContent(id)%>" ></c:set>
<c:set var="code" value='<%=request.getParameter("code")%>'></c:set>
<body>

<div class="container">
			
		<div class="loginForm">
			<div class="gosu_register_box">
					<form action="<%= request.getContextPath() %>/GosuregisterOk.go" method = "post" id="gosujoin">
					
					<div class="gosu_register">
					<p><b>이름</b></p>
					<input type="text" name="name" value="${ member.name }" readonly><br>
					</div>
					
					<div class="gosu_register">
					<p><b>이메일</b></p>
					<input type="text" name="email" value="${ member.email }" readonly><br>
					</div>
					
					<div class="gosu_register">
					<p><b>한줄 자기소개</b></p>
					<input type="text" name="pr" id="pr" maxlength="30" value="" placeholder="자기소개를 입력해주세요."><br>
					</div>
					
					<div class="gosu_register">
					<p><b>활동지역</b></p>
					<input type="text" name="adr" id="adr" value="${ member.adr }"><br>
					</div>
					
					<div class="gosu_register">	
					<p><b>선택한 서비스분야</b></p>
					<input type="text" name="d_code" id="d_code" value="${ code }" readonly><br>
					</div>	
					<div class="gosu_register3_buttonbox">
					<input type="button" value="이전" class="gosu_button" id="gosu_Previous_button"> 
					<input type="button" value="가입하기" class="gosu_button" id="gosu_next_button"> 
					</div>
				</form>
			</div>
		</div>	
	</div>		
</body>
<script type="text/javascript">
	
	// 이전 버튼
	$('#gosu_Previous_button').click(function(){
			location.href="Gosuregister_2.go?code=" + ${code};
	});

	// 가입하기 버튼
	$('#gosu_next_button').click(function(){
		$('#gosujoin').submit();
	});
	
</script>
</html>
