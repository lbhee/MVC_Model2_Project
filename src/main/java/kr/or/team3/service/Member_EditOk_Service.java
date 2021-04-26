package kr.or.team3.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.MemberDao;
import kr.or.team3.dto.member.Member;

public class Member_EditOk_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		
		String email = request.getParameter("email");
		String name = request.getParameter("name");
		String adr = request.getParameter("adr");
		String pwd = request.getParameter("new_pwd");
		String change = request.getParameter("change");
		
		Member member = new Member(email,name,pwd,adr);
		
		ActionForward actionForward = new ActionForward();
		
		try {
			MemberDao memberDao = new MemberDao();
			int result = memberDao.memberEdit(member,change);
			
			String msg="";
			String url="";
			
			if(result > 0 ) {
				url="/main.jsp";
				msg="변경 되었습니다.";
				
			}else {
				msg="변경 실패";
				url="/Mypage.go";
			}
			
			request.setAttribute("member_msg", msg);
			request.setAttribute("member_url", url);
			
			actionForward.setPath("/WEB-INF/views/include/redirect.jsp");
			
		} catch (Exception e) {
			
		}
		
		return actionForward;
	}

}
