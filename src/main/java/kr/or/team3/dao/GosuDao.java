package kr.or.team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.team3.dto.gosu.Gosu_Register;

public class GosuDao {
	DataSource ds = null;
	
	public GosuDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
	}
	//고객이 고수로 가입하기 by 안승주 21.04.19
	public int joinGosuOk(Gosu_Register gosudata) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			//conn.setAutoCommit(false);
			
			String sql = "insert into G_register(email, g_code, pr, D_code) values(?, 10000,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, gosudata.getEmail());
			pstmt.setString(2, gosudata.getPr());
			pstmt.setInt(3, gosudata.getD_code());
			
			row = pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
				e2.getMessage();
			}
		}
		
		return row;
	}
	
	public int insertGosuInfo_basic() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "insert into G_INFO_BASIC(email, G_code, payment, area, hire_num, calltime) values(?,?,?,?,0,0)";
			pstmt = conn.prepareStatement(sql);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return row;
	}
}