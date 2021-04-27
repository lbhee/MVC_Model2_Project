package kr.or.team3.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;

public class Notice_File_Download implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ActionForward actionForward = null;
		
		  byte[] b = new byte[4096]; //4kb  //요기는 필요에 따라 조정 가능
		    
		    try {
		    	String filename = request.getParameter("filename");
				
				String savepath = "upload";
			    String downloadpath = request.getSession().getServletContext().getRealPath(savepath);
			    String FilePath = downloadpath + "/" + filename;
		    	
				FileInputStream in = new FileInputStream(FilePath);
				String sMimeType = request.getSession().getServletContext().getMimeType(FilePath); //파일의 타입 정보
			    if(sMimeType == null){
			     //알수 없는 형식의 파일은 
			     //application/octet-stream 기본값으로 (알수 없는 파일 형식)
			     sMimeType = "application/octet-stream";
			    }
			    response.setContentType(sMimeType);
			    response.setHeader("Content-Disposition", 
			            "attachment;filename="+new String(filename.getBytes(),"ISO8859_1"));
			    
			    ServletOutputStream out2 = response.getOutputStream();
			    int numread;
			    while((numread = in.read(b,0,b.length)) != -1){
			       out2.write(b,0,numread);
			    }
			    
			    out2.flush();
			    out2.close();
			    in.close(); 
			    
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} //실 저장 경로에서
		    catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    
			
		   // actionForward.setPath("/WEB-INF/views/gosupage.jsp");
			
			return actionForward;
	}

}