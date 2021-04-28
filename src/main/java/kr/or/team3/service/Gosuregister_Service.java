package kr.or.team3.service;


import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.GosuDao;
import kr.or.team3.dao.MemberDao;
import kr.or.team3.dto.gosu.Gosu_Info_Add;
import kr.or.team3.dto.gosu.Gosu_Info_Basic;
import kr.or.team3.dto.gosu.Gosu_Register;
import kr.or.team3.dto.member.Member;

public class Gosuregister_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response)  {
		
		String email = request.getParameter("email");
		String pr = null;
		int d_code = Integer.parseInt(request.getParameter("code"));
		
		
		Gosu_Register gosu_register = null;
		Gosu_Info_Basic gosu_info_basic = null;
		Gosu_Info_Add gosu_info_add = null;
			
		ActionForward actionForward = new ActionForward();
		GosuDao gosudao = null;
		int row = 0;
		
		try {
			
			 MemberDao memberDao = new MemberDao();
			 Member member = memberDao.getContent(email);
			 
			 // 고수 가입정보
			 gosu_register = new Gosu_Register(email, d_code, pr, d_code, email, null);
			 // 고수 기본정보
			 gosu_info_basic = new Gosu_Info_Basic(member.getEmail(), 10000, "카드", member.getAdr(), 0, "24시~24시", null);
			 // 고수 추가정보
		  	 gosu_info_add = new Gosu_Info_Add(member.getEmail(), 10000, null, null, null);
		  	 
		  	 
			 gosudao = new GosuDao();
			 // 고수 가입정보
			 row = gosudao.joinGosuOk(gosu_register);
			 // 고수 기본정보
			 gosudao.insertGosuInfo_B(gosu_info_basic);
			 // 고수 추가정보
			 gosudao.insertGosuInfo_A(gosu_info_add);
			 			 
			 String msg="";
			 String url="";
			 
			 if(row > 0) {
				 msg = "null";
				 url = "/Gosuregister_Info.go";
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
