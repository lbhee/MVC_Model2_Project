package kr.or.team3.service;

import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.team3.action.Action;
import kr.or.team3.action.ActionForward;
import kr.or.team3.dao.GosuDao;
import kr.or.team3.dto.member.Member;

public class GosuMap_Service implements Action {

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) {
		String adr = request.getParameter("adr");
		String d_name = request.getParameter("d_name");
		ActionForward actionforward = new ActionForward();
	
		
		
		List<Member> gosumap = null;
		try {
			PrintWriter out = response.getWriter();
			GosuDao gosudao = new GosuDao();
			gosumap = gosudao.gosumap();
			
			
			for(Member m : gosumap) {
				out.print("['" + m.getAdr() + "','" + m.getName() + "'],");
			}
			
			
			actionforward.setPath("/map.html");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return actionforward;
	}

}

