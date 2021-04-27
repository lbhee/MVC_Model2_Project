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
import kr.or.team3.dto.review.Review_Board;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/ReviewList_Ajax")
public class ReviewList_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ReviewList_Ajax() {
        super();

    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");

    	String email = request.getParameter("email");
    	
    	PrintWriter out = response.getWriter();
    	List<Review_Board> list = null;    	    	
    	
    	try {
			MemberDao memberdao = new MemberDao();
			list = memberdao.Reviewlist(email);
			JSONArray jsonarr = new JSONArray();
			for(int i=0; i < list.size(); i++) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("name", list.get(i).getName());
				jsonobj.put("content", list.get(i).getContent());
				jsonobj.put("writedate", list.get(i).getWritedate());
				jsonobj.put("grade", list.get(i).getGrade());
				
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