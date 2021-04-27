<%@page import="javax.tools.DocumentationTool.Location"%>
<%@page import="kr.or.team3.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>

<%

	String name = (String)request.getAttribute("name");
	String check = (String)request.getAttribute("check");
	
	MemberDao memberDao = new MemberDao();
	
	
	if(name == "null" || check == "false"){
		response.sendRedirect(request.getContextPath()+"/main.go");
		return;
	}
%>


<c:set var = "name" value = "<%=name%>"/>
<c:set var = "check" value = "<%=check%>"/>

<div class="container">
	<div class="RQ_ok_Header">
		<img src="images/RQ_OK_img.svg">
		<h1><b style="color:#00c7ae">${ name }</b> 고수님에게 <br> 요청서를 보냈습니다!</h1>
	</div>
	<div class="RQ_ok_nac">
		<p><b>같은 서비스를 제공하는 다른 고수님들에게도 요청을 보내 비교하여 진행할 수 있습니다.</b></p>
		<p>입력하신 번호를 통해 고수님에게 전화가 올 수 있습니다.</p>
		<p><b><a href="RQList_Member.go">보낸 요청서 확인하기</a></b></p>
	</div>
		
</div>
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>