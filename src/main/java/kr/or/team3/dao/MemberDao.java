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
import kr.or.team3.dto.member.RQ_Content_Member;
import kr.or.team3.dto.member.RQ_Edit_Member;
import kr.or.team3.dto.member.RQ_Form;
import kr.or.team3.dto.review.Review_Board;

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
	//고객이 고수에게 보낸 요청서리스트 가져오기 by 안승주 21.04.19 수정 21.04.21
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
	
	//고객이 요청취소시 by 안승주 21.04.23
	public boolean delete_RQ_Member(int num) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			String sql = "update RQ_Form set done = 3 where num = ?";
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
		
		//가입된 총 회원 수 by 안승주 21.04.23
		public int totalMemberCount() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int totalmembercount = 0;
			
			try {
				conn = ds.getConnection();
				String sql = "select count(*) cnt from member";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					totalmembercount = rs.getInt("cnt");
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
			return totalmembercount;
		}
		
		//고객이 쓴 요청서 건수 by 안승주 21.04.23
		public int totalRQMemberCount(String M_email) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int totalrqmembercount = 0;
			
			try {
				conn = ds.getConnection();
				String sql = "select count(*) cnt from RQ_Form where m_email = ? and done = 0";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, M_email);
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
		
		//요청서에서 고객의 이름 가져오기 by 안승주
//		public String getMemberName(int num) {
//			Connection conn = null;
//			PreparedStatement pstmt = null;
//			ResultSet rs = null;
//			String memberName = null;
//			
//			try {
//				conn = ds.getConnection();
//				String sql = "SELECT m.NAME FROM RQ_FORM rf JOIN MEMBER m ON rf.M_EMAIL = m.EMAIL WHERE rf.num = ?";
//				pstmt = conn.prepareStatement(sql);
//				
//				pstmt.setInt(1, num);
//				
//				rs = pstmt.executeQuery();
//				
//				if(rs.next()) {
//					memberName = rs.getString("name");
//				}
//			} catch (Exception e) {
//				// TODO: handle exception
//				e.getMessage();
//			}finally {
//				try {
//					pstmt.close();
//					rs.close();
//					conn.close();
//					
//				} catch (Exception e2) {
//					// TODO: handle exception
//					e2.getMessage();
//				}
//			}
//			return memberName;
//		}
		
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
				String sql1 = "SELECT rf.NUM ,rf.TITLE , rf.CONTENT , rf.WRITEDATE ,rf.HOPEDATE, m.NAME, rf.G_EMAIL FROM RQ_FORM rf JOIN MEMBER m ON rf.M_EMAIL = m.EMAIL WHERE rf.num = ?";
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
				pstmt = conn.prepareStatement(sql2);
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
		// 고객이 보낸 요청서 수정하기 by 안승주 21.04.26
		public int RQ_Form_EditOk(RQ_Edit_Member memberdata) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			int row = 0;
			
			try {
				conn = ds.getConnection();
				String sql = "UPDATE RQ_FORM SET title = ?, CONTENT = ? ,writedate = sysdate, HOPEDATE = ? , PHONE = ? WHERE num = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, memberdata.getTitle());
				pstmt.setString(2, memberdata.getContent());
				pstmt.setDate(3, memberdata.getHopedate());
				pstmt.setString(4, memberdata.getPhone());
				pstmt.setInt(5, memberdata.getNum());
				row = pstmt.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
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
		// 리뷰 게시판(원본) 쓰기 by 안승주 21.04.26
		public int writeReviewBoard(Review_Board reviewdata) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			int row = 0;
			
			try {
				conn = ds.getConnection();
				String sql = "insert into review (num, title, content, writedate, grade, count ,refer, m_email, g_email, g_code) "
						+    "values ( review_num.nextval , ? , ? , sysdate, ? , 0 , ? , ? , ? , 10000)";
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, reviewdata.getTitle());
				pstmt.setString(2, reviewdata.getContent());
				pstmt.setInt(3, reviewdata.getGrade());
				
				int referMax = getMaxRefer();
				int refer = referMax + 1;
				
				pstmt.setInt(4,refer);
				pstmt.setString(5, reviewdata.getM_email());
				pstmt.setString(6, reviewdata.getG_email());
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return row;
		}
		
		
		// 글쓰기 (refer) 값 생성하기 (원본글) by 안승주 21.04.26 
		private int getMaxRefer() {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int refer_max = 0;
			
			try {
				conn = ds.getConnection();
				String sql = "select nvl(max(refer),0) refernum from review";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					refer_max = rs.getInt("refernum");
				}
			} catch (Exception e) {
				e.getMessage();
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			
			return refer_max;
		}
		
		public int reWirteReviewOk(Review_Board reviewdata) {
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int result = 0;
			
			try {
				conn = ds.getConnection();
				
				String refer_depth_step_sal = "select refer, depth, step, from review where num = ?";
				
				String step_update_sql = "update review set step = step + 1 where step > ? and refer = ?";
				
				String rewritereview = "insert into review(num, title, content, writedate, grade, count ,refer, depth, step, m_email, g_email, g_code) "
									 + "values(review_num.nextval , ? , ? , sysdate, ? , 0 , ? , ? , ? , ? , ? , 10000)";
				
				pstmt = conn.prepareStatement(refer_depth_step_sal);
				pstmt.setInt(1, reviewdata.getNum());
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			return 0;
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
