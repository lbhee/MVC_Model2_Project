<%@page import="kr.or.team3.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>

<%
   Date nowTime = new Date();
   SimpleDateFormat sf = new SimpleDateFormat("yyyy년 MM월 dd일");
   String g_email = request.getParameter("g_email");
   String m_email = (String)session.getAttribute("ID");
%>

<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="g_email" value="<%=g_email %>"/>

<body>

<form name="reply" action="ReviewWriteOk.go" method="POST">
      <table width="80%" border="1">
         <tr>
            <th colspan="2">리뷰 쓰기</th>
         </tr>
         <tr>
            <td align="left">작성자 :
                <input type="text" name="m_email" value="<%=(String)session.getAttribute("ID")%>"/>
                작성일 :<input type="text" name ="writedate"><br>
                내&nbsp;&nbsp;용 : 
                <textarea name="content" rows="2" cols="50"></textarea>
                 <input type="hidden" value="${g_email}" name="g_email">
            </td>
            <td align="left">                           
               <input type="submit" value="등록">
            </td>
         </tr>
      </table>
</form>


</body>
</html>