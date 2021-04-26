package kr.or.team3.service;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.MemberDao;
import kr.or.team3.dto.member.RQ_Edit_Member;

public class Member_RQ_EditOk implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward actionForward = new ActionForward();
		
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		
		int cpage = Integer.parseInt(request.getParameter("cp"));
		int pagesize = Integer.parseInt(request.getParameter("ps"));
		
		int num = Integer.parseInt(request.getParameter("num"));
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String rq_hopedate = request.getParameter("hopedate");
		java.util.Date rq_hopedate_util = null;
		
		try {
			rq_hopedate_util = format.parse(rq_hopedate);
		} catch (Exception e) {
			e.getMessage();
		}
		
		Date hopedate = new Date(rq_hopedate_util.getTime());
		String phone = request.getParameter("phone");
		
		RQ_Edit_Member memberdata = new RQ_Edit_Member(num, title, content, hopedate, phone);
		try {
			MemberDao memberdao = new MemberDao();
			
			int result = memberdao.RQ_Form_EditOk(memberdata);
			String msg = "";
			String url = "";
			
			if(result > 0) {
				msg = "수정완료";
				url = "/Rq_Content_Memeber.go?num=" + num + "&cp=" + cpage + "&ps=" + pagesize;
			}else {
				msg = "수정실패";
				url = "/Rq_Content_Memeber.go?num=" + num + "&cp=" + cpage + "&ps=" + pagesize;
			}
			
			request.setAttribute("member_msg", msg);
			request.setAttribute("member_url", url);
			
			actionForward.setPath("/WEB-INF/views/include/redirect.jsp");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		return actionForward;
	}

}
