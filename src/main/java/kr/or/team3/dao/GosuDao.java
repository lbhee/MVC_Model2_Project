package kr.or.team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GosuDao {
	DataSource ds = null;
	
	public GosuDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/enb/jdbc/oracle");
	}
	//고객이 고수로 가입하기 by 안승주 21.04.19
	public int registerGosuOk(String email, int G_code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			//conn.setAutoCommit(false);
			
			String sql = "insert into G_registe(email, G_code) values(?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			pstmt.setInt(2, G_code);
			
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
	
	public int insertGosuInfo() {
		
		return 0;
	}
}
