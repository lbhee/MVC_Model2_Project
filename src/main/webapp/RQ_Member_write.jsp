<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <meta name="viewport" content="width=device-width, initial-scale=1">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%

	String M_email = (String)session.getAttribute("ID");

%>
<c:set var = "M_email" value = "<%=M_email%>"/>

<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>




<script type="text/javascript">
</script>
<div class = "container">
	<div class = "row text-center">
		<div class = "col-lg-12">
			<form action = "SendRQOk.go" method = "GET" class = "checkdomain form-inline">
				<div class = "checkdomain-wrapper">
					<div class = "form-group">
						<input type = "text" class = "form-control" id = "title" name = "title" placeholder="title">
					</div>
				</div>
				<br>
				<div class = "checkdomain-wrapper">
					<div class = "form-group">
						<input type = "text" class = "form-control" id = "content" name = "content" placeholder="content">
					</div>
				</div>
				<br>
				<div class = "checkdomain-wrapper">
					<div class = "form-group">
						<input type = "date" class = "form-control" id = "hopedate" name = "hopedate">
					</div>
				</div>
				<br>
				<div class = "checkdomain-wrapper">
					<div class = "form-group">
						<input type = "text" class = "form-control" id = "phone" name = "phone" placeholder="phone">
					</div>
				</div>
				<br>
				<div class = "checkdomain-wrapper" style = " display :none">
					<div class = "form-group">
						<input type = "text" class = "form-control" id = "g_email" name = "g_email" value = "sj@naver.com">
					</div>
				</div>
				<br>
				<div class = "checkdomain-wrapper" style = " display :none">
					<div class = "form-group">
						<input type = "text" class = "form-control" id = "g_code" name = "g_code" value = "10000">
					</div>
				</div>
				<div class = "checkdomain-wrapper" style = " display :none">
					<div class = "form-group">
						<input type = "text" class = "form-control" id = "m_email" name = "m_email" value = "${M_email}">
					</div>
				</div>
				<br>
				<input type = "submit" class = "hover-btn-new log" value = "전송">
			</form>
		</div>
	</div>
</div>