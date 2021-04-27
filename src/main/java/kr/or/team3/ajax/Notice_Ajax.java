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

import kr.or.team3.dao.GosuDao;
import kr.or.team3.dto.notice.Notice;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet("/Notice_Ajax")
public class Notice_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	 public Notice_Ajax() {
	        super();
	    }

	    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	response.setContentType("text/html;charset=UTF-8");
	    	
	    	PrintWriter out = response.getWriter();
	    	String email = request.getParameter("email");
	    	List<Notice> list = null;
	    		    	
	    	try {
				GosuDao gosudao = new GosuDao();
				list = gosudao.Notice(email);
				
				JSONArray jsonarr = new JSONArray();

				for(int i=0; i < list.size(); i++) {
					JSONObject jsonobj = new JSONObject();
					jsonobj.put("num", list.get(i).getNum());
					jsonobj.put("title", list.get(i).getTitle());
					jsonobj.put("writedate", list.get(i).getWritedate());
					
					
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
