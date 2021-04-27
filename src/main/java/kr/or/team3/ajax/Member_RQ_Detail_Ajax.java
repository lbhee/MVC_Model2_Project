package kr.or.team3.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.dao.MemberDao;
import kr.or.team3.dto.member.RQ_Content_Member;

@WebServlet("/Member_RQ_Detail")
public class Member_RQ_Detail_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public Member_RQ_Detail_Ajax() {
        super();
    }


	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int num = Integer.parseInt(request.getParameter("num"));
		int cpage = Integer.parseInt(request.getParameter("cp"));
		int pagesize = Integer.parseInt(request.getParameter("ps"));

		
		try {
			MemberDao memberdao = new MemberDao();
			
			RQ_Content_Member content = memberdao.getRQContent_Member(num);
			
			String div = "<div class='RQ_Detail_modal_box'>";
			div += "<div class='RQ_Detail_modal_Header'><input type='button' class='closeMadal' value='✖️'></div>";
			div += "<div class='RQ_Detail_modal_Nav'><h3><b>내 요청서</b></h3></div>";
			div += "<p>제목: "  + content.getTitle() + "</p>";
			div += "<p>내용: "  + content.getContent() + "</p>";
			div += "<p>희망 시간: "  + content.getHopedate() + "</p>";
			div += "<p>고객 이름: "  + content.getMemberName() + "</p>";
			div += "<p>분야: "  + content.getSubject() + "</p>";
			div += "<hr>";
			div += "<p>고수 이름: "  + content.getGosuName() + "</p>";
			div += "<p>고수 이메일: "  + content.getG_email() + "</p>";
			div += "<p>고수 활동지역: "  + content.getAdr() + "</p>";
			
			div += "<p>"  + content.getWritedate() + "</p>";
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
