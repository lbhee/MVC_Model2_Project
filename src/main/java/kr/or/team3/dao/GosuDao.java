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
import kr.or.team3.dto.member.Member;
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
			

			String sql = "insert into G_REGISTER(email, G_code, pr, D_code) values(?, 10000,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, gosudata.getEmail());
			pstmt.setString(2, gosudata.getPr());
			pstmt.setInt(3, gosudata.getD_code());

			
			row = pstmt.executeUpdate();
			

			
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
				e2.getMessage();
			}
		}
		
		return row;
	}
	

	//고객이 고수로 가입할때 작성하는 기본정보 by 안승주 21.04.21
	public int insertGosuInfo_B(Gosu_Info_Basic gosu_info_basic) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String gosu_info_basic_sql = "INSERT INTO G_INFO_BASIC(EMAIL, G_CODE, PAYMENT, AREA, HIRE_NUM, CALLTIME, PHOTO)" + 
										 "values(?,10000,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(gosu_info_basic_sql);
			pstmt.setString(1, gosu_info_basic.getEmail());
			pstmt.setString(2, gosu_info_basic.getPayment());			
			pstmt.setString(3, gosu_info_basic.getArea());
			pstmt.setInt(4, gosu_info_basic.getHire_num());
			pstmt.setString(5, gosu_info_basic.getCalltime());
			pstmt.setString(6, gosu_info_basic.getPohoto());
			
			pstmt.executeUpdate();
			
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
	public int insertGosuInfo_A(Gosu_Info_Add gosu_info_add) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String gosu_info_add_sql = "INSERT INTO G_INFO_ADD(EMAIL,G_CODE,TURN,CAREER,LICENCE)" + 
										"values(?,10000,NO_INFO_ADD.nextval,?,?)";
			
			pstmt = conn.prepareStatement(gosu_info_add_sql);
		
			pstmt.setString(1, gosu_info_add.getEmail());
			pstmt.setString(2, gosu_info_add.getCareer());
			pstmt.setString(3, gosu_info_add.getLicense());
			
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
			String spl = "SELECT * FROM RQ_FORM WHERE G_EMAIL = ? AND G_CODE = ? AND done = 1 ORDER BY num desc;";
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
	
 // 이거 수정할것 
	public List<RQ_Form> dds(String g_email, int g_code){
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
	
// ------------------------------------------------------------------
	public List<Gosu_Register> searchgosu(String d_code){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Gosu_Register> gosulist = null;
	    Member member = new Member();
		try {
			conn = ds.getConnection();
			String sql = "select g_register.pr, member.name from member join g_register on g_register.email = member.email where d_code like '" + d_code +"%'";
			
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			gosulist = new ArrayList<Gosu_Register>();

			while(rs.next()) {
				String pr = rs.getString("pr");
				String name = rs.getString("name");
				Gosu_Register gosuregister = new Gosu_Register(pr, name);
				gosulist.add(gosuregister);
				System.out.println(gosulist);
			
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return gosulist;
	}
	
// --------------- 이
	//
	public List<Member> gosumap(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> gosumaplist = null;
		Member member = null;
	    
		try {
			conn = ds.getConnection();
			String sql = "SELECT m.ADR , gd.D_NAME  FROM G_REGISTER g JOIN MEMBER m ON g.EMAIL = m.EMAIL JOIN G_DETAIL gd ON g.D_CODE = gd.D_CODE ";
		
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			gosumaplist = new ArrayList<Member>();

			while(rs.next()) {
				String adr = rs.getString("adr");
				String d_name = rs.getString("d_name");
				member = new Member(adr, d_name);
				gosumaplist.add(member);
		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return gosumaplist;
	}
	
}

































