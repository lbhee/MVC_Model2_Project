package kr.or.team3.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/MemberWriteShow_Ajax")
public class MemberWriteShow_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberWriteShow_Ajax() {
        super();
        // TODO Auto-generated constructor stub
    }
    

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	response.setContentType("text/html;charset=UTF-8");
    	
    	PrintWriter out = response.getWriter();
    	
    	String div = "<div class = 'showwrite'>";
    	
    	div += "<form action = '' method = 'POST'>";
    	div += "별점: <div class='make_star'>";
    	div += "<div class= 'rating' name = 'grade' id = 'grade' data-rate='3'>";
    	div += "<i class='fas fa-star'></i><i class='fas fa-star'></i><i class='fas fa-star'></i><i class='fas fa-star'></i><i class='fas fa-star'></i>";
    	div += "</div></div>";
    	div += "제목: <input type = 'text' name = 'title' id = 'title' placeholder = '제목을 적어주세요'><br>";
    	div += "글 내용: <input type = 'text' name = 'content' id = 'content' placeholder = '리뷰 내용을 적어주세요'>";
    	div += "<input type = 'hidden' name = 'm_email' id = 'm_email' value = 'loginemail'>";
    	div += "<input type = 'hidden' name = 'm_email' id = 'g_email' value = 'email'>";
    	div += "</form>";
    	div += "</div>";
    	
    	out.print(div);
    	
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
