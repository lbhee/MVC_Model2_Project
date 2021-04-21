package kr.or.team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import kr.or.team3.dto.gosu.Gosu_Info_Add;
import kr.or.team3.dto.gosu.Gosu_Info_Basic;
import kr.or.team3.dto.gosu.Gosu_Register;
import kr.or.team3.dto.member.RQ_Form;

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
			
			String sql = "insert into G_registe(email, G_code, pr, photo, D_code) values(?,100000,?,?,?)";
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
	//고수가 받은 고객의 미완료 요청서목록 by 안승주 21.04.21
	public List<RQ_Form> getGosuRQ(String g_email, int g_code){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RQ_Form> list = null;
		
		try {
			conn = ds.getConnection();
			String spl = "SELECT r.TITLE , r.CONTENT, r.WRITEDATE , r.HOPEDATE , r.PHONE , r.M_EMAIL , r.G_CODE ,m.NAME"
					+ "FROM RQ_FORM r JOIN MEMBER m ON r.m_email = m.EMAIL"
					+ "WHERE r.G_EMAIL = ? AND r.G_CODE = ? AND  r.done = 0"
					+ "ORDER BY r.num desc;";
			pstmt = conn.prepareStatement(spl);
			
			pstmt.setString(1, g_email);
			pstmt.setInt(2, g_code);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<RQ_Form>();
			
			while(rs.next()) {
				RQ_Form rq_form = new RQ_Form();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}
	
}
































