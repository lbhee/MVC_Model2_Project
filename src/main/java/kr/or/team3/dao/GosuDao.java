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

import kr.or.team3.dto.gosu.Gosu_Detail_Join_Service;
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
			pstmt.setString(6, gosu_info_basic.getPhoto());
			
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
			
			String gosu_info_add_sql = "INSERT INTO G_INFO_ADD(EMAIL,G_CODE,TURN,CAREER,LICENSE)" + 
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
	
	// 고수가입정보 수정
			public int UpdateRegister(Gosu_Register gosudata) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				int row = 0;
				
				try {
					conn = ds.getConnection();
					//conn.setAutoCommit(false);
					

					String sql = "UPDATE G_REGISTER SET PR = ? WHERE EMAIL = ?";
					
					pstmt = conn.prepareStatement(sql);

					pstmt.setString(1, gosudata.getPr());
					pstmt.setString(2, gosudata.getEmail());

					
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
	
	
	// 고수 기본정보 수정
	public int UpdateGosuInfo_B(Gosu_Info_Basic gosu_info_basic) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String gosu_info_basic_sql = "UPDATE G_INFO_BASIC SET PAYMENT = ?, AREA = ?, CALLTIME = ?, PHOTO = ?" +
									     " WHERE EMAIL = ?";

			pstmt = conn.prepareStatement(gosu_info_basic_sql);
			
			pstmt.setString(1, gosu_info_basic.getPayment());
			pstmt.setString(2, gosu_info_basic.getArea());			
			pstmt.setString(3, gosu_info_basic.getCalltime());
			pstmt.setString(4, gosu_info_basic.getPhoto());
			pstmt.setString(5, gosu_info_basic.getEmail());
			
			
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
	
	// 고수 추가정보 수정
	public int UpdateGosuInfo_A(Gosu_Info_Add gosu_info_add) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String gosu_info_add_sql = "UPDATE G_INFO_ADD SET CAREER = ?, LICENSE = ? WHERE EMAIL = ?";
			
			pstmt = conn.prepareStatement(gosu_info_add_sql);
		
			pstmt.setString(1, gosu_info_add.getCareer());
			pstmt.setString(2, gosu_info_add.getLicense());
			pstmt.setString(3, gosu_info_add.getEmail());
			
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
	
	
	// 해당 이메일로 고수가입 여부 가져오기
	public int getRegister(String Email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		
		Gosu_Detail_Join_Service gosu_Detail_Join_Service = null;
		
		try {
			conn = ds.getConnection();
			String sql= "SELECT * FROM MEMBER m JOIN G_REGISTER g ON m.EMAIL = g.EMAIL WHERE m.EMAIL = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, Email);
			
			
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				row = 1;
			}else {
				row = 0;
			}
			
		} catch (Exception e) {
			System.out.println("Detail Error: " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환하기
			} catch (Exception e2) {
				
			}
		}
		
		return row;
	}
	
	
	
	// 해당 이메일에 맞는 고수 기본 정보 가져오기 
	public Gosu_Info_Basic getGosuInfoBasic_Content(String Email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Gosu_Info_Basic gosu_info_basic = null;
		
		try {
			conn = ds.getConnection();
			String sql="SELECT PAYMENT, AREA, HIRE_NUM, CALLTIME, PHOTO from G_Info_Basic where email=?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Email);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String PAYMENT = rs.getString("PAYMENT");
				String AREA = rs.getString("AREA");
				int HIRE_NUM = Integer.parseInt(rs.getString("HIRE_NUM"));
				String CALLTIME = rs.getString("CALLTIME");
				String PHOTO = rs.getString("PHOTO");
				
				
				gosu_info_basic = new Gosu_Info_Basic(Email, 10000, PAYMENT, AREA, HIRE_NUM, CALLTIME, PHOTO);
				
				
			}
			
		} catch (Exception e) {
			System.out.println("content: " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환하기
			} catch (Exception e2) {
				
			}
		}
		
		return gosu_info_basic;
	}
	
	
	
	// 해당 이메일에 맞는 고수 추가 정보 가져오기 
	public Gosu_Info_Add getGosuInfoAdd_Content(String Email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Gosu_Info_Add gosu_info_add = null;
		
		try {
			conn = ds.getConnection();
			String sql="SELECT TURN,CAREER,LICENSE from G_INFO_ADD where email=?"; //* 하지 말자
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, Email);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String TURN = rs.getString("TURN");
				String CAREER = rs.getString("CAREER");
				String LICENSE = rs.getString("LICENSE");
				
				
				gosu_info_add = new Gosu_Info_Add(Email, 10000, TURN, CAREER, LICENSE);
						
			}
			
		} catch (Exception e) {
			System.out.println("content: " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환하기
			} catch (Exception e2) {
				
			}
		}
		
		return gosu_info_add;
	}
	
	
	// 해당 코드에 맞는 상세서비스, 서비스 분야 조인
	public Gosu_Detail_Join_Service getGosuDetail_Join_Service_Content(String Email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Gosu_Detail_Join_Service gosu_Detail_Join_Service = null;
		
		try {
			conn = ds.getConnection();
			String sql= "SELECT d.D_CODE, d.D_NAME, s.S_CODE, s.S_NAME, g.PR FROM G_DETAIL d JOIN G_SERVICE s ON d.s_code = s.s_code JOIN G_REGISTER g ON d.D_CODE = g.D_CODE WHERE EMAIL=? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, Email);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				int D_CODE = rs.getInt("D_CODE");
				String D_NAME = rs.getString("D_NAME");
				int S_CODE = rs.getInt("S_CODE");
				String S_NAME = rs.getString("S_NAME");
				String PR = rs.getString("PR");
				
				gosu_Detail_Join_Service = new Gosu_Detail_Join_Service(D_CODE, D_NAME, S_CODE,S_NAME, PR);
						
			}
			
		} catch (Exception e) {
			System.out.println("Detail Error: " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환하기
			} catch (Exception e2) {
				
			}
		}
		
		return gosu_Detail_Join_Service;
	}
}

































