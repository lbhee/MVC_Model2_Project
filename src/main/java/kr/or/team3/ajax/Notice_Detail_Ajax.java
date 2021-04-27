package kr.or.team3.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.dao.GosuDao;
import kr.or.team3.dao.MemberDao;
import kr.or.team3.dto.member.RQ_Content_Member;

@WebServlet("/Notice_Detail")
public class Notice_Detail_Ajax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
    public Notice_Detail_Ajax() {
        super();
    }


	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		GosuDao gosudao;
		try {
			
			gosudao = new GosuDao();
			
			int noticenum = Integer.parseInt(request.getParameter("num"));
			
			String title = gosudao.NoticeContent(noticenum).getTitle();
			String write = gosudao.NoticeContent(noticenum).getWritedate();
			String filename = gosudao.NoticeContent(noticenum).getFilename();
			String content = gosudao.NoticeContent(noticenum).getContent();
			int num = gosudao.NoticeContent(noticenum).getNum();
			

			
			String div = "<div class='Notice_Detail_Box'>";
			
			div += "<div class='RQ_Detail_modal_Header'><input type='button' class='closeMadal' value='✖️'></div>";
			div += "<div class='Notice_div'>";
			div += "<div class='Notice_Detail_Box_nav'><h3 class='Notice_Header'>공지</h3> <b>" + title + "</b>";
			div += "<p class='Notice_wrtie'>" + write + "</p> ";
			
			if(filename.equals("0")) {
				div += "<p class='Notice_wrtie'></div>";
			}else {
				div += "<p class='Notice_wrtie'><a href='NoticeFileDownload.go?filename=" + filename + "'>" + filename + "</a></p> </div>";
			}
			
			div += "<div class='Notice_Detail_Main'>";
			
			div += "<div class='Notice_Detail_Content'>";
			div += "<p>" + content + "</p>";
			div += "</div>";
			div += "</div>";
			
			div += "<a href='#' class='edit_btn' id='NoticeBtn'>수정</a>";
			div += "<a href='#' class='delete_btn' id='NoticeBtn'>삭제</a>";

			div += "</div>";
			div += "</div>";
				
			
			out.print(div);
			
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
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
