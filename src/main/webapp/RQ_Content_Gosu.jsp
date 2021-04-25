<%@page import="kr.or.team3.dto.member.RQ_Form"%>
<%@page import="kr.or.team3.dto.gosu.Gosu_RQ_Content"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.team3.dao.GosuDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
<%

String G_email = (String)session.getAttribute("ID");
GosuDao gosudao = new GosuDao();




%>
<body>
	
</body>