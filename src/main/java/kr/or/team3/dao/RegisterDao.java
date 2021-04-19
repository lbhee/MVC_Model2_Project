package kr.or.team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.team3.dto.member.Member;

public class RegisterDao {
	
	DataSource ds = null;
	
	public RegisterDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	
	//회원가입 by 안승주 21.04.19
	public int registerOk(Member memberdata) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into member(email, name, pwd, adr) values(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberdata.getEmail());
			pstmt.setString(2, memberdata.getName());
			pstmt.setString(3, memberdata.getPwd());
			pstmt.setString(4, memberdata.getAdr());
			
			row = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return row;
	}
}
