<%@page import="kr.or.team3.dto.member.RQ_Content_Member"%>
<%@page import="kr.or.team3.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
<%

int num = Integer.parseInt(request.getParameter("num"));
int cpage = Integer.parseInt(request.getParameter("cp"));
int pagesize = Integer.parseInt(request.getParameter("ps"));

MemberDao memberdao = new MemberDao();
RQ_Content_Member content = memberdao.getRQContent_Member(num);
%>
<c:set var = "num" value = "<%=num%>"/>
<c:set var = "cpage" value = "<%=cpage%>"/>
<c:set var = "pagesize" value = "<%=pagesize%>"/>
<c:set var = "content" value = "<%=content%>"/>
<body> 
	요청 번호: ${content.getNum()}<br>
	제목: ${content.getTitle()}<br>
	내용: ${content.getContent()}<br>
	작성시간: ${content.getWritedate()}<br>
	희망 시간: ${content.getHopedate()}<br>
	고객 이름: ${content.getMemberName()}<br>
	고수 이름: ${content.getGosuName()}<br>
	고수 이메일: ${content.getG_email()}<br>
	고수 활동지역: ${content.getAdr()}<br>
	분야: ${content.getSubject()}<br>
	
	<a href = "Member_RQ_DeleteOk.go?num=${num}&cp=${cpage}&ps=${pagesize}">지우기</a>
	<a href = "Member_RQ_EditOk.go?num=${num}&cp=${cpage}&ps=${pagesize}">수정하기</a>
	
</body>
