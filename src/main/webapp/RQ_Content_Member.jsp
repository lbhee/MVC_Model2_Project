<%@page import="kr.or.team3.dto.member.RQ_Content_Member"%>
<%@page import="kr.or.team3.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
<%

int num = Integer.parseInt(request.getParameter("num"));

MemberDao memberdao = new MemberDao();
RQ_Content_Member content = memberdao.getRQContent_Member(num);
%>
<c:set var = "content" value = "<%=content%>"/>
<body> 
	${content.getNum()}<br>
	${content.getTitle()}<br>
	${content.getContent()}<br>
	${content.getWritedate()}<br>
	${content.getHopedate()}<br>
	${content.getMemberName()}<br>
	${content.getGosuName()}<br>
	${content.getG_email()}<br>
	${content.getAdr()}<br>
	${content.getSubject()}<br>
	
</body>
