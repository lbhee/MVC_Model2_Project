package kr.or.team3.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.GosuDao;
import kr.or.team3.dao.MemberDao;
import kr.or.team3.dto.member.Member;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet("/GosuMap_Ajax")
public class GosuMap_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public GosuMap_Ajax() {
        super();
    }


	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		List<Member> gosumap = null;
		
		try {

			GosuDao gosudao = new GosuDao();
			gosumap = gosudao.gosumap();

			JSONArray jsonarr = new JSONArray();
			
			for(int i=0; i < gosumap.size(); i++) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("email", gosumap.get(i).getEmail());
				jsonobj.put("adr", gosumap.get(i).getAdr());
				jsonobj.put("name", gosumap.get(i).getName());
				jsonobj.put("s_name", gosumap.get(i).getS_name());
				jsonobj.put("d_name", gosumap.get(i).getD_name());
				
				jsonarr.add(jsonobj);
			}
	
		out.print(jsonarr);
		
		} catch (Exception e) {
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
