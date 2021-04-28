package kr.or.team3.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.dao.GosuDao;
import kr.or.team3.dto.gosu.Gosu_Register;


@WebServlet("/SearchGosu_A")
public class SearchGosu_A extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchGosu_A() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	response.setContentType("text/html;charset=UTF-8");
    	
    	PrintWriter out = response.getWriter();
    	String d_code = request.getParameter("d_code");
    	// String name = request.getParameter("name");
    
		try {
			GosuDao gosudao = new GosuDao();
			List<Gosu_Register> gosulist = gosudao.searchgosu(d_code);
			
			
			
			String div ="<div class='row'>";
			for(Gosu_Register list : gosulist ) {
			
				
				div += "<div class='col-md-4 col-sm-6 col-xs-12'>";
				div += "<div class='icon-wrapper wow fadeIn'>";
				div += "<a href ='GosuProfile.go?email="+list.getEmail()+"'>";
				// null 일시 
				if(list.getPhoto()  == null ) {
					div += "<img class='search_gosuImg' src='images/default_img.svg'>";
				}else {
					div += "<img class='search_gosuImg' src='upload/" + list.getPhoto() + "'>";
				}
				
				
				div += "<p>" + list.getName()+ " 선생님 </p>";
				div += "</div>";
				div += "</div>";
					
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
