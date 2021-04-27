package kr.or.team3.service;

import java.io.IOException;
import java.util.Enumeration;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.GosuDao;
import kr.or.team3.dto.notice.Notice;

public class Notice_Write_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String uploadpath  = request.getSession().getServletContext().getRealPath("upload");
		String title = "";
		String email = "";
		String writedate = "";
		String content = "";
		String filename = "";
		int size = 1024 * 1024 * 10;
		int result = 0;

		Notice notice = new Notice();

		MultipartRequest multi = new MultipartRequest(
				request,
				uploadpath,
				size,
				"UTF-8",
				new DefaultFileRenamePolicy()
				
				);
		
		try {
			
			
			 Enumeration filenames = multi.getFileNames();
			 
			 String file = (String)filenames.nextElement();
			 filename = multi.getFilesystemName(file);
			
			 
			 if(filename == null) {
				 filename = "0";
			 }
			 
			 String orifilename = multi.getOriginalFileName(file);


			 title = multi.getParameter("title");			 
			 HttpSession session = request.getSession();
			 email = (String)session.getAttribute("ID");			
			 content = multi.getParameter("content");
			 writedate = multi.getParameter("writedate");
			 
			 notice.setTitle(title);
			 notice.setContent(content);
			 notice.setWritedate(writedate);
			 notice.setEmail(email);
			 notice.setFilename(filename);

			GosuDao gosudao = new GosuDao();
			
			result = gosudao.NoticeWrite(notice);			
	
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		String msg = "";
		String url = "";
		if (result > 0) {
			msg = "글쓰기 완료";
			url = "/GosuProfile.go?email=" + email;
		} else {
			msg = "글쓰기 실패";
			 url = "/NoticeWrite.go";
		}
		
		ActionForward actionForward = new ActionForward();
		
		request.setAttribute("member_msg", msg);
		request.setAttribute("member_url", url);
		
		actionForward.setPath("/WEB-INF/views/include/redirect.jsp");

		return actionForward;

	}

}
