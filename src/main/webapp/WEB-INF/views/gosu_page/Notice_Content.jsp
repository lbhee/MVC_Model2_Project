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

<script type="text/javascript">
	var loginemail = '<%= session.getAttribute("ID")%>';
	var email = '<%=gosudao.NoticeContent(noticenum).getEmail() %>';
</script>   

<body>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="<%=gosudao.NoticeContent(noticenum).getTitle() %>"/>
<c:set var="writedate" value="<%=gosudao.NoticeContent(noticenum).getWritedate() %>"/>
<c:set var="filename" value="<%=gosudao.NoticeContent(noticenum).getFilename() %>"/>
<c:set var="content" value="<%=gosudao.NoticeContent(noticenum).getContent() %>"/>
<c:set var="num" value="<%=gosudao.NoticeContent(noticenum).getNum() %>"/>


<table width="80%" border="1">
	<tr>
		<td width="20%" align="center"><b>제목</b></td>
		<td colspan="3">${title}</td>
	</tr>
	<tr>
		<td width="20%" align="center"><b>작성일</b></td>
		<td>${writedate}</td>
		<td width="20%" align="center"><b>첨부파일</b></td>
		<td><a href="NoticeFileDownload.go?filename=${filename}">${filename}</a></td>
	</tr>
	<tr height="100">
		<td width="20%" align="center"><b>글내용</b></td>
		<td colspan="3">${content}</td>
	</tr>
	<tr>
		<td colspan="4" align="center">
			<a href="#" onclick="goback()">이전으로  </a>
			<a href="#" onclick="noticeEdit()" class="edit_btn" style="display: none">수정  </a>
			<a href="#" onclick="noticeDel()" class="edit_btn" style="display: none">삭제</a>
		</td>
	</tr>

</table>

</body>
<script type="text/javascript">
$(function(){
	if(email == loginemail){
		$('.edit_btn').attr('style','');
	}
});

function goback() {
	window.history.back();
}


</script>
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>