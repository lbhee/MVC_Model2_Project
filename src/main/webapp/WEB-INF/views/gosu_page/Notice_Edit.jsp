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
	
	function back(){
		   window.history.back();
		 
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

<div id="pageContainer">
        <div class="container">
        	<div class="Notice_Write_Box">
            <!-- form 시작 ---------->
            <form name="bbs" action="${pageContext.request.contextPath}/NoticeEditOK.go" method="POST" enctype="multipart/form-data">
           		<input type="hidden" value="${num} " name="num">
				<input type="hidden" value="${email}" name="email">
            	<div class="Notice_Write_Header"><h2><b>공지사항 수정</b></h2></div>
            	<div class="Notice_Write_Header_2"><input type="button" value="수정" onclick="check();" />
            										<input type="button" value="취소" onclick="back();" />
            	</div>
                <table class="Notice_Write_main">
                    <tr>
                        <td ><input type="text" class="Notice_Write_input" name="title" size="40" value="${title}" placeholder="제목을 입력해주세요."></td>
                    	
                    </tr>
                    
                    <tr>
                        <td ><input type="file" name="filename" class="fileUplod" value="${filename}"></td>
                        
                    </tr>
                    <tr>
                        
                        <td class="Notice_Write_Content"><textarea rows="10" cols="60" name="content" class="ckeditor" placeholder="내용을 입력해주세요.">${content}</textarea></td>
                    </tr>   
                </table>
              </form>
            </div>	
        </div>
    </div>
</body>
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>