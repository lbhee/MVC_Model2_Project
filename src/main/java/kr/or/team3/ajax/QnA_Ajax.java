package kr.or.team3.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.dao.GosuDao;
import kr.or.team3.dto.QnABoard.QnA_Board;

@WebServlet("/QnA_Ajax")
public class QnA_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public QnA_Ajax() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	
    	System.out.println("ajax");
    	String email = request.getParameter("email");
    	//String email = (String)request.getSession().getAttribute("ID");
    	
    	PrintWriter out = response.getWriter();
    	QnA_Board qna = new QnA_Board();
    	
    	try {
			GosuDao gosudao = new GosuDao();
			qna = gosudao.Qna(email);
			
			out.print(qna);
			System.out.println("결과"+qna);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
    	
    	
    	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
