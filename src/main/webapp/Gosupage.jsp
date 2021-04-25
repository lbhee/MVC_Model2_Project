<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- header -->
<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>

	<body>
	
	<div class="app-body">
	
	 <div class="container">
            <div class="rowprofile">
            	<div class="profile">
            		<img src="images/default_img.svg" alt="" width="100px" height="100px">
            	</div>
				<div id="gosu">
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
					<li><a class="tab_selected" href="#">공지사항</a></li>
					<li><a class="" href="#" id="qnabord">자주하는 질문</a></li>
					<li><a class="" href="#">리뷰</a></li>
				</ul>
		  </div>  
  		</div>  	
      </div>    
          
          
      <div class="container">       
	       <div id="boarddata" style="padding-bottom: 30px; padding-top: 30px">
	       </div> 
	       <button type="button" id="noticebtn">공지사항글쓰기</button>
	       <button type="button" id="qnabtn">질문답변글쓰기</button>
	       <button type="button" id="qnaeditbtn">질문답변수정하기</button>  
	        
       </div>  
        
	</div>
	
    </body>
    <script type="text/javascript">
	var loginemail = '<%=(String)session.getAttribute("ID")%>';
    var email = '<%=request.getParameter("email")%>';
    
    console.log("ajax");
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
    				
    			});

    		}
    	});
    }
    
    $(function(){
    	gosupage();
    });
    
    $('.script_ul > li >a').click(function(){
		$('.script_ul > li >a').attr('class','');
		$(this).attr('class','tab_selected');
		
	})
 
	
	
	$('#qnabtn').click(function(){
		if(email == loginemail){
			location.href = 'QnA_Write.jsp';
		}else {
			alert("권한이 없습니다.");
		}
	});
    
    $('#qnabord').click(function(){
    	$.ajax({
    		url:"QnA_Ajax",
    		data: {email: email},
    		success: function(responsedata){
    			$('#boarddata').empty();
    			$('#boarddata').append(responsedata);
    		}
    	});
    });
    
    $('#qnaeditbtn').click(function(){
		if(email == loginemail){
			location.href = 'QnA_Edit.jsp';
		}else {
			alert("권한이 없습니다.");
		}
	});
    
    $('#noticebtn').click(function(){
		if(email == loginemail){
			location.href = 'Notice_Write.jsp';
		}else {
			alert("권한이 없습니다.");
		}
	});
    
	</script>
    </html>
    
    