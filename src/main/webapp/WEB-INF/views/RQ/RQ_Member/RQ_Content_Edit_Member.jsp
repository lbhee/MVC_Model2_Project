<%@page import="kr.or.team3.dao.GosuDao"%>
<%@page import="kr.or.team3.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%

	String id = (String)session.getAttribute("ID");
	int num = Integer.parseInt(request.getParameter("num"));
	int cpage = Integer.parseInt(request.getParameter("cp"));
	int pagesize = Integer.parseInt(request.getParameter("ps"));
	
	if(id == null){
		response.sendRedirect(request.getContextPath()+"/main.jsp");
		return;
	}
	
	
	
	MemberDao memberDao = new MemberDao();
	GosuDao gosudao = new GosuDao();
%>
<c:set var = "num" value = "<%=num%>"/>
<c:set var = "cpage" value = "<%=cpage%>"/>
<c:set var = "pagesize" value = "<%=pagesize%>"/>
<c:set var="rq2" value="<%=memberDao.getRQContent_Member(num)%>" />
<c:set var = "rq" value = "<%=gosudao.getRQContent_Member(num)%>"/>



<body>
	<div class="container loginForm">
		<div class="loginForm">
			<form action="Member_RQ_EditOk.go?num=${num}&cp=${cpage}&ps=${pagesize}" method="post" id="join">
				
				<h2 style="text-align: center"><b>요청서 수정</b></h2>
				<div class="regidiv">
				<p style="text-align: center"><b>${rq.getMemberName()}</b> 고객님이 <b>${rq.getGosuName()}</b> 고수님에게 보내는 요청서</p>
				<br>
				<p>분야: ${rq2.getSubject()}</p>
				<p>작성 시간: ${rq.getWritedate()}</p>
				<p>제목</p>
				<input type="text" maxlength="20" id="title" name="title" value="${rq.getTitle()}">
				<p>내용</p>
				<input type="text" maxlength="50" id="content" name="content" value="${rq.getContent()}" >
				<p>희망 날짜</p>
				<input type="date" maxlength="50" id="hopedate" name="hopedate" value="${rq.getHopedate()}" >
				<p>핸드폰 번호</p>
				<input type="text" maxlength="50" id="phone" name="phone" value="${rq.getPhone()}" >
				<p></p>
				<input type="button" value="정보수정" class="button"> 
				</div>	
			</form>
			
				
 			
 			
		</div>
	</div>
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
// 버튼 선택 
$('.button').click(function() {
	$('#join').submit();
	})
</script>		

</html>
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>