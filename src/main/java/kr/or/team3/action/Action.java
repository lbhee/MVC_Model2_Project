package kr.or.team3.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public interface Action{
		public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws IOException;
	}
		
	


