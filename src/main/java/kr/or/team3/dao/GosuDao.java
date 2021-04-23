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
import kr.or.team3.dto.member.RQ_Content_Member;
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
	public List<RQ_Form> getRQList_Gosu(int cpage, int pagesize, String G_email){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RQ_Form> list = null;
		
		try {
			conn = ds.getConnection();
			String spl = "SELECT *" +
					"FROM (" +
					"SELECT rownum AS rnum, rq.*" +
					"FROM (" +
							"SELECT * " +
							"FROM RQ_FORM " +
							"WHERE done = 0 AND G_EMAIL = ?" +
							"ORDER BY num DESC) rq " +
							"WHERE rownum <= ?" +//end
				") n WHERE rnum >= ?"; //start;
			pstmt = conn.prepareStatement(spl);
			
			int start = cpage * pagesize - (pagesize -1); 	// 1 * 5 - (5 - 1 ) >> 1 
			int end = cpage * pagesize; 					// 1 * 5 >> 5
			
			
			pstmt.setString(1,G_email);
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<RQ_Form>();
			
			while(rs.next()) {
				RQ_Form rq_Form = new RQ_Form();
				rq_Form.setNum(rs.getInt("num"));
				rq_Form.setTitle(rs.getString("title"));
				rq_Form.setContent(rs.getString("content"));
				rq_Form.setWritedate(rs.getDate("writedate"));
				rq_Form.setHopedate(rs.getDate("hopedate"));
				rq_Form.setPhone(rs.getString("phone"));
				rq_Form.setDone(rs.getInt("done"));
				rq_Form.setM_mail(rs.getString("M_email"));
				rq_Form.setG_email(rs.getString("G_email"));
				rq_Form.setG_code(rs.getInt("G_code"));
				
				list.add(rq_Form);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}
	
	//고수가 받은 요청서 지우기 by 안승주 21.04.23
	public boolean cancel_RQ_Gosu(int G_code, String G_email, String M_email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			String sql = "update RQ_Form set done = 3 where G_code = ? and G_email = ? and M_email = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, G_code);
			pstmt.setString(2, G_email);
			pstmt.setString(3, M_email);
			
			int row = pstmt.executeUpdate();
			if(row > 0) {
				result = true;
			}
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
		return result;
		
	}
	
	//고수가 받은 요청서 완료 by 안승주 21.04.23
	
	public boolean complet_RQ_Gosu(int G_code, String G_email, String M_email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			String sql = "update RQ_Form set done = 2 where G_code = ? and G_email = ? and M_email = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, G_code);
			pstmt.setString(2, G_email);
			pstmt.setString(3, M_email);
			
			int row = pstmt.executeUpdate();
			if(row > 0) {
				result = true;
			}
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
		return result;
		
	}
	public String getGosuName(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String gosuName = "";
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT m.name FROM RQ_FORM rf JOIN MEMBER m ON rf.G_EMAIL = m.EMAIL WHERE rf.NUM = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				gosuName = rs.getString("name");
			}
		} catch (Exception e) {
			// TODO: handle exception
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
		
		
		
		return gosuName;
	}
	
 // 이
//	public List<RQ_Form> get_RQlist_Gosu(String g_email, int g_code){
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		List<RQ_Form> list = null;
//		
//		try {
//			conn = ds.getConnection();
//			String spl = "SELECT r.TITLE , r.CONTENT, r.WRITEDATE , r.HOPEDATE , r.PHONE , r.M_EMAIL , r.G_CODE ,m.NAME"
//					+ "FROM RQ_FORM r JOIN MEMBER m ON r.m_email = m.EMAIL"
//					+ "WHERE r.G_EMAIL = ? AND r.G_CODE = ? AND  r.done = 0"
//					+ "ORDER BY r.num desc;";
//			pstmt = conn.prepareStatement(spl);
//			
//			pstmt.setString(1, g_email);
//			pstmt.setInt(2, g_code);
//			
//			rs = pstmt.executeQuery();
//			list = new ArrayList<RQ_Form>();
//			
//			while(rs.next()) {
//				RQ_Form rq_form = new RQ_Form();
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		return null;
//	}
	
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
		
		return totalgosucount;
	}
	// 고객이 고수에게 보낸 요청서 콘텐츠 가져오기 by 안승주 21.04.23
			@SuppressWarnings("resource")
			public RQ_Content_Member getRQContent_Member(int num) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				RQ_Content_Member content = null;
				
				try {
					content = new RQ_Content_Member();
					conn = ds.getConnection();
					String sql1 = "SELECT rf.NUM ,rf.TITLE , rf.CONTENT , rf.WRITEDATE ,rf.HOPEDATE, m.NAME, rf.M_EMAIL FROM RQ_FORM rf JOIN MEMBER m ON rf.M_EMAIL = m.EMAIL WHERE rf.num = ?";
					String sql2 = "SELECT m.name , m.ADR FROM RQ_FORM rf JOIN MEMBER m ON rf.G_EMAIL = m.EMAIL WHERE rf.NUM = ?";
					String sql3 = "SELECT gs.S_NAME ,gd.D_NAME FROM RQ_FORM rf JOIN G_REGISTER gr ON rf.G_EMAIL = gr.EMAIL JOIN G_DETAIL gd ON gr.D_CODE = gd.D_CODE JOIN G_SERVICE gs ON gd.S_CODE = gs.S_CODE WHERE rf.num = ?";
					pstmt = conn.prepareStatement(sql1);
					pstmt.setInt(1,num);
					System.out.println("4");
					rs = pstmt.executeQuery();
					if(rs.next()) {
						content.setNum(rs.getInt("num"));
						content.setTitle(rs.getString("title"));
						content.setContent(rs.getString("content"));
						content.setWritedate(rs.getDate("writedate"));
						content.setHopedate(rs.getDate("hopedate"));
						content.setMemberName(rs.getString("name"));
						content.setG_email(rs.getString("G_email"));
					}
					System.out.println("1");
					pstmt = conn.prepareStatement(sql2);
					System.out.println("2");
					pstmt.setInt(1, num);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						System.out.println(rs.getString("name"));
						content.setGosuName(rs.getString("name"));
						content.setAdr(rs.getString("adr"));
					}
					pstmt = conn.prepareStatement(sql3);
					pstmt.setInt(1, num);
					rs = pstmt.executeQuery();
					if(rs.next()){
						String sname = rs.getString("s_name");//운동
						String dname = rs.getString("d_name");//유산소
						System.out.println(sname);
						System.out.println(dname);
						String subject = sname + "/" + dname;
						content.setSubject(subject);
					}
				} catch (Exception e) {
					e.printStackTrace();
					e.getMessage();
				}finally {
					try {
						pstmt.close();
						rs.close();
						conn.close();
					} catch (Exception e2) {
						e2.printStackTrace();
						e2.getMessage();
					}
				}
				return content;
			}
	
// --------------- 이
}

































