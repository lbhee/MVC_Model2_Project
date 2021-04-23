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
    	response.setContentType("text/html;charset=UTF-8");
    	
    	PrintWriter out = response.getWriter();
    	String d_code = request.getParameter("d_code");
    	String name = request.getParameter("name");
    	Gosu_Register gosu = null;
    	
    
		try {
			GosuDao gosudao = new GosuDao();
			List<Gosu_Register> gosulist = gosudao.searchgosu(d_code);
			
			
			String div =   "<div class='row'> ";
			for(Gosu_Register list : gosulist ) {
				
				div += "<div class='col-md-4 col-sm-6 col-xs-12' >";
				div += "<div class='icon-wrapper wow fadeIn' data-wow-duration='1s' data-wow-delay='0.2s' style = 'text-align : center' , 'height : 180 px ';>";
	
				div += "<h1>" + list.getName()+ " 선생님 </h1>";
				div += "<hr><br>";
				div += "<h3>" + list.getPr() + "</h3>"; 
				div += "<a href ='Gosupage.jsp'><h>개인 페이지 이동</h2></a>";
				
				
				div += "</div> </div>  ";
				
				
			}
				div += "</div>";
				out.print(div);
				
				
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