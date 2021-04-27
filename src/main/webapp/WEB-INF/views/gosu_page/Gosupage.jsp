<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!-- header -->
<%
	String g_email = request.getParameter("email");
	String m_email = (String)session.getAttribute("ID");

%>
<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>

<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>

    <script type="text/javascript">
    var email = '<%=request.getParameter("email")%>';
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
      	<div class="tabs2">   
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
	       <div class="RQ_B" style="padding-bottom: 30px; padding-top: 5px">	       
					<div class="pricingTable">
						<div class="pricingContent">
							<div class="pricingContent">
					            <ul id="boarddata">
					            
					            </ul>
					        </div>
					    </div>
					</div>
	       	<div class="RQ_Detail_modal"></div>
	       		<button style="display: none" id="write_btn" onclick="location.href='NoticeWrite.go'">글쓰기</button >
		        <button style="display: none"  id="edit_btn" onclick="location.href='#'">수정하기</button >
	       </div> 
		       
       </div>  
        
        <div class=""></div>
	</div>
    </body>
    <script type="text/javascript">
	var d_code = null;
    
    $(function(){
    	gosupage();

    	notice();
    	
    	$('.RQ_Detail_modal').hide();
        
    	
    	if(email == loginemail){
    		$('#write_btn').attr('style','');
    	}
    	
    });
    
    //고수정보
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
	

    //탭 css
    $('.script_ul > li >a').click(function(){
    	$('.script_ul > li').animate({
			scrollTop: 300
		}, 500);
    	
		$('.script_ul > li >a').attr('class','');
		$(this).attr('class','tab_selected');
		
	})
	



	
	// 모달 숨기기
	
	//공지사항
	function notice() {
    	$.ajax({
    		url:"Notice_Ajax",
    		dataType:"JSON",
    		data: {email: email},
    		success: function(responsedata){
    			

    			$.each(responsedata,function(index,obj){
    				$('#boarddata').append("<li id='no"+obj.num+"'>" + "<i class='Notice_Header'>공지</i>" + obj.title + "<i class='Notice_Date'>" + obj.writedate + "</i></li>");
    			
    			     $('#no'+obj.num).click(function(){
    			    	
    			    	 
    			    	 $.ajax({
    	    					url:"Notice_Detail", 
    	    					   data: {num:obj.num},
    	    					   dataType:"html",
    	    					   success:function(data){
    	    						   
    	    						   
    	    						   $('.RQ_Detail_modal').empty();
    	    						   $('.RQ_Detail_modal').append(data);
    	    						   $('.RQ_Detail_modal').show();
    	    						   
    	    							if(email != loginemail){
      	    						   		$('.edit_btn').attr('style','display: none');
      	    						     	$('.delete_btn').attr('style','display: none');
      	    						   	}
    	    						 
    	    						  
    	    						   
    	    						   $('.edit_btn').click(function(){
    	    							   location.href = "NoticeEdit.go?num="+obj.num;
    	    						   })  
   	       					 			
    	    						   $('.delete_btn').click(function(){
    	    							   location.href = "NoticeDel.go?num="+obj.num;
    	    						   })
    	    						   
    	    						   
    	    						   
    	    						   
    	    						   
    	    						   $('.closeMadal').click(function(){
    	    								$('.RQ_Detail_modal').hide();
    	    								$('.RQ_Detail_modal').empty();
    	    							})
    	    							
    	    							

    	    					
    	    					   },
    	    					   error:function(xhr){
    	    						   alert(xhr);
    	    					   }
    	    					
    	    				})
    			     });
    		    });
    		
    	    }
       });
    }
	
    
    
    //공지사항 탭
    $('#noticeboard').click(function(){ 
    	if(email == loginemail){
    		$('#write_btn').attr('style','');
    		$('#edit_btn').attr('style','display: none');
    	}
    	
		$('#write_btn').attr("onclick", "location.href='NoticeWrite.go'");
		$('#edit_btn').attr("onclick", "location.href='NoticeEdit.go'");
		
		$('#boarddata').empty();
		notice();
    });	
	
	
    //자주하는 질문 탭
	$('#qnaboard').click(function(){ 
    	$.ajax({
    		url:"QnA_Ajax",
    		dataType:"JSON",
    		data: {email: email},
    		success: function(responsedata){
    			
    			console.log(responsedata);
    			
    			if(email == loginemail){
    	    		$('#write_btn').attr('style','');
    	    		$('#edit_btn').attr('style','');
    	    	}
    			
    			$('#write_btn').attr("onclick", "location.href='QnAwrite.go'");
    			$('#edit_btn').attr("onclick", "location.href='QnAEdit.go'");
    			
    			$('#boarddata').empty();
    			
    			$.each(responsedata,function(index,obj){
    				$('#boarddata').append("<li class='Qna_box'><i class='Qna_Header'>Q. " + obj.title + "</i>" + obj.content + "</li>");
    	
    		    });
    		}
    	});
    });
    
    
    
    
	//review 계시판 글쓰기(보여주기)
	$('#reviewboard').click(function(){
		$.ajax({
    		url : "ReviewWriteShow_Ajax",
    		dataType:"HTML",
			success: function(responsedata){
			console.log(responsedata);
			$('#boarddata').empty();
			$('#boarddata').html(responsedata);
			
			 $('.rating > i').click(function(){
					let clickstar = $(this).index() + 1;
					
					$('.make_star > i').css({color : '#000'});
					
					$('.make_star > i:nth-child(-n +' + clickstar +')').css({color: '#F05522'});
					
				});
			}    		
    	});
	});

	//요청서보내기
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
 <jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>
    