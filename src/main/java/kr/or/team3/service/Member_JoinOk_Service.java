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
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String adr = request.getParameter("adr");
		
		
		
		Member member = new Member(email,name,pwd,adr);
		
		ActionForward actionForward = new ActionForward();
		
		try {
			MemberDao memberDao = new MemberDao();
			int result = memberDao.registerOk(member);
			
			String msg="";
			String url="";
			
			if(result > 0 ) {
				msg="가입이 완료되었습니다.";
				url="/Login.go";
				
			}else {
				msg="가입이 실패되었습니다.";
				url="/Join.go";
			}
			
			request.setAttribute("member_msg", msg);
			request.setAttribute("member_url", url);
			
			actionForward.setPath("/WEB-INF/views/include/redirect.jsp");
			
		} catch (Exception e) {
			
		}
		
		return actionForward;
	}
	
	
}
