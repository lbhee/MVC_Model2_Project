<%@page import="kr.or.team3.dto.member.RQ_Form"%>
<%@page import="kr.or.team3.dto.gosu.Gosu_RQ_Content"%>
<%@page import="kr.or.team3.dao.GosuDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
<%

int num = Integer.parseInt(request.getParameter("num"));

int cpage = Integer.parseInt(request.getParameter("cp"));
int pagesize = Integer.parseInt(request.getParameter("ps"));



GosuDao gosudao = new GosuDao();
Gosu_RQ_Content content = gosudao.getRQContent_Member(num);
%>
<c:set var = "num" value = "<%=num%>"/>
<c:set var = "cpage" value = "<%=cpage%>"/>
<c:set var = "pagesize" value = "<%=pagesize%>"/>
<c:set var = "content" value = "<%=content%>"/>
${content}<br>
<body>
	요청 번호: ${content.getNum()}<br>
	제목: ${content.getTitle()}<br>
	내용: ${content.getConent()}<br>
	작성 시간: ${content.getWritedate()}<br>
	고객 이름: ${content.getMemberName()}<br>
	고수 이름: ${content.getGosuName()}<br>
	희망 날짜: ${content.getHopedate()}<br>
	전화 번호: ${content.getPhone()}<br>
	고객 이메일: ${content.getM_email()}<br>
	<br>
	<a href = "Gosu_RQ_DeleteOk.go?num=${num}&cp=${cpage}&ps=${pagesize}">지우기</a>
	<a href = "Gosu_RQ_CompleteOk.go?num=${num}&cp=${cpage}&ps=${pagesize}">완료</a>
</body>