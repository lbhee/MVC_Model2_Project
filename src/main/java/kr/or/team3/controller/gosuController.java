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
import kr.or.team3.service.Member_JoinOk_Service;
import kr.or.team3.service.Member_LoginOk_Service;

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
    	
    	if(URL.equals("/Join.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/register/Join.jsp");
    		
    	} else if(URL.equals("/JoinOk.go")) {
    		action = new Member_JoinOk_Service();
    		actionForward = action.excute(request, response);
    		
    	} else if(URL.equals("/Login.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/register/Login.jsp");
    		
    	} else if(URL.equals("/LoginOk.go")) {
    		action = new Member_LoginOk_Service();
    		actionForward = action.excute(request, response);
    	}
    	
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
