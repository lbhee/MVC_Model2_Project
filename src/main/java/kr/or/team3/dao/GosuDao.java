package kr.or.team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.team3.dto.gosu.Gosu_Info_Add;
import kr.or.team3.dto.gosu.Gosu_Info_Basic;
import kr.or.team3.dto.gosu.Gosu_Register;

public class GosuDao {
	DataSource ds = null;
	
	public GosuDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource) context.lookup("java:comp/enb/jdbc/oracle");
	}
	//고객이 고수로 가입하기 by 안승주 21.04.19
	public int joinGosuOk(Gosu_Register gosudata) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			//conn.setAutoCommit(false);
			
			String sql = "insert into G_registe(email, G_code, pr, photo, D_code) values(?,g_codenum.nextval,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, gosudata.getEmail());
			pstmt.setString(2, gosudata.getPr());
			pstmt.setString(3, gosudata.getPhoto());
			pstmt.setInt(4, gosudata.getD_code());
			
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
	
	//고객이 고수로 가입할때 작성하는 기본정보 by 안승주 21.04.21
	public int insertGosuInfo_B(Gosu_Info_Basic gosuinfo_B_data) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "insert into G_INFO_BASIC(email, G_code, payment, area, hire_num, calltime) values(?,?,?,?,0,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, gosuinfo_B_data.getEmail());
			pstmt.setInt(2, gosuinfo_B_data.getG_code());
			pstmt.setString(3, gosuinfo_B_data.getPayment());
			pstmt.setString(3, gosuinfo_B_data.getArea());
			pstmt.setString(4, gosuinfo_B_data.getCalltime());
			
			row = pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.getMessage();
			}
			
		}
		
		return row;
	}
	
	//고객이 고수로 가입할 때 입력하는 추가정보 by 안승주 21.04.21
	public int insertGosuInfo_A(Gosu_Info_Add gosuinfo_A_data) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "insert into G_INFO_ADD(email, G_code, turn, career, license) values(?,?,turnnum.nextval,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, gosuinfo_A_data.getEmail());
			pstmt.setInt(2, gosuinfo_A_data.getG_code());
			pstmt.setString(3, gosuinfo_A_data.getCareer());
			pstmt.setString(4, gosuinfo_A_data.getLicense());
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.getMessage();
			}
		}
		
		return row;
	}
	
	
}
































