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
        swal("제목을 입력하세요","","error");
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
        <div class="container">
        	<div class="Notice_Write_Box">
            <!-- form 시작 ---------->
          	 <form name="bbs" action="${pageContext.request.contextPath}/QnAEditOK.go" method="POST">
            	<div class="Notice_Write_Header"><h2><b>질문 답변 수정하기</b></h2></div>
            	<div class="Notice_Write_Header_2"><input type="button" value="수정" onclick="check();" />
            									<input type="button" value="취소" onclick="back();" />
            	</div>
                <table class="Notice_Write_main">
                    <tr>
                        <td ><input type="text" class="Notice_Write_input" name="title" size="40" value="${title}" placeholder="Q. 질문을 입력하세요."></td>
                    	
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