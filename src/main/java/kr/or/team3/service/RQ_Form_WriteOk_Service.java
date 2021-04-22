package kr.or.team3.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.MemberDao;
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
			System.out.println("util date: " + rq_hopedate_util);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("util date: " + rq_hopedate_util);
		Date hopedate = new Date(rq_hopedate_util.getTime());
		System.out.println("try 밖 hope date: " + hopedate);
		String phone = request.getParameter("phone");
		String M_email = request.getParameter("m_email");
		//섹션의 아이디값을 넣기로 함
		//String M_email = (String)request.getSession().getAttribute("email");
		String G_email = request.getParameter("g_email");
		int G_code = Integer.parseInt(request.getParameter("g_code"));
		System.out.println("요청중");
		RQ_Form rq_form = new RQ_Form(0, title, content, null, hopedate, 0, phone, M_email, G_email, G_code);
		
		ActionForward actionForward = new ActionForward();
		System.out.println(rq_form.toString());
		try {
			MemberDao memberDao = new MemberDao();
			int result = memberDao.sendRQ_Form(rq_form);
			
			String msg = "";
			String url = "";
			
			if(result > 0) {
				msg = "요청 완료";
				url = "";
			}else {
				msg = "요청실패";
				url = "";
			}
			request.setAttribute("member_msg", msg);
			request.setAttribute("member_url", url);
			
			actionForward.setPath("/WEB-INF/views/include/redirect.jsp");
			
		}catch (Exception e2) {
			e2.getMessage();
		}
		
		
		return actionForward;
	}

	private Date Date(long time) {
		// TODO Auto-generated method stub
		return null;
	}

}
