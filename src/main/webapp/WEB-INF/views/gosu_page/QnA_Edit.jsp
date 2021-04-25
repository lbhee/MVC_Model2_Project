<%@page import="kr.or.team3.dao.GosuDao"%>
<%@page import="kr.or.team3.dto.QnABoard.QnA_Board"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
    String id = (String)session.getAttribute("ID");

	GosuDao gosudao = new GosuDao();
%>




<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>

	<script src="https://cdn.ckeditor.com/4.16.0/standard/ckeditor.js"></script>

	<script type="text/javascript">
	function check(){
    if(!bbs.title.value){
        alert("제목을 입력하세요");
        bbs.subject.focus();
        return false;
    }
 
    document.bbs.submit();
 
}


</script>
</head>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="title" value="<%=gosudao.QnaEditSelect(id).getTitle() %>"/>
<c:set var="writedate" value="<%=gosudao.QnaEditSelect(id).getWirtedate() %>"/>
<c:set var="content" value="<%=gosudao.QnaEditSelect(id).getContent() %>"/>
<c:set var="g_email" value="<%=gosudao.QnaEditSelect(id).getG_email() %>"/>
<body>


    <div id="pageContainer">
        <div style="padding-top: 25px; text-align: center">
            <!-- form 시작 ---------->
            <form name="bbs" action="${pageContext.request.contextPath}/QnAEditOK.go" method="POST">
            	<input type="hidden" value="${g_email} " name="g_email">
                <table width="95%" border="2" align="center">
                    <tr>
                        <td width="20%" align="center">제목</td>
                        <td width="80%" align="left"><input type="text" value="${title}" name="title" size="40"></td>
                        
                    </tr>
                       <tr>
                        <td width="20%" align="center">작성날짜</td>
                        <td width="80%" align="left"><input type="text"  value="${writedate}" name="writedate" size="40"></td>
                        
                    </tr>
                    <tr>
                        <td width="20%" align="center">글내용</td>
                        <td width="80%" align="left"><textarea rows="10" cols="60" name="content" class="ckeditor">${content}</textarea></td>
                    </tr>
   
                    <tr>
                        <td colspan="2" align="center">
                            <input type="button" value="수정하기" onclick="check();" /> 
                        </td>
                    </tr>
                </table>
              </form>
            
        </div>
    </div>
</body>
</html>