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

import kr.or.team3.service.Gosuregister_Service;

import kr.or.team3.service.Member_EditOk_Service;

import kr.or.team3.service.Member_JoinOk_Service;
import kr.or.team3.service.RQ_Form_WriteOk_Service;

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
    		
    	// ==================================================================================	
    	// 고수가입하기_1 화면	
    	} else if(URL.equals("/Gosuregister_1.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/register/GosuRegister/Gosuregister_1.jsp");
    	// 고수가입하기_2 화면	
    	} else if(URL.equals("/Gosuregister_2.go")) {
			actionForward = new ActionForward();
			actionForward.setPath("/WEB-INF/views/register/GosuRegister/Gosuregister_2.jsp");
   		// 고수가입하기_3 화면	
    	}else if(URL.equals("/Gosuregister_3.go")) {
			actionForward = new ActionForward();
			actionForward.setPath("/WEB-INF/views/register/GosuRegister/Gosuregister_3.jsp");
    	// 고수가입완료
    	} else if(URL.equals("/GosuregisterOk.go")) {
    		action = new Gosuregister_Service();
    		actionForward = action.excute(request, response);
    		
    	} else if(URL.equals("/Gosuregister_Info.go")) {
    		actionForward = new ActionForward();
			actionForward.setPath("/WEB-INF/views/register/GosuRegister/Gosuregister_Info.jsp");
    		// ==================================================================================
	
    	// 마이페이지	
    	} else if(URL.equals("/Mypage.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("/WEB-INF/views/register/Mypage.jsp");
    	
    	// 회원정보수정
    	} else if(URL.equals("/EditOk.go")) {
    		action = new Member_EditOk_Service();
    		actionForward = action.excute(request, response);
    	
    	// 요청서 작성완료 by 안승주
    	}else if(URL.equals("/SendRQOk.go")) {
    		action = new RQ_Form_WriteOk_Service();
    		actionForward = action.excute(request, response);
    	// 요청서 작성하기 by 안승주
    	}else if(URL.equals("/WriteRQ.go")) {
    		actionForward = new ActionForward();
    		actionForward.setPath("RQ_Member_write.jsp");
    	// 요청서 가져오기 by 안승주
    	}else if(URL.equals("/RQList.go")) {
    		System.out.println("요청서 가져오기");
    		actionForward = new ActionForward();
    		actionForward.setPath("RQ_List.jsp");
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
