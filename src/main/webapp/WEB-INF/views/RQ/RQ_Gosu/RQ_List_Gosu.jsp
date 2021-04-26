<%@page import="kr.or.team3.dao.GosuDao"%>
<%@page import="kr.or.team3.dto.member.RQ_Form"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.team3.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
<%

String G_email = (String) session.getAttribute("ID");
GosuDao dao = new GosuDao();
MemberDao memberDao = new MemberDao();

String cp = request.getParameter("cp");
String ps = request.getParameter("ps");

if (ps == null || ps.trim().equals("")) {
	ps = "20";
}

if (cp == null || cp.trim().equals("")) {
	cp = "1";
}

int cpage = Integer.parseInt(cp);
int pagesize = Integer.parseInt(ps);

List<RQ_Form> list = dao.getRQList_Gosu(cpage, pagesize, G_email);

int totalRQcount = dao.totalRQMemberCount(G_email);
%>
<c:set var="list" value="<%=list%>"/>
<c:set var="cpage" value="<%=cpage%>" />
<c:set var="pagesize" value="<%=pagesize%>" />
<c:set var="conextPath" value="<%=request.getContextPath()%>" />
<c:set var="totalcount" value="<%=totalRQcount%>"/>
<c:set var = "cpage" value = "<%=cpage%>"/>
<c:set var = "pagesize" value = "<%=pagesize%>"/>
<c:set var = "memberDao" value = "<%=memberDao%>"/>

<div id="overviews" class="section lb" style="background-color: white">
	<div class="container">
		<div class="RqListHead">
				<c:choose>
					<c:when test="${list.size() == 0 || list == null}">
						<h3>도착한 요청서가 없습니다</h3>
					</c:when>
					<c:otherwise>
						<h3>도착한 요청서가 <b>${totalcount}건 </b>있습니다</h3>
					</c:otherwise>
				</c:choose>
		</div>		
	</div>	
</div>

<div class="RQ_box">
		
		<h2><b>받은 요청서</b></h2>
		</div>
		<div class="row">
			<c:forEach var="rqlist" items="${list}" begin="0" end="${list.size()}" step="1">
				<div class="col-md-4 col-sm-6 col-xs-12">
					<div class='RqListBox'>
						<div class="RqListHeaderBox">
							${rqlist.writedate}
							<p>요청번호: ${rqlist.num}</p>
						</div>
						<div class="RQListMainBox">
							<c:set var="member" value="${ memberDao.getContent(rqlist.m_mail) }"></c:set>
							<p>${member.name}님에게서 온 요청서</p>
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

<script type="text/javascript">
	$('.RQ_Detail_modal').hide();
	$('.RQ').click(function(){
		console.log($(this).children("p"));
		
		var date = $(this).children("p")[0];
		
		console.log(date.attributes[0].nodeValue);
		$.ajax({
			url:"Gosu_RQ_Detail", 
			   data:date.attributes[0].nodeValue,
			   dataType:"html",
			   success:function(data){
				   console.log(data);
				   
				   
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