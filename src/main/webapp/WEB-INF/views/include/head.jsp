<%@page import="kr.or.team3.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	MemberDao memberDao = new MemberDao();
	String memberid = (String)session.getAttribute("ID");
%>
<c:set var="id" value="${ sessionScope.ID }" />
<c:set var="member" value="<%= memberDao.getContent(memberid) %>" />
<c:set var="path" value="<%= request.getContextPath() %>" />
<meta charset="UTF-8">
    <title>숨고 : 고수를찾아서</title>  
 
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/boot.css">
    <!-- Site CSS -->
    <link rel="stylesheet" href="css/style.css">
    <!-- ALL VERSION CSS -->
    <link rel="stylesheet" href="css/versions.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/custom.css">  
	

</head>
<body class="host_version"> 

    <!-- LOADER -->
	<div id="preloader">
		<div class="loader-container">
			<div class="progress-br float shadow">
				<div class="progress__item"></div>
			</div>
		</div>
	</div>
	<!-- END LOADER -->	
	
	<!-- Start header -->
	<header class="top-navbar">
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">
				<a class="navbar-brand" href="main.jsp">

					<img src="images/logo.jpg" alt="" />

				</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbars-host" aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
					<span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
				</button>
				<div class="collapse navbar-collapse" id="navbars-host">
					<ul class="navbar-nav ml-auto">
						<c:choose>
				 			<c:when test="${id==null}">
				 				<li class="nav-item active"><a class="nav-link" href="${ path }/Join.go">회원가입</a></li>
								<li class="nav-item active"><a class="nav-link" href="${ path }/Login.go">로그인</a></li>
				 			</c:when>
				 			<c:otherwise>
				 				<li class="nav-item active"><a class="" href="WriteRQ.go">요청서쓰기</a>
				 				<li class="nav-item active"><a class="" href="RQList.go">요청서리스트</a>
				 				<li class="nav-item active"><a class="nav-link" href="#">${ member.name } 고객님</a>
				 				<div class="user_info" style="display:none">

				 					<h4 class="head_userName">안녕하세요, ${ member.name }</h4>
				 					<div class="mypage"><a href="${ path }/Mypage.go">마이페이지</a></div>
				 					<div class="gosu_register"><a href="${ path }/Gosuregister_1.go">고수로 가입하기</a></div>

				 					<div class="logout"><a href="#">로그아웃</a></div>
				 				</div>
				 				</li>
				 			</c:otherwise>
				 		</c:choose>
				 		
					</ul>
				</div>
			</div>
		</nav>
	</header>
	<!-- End header -->
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- ALL JS FILES -->
    <script src="js/all.js"></script>
    <!-- ALL PLUGINS -->
    <script src="js/custom.js"></script>
	<script src="js/timeline.min.js"></script>
	<script>
		timeline(document.querySelectorAll('.timeline'), {
			forceVerticalMode: 700,
			mode: 'horizontal',
			verticalStartPosition: 'left',
			visibleItems: 4
		}); 
		
		// 회원 정보
		$('.nav-link').click(function() {
			var toggle = $('.user_info').attr('style');
			
			if(toggle == "display:none"){
				$('.user_info').attr('style','');
			}else {
				$('.user_info').attr('style','display:none');
			}
		})
		
		//로그아웃
		$('.logout').click(function(){
			$.ajax({
				   url:"LogOut_Ajax", 
				   dataType:"html",
				   success:function(data){
					   if(data == "true"){
				   		   location.href = "main.jsp";
					   }
				   },
				   error:function(xhr){
					   alert(xhr.status);
				   }
				
			})
		})
		
		
	</script>
<link rel="stylesheet" href="css/Team3.css">