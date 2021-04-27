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
        swal("제목을 입력하세요","","error");
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
	function back(){
		   window.history.back();
		 
		}

</script>
</head>
<body>
    
     <div id="pageContainer">
        <div class="container">
        	<div class="Notice_Write_Box">
            <!-- form 시작 ---------->
            <form name="bbs" action="${pageContext.request.contextPath}/QnAwriteOK.go" method="POST">
            	<div class="Notice_Write_Header"><h2><b>자주하는 질문 답변 글쓰기</b></h2></div>
            	<div class="Notice_Write_Header_2"><input type="button" value="글쓰기" onclick="check();" />
            									<input type="button" value="취소" onclick="back();" />
            	</div>
                <table class="Notice_Write_main">
                    <tr>
                        <td ><input type="text" class="Notice_Write_input" name="title" size="40" placeholder="Q. 질문을 입력하세요."></td>
                    	
                    </tr>
                    
                    <tr>
                        <td ><input type="text"  value="<%= nowTime %>" name="writedate" size="40" hidden></td>
                        
                    </tr>
                    <tr>
                        
                        <td class="Notice_Write_Content"><textarea rows="10" cols="60" name="content" class="ckeditor" placeholder="내용을 입력해주세요."></textarea></td>
                    </tr>   
                </table>
              </form>
            </div>	
        </div>
    </div>
</body>
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>