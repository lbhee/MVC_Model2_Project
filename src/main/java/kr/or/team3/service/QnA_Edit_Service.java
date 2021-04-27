package kr.or.team3.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.GosuDao;
import kr.or.team3.dto.QnABoard.QnA_Board;

public class QnA_Edit_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response){
		
		String g_email = request.getParameter("g_email");
		String title = request.getParameter("title");
		String writedate = request.getParameter("writedate");
		String content = request.getParameter("content");

		ActionForward actionForward = new ActionForward();
		
		QnA_Board qnaboard = new QnA_Board(title, content, writedate, g_email);

		String msg = "";
		String url = "";
		
		
		try {
			GosuDao gosudao = new GosuDao();
			int result = gosudao.QnaEdit(qnaboard);

			if(result > 0) {
				msg = "수정 완료";
				url = "/GosuProfile.go?email=" + g_email;
			}else {
				msg = "수정실패";
				url = "/GosuProfile.go?email=" + g_email;
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
