package kr.or.team3.service;


import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.GosuDao;
import kr.or.team3.dto.gosu.Gosu_Register;

public class Gosuregister_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)  {
		
		String email = request.getParameter("email");
		String pr = request.getParameter("pr");
		int d_code = Integer.parseInt(request.getParameter("d_code"));
		
		Gosu_Register g_register = new Gosu_Register(email, pr, d_code);
		
		ActionForward actionForward = new ActionForward();
		GosuDao gosudao = null;
		int row = 0;
		try {
			gosudao = new GosuDao();
			 row = gosudao.joinGosuOk(g_register);
			 
			 String msg="";
			 String url="";
			 
			 if(row > 0) {
				 msg = "고수 가입 완료";
				 url = "/main.jsp";
			 }else {
				 msg = "이미 고수로 가입된 회원입니다.";
				 url = "/main.jsp";
			 }
			 
			 request.setAttribute("member_msg", msg);
			 request.setAttribute("member_url", url);
			 
			 actionForward.setPath("/WEB-INF/views/include/redirect.jsp");
			 
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return actionForward;
	}

}
