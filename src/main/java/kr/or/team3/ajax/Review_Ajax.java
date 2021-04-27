package kr.or.team3.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.dao.MemberDao;
import kr.or.team3.dto.member.RQ_Form;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/Review_Ajax")
public class Review_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Review_Ajax() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	response.setContentType("text/html;charset=UTF-8");
    	
    	PrintWriter out = response.getWriter();
    	int done = Integer.parseInt(request.getParameter("done"));
    	
    	List<RQ_Form> list = null;
    	
    	
    	try {
			MemberDao memberdao = new MemberDao();
			list = memberdao.ReviewMember(done);
			JSONArray jsonarr = new JSONArray();
			
			for(int i=0; i < list.size(); i++) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("m_email", list.get(i).getM_mail());
				jsonobj.put("g_email", list.get(i).getG_email());
				
				jsonarr.add(jsonobj);
			}
			
			out.print(jsonarr);
			
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
