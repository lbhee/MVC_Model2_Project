<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="kr.or.team3.dto.member.RQ_Form"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.team3.dao.MemberDao"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
<%
String M_email = (String) session.getAttribute("ID");
MemberDao dao = new MemberDao();
String cp = request.getParameter("cp");
String ps = request.getParameter("ps");



if (ps == null || ps.trim().equals("")) {
	ps = "10";
}

if (cp == null || cp.trim().equals("")) {
	cp = "1";
}

int cpage = Integer.parseInt(cp);
int pagesize = Integer.parseInt(ps);

List<RQ_Form> list = dao.getRQ_Form_Member(cpage, pagesize, M_email);
List<RQ_Form> Donelist = dao.getDone_RQ_Form_Member(cpage, pagesize, M_email);

int totalRQcount = dao.totalRQMemberCount(M_email);

%>
<c:set var="list" value="<%=list%>"/>
<c:set var="Donelist" value="<%=Donelist%>"/>
<c:set var="cpage" value="<%=cpage%>" />
<c:set var="pagesize" value="<%=pagesize%>" />
<c:set var="conextPath" value="<%=request.getContextPath()%>" />
<c:set var="totalcount" value="<%=totalRQcount%>"/>
<c:set var="dao" value="<%=dao%>" />

<div id="overviews" class="section lb" style="background-color: white">
	<div class="container">
		<div class="RqListHead">
				<c:choose>
					<c:when test="${list.size() == 0 || list == null}">
						<h3>고수에게 보낸 요청서가 없습니다</h3>
					</c:when>
					<c:otherwise>
						<h3>고수에게 보낸 요청서가 <b>${totalcount}건 </b>있습니다</h3>
					</c:otherwise>
				</c:choose>
		</div>		
	</div>	
</div>	
		<!-- end title -->
		<div class="container">
		<div class="RQ_box">
		<h2><b>보낸 요청서</b></h2>
		</div>
		
		<div class="row">
			<c:forEach var="rqlist" items="${list}" begin="0" end="${list.size()}" step="1">
				<div class="col-md-4 col-sm-6 col-xs-12">
					<div class='RqListBox'>
						<div class="RqListHeaderBox">
							<p class="Rq_boardBox">진행중 ✉️</p>
							${rqlist.writedate}
							<p>요청번호: ${rqlist.num}</p>
						</div>
						<div class="RQListMainBox">
							<c:set var="member" value="${ dao.getContent(rqlist.g_email) }"></c:set>
							<p>${member.name}고수에게 보낸요청</p>
						</div>
						<form action="" class="RQ">
						<p class="num=${rqlist.num}&cp=${cpage}&ps=${pagesize}" hidden"></p>
						<input type="button" value="자세히 보기" class="button">
						</form>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="RQ_Detail_modal"></div>

		<!-- ================================================================================= -->
		
		
		<div class="RQ_box">
		<hr>
		<h2><b>처리가 완료된 요청서</b></h2>
		</div>
		<div class="row">
			<c:forEach var="Donelist" items="${Donelist}" begin="0" end="${Donelist.size()}" step="1">
				<div class="col-md-4 col-sm-6 col-xs-12">
					<div class='RqListDoneBox'>
						<div class="RqDoneListHeaderBox">
							<c:if test="${ Donelist.done == 1 || Donelist.done == 4}">
								<p>수락된 요청서</p>
							</c:if>
							<c:if test="${ Donelist.done == 2 }">
								<p>거절된 요청서</p>
							</c:if>
							${Donelist.writedate}
							<p>요청번호: ${Donelist.num}</p>
						</div>
						<div class="RQListMainBox">
							<c:set var="member" value="${ dao.getContent(Donelist.g_email) }"></c:set>
							<c:if test="${ Donelist.done == 1 }">
								<a href="ReviewWrite.go?g_email=${Donelist.g_email}">리뷰 쓰러가기</a>
							</c:if>
								
							<c:if test="${ Donelist.done == 4 }">
								<a>리뷰 작성 완료</a>	
							</c:if>
							
							<p>${member.name}고수에게 보낸요청</p>
						</div>
						<form action="" class="RQ">
						<p class="num=${Donelist.num}&cp=${cpage}&ps=${pagesize}&CheckList=Done" hidden"></p>
						<input type="button" value="자세히 보기" class="button">
						</form>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>	

<script type="text/javascript">
	$('.RQ_Detail_modal').hide();
	$('.RQ').click(function(){
		
		var date = $(this).children("p")[0];
		
		console.log(date.attributes[0].nodeValue);
		$.ajax({
			url:"Member_RQ_Detail", 
			   data:date.attributes[0].nodeValue,
			   dataType:"html",
			   success:function(data){
				   
				   
				   $('.RQ_Detail_modal').empty();
				   $('.RQ_Detail_modal').append(data);
				   $('.RQ_Detail_modal').show();
				   
				   
				   $('.closeMadal').click(function(){
						$('.RQ_Detail_modal').hide();
						$('.RQ_Detail_modal').empty();
					})
			   },
			   error:function(xhr){
				   alert(xhr);
			   }
			
		})
	})
	
	

</script>
<jsp:include page="/WEB-INF/views/include/footer.jsp"></jsp:include>