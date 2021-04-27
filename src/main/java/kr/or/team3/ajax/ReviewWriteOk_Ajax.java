package kr.or.team3.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.dao.MemberDao;
import kr.or.team3.dto.review.Review_Board;

@WebServlet("/ReviewWriteOk_Ajax")
public class ReviewWriteOk_Ajax extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    public ReviewWriteOk_Ajax() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	
    	PrintWriter out = response.getWriter();
    	
    	String title = request.getParameter("title");
    	String content = request.getParameter("content");
    	int grade = Integer.parseInt(request.getParameter("grade"));
    	String g_email = request.getParameter("g_email");
    	String m_email = (String)request.getSession().getAttribute("ID");
    	
    	if (g_email == null || m_email == null || g_email.trim().equals("") ||m_email.trim().equals("")) {
			out.print("fasle");
		}
    	
    	Review_Board reviewboard = new Review_Board(0, title, content, null, grade, 0, 0, 0, 0, m_email, g_email, 10000);
    	
    	MemberDao memberdao = null;
    	
    	try {
			memberdao = new MemberDao();
			
			int row = memberdao.writeReviewBoard(reviewboard);
			
			if(row > 0) {
				out.print("true");
			}else {
				out.print("true");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    	
    	
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
