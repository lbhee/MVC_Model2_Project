package kr.or.team3.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.team3.dao.MemberDao;
import kr.or.team3.dto.member.Member;


@WebServlet("/Member_LoginOk_Service")
public class Member_LoginOk_Service extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	public Member_LoginOk_Service() {
        super();
	}

	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		PrintWriter out = response.getWriter();
		
		String email= request.getParameter("email");
		String pwd=request.getParameter("pwd");
		
		
		
		Member member = new Member(email.toLowerCase(),"0",pwd,"0");
		
		
		try {
			MemberDao memberDao = new MemberDao();
			// 로그인완료시 세션 날려줄려고 받음
			boolean result = memberDao.loginOk(member,request);
			
			if(result == true) {
				out.print("true");
				
			}else {
				out.print("false");
			}
			
		}catch (Exception e) {
			e.getMessage();
		}finally {
			
		}
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
