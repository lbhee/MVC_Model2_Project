package kr.or.team3.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.service.Member_EditOk_Service;
import kr.or.team3.service.Member_JoinOk_Service;

@WebServlet("*.go")
public class gosuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public gosuController() {
        super();
    }
    
    
    
    protected void doprocess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	
    	String requesturl = request.getRequestURI();
    	String requestpath = request.getContextPath();
    	String URL = requesturl.substring(requestpath.length());
    	
    	System.out.println(URL);
    	
    	Action action = null;
    	ActionForward actionForward = null;
    	
    	// 회원가입
    	if(URL.equals("/Join.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/register/Join.jsp");
    	
    	// 회원가입 확인
    	} else if(URL.equals("/Joinok.go")) {
    		action = new Member_JoinOk_Service();
    		actionForward = action.excute(request, response);
    	
    	// 로그인  ( 로그인 확인은 ajax )
    	} else if(URL.equals("/Login.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/register/Login.jsp");
    	
    	// 마이페이지	
    	} else if(URL.equals("/Mypage.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/register/Mypage.jsp");
    	
    	// 회원정보수정
    	} else if(URL.equals("/EditOk.go")) {
    		action = new Member_EditOk_Service();
    		actionForward = action.excute(request, response);
    	}
    		
    	
    	
    	
    	
    	
    	
    	// 결과 forward
    	if(actionForward != null) {
            RequestDispatcher dis = request.getRequestDispatcher(actionForward.getPath());
            dis.forward(request, response);
         }
    	
    	
  
    	
    	
    	
    	
    	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}

}
