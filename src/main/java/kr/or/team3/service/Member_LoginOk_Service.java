package kr.or.team3.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
<<<<<<< HEAD

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dto.member.Member;

public class Member_LoginOk_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		
		//Member member = new Member(email,pwd, pwd, pwd);
		//rr
		ActionForward actionForward = new ActionForward();
		
		try {
=======
import javax.servlet.http.HttpSession;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.MemberDao;
import kr.or.team3.dto.member.Member;
public class Member_LoginOk_Service implements Action {
	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		session.setAttribute("ID", "abcd");
		
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		
		Member member = new Member(email,"0",pwd,"0");
		
		ActionForward actionForward = new ActionForward();
		
		try {
			MemberDao memberDao = new MemberDao();
			String result = memberDao.loginOk(member);
			
			String msg="";
			String url="";
			
			if(result != null) {
				msg = "로그인 성공";
				url = "/main.jsp";
				session.setAttribute("ID", email);
				session.setAttribute("Name", result);
				
			}else {
				msg = "로그인 실패";
				url = "/Login.go";
			}
			
			
			request.setAttribute("member_msg", msg);
			request.setAttribute("member_url", url);
			
			actionForward.setPath("/member/redirect.jsp");
>>>>>>> 871714e32a06625d73bf7430fc0d0b8058fbb3ad
			
		} catch (Exception e) {
			
		}
<<<<<<< HEAD
		return null;
	}
	
}
=======
		return actionForward;
	}
	
}
>>>>>>> 871714e32a06625d73bf7430fc0d0b8058fbb3ad
