package kr.or.team3.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.MemberDao;
import kr.or.team3.dto.member.Member;

public class Member_JoinOk_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		String email=request.getParameter("email");
		String adr =request.getParameter("adr");
		String name=request.getParameter("name");
		String pwd=request.getParameter("pwd");
		
		Member member = new Member(email,adr,name,pwd);
		
		ActionForward actionForward = new ActionForward();
		
		try {
			MemberDao memberDao = new MemberDao();
			int result = memberDao.registerOk(member);
			
			String msg="";
			String url="";
			
			
			
			if(result > 0 ) {
				msg="insert success";
				url="/main.jsp";
				
			}else {
				msg="insert fail";
				url="/WEB-INF/views/register/Join.jsp";
			}
			
			request.setAttribute("member_msg", msg);
			request.setAttribute("member_url", url);
			
			actionForward.setPath("/member/redirect.jsp");
			
		} catch (Exception e) {
			
		}
		
		return actionForward;
	}
	
	
}
