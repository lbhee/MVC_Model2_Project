<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
	Date nowTime = new Date();
	SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일");
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
	/*
   if(!bbs.content.value){            
        alert("글 내용을 입력하세요");
        bbs.content.focus();
        return false;
    } 
*/
 
    document.bbs.submit();
 
}


</script>
</head>
<body>


    <div id="pageContainer">
        <div style="padding-top: 25px; text-align: center">
            <!-- form 시작 ---------->
            <form name="bbs" action="${pageContext.request.contextPath}/QnAwriteOK.go" method="POST">
                <table width="95%" border="2" align="center">
                    <tr>
                        <td width="20%" align="center">제목</td>
                        <td width="80%" align="left"><input type="text"    name="title" size="40"></td>
                        
                    </tr>
                       <tr>
                        <td width="20%" align="center">작성날짜</td>
                        <td width="80%" align="left"><input type="text"  value="<%= nowTime %>" name="writedate" size="40"></td>
                        
                    </tr>
                    <tr>
                        <td width="20%" align="center">글내용</td>
                        <td width="80%" align="left"><textarea rows="10" cols="60" name="content" class="ckeditor"></textarea></td>
                    </tr>
   
                    <tr>
                        <td colspan="2" align="center">
                            <input type="button" value="글쓰기" onclick="check();" /> 
                            <input type="reset"  value="다시쓰기" />
                        </td>
                    </tr>
                </table>
              </form>
            
        </div>
    </div>
</body>
</html>