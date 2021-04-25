package kr.or.team3.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.GosuDao;

public class Gosu_RQ_DeleteOk_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		int cpage = Integer.parseInt(request.getParameter("cp"));
		int pagesize = Integer.parseInt(request.getParameter("ps"));
		
		ActionForward actionForward = new ActionForward();
		
		GosuDao gosudao = null;
		
		try {
			gosudao = new GosuDao();
			boolean result = gosudao.delete_RQ_Gosu(num);
			
			String msg = "";
			String url = "";
			
			if(result == true) {
				msg = "삭제 완료";
				url = "/RQList_Gosu.go?cp=" + cpage + "&ps=" + pagesize;
			}else {
				msg = "삭제 실패";
				url = "/RQList_Gosu.go?cp=" + cpage + "&ps=" + pagesize;
			}
			
			request.setAttribute("member_msg", msg);
			request.setAttribute("member_url", url);
			
			actionForward.setPath("/WEB-INF/views/include/redirect.jsp");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return actionForward;
	}

}
