<%@page import="kr.or.team3.dto.member.RQ_Form"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.team3.dao.MemberDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="/WEB-INF/views/include/head.jsp"></jsp:include>
<%
String M_email = (String) session.getAttribute("ID");
MemberDao dao = new MemberDao();
String cp = request.getParameter("cp");
String ps = request.getParameter("ps");



if (ps == null || ps.trim().equals("")) {
	ps = "6";
}

if (cp == null || cp.trim().equals("")) {
	cp = "1";
}

int cpage = Integer.parseInt(cp);
int pagesize = Integer.parseInt(ps);

List<RQ_Form> list = dao.getRQ_Form_Member(cpage, pagesize, M_email);
System.out.println(list.size());
System.out.println(list.isEmpty());

int totalRQcount = dao.totalRQMemberCount(M_email);
System.out.println(totalRQcount);
System.out.println(M_email);
%>
<c:set var="list" value="<%=list%>"/>
<c:set var="cpage" value="<%=cpage%>" />
<c:set var="pagesize" value="<%=pagesize%>" />
<c:set var="conextPath" value="<%=request.getContextPath()%>" />
<c:set var="totalcount" value="<%=totalRQcount%>"/>

<%-- <div class="RQContainer">
	<c:choose>
		<c:when test="${list.size() == 0 || list == null }">
			데이터가 없습니다
		</c:when>
		<c:otherwise>
			데이터가 있습니다
		</c:otherwise>
	</c:choose>
	
	<c:forEach var="rqtest" items="<%=list%>">
		${rqtest.title}
	</c:forEach>
</div> --%>

<div class="all-title-box">
	<div class="container text-center">
		<h1>
			요청서리스트<span class="m_1">보낸 요청서 리스트입니다</span>
		</h1>
	</div>
</div>

<div id="overviews" class="section lb" style="background-color: white">
	<div class="container">
		<div class="section-title row text-center">
			<div class="col-md-8 offset-md-2">
				<c:choose>
					<c:when test="${list.size() == 0 || list == null}">
						<h3>고수에게 보낸 요청서가 없습니다</h3>
					</c:when>
					<c:otherwise>
						<h3>고수에게 보낸 요청서가 <b>${totalcount}건 </b>있습니다</h3>
					</c:otherwise>
				</c:choose>
				
				<p class="lead">뭐임마</p>
			</div>
		</div>
		<!-- end title -->

		<hr class="invis">
		
		<div class="row">
			<c:forEach var="rqlist" items="${list}" begin="0" end="2" step="1">
				<div class="col-md-4 col-sm-6 col-xs-12">
					<div class="icon-wrapper wow fadeIn" data-wow-duration="1s"
						data-wow-delay="0.2s">
						<i class="flaticon-server global-radius effect-1 alignleft"></i>
						<h3>${rqlist.g_email}에게 요청</h3>
						<p>
							요청번호: ${rqlist.num}<br>
							요청날짜: ${rqlist.writedate}<br>
							<small class="readmore">
								<a href="Rq_Content_Memeber.go?num=${rqlist.num}">요청서 자세히 보기</a>
							</small>
						</p>
					</div>
				</div><!-- end col -->
			</c:forEach>
		</div><!-- end row -->
		<c:if test = "${fn:length(list) > 3}">
			<hr class="hr3">
		</c:if>
		<div class="row">
			<c:forEach var="rqlist" items="${list}" begin="3" end="6" step="1">
				<div class="col-md-4 col-sm-6 col-xs-12">
					<div class="icon-wrapper wow fadeIn" data-wow-duration="1s"
						data-wow-delay="0.2s">
						<i class="flaticon-server global-radius effect-1 alignleft"></i>
						<h3>${rqlist.g_email}에게 요청</h3>
						<p>
							요청번호: ${rqlist.num}<br>
							<small class="readmore">
								<a href="#">요청서 자세히 보기</a>
							</small>
						</p>
					</div>
				</div><!-- end col -->
			</c:forEach>
		</div><!-- end row -->
	</div>
	<!-- end container -->
</div>
<!-- end section -->