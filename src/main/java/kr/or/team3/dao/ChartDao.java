package kr.or.team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ChartDao {
	DataSource ds = null;
	
	public ChartDao() throws NamingException {
		
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}
	
	
	//가입된 총 회원 수 by 안승주 21.04.23
	public int totalMemberCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalmembercount = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*) cnt from member";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
	
			if(rs.next()) {
				totalmembercount = rs.getInt("cnt");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.getMessage();
			}
		}
		return totalmembercount;
	}
	
	
	
	
	//누적 요청서 총건수 by 안승주 21.04.23
	public int totalRQCount() {
				
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalrqmembercount = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*) cnt from RQ_Form where done = 0 or done = 1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
					
			if(rs.next()) {
				totalrqmembercount = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.getMessage();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				e2.getMessage();
			}
		}
				
				
		return totalrqmembercount;
	}
	
	//가입된 총 고수의 수 by 안승주 21.04.23
	public int totalGosuCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalgosucount = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*) cnt from g_register";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalgosucount = rs.getInt("cnt");
			}
		} catch (Exception e) {
			e.getMessage();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				e2.getMessage();
			}
		}
		return totalgosucount;
	}
	
	//총 고용 횟수
	public int hireCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		int count = 0;
				
		try {
			conn = ds.getConnection();
			String sql = "SELECT sum(hire_num) hn FROM G_INFO_BASIC gib";
					
			pstmt  = conn.prepareStatement(sql);
					
			rs = pstmt.executeQuery();
					
			if(rs.next()){;
				count = rs.getInt("hn");
			}
			
					
		} catch (Exception e) {
			e.getMessage();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				e2.getMessage();
			}
		}
		return count;
	}
	
	//고수가 받은 누적 요청서
	public int totalGosuRQCount(String g_email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		int count = 0;
				
		try {
			conn = ds.getConnection();
			String sql = "select count(*) cnt from ( SELECT * FROM  RQ_Form WHERE G_EMAIL = ?) where done = 0 or done = 1 OR done = 2 OR done = 4";
					
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, g_email);
			rs = pstmt.executeQuery();
					
			if(rs.next()){;
				count = rs.getInt("cnt");
			}
			
					
		} catch (Exception e) {
			e.getMessage();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				e2.getMessage();
			}
		}
		return count;
	}
	
	//고수가 고용된 횟수
	public int totalHireCount(String g_email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
			
		int count = 0;
				
		try {
			conn = ds.getConnection();
			String sql = "SELECT hire_num hn FROM G_INFO_BASIC gib WHERE email = ?";
					
			pstmt  = conn.prepareStatement(sql);
			pstmt.setString(1, g_email);
			rs = pstmt.executeQuery();
					
			if(rs.next()){;
				count = rs.getInt("hn");
			}
			
					
		} catch (Exception e) {
			e.getMessage();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				e2.getMessage();
			}
		}
		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
