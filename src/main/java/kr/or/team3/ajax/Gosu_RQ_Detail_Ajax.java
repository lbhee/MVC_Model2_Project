package kr.or.team3.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.dao.GosuDao;
import kr.or.team3.dto.gosu.Gosu_RQ_Content;


@WebServlet("/Gosu_RQ_Detail")
public class Gosu_RQ_Detail_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public Gosu_RQ_Detail_Ajax() {
        super();
    }


	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int num = Integer.parseInt(request.getParameter("num"));
		int cpage = Integer.parseInt(request.getParameter("cp"));
		int pagesize = Integer.parseInt(request.getParameter("ps"));
		String checkList = request.getParameter("CheckList");
		
		if(checkList == null) {
			checkList = "null";
		}
		
		try {
			
			GosuDao gosudao = new GosuDao();
			Gosu_RQ_Content content = gosudao.getRQContent_Member(num);
			
			String div = "<div class='RQ_Detail_modal_box'>";
			div += "<div class='RQ_Detail_modal_Header'><input type='button' class='closeMadal' value='✖️'></div>";
			div += "<div class='RQ_Detail_modal_Nav'><h3><b>받은 요청서</b></h3></div>";
			div += "<p>요청 번호: "  + content.getNum() + "</p>";
			div += "<p>제목: "  + content.getTitle() + "</p>";
			div += "<p>내용: "  + content.getContent() + "</p>";
			div += "<p>희망 시간: "  + content.getHopedate() + "</p>";
			div += "<p>고객 이름: "  + content.getMemberName() + "</p>";
			div += "<p>전화번호: "  + content.getPhone() + "</p>";
			
			div += "<p class='RQ_last_p'>"  + content.getWritedate() + "</p>";
			
			if(checkList.equals("Done")) {
				div += "<p>처리가 완료된 요청서입니다.</p>";
			}else {
				div += "<a class='Rq_btn' href = 'Gosu_RQ_CompleteOk.go?num="+ num + "&cp=" + cpage + "&ps=" + pagesize + "'>수락</a>";
				div += "<a class='Rq_btn' href = 'Gosu_RQ_DeleteOk.go?num="+ num + "&cp=" + cpage + "&ps=" + pagesize + "'>거절</a>";	
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
