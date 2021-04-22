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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import kr.or.team3.dto.member.Member;
import kr.or.team3.dto.member.RQ_Form;

public class MemberDao {
DataSource ds = null;
	
	public MemberDao() throws NamingException {
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
			
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				
			}
			
		}
		
		return row;
	}
	
    // 로그인
	public boolean loginOk(Member memberdata, HttpServletRequest request) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		ResultSet rs = null;
		
		HttpSession session = request.getSession();

		
		try {
			conn = ds.getConnection();
			String sql = "select name,pwd from member where email = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberdata.getEmail());
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("pwd").equals(memberdata.getPwd())) {
					session.setAttribute("ID", memberdata.getEmail());
					result = true;
				} else {
					result = false;
				}
			}
		
		} catch(Exception e) {
			e.getMessage();
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e2) {
				
			}
		}
		return result;
		
	}
	
	
	
	
	//요청서 보내기 by 안승주 21.04.19
	public int sendRQ_Form(RQ_Form RQdata) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into RQ_Form(num, title, content, writedate, hopedate, phone, done, m_email, g_email, G_code) "
					+ "values(rqnum.nextval,?,?,sysdate,?,?,0,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, RQdata.getTitle());
			pstmt.setString(2, RQdata.getContent());
			pstmt.setDate(3, RQdata.getHopedate());
			pstmt.setString(4,RQdata.getPhone());
			pstmt.setString(5, RQdata.getM_mail());
			pstmt.setString(6, RQdata.getG_email());
			pstmt.setInt(7, RQdata.getG_code());
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		return row;
	}
	//고객이 고수에게 보낸 요청서 가져오기 by 안승주 21.04.19 수정 21.04.21
	public List<RQ_Form> getRQ_Form_Member(int cpage, int pagesize, String M_email) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RQ_Form> list = null;
		
		try {
			conn = ds.getConnection();
			String sql = 	"SELECT *" +
					"FROM (" +
							"SELECT rownum AS rnum, rq.*" +
							"FROM (" +
									"SELECT * " +
									"FROM RQ_FORM " +
									"WHERE done = 0 AND M_EMAIL = ?" +
									"ORDER BY num DESC) rq " +
									"WHERE rownum <= ?" +//end
						") n WHERE rnum >= ?"; //start
			
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize -1); 	// 1 * 5 - (5 - 1 ) >> 1 
			int end = cpage * pagesize; 					// 1 * 5 >> 5
			
			
			pstmt.setString(1,M_email);
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
			e.getMessage();
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.getMessage();
				e2.printStackTrace();
			}
		}
		return list;
	}
	
	//고객이나 고수가 요청완료 요청시
	public boolean completeRQ(int G_code) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			String sql = "update RQ_Form set done = 1 where G_code = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, G_code);
			
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
	
	
	// 로그인 완료 했을때 함수 
		public boolean loginOk(String email , String pwd) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			boolean result = false;
			ResultSet rs = null;
			try {
				conn = ds.getConnection();
				String sql = "select pwd from member where email = ? ";
				pstmt.setString(1, email);
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					if(rs.getString("pwd").equals(pwd)) {
						result = true;
					} else {
						result = false;
					}
				}
			} catch(Exception e) {
				e.getMessage();
			} finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch(Exception e2) {
					
				}
			}
			return result;
			
		}
		
		//회원정보 수정
		public int memberEdit(Member member, String change) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			
	
			int row = 0;
			
			try {
				conn = ds.getConnection();
				
				if(change.equals("userinfo")) {
					String sql = "update member set name=?, adr=? where email = ?";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1,member.getName());
					pstmt.setString(2,member.getAdr());
					pstmt.setString(3,member.getEmail());
				}else if(change.equals("pwd")) {
					String sql = "update member set pwd=? where email = ?";
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1,member.getPwd());
					pstmt.setString(2,member.getEmail());
				}
				
				row = pstmt.executeUpdate();
				
			} catch(Exception e) {
				e.getMessage();
			} finally {
				try {
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					
				}
			}
			
			return row;
		}
		
		// 해당 이메일에 맞는 회원 정보 가져오기 
		public Member getContent(String Email) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Member member = null;
			
			try {
				conn = ds.getConnection();
				String sql="select email,name,pwd,adr from member where email=?"; //* 하지 말자
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, Email);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					String name = rs.getString("name");
					String email = rs.getString("email");
					String pwd = rs.getString("pwd");
					String adr = rs.getString("adr");
					
					
					member = new Member(email, name, pwd, adr);
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
			
			return member;
		}
}
