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
   
  	MemberDao memberDao = new MemberDao();

%>

<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
<%@ taglib prefix= "c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="gosu_profile" value="<%=memberDao.getContent(g_email) %>"/>

<body>
 <div id="pageContainer">
        <div class="container">
        	<div class="Notice_Write_Box">
            <!-- form 시작 ---------->
            <form name="reply" action="ReviewWriteOk.go" method="POST">
            	<input type="hidden" name="m_email" value="<%=m_email%>"/>
            	 <input type="hidden" name="g_email" value="<%=g_email%>">
            	
	            	
            	
            	<div class="Notice_Write_Header"><h2><b>${gosu_profile.name}</b>님에게 리뷰를 남겨주세요.</h2></div>
            
            	<div class="star-input">
            	<div class="score"></div>
            	<input type="hidden" name="grade" class="gradesubmit" value="">
				<span class="input"> <input type="radio" name="star-input"
					value="1" id="p1"> <label for="p1">1</label> 
					<input type="radio" name="star-input" value="2"
					id="p2"> <label for="p2">2</label> 
					<input type="radio" name="star-input" value="3" id="p3"> <label
					for="p3">3</label>  <input type="radio"
					name="star-input" value="4" id="p4"> <label for="p4">4</label>
					 <input type="radio" name="star-input" value="5"
					id="p5"> <label for="p5">5</label> 
				</span>
				</div>
				
            		
                <table class="Notice_Write_main">
					<tr>                    
            		
            		</tr>
                    <tr>
                        <td ><textarea name="content" rows="10" cols="98" placeholder="다음 이용자를 위해 서비스 만족도를 적어주세요!"></textarea></td>
                        
                    </tr>
                     <tr>
                        <td><input type="button" value="리뷰 등록" onclick="check();" class="Review_btn"/></td>
                    </tr>
                </table>
              </form>
            </div>	
        </div>
    </div>


</body>
<script type="text/javascript">

	function check(){
		var star = $('.star-input>.input').find(":checked").val();
		
	    if(!reply.content.value){
	        swal("","내용을 입력하세요","error");
	        return false;
	    }
	    
	    
	    
	    document.reply.submit(); 
	    
	}
	
	var starRating = function(){
		var $star = $(".star-input"),
		    $result = $star.find("output>b");
			
		  	$(document)
		  	.on("focusin", ".star-input>.input", 
				function(){
		   		 $(this).addClass("focus");
		 	})
				 
		   	.on("focusout", ".star-input>.input", function(){
		    	var $this = $(this);
		    	setTimeout(function(){
		      		if($this.find(":focus").length === 0){
		       			$this.removeClass("focus");
		     	 	}
		   		}, 100);
		 	 })
		  
		    .on("change", ".star-input :radio", function(){
		    	$result.text($(this).next().text());
		    	
		    	var grade_code = $('.star-input>.input').find(":checked").val()
		    	var gtade_text = null;
		    	if(grade_code <= 1){
		    		gtade_text = "매우 별로예요";
		    	}else if(grade_code <= 2){
		    		gtade_text = "별로예요";
		    	}else if(grade_code <= 3){
		    		gtade_text = "보통이에요";
		    	}else if(grade_code <= 4){
		    		gtade_text = "좋아요";
		    	}else if(grade_code <= 5){
		    		gtade_text = "매우 좋아요";
		    	}
		    	
		    	
		    	$('.score').text(gtade_text + " - "  + grade_code );
		    	$('.gradesubmit').val($('.star-input>.input').find(":checked").val());
		  	})
		    .on("mouseover", ".star-input label", function(){
		    	$result.text($(this).text());
		    })
		    .on("mouseleave", ".star-input>.input", function(){
		    	var $checked = $star.find(":checked");
		    		if($checked.length === 0){
		     	 		$result.text("0");
		   		 	} else {
		     	 		$result.text($checked.next().text());
		    		}
		  	});
		};

		starRating();

</script>
</html>