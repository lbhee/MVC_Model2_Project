<%@page import="org.apache.commons.collections.bag.SynchronizedSortedBag"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:include page="head.jsp"></jsp:include>    
  
    
<%
  String msg = (String)request.getAttribute("member_msg");
  String url = (String)request.getAttribute("member_url");
 
	  
  if(msg != "null" && url != null){
%>
	<script>	
		swal('', '<%=msg%>', "success");
		setTimeout(function() {
			
		    location.href='<%=request.getContextPath()+url%>';
		}, 500);
		
	</script>
	
<%	  
  }else if(msg == "null" || url == null){
%>
	<script>	
		location.href='<%=request.getContextPath() +url%>';
	
	</script>
<%
}
%>