<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- header -->
<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>

    <script type="text/javascript">
    var email = '<%=request.getParameter("email")%>';
    var loginemail = '<%= session.getAttribute("ID")%>';
    
    if(email == 'null'){
    	email = '<%= session.getAttribute("ID")%>';
    			
    }
    

   </script>

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
					<li><a class="" href="#">리뷰</a></li>
				</ul>
		  </div>  
  		</div>  	
      </div>    

          
                        
                        
                        
                          
      <div class="container">       
	       <div style="padding-bottom: 30px; padding-top: 30px">	       
					<div class="pricingTable">
						<div class="pricingContent">
							<div class="pricingContent">
					            <ul id="boarddata">
					            
					            </ul>
					        </div>
					    </div>
					</div>
	       
	       </div> 
		       <button style="display: none" id="write_btn" onclick="location.href='NoticeWrite.go'">글쓰기</button >
		       <button style="display: none"  id="edit_btn" onclick="location.href='#'">수정하기</button >
       </div>  
        
	</div>
	
    </body>
    <script type="text/javascript">

    $(function(){
    	gosupage();
    	notice();
    	
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
    			console.log(responsedata);
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

    		}
    	});
    }
	
    //탭 css
    $('.script_ul > li >a').click(function(){
		$('.script_ul > li >a').attr('class','');
		$(this).attr('class','tab_selected');
		
	})
	
	//공지사항
	function notice() {
    	$.ajax({
    		url:"Notice_Ajax",
    		dataType:"JSON",
    		data: {email: email},
    		success: function(responsedata){
    			$.each(responsedata,function(index,obj){
    				$('#boarddata').append("<li id='no"+obj.num+"'>" + "<i class='fas fa-check'></i>        " + obj.title + "</li>");
    			
    			     $('#no'+obj.num).click(function(){
    			    	 location.href = "NoticeContent.go?num="+obj.num;
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
    		data: {email: email},
    		success: function(responsedata){
    			
    			if(email == loginemail){
    	    		$('#write_btn').attr('style','');
    	    		$('#edit_btn').attr('style','');
    	    	}
    			
    			$('#write_btn').attr("onclick", "location.href='QnAwrite.go'");
    			$('#edit_btn').attr("onclick", "location.href='QnAEdit.go'");
    			
    			$('#boarddata').empty();
    			$('#boarddata').append(responsedata);
    		}
    	});
    });

	//요청서보내기
    $('.button').click(function(){
    	if(email == loginemail){
    		alert("자기 자신에게 요청서를 보낼 수 없습니다.");
			
		}else {
			location.href = 'WriteRQ.go';
		}
    });

</script>
</html>
   
    