package kr.or.team3.service;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.GosuDao;
import kr.or.team3.dto.notice.Notice;

public class Notice_Edit_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String uploadpath  = request.getSession().getServletContext().getRealPath("upload");
		int num = 0;
		String title = "";
		String content = "";
		String filename = "";
		String writedate = "";
		String email = "";
		int size = 1024 * 1024 * 10;
		int result = 0;

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
			String orifilename = multi.getOriginalFileName(file); //새로운파일 업로드에 필요한 코드

			Notice notice = new Notice();
			GosuDao gosudao = new GosuDao();
			
			title = multi.getParameter("title");
			content = multi.getParameter("content");
			num = Integer.parseInt(multi.getParameter("num").trim());			
			
			if(filename == null) {
				 filename = "0";
			 }
			 
				
			notice.setFilename(filename);
			notice.setNum(num);
			notice.setContent(content);
			notice.setTitle(title);
			

			result = gosudao.NoticeEdit(notice);
			
			
			email = multi.getParameter("email");
			
		} catch (Exception e) {
			e.getMessage();
		}
				
		String msg = "";
		String url = "";
		
		if(result >0) {
			msg = "수정 완료";
			url = "/GosuProfile.go?email=" + email;
		} else {
			msg = "수정 실패";
			url = "/GosuProfile.go?email=" + email;
		}
		
		ActionForward actionForward = new ActionForward();
		
		request.setAttribute("member_msg", msg);
		request.setAttribute("member_url", url);

		actionForward.setPath("/WEB-INF/views/include/redirect.jsp");
		return actionForward;
	}

}
