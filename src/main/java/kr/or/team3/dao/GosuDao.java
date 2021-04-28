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
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import kr.or.team3.dto.QnABoard.QnA_Board;
import kr.or.team3.dto.gosu.Gosu_Detail_Join_Service;
import kr.or.team3.dto.gosu.Gosu_Info_Add;
import kr.or.team3.dto.gosu.Gosu_Info_Basic;
import kr.or.team3.dto.gosu.Gosu_Page;
import kr.or.team3.dto.gosu.Gosu_RQ_Content;
import kr.or.team3.dto.gosu.Gosu_Register;
import kr.or.team3.dto.member.Member;

import kr.or.team3.dto.member.RQ_Content_Member;
import kr.or.team3.dto.member.RQ_Form;
import kr.or.team3.dto.notice.Notice;
import kr.or.team3.dto.review.Review_Board;


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
		return list;
	}
	
	   //고수가 받은 고객의 수락 및 삭제된 요청서목록 by 안승주 21.04.21
		public List<RQ_Form> get_RqDoneList_Gosu(int cpage, int pagesize, String G_email){
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
								"WHERE done != 0 AND G_EMAIL = ?" +
								"ORDER BY num DESC) rq " +
								"WHERE rownum <= ?" +
					") n WHERE rnum >= ?"; 
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
			return list;
		}
	

	//고수가 받은 요청서 지우기 by 안승주 21.04.23
	public boolean delete_RQ_Gosu(int num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			String sql = "update RQ_Form set done = 2 where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
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
	@SuppressWarnings("resource")
	public boolean complet_RQ_Gosu(int num, String g_email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			String sql = "update RQ_Form set done = 1 where num = ?";
			String sql2 = "update g_info_basic set HIRE_NUM = HIRE_NUM +1 WHERE EMAIL = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			
			int row = pstmt.executeUpdate();
			if(row > 0) {
				try {
					
					pstmt = conn.prepareStatement(sql2);
					pstmt.setString(1, g_email);
					
					row = pstmt.executeUpdate();
					
					if(row > 0) {
						result = true;
					}
					
				} catch (Exception e) {
					
				}
			}
		}catch (Exception e) {		
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
	
	public List<Gosu_Register> searchgosu(String d_code){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Gosu_Register> gosulist = null;
	    
	    String D_Code = d_code + "%";
	    
		try {
			conn = ds.getConnection();
			String sql = "select g_register.pr, member.name, member.email, gb.PHOTO from member join g_register on g_register.email = member.email" 
					    +" JOIN G_INFO_BASIC gb ON MEMBER.EMAIL = gb.EMAIL where d_code like ?";
			
			pstmt = conn.prepareStatement(sql);
		
			pstmt.setString(1, D_Code);
			
			rs = pstmt.executeQuery();
			
			gosulist = new ArrayList<Gosu_Register>();

			while(rs.next()) {
				String pr = rs.getString("pr");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String photo = rs.getString("photo");
				
				Gosu_Register gosuregister = new Gosu_Register(email,0,pr,0,name,photo);
				
				gosulist.add(gosuregister);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	

	//고수찾기(지도)
	public List<Member> gosumap(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Member> gosumaplist = null;
		Member member = null;
	    
		try {
			conn = ds.getConnection();
			String sql = "select m.email, m.adr, m.name, gs.s_name, gd.d_name from g_register g join member m on g.email = m.email "
					   + "join g_detail gd on g.d_code = gd.d_code join g_service gs on gs.s_code = gd.s_code";
		
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			gosumaplist = new ArrayList<Member>();

			while(rs.next()) {
				String email = rs.getString("email");
				String adr = rs.getString("adr"); //고수주소
				String name = rs.getString("name"); //고수이름
				String s_name = rs.getString("s_name"); //서비스
				String d_name = rs.getString("d_name"); //상세서비스
				
				member = new Member(email, adr, name, s_name, d_name);
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
			public Gosu_RQ_Content getRQContent_Member(int num) {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				Gosu_RQ_Content content = null;
				
				try {
					content = new Gosu_RQ_Content();
					conn = ds.getConnection();
					String sql1 = "SELECT rf.NUM ,rf.TITLE , rf.CONTENT , rf.WRITEDATE ,rf.HOPEDATE, m.NAME, rf.M_EMAIL , rf.PHONE FROM RQ_FORM rf JOIN MEMBER m ON rf.M_EMAIL = m.EMAIL WHERE rf.num = ?";
					String sql2 = "SELECT m.name FROM RQ_FORM rf JOIN MEMBER m ON rf.G_EMAIL = m.EMAIL WHERE rf.NUM = ?";
					pstmt = conn.prepareStatement(sql1);
					pstmt.setInt(1, num);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						content.setNum(rs.getInt("num"));
						content.setTitle(rs.getString("title"));
						content.setContent(rs.getString("content"));
						content.setWritedate(rs.getDate("writedate"));
						content.setHopedate(rs.getDate("hopedate"));
						content.setMemberName(rs.getString("name"));
						content.setM_email(rs.getString("m_email"));
						content.setPhone(rs.getString("phone"));
						
					}
					
					pstmt = conn.prepareStatement(sql2);
					pstmt.setInt(1, num);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						
						content.setGosuName(rs.getString("name"));
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
				
				gosu_Detail_Join_Service = new Gosu_Detail_Join_Service(D_CODE, D_NAME, S_CODE, S_NAME, PR);
						
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


	//고수페이지
	public List<Gosu_Page> gosupage(String email){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Gosu_Page> gosuinfo = null;
		Gosu_Page gosupage = null;
	    
		try {
			conn = ds.getConnection();
			String sql = "select m.name, g.pr, gb.area, gb.calltime, gb.hire_num, gb.payment, gb.photo, ga.license, ga.career, gd.d_name, gs.s_name "
					   + "from member m join g_register g on m.email = g.email  join g_info_basic gb on m.email = gb.email "
					   + "join g_info_add ga on m.email = ga.email join g_detail gd on gd.d_code = g.d_code join g_service gs on gd.s_code = gs.s_code "
					   + "where m.email = ?";
		
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			gosuinfo = new ArrayList<Gosu_Page>();

			while(rs.next()) {

				String name = rs.getString("name"); //고수이름
				String pr = rs.getString("pr"); //자기소개
				String area = rs.getString("area"); //활동지역
				String calltime = rs.getString("calltime"); //연락가능시간
				int hire_num = rs.getInt("hire_num"); //고용횟수
				String payment = rs.getString("payment"); //결재수단
				String photo = rs.getString("photo"); //사진
				String license = rs.getString("license"); //자격증
				String career = rs.getString("career"); //경력
				String d_name = rs.getString("d_name"); //상세서비스이름
				String s_name = rs.getString("s_name"); //서비스이름
				
				gosupage = new Gosu_Page(name, pr, area, calltime, hire_num, payment, photo, license, career, d_name, s_name);
				
				gosuinfo.add(gosupage);
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
		return gosuinfo;
	}
	// 고수가 받은 요청서 건수 by 안승주 21.04.25
	public int totalRQMemberCount(String G_email) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalrqmembercount = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "select count(*) cnt from RQ_Form where g_email = ? and done = 0";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, G_email);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalrqmembercount = rs.getInt("cnt");
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
		
		
		return totalrqmembercount;
	}
	
	
	//자주하는 질문 글쓰기
	public int QnAWrite(QnA_Board qna) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		String sql = "insert into qna(num, title, content, g_email, g_code) values(qna_num.nextval, ?, ?, ?, 10000)";
		try {
			conn = ds.getConnection();
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, qna.getTitle());
			pstmt.setString(2, qna.getContent());
			pstmt.setString(3, qna.getG_email());
			
			row = pstmt.executeUpdate();

		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return row;
	}
	
	//자주하는 질문 내용보기
	public List<QnA_Board> Qna(String Email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<QnA_Board> qna = null;
		QnA_Board qnaboard = null;
		try {
			conn = ds.getConnection();
			String sql= "select title, content from qna where g_email = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, Email);
			
			qna = new ArrayList<QnA_Board>();
			
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				
				String title = rs.getString("title");
				String content = rs.getString("content");
				
					
				qnaboard = new QnA_Board(title, content, null, null);
				
				qna.add(qnaboard);
				
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
		
		return qna;
	}
	
	//자주하는 질문 수정하기(내용보기)
	public QnA_Board QnaEditSelect(String email) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		QnA_Board qnaboard = null;
		
		try {
			conn = ds.getConnection();
			
			String sql_select = "select title, content, writedate, g_email from qna where g_email = ?";
			
			pstmt = conn.prepareStatement(sql_select);
			
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String title = rs.getString("title");
				String writedate = rs.getString("writedate");
				String content = rs.getString("content");
				String g_email = rs.getString("g_email");
				
				qnaboard = new QnA_Board(title, content, writedate, g_email);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환하기
			} catch (Exception e2) {
				
			}
		}
		
		
		return qnaboard;
	}
	
	
	//자주하는 질문 수정하기
	public int QnaEdit(QnA_Board qnaboard) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int row =0;
		try {
			conn = ds.getConnection();

			String sql_update = "update qna set content = ? where g_email = ?";

			pstmt = conn.prepareStatement(sql_update);

			pstmt.setString(1, qnaboard.getContent());
			pstmt.setString(2, qnaboard.getG_email().trim());
			
			row = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println("Detail Error: " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				conn.close();//반환하기
			} catch (Exception e2) {
				
			}
		}
		
		return row;
	}
	
	// 공지사항 글쓰기
		public int NoticeWrite(Notice notice) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			int row = 0;
			
			String sql = "insert into notice(num,title,content,G_EMAIL,G_CODE,FILENAME) values(notice_num.nextval,?,?,?,10000,?)";
			try {
				conn = ds.getConnection();
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, notice.getTitle());
				pstmt.setString(2, notice.getContent());
				pstmt.setString(3, notice.getEmail());
				pstmt.setString(4, notice.getFilename());
				
				row = pstmt.executeUpdate();

			} catch (SQLException e) {
				
				e.printStackTrace();
			} finally {
				try {
					pstmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return row;
		}
		// 고용횟수 by 안승주
		public int hireCount(String G_email) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int count = 0;
			
			try {
				conn = ds.getConnection();
				String sql = "SELECT count(*) cnt FROM rq_form WHERE done = 1 AND g_email = ?";
				String sql2 = "";
				
				pstmt  = conn.prepareStatement(sql);
				
				pstmt.setString(1, G_email);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){;
					count = rs.getInt("cnt");
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
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
			
			
			return count;
			
		}
		

		//공지사항 내용보기
		public List<Notice> Notice(String Email) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Notice> list = new ArrayList<Notice>();
			
			
			try {
				conn = ds.getConnection();
				String sql= "select num, title,WRITEDATE from notice where g_email = ? ";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, Email);
				
				rs = pstmt.executeQuery();
				while(rs.next()) {
					int num = rs.getInt("num");
					String title = rs.getString("title");
					String writedate = rs.getString("writedate");
					
					Notice notice = new Notice(title, num, writedate);
				

					list.add(notice);
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
			
			return list;
		}
		
		//공지사항 상세보기 & 수정하기(내용보기)
		public Notice NoticeContent(int num) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			Notice notice = null;
			
			try {
				conn = ds.getConnection();
				
				String sql_select = "select num, title, content, writedate, g_email, filename from notice where num = ?";
				
				pstmt = conn.prepareStatement(sql_select);
				
				pstmt.setInt(1, num);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					String title = rs.getString("title");
					String writedate = rs.getString("writedate");
					String content = rs.getString("content");
					int noticenum = rs.getInt("num");
					String filname = rs.getString("filename");
					String g_email = rs.getString("g_email");
					
					notice = new Notice(noticenum, title, content, writedate, g_email, filname);
					
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close();//반환하기
				} catch (Exception e2) {
					
				}
			}
			
			
			return notice;
		}

		//공지사항 수정하기
		public int NoticeEdit(Notice notice) {
			System.out.println("dao 공지수정");
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			int row = 0;
			
			try {
				conn = ds.getConnection();
				String sql = "update notice set title = ? , content = ?, filename = ? where num = ?";
				
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, notice.getTitle());
				pstmt.setString(2, notice.getContent());
				pstmt.setString(3, notice.getFilename().trim());
				pstmt.setInt(4, notice.getNum());
				
				
				System.out.println(notice.getTitle());
				System.out.println(notice.getContent());
				System.out.println(notice.getFilename());
				
				row = pstmt.executeUpdate();
				
				
			} catch(Exception e) {
				System.out.println("Detail Error: " + e.getMessage());
			} finally {
				try {
					pstmt.close();
					conn.close();//반환하기
				} catch (Exception e2) {
					System.out.println("Detail Error: " + e2.getMessage());
				}
			}
			
			return row;
		}
		
		//공지사항 삭제하기
		public int noticeDeleteOk(int num) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			int row = 0;
			
			try {
				conn = ds.getConnection();
				String sql = "delete from notice where num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, num);
				row = pstmt.executeUpdate();
			
			} catch (SQLException e) {				
				e.printStackTrace();
				
			} finally {
				try {
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					e2.getMessage();
				}
			}
			return row;
		}	
}