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
import kr.or.team3.dto.gosu.Gosu_Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/Gosupage_Ajax")
public class Gosupage_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Gosupage_Ajax() {
        super();
    }


    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	
    	PrintWriter out = response.getWriter();
    	String email = request.getParameter("email");
    	List<Gosu_Page> gosupage = null;
    	
    	try {
			GosuDao gosudao = new GosuDao();
			gosupage = gosudao.gosupage(email);
			
			JSONArray jsonarr = new JSONArray();
			
			for(int i=0; i < gosupage.size(); i++) {
				JSONObject jsonobj = new JSONObject();
				jsonobj.put("name", gosupage.get(i).getName());
				jsonobj.put("pr", gosupage.get(i).getPr());
								
				jsonobj.put("area", gosupage.get(i).getArea());
				jsonobj.put("calltime", gosupage.get(i).getCalltime());
				jsonobj.put("hire_num", gosupage.get(i).getHire_num());
				jsonobj.put("payment", gosupage.get(i).getPayment());
				jsonobj.put("photo", gosupage.get(i).getPhoto());
				if(gosupage.get(i).getCareer() == null) {
					jsonobj.put("career", "이 입력되지않았습니다.");
				}else {
					jsonobj.put("career", gosupage.get(i).getCareer());
				}
				
				if(gosupage.get(i).getLicense() == null) {
					jsonobj.put("license", "");
				}else {
					jsonobj.put("license", gosupage.get(i).getLicense());
				}
				
				jsonobj.put("d_name", gosupage.get(i).getD_name());
				jsonobj.put("s_name",  gosupage.get(i).getS_name());
				
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
