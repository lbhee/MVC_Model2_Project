package kr.or.team3.service;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.GosuDao;
import kr.or.team3.dto.notice.Notice;

public class Notice_Del_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		HttpSession session = request.getSession();
		String email = (String)session.getAttribute("ID");
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		ActionForward actionForward = new ActionForward();
		
		Notice notice = new Notice(num);
		
		String msg="";
	    String url="";
	    
	    try {
			GosuDao gosudao = new GosuDao();
			int result = gosudao.noticeDeleteOk(num);

			if(result > 0) {
				msg = "삭제 완료";
				url = "/GosuProfile.go?email=" +email;
			}else {
				msg = "삭제 실패";
				url = "/GosuProfile.go?email=" +email;
			}
			
			request.setAttribute("member_msg", msg);
			request.setAttribute("member_url", url);
			
			actionForward.setPath("/WEB-INF/views/include/redirect.jsp");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return actionForward;
	}

}
