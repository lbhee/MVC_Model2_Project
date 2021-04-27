package kr.or.team3.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.MemberDao;
import kr.or.team3.dto.member.Member;
import kr.or.team3.dto.member.RQ_Form;

public class RQ_Form_WriteOk_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionForward forward = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String rq_hopedate = request.getParameter("hopedate");
		
		java.util.Date rq_hopedate_util =null;
		
		try {
			rq_hopedate_util = format.parse(rq_hopedate);
			
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Date hopedate = new Date(rq_hopedate_util.getTime());
		
		String phone = request.getParameter("phone");
		//String M_email = request.getParameter("m_email");
		
		String M_email = (String)request.getSession().getAttribute("ID");
		String G_email = request.getParameter("g_email");
		int G_code = Integer.parseInt(request.getParameter("g_code"));
		
		RQ_Form rq_form = new RQ_Form(0, title, content, null, hopedate, 0, phone, M_email, G_email, G_code);
		
		ActionForward actionForward = new ActionForward();
		
		try {
			MemberDao memberDao = new MemberDao();
			int result = memberDao.sendRQ_Form(rq_form);
			Member member = memberDao.getContent(G_email);
			
			String name = "";
			String check = "";
			
			if(result > 0) {
				name = member.getName();
				check = "true";
			}else {
				name = "null";
				check = "false";
			}
			request.setAttribute("name", name);
			request.setAttribute("check", check);
			
			actionForward.setPath("/WEB-INF/views/RQ/RQ_Member_writeOk.jsp");
			
		}catch (Exception e2) {
			e2.getMessage();
		}
		
		
		return actionForward;
	}


}
