package kr.or.team3.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.GosuDao;
import kr.or.team3.dto.gosu.Gosu_Info_Add;
import kr.or.team3.dto.gosu.Gosu_Info_Basic;
import kr.or.team3.dto.gosu.Gosu_Register;

public class Gosu_register_Info_Edit_Sevice implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		Gosu_Register gosu_register = null;
		Gosu_Info_Basic gosu_info_basic = null;
		Gosu_Info_Add gosu_info_add = null;
		GosuDao gosudao = null;
		
		HttpSession session = request.getSession();
		
		ActionForward actionForward = new ActionForward();
		
		String email= (String)session.getAttribute("ID");
		
		String pr=request.getParameter("pr");
		String area=request.getParameter("area");
		String calltime=request.getParameter("calltime");
		String payment=request.getParameter("payment");
		String career=request.getParameter("career");
		String license=request.getParameter("license");
		String photo=request.getParameter("photo");
		
		try {
			
			 gosudao = new GosuDao();
			 
			 // 고수 가입정보
			 gosu_register = new Gosu_Register(email, pr, 0);
			 // 고수 기본정보
			 gosu_info_basic = new Gosu_Info_Basic(email, 10000, payment, area, 0, calltime, photo);
			 // 고수 추가정보
		  	 gosu_info_add = new Gosu_Info_Add(email, 10000, null, career, license);
			
			int row1 = gosudao.UpdateGosuInfo_B(gosu_info_basic);
			int row2 = gosudao.UpdateGosuInfo_A(gosu_info_add);
			int row3 = gosudao.UpdateRegister(gosu_register);
			
			String msg="";
			String url="";
			
			if(row1 > 0 || row2 > 0 || row3 > 0) {
				msg="변경 완료";
				url="/Gosuregister_Info.go";
				
			}else {
				msg="변경 실패";
				url="/Gosuregister_Info.go";
			}
			
			request.setAttribute("member_msg", msg);
			request.setAttribute("member_url", url);
			
			actionForward.setPath("/WEB-INF/views/include/redirect.jsp");
			
		} catch (Exception e) {
			
		}
		
		return actionForward;
	}

}
