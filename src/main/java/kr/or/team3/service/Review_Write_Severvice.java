package kr.or.team3.service;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.MemberDao;
import kr.or.team3.dto.review.Review_Board;

public class Review_Write_Severvice implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String g_email = request.getParameter("g_email");
		String m_email = request.getParameter("m_email");
		// String writer = request.getParameter("writer");
		
		String content = request.getParameter("content");
		String writedate = request.getParameter("writedate");
		String grade = request.getParameter("grade");
		
		ActionForward actionForward = new ActionForward();
		
		Review_Board review = new Review_Board(null,0,content,writedate,m_email,g_email,0,grade);
		
		System.out.println(g_email);
		try {
			
			MemberDao memberdao = new MemberDao();
			int row = memberdao.ReviewWrite(review);
			int row2 = memberdao.ReviewWrite_RQ_Update(review);
			
			String msg = "";
			String url = "";
			
			if(row > 0) {
				msg = "리뷰쓰기 완료";
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
