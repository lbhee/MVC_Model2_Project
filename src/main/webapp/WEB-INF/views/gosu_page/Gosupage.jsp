<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- header -->

<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>

    <script type="text/javascript">
    var email = '<%=request.getParameter("email")%>';
    var loginemail = '<%=(String)session.getAttribute("ID")%>';
    
    var loginemail = '<%= session.getAttribute("ID")%>';
    
    if(email == 'null'){
    	email = '<%= session.getAttribute("ID")%>';
    			
    }
	
   </script>
	<c:set var="gosu_email" value='<%=request.getParameter("email")%>'></c:set>
	<body>
	
	<div class="app-body">
	
	 <div class="container">
            <div class="rowprofile">
            	<div class="profile">

            		<img class="photo" src="images/default_img.svg">

            	</div>
				<div id="gosu">
				</div>
				<div class="RQ_Box navbar-light">
					<p class="RQ_Header">고수에게 원하는 서비스의 견적을 받아보세요.</p>
					<input type="button" value="견적 요청하기" class="button"> 
				</div>
				
			</div>
	</div>

    <div>
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-4 col-xs-12">
                    <div class="widget clearfix">
                        <div class="widget-title">
                            <h2>한줄 소개</h2>
                        </div>
                        <ul class="footer-links">
                            <li id="introduction"></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

		<div class="container">
            <div class="row">
				<div class="col-lg-4 col-md-4 col-xs-12">
                    <div class="widget clearfix">
                        <div class="widget-title">
                            <h2>기본정보</h2>
                        </div>
                        <ul class="footer-links">
                            <li id="area"><i class="fas fa-map-marker-alt"></i></li>
                            <li id="calltime"><i class="far fa-clock"></i></li>
                            <li id="payment"><i class="far fa-credit-card"></i></li>
                            <li id="hire_num"><i class="fas fa-trophy"></i></li>
                        </ul>
                    </div>
                </div>
				
                <div class="col-lg-4 col-md-4 col-xs-12">
                    <div class="widget clearfix">
                        <div class="widget-title">
                            <h2>추가정보</h2>
                        </div>

                        <ul class="footer-links">
                            <li id="career"><i class="fas fa-briefcase"></i></li>
                            <li id="license"><i class="fas fa-award"></i></li>
                        </ul>
                    </div>
                </div>                
            </div>
        </div>
      </div>      
         
      <div class="container">  
      	<div class="tabs">   
	      <div class="tab_select">
				<ul class="script_ul">
					<li><a class="tab_selected" href="#" id="noticeboard">공지사항</a></li>
					<li><a class="" href="#" id="qnaboard">자주하는 질문</a></li>
					<li><a class="" id = "reviewboard" href="#">리뷰</a></li>
				</ul>
		  </div>  
  		</div>  	
      </div>    

          
      <div class="container">       
	       <div id="boarddata" style="padding-bottom: 30px; padding-top: 30px">
	       </div> 
	       <div id="button_div" style="display: none">
		       <button id="write_btn" onclick="location.href='#'">글쓰기</button >
		       <button id="edit_btn" onclick="location.href='#'">수정하기</button >
	       </div>
       </div>  
        
	</div>
	
    </body>
    <script type="text/javascript">
	var d_code = null;
    
    $(function(){
    	gosupage();
    	if(email == loginemail){
    		$('#button_div').attr('style','');
    	}
    });

    function gosupage() {
    	$.ajax({
    		url:"Gosupage_Ajax",
    		dataType:"JSON",
    		data: {email: email},
    		success:function(responsedata){
    			
    			// 뿌려지는 화면 없으면 메인화면으로 전환
    			if(responsedata.length == 0){
    				location.href = "main.go";
					return;
				}
    			
    			$.each(responsedata,function(index,obj){
    				
    				$('#gosu').append("<h2>" + obj.name + "</h2><p>" + obj.s_name + "(" + obj.d_name + ") 고수");

    				$('#introduction').append(obj.pr);
    				$('#area').append(obj.area.substring(0,7));
    				$('#calltime').append("연락 가능 시간 : "+obj.calltime);
    				$('#payment').append(obj.payment+" 가능");
    				$('#hire_num').append(obj.hire_num +"회 고용됨");
    				$('#career').append("경력 " + obj.career);
    				$('#license').append(obj.license + " 보유");

    				$('.photo').attr('src','upload/' + obj.photo);

    			});
    			 console.log($('#gosu >p').text());
    			 d_code = $('#gosu >p').text();
    		}
    	});
    }
	
    
    
    $('.script_ul > li >a').click(function(){
		$('.script_ul > li >a').attr('class','');
		$(this).attr('class','tab_selected');
		
	})
	
	//review 계시판 글쓰기(보여주기)
	
	$('#reviewboard').click(function(){
		$.ajax({
    		url : "MemberWriteShow_Ajax",
    		dataType:"HTML",
			success: function(responsedata){
			console.log(responsedata);
			$('#boarddata').html(responsedata);
			
			 $('.rating > i').click(function(){
					let clickstar = $(this).index() + 1;
					console.log(this);
					console.log(clickstar);
					$('.make_star > i').css({color : '#000'});
					console.log($('.make_star > i'));
					$('.make_star > i:nth-child(-n +' + clickstar +')').css({color: 'white'});
					console.log($('.make_star > i:nth-child(-n +' + clickstar +')'));
				});
			}    		
    	});
	});

   //자주하는 질문
    $('#qnaboard').click(function(){ 
    	$.ajax({
    		url:"QnA_Ajax",
    		data: {email: email},
    		success: function(responsedata){
    			$('#write_btn').attr("onclick", "location.href='QnAwrite.go'");
    			$('#edit_btn').attr("onclick", "location.href='QnAEdit.go'");
    			
    			$('#boarddata').empty();
    			$('#boarddata').append(responsedata);
    			
    			
    			
    		}
    	});
    });
	
    
    $('#qnaeditbtn').click(function(){
		if(email == loginemail){
			location.href = 'QnA_Edit.jsp';
		}else {
			swal('', '권한이 없습니다.', "error");
		}
	});
    
    $('#noticebtn').click(function(){
		if(email == loginemail){
			location.href = 'Notice_Write.jsp';
		}else {
			swal('', '권한이 없습니다.', "error");
		}
	});
    
    
    $('.button').click(function(){
    	if(email == loginemail){
    		swal('', '자기 자신에게 요청서를 보낼 수 없습니다.', "error");
		}else if(loginemail == 'null'){
			swal('', '로그인 후 이용가능합니다.', "error");
		}else {
			location.href = 'WriteRQ.go?email=${gosu_email}&code=' + d_code;
		}
    });

	</script>
    </html>
    
    