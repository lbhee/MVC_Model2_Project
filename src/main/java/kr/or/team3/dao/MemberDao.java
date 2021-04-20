package kr.or.team3.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
	public String loginOk(Member memberdata) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		ResultSet rs = null;
		String name = null;
		try {
			conn = ds.getConnection();
			String sql = "select name,pwd from member where email = ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memberdata.getEmail());
			rs = pstmt.executeQuery();
			
			
			if(rs.next()) {
				if(rs.getString("pwd").equals(memberdata.getPwd())) {
					result = true;
					name = rs.getString("name");
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
		return name;
		
	}
	
	
	
	
	//요청서 보내기 by 안승주 21.04.19
	public int sendRQ_Form(RQ_Form RQdata) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into RQ_Form(num, title, content, writedate, hopedate, phone, done, email, G_code) "
					+ "values(0,?,?,sysdate,?,?,0,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, RQdata.getTitle());
			pstmt.setString(2, RQdata.getContent());
			pstmt.setDate(3, RQdata.getHopedate());
			pstmt.setString(4,RQdata.getPhone());
			//pstmt.setString(5, RQdata.getEmail());
			pstmt.setInt(6, RQdata.getG_code());
			
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
	//해당 고수에게 보낸 요청서 가져오기 by 안승주 21.04.19
	public List<RQ_Form> getRQ_Form(int cpage, int pagesize, int G_code) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<RQ_Form> list = null;
		
		try {
			conn = ds.getConnection();
			String sql = 	"select * from (select rownum rn, num, title, content, writedate, hopedate, done, email, G_code from "  +
							"(select * from RQ_Form order by num asc where G_code = ? and done = 0)" +
							"where rownum <= ?)" + 	//end row
							"where rn >=?";			//start row
			
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize -1); 	// 1 * 5 - (5 - 1 ) >> 1 
			int end = cpage * pagesize; 					// 1 * 5 >> 5
			
			pstmt.setInt(1, G_code);
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);
			
			while(rs.next()) {
				RQ_Form rq_Form = new RQ_Form();
				rq_Form.setNum(rs.getInt("num"));
				rq_Form.setTitle(rs.getString("title"));
				rq_Form.setContent(rs.getString("content"));
				rq_Form.setWritedate(rs.getDate("writedate"));
				rq_Form.setHopedate(rs.getDate("hopedate"));
				rq_Form.setDone(rs.getInt("done"));
				//rq_Form.setEmail(rs.getString("email"));
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
		public boolean memberEdit(String email) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			boolean result = false;
			
			try {
				conn = ds.getConnection();
				String sql = "update member set name = ? , pwd = ? , addr = ? , where email = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1,"name");
				pstmt.setString(2,"pwd");
				pstmt.setString(3,"addr");
				pstmt.setString(4,email);
				
				int row = pstmt.executeUpdate();
				
				if(row > 0) {
					result = true;
				} else {
					result = false;
				}
				
				
			} catch(Exception e) {
				e.getMessage();
			} finally {
				try {
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					
				}
			}
			
			
			return result;
		}
	
}
