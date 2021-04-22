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
import kr.or.team3.dto.gosu.Gosu_Register;

@WebServlet("/searchgosu.go")
public class SearchGosu_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
    public SearchGosu_Ajax() {
        super();
       
    }
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	PrintWriter out = response.getWriter();
    	String d_code = request.getParameter("d_code");
    	Gosu_Register gosu = null;
    	
		try {
			GosuDao gosudao = new GosuDao();
			List<Gosu_Register> gosulist = gosudao.searchgosu();
			
			
			
			for(Gosu_Register list : gosulist ) {
				String div =   "<div class='row'> ";
				div += "<div class='col-md-4 col-sm-6 col-xs-12'>";
				div += "<div class='icon-wrapper wow fadeIn' data-wow-duration='1s' data-wow-delay='0.2s'>";
				div += "<p>" + list + "</p>";
				div += "</div> </div> </div> ";
				
				out.print(div);
			}
			
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
