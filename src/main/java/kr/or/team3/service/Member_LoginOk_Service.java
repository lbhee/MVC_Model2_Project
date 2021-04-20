package kr.or.team3.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dto.member.Member;

public class Member_LoginOk_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		String email=request.getParameter("email");
		String pwd=request.getParameter("pwd");
		
		//Member member = new Member(email,pwd, pwd, pwd);
		
		ActionForward actionForward = new ActionForward();
		
		try {
			
		} catch (Exception e) {
			
		}
		return null;
	}
	
}
