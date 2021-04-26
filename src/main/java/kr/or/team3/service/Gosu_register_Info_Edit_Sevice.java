package kr.or.team3.service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.GosuDao;
import kr.or.team3.dto.gosu.Gosu_Info_Add;
import kr.or.team3.dto.gosu.Gosu_Info_Basic;
import kr.or.team3.dto.gosu.Gosu_Register;

public class Gosu_register_Info_Edit_Sevice implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
	    
		//cos.jar 파일에서 제공하는 MultipartRequest 객체 사용
		
		//업로드 파일에 대한 기본 정보 세팅
		int size = 1024*1024*10; //10M 네이버 계산기
				
		
		Gosu_Register gosu_register = null;
		Gosu_Info_Basic gosu_info_basic = null;
		Gosu_Info_Add gosu_info_add = null;
		GosuDao gosudao = null;
		
		HttpSession session = request.getSession();
		
		ActionForward actionForward = new ActionForward();
		
		String email= (String)session.getAttribute("ID");
		
		
		try {
				
			MultipartRequest multi = new MultipartRequest(
					request,
					uploadpath, // 실 저장 경로 (배포된 경로)
					size, // 10M
					"UTF-8",
					new DefaultFileRenamePolicy() // 파일 중복 (upload > 중복된 이름 변경)
					); // 파일 업로드 완료
			Enumeration filenames = multi.getFileNames();
			
			String file = (String)filenames.nextElement();
			
			String pr = multi.getParameter("pr");
			String area = multi.getParameter("area");
			String calltime_1 = multi.getParameter("calltime_1");
			String calltime_2 = multi.getParameter("calltime_2");
			String calltime = calltime_1 + "~" + calltime_2;
			
			String payment = multi.getParameter("payment");
			String career = multi.getParameter("career");
			String license = multi.getParameter("license");
			String photo = multi.getFilesystemName(file);
		
			if(photo == null) {
				photo = multi.getParameter("photo_Defalut");
			}
			
			
			
			 gosudao = new GosuDao();
			 
			 // 고수 가입정보
			 gosu_register = new Gosu_Register(email, size, pr, size, license, photo);
			 // 고수 기본정보
			 gosu_info_basic = new Gosu_Info_Basic(email, 10000, payment, area, 0, calltime, photo);
			 // 고수 추가정보
		  	 gosu_info_add = new Gosu_Info_Add(email, 10000, null, career, license);
			
			int row1 = gosudao.UpdateGosuInfo_B(gosu_info_basic);
			int row2 = gosudao.UpdateGosuInfo_A(gosu_info_add);
			int row3 = gosudao.UpdateRegister(gosu_register);
			
			String url="";
			String msg="";
			
			if(row1 > 0 || row2 > 0 || row3 > 0) {
				url="/GosuProfile.go";
				msg="변경 되었습니다.";
				
			}else {
				url="/Gosuregister_Info.go";
				msg="변경 실패";
			}
			
			request.setAttribute("member_msg", msg);
			request.setAttribute("member_url", url);
			
			actionForward.setPath("/WEB-INF/views/include/redirect.jsp");
			
		} catch (Exception e) {
			
		}
		
		return actionForward;
	}

}
