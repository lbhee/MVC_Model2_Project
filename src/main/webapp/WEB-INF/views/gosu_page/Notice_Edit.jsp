<%@page import="kr.or.team3.dto.notice.Notice"%>
<%@page import="kr.or.team3.dao.GosuDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
	GosuDao gosudao = new GosuDao();
	int noticenum = Integer.parseInt(request.getParameter("num"));
%>

<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
<script src="https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>
	
	<SCRIPT type="text/javascript">
	function check(){
    if(!bbs.title.value){
        alert("제목을 입력하세요");
        bbs.subject.focus();
        return false;
    }

    document.bbs.submit();
 
}
</SCRIPT>



<body>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="<%=gosudao.NoticeContent(noticenum).getTitle() %>"/>
<c:set var="writedate" value="<%=gosudao.NoticeContent(noticenum).getWritedate() %>"/>
<c:set var="filename" value="<%=gosudao.NoticeContent(noticenum).getFilename() %>"/>
<c:set var="content" value="<%=gosudao.NoticeContent(noticenum).getContent() %>"/>
<c:set var="num" value="<%=gosudao.NoticeContent(noticenum).getNum() %>"/>
<c:set var="email" value="<%=gosudao.NoticeContent(noticenum).getEmail() %>"/>

<form name="bbs" action="${pageContext.request.contextPath}/NoticeEditOK.go" method="POST" enctype="multipart/form-data">
	<input type="hidden" value="${num} " name="num">
	<input type="hidden" value="${email}" name="email">
	<table width="80%" border="1">
		<tr>
			<td width="20%" align="center"><b>제목</b></td>
			<td colspan="3"><input type="text" value="${title}" name="title" size="40"></td>
		</tr>
		<tr>
			<td width="20%" align="center"><b>작성일</b></td>
			<td><input type="text" value="${writedate}" name="writedate" size="40"></td>
			<td width="20%" align="center"><b>첨부파일</b></td>
			<td><input type="file" name="filename" value="${filename}"></td>
		</tr>
		<tr height="100">
			<td width="20%" align="center"><b>글내용</b></td>
			<td colspan="3"><textarea rows="10" cols="60" name="content" class="ckeditor">${content}</textarea></td>
		</tr>
		<tr>
			<td colspan="4" align="center">
				<a href="#">목록가기</a>
				<a href="#" onclick="check()">수정</a>
			</td>
		</tr>
	</table>
</form>
</body>
</html>