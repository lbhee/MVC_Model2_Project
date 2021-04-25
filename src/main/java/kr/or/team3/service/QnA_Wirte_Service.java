package kr.or.team3.service;


import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.GosuDao;
import kr.or.team3.dto.QnABoard.QnA_Board;

public class QnA_Wirte_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String writedate = request.getParameter("writedate");
		String email =  (String)request.getSession().getAttribute("ID");
		
		ActionForward actionForward = new ActionForward();
		
		QnA_Board qna = new QnA_Board(0, title, content, writedate, email, 10000);
		
		try {
			
			GosuDao dao = new GosuDao();
			int row = dao.QnAWrite(qna);
			
			String msg = "";
			String url = "";
			
			if(row > 0) {
				msg = "글쓰기 완료";
				url = "/GosuProfile.go?email=" + email;
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
