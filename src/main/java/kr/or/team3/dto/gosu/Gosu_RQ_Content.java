package kr.or.team3.dto.gosu;

import java.sql.Date;

public class Gosu_RQ_Content {
	private int num;
	private String title;
	private String content;
	private Date writedate;
	private Date hopedate;
	private String memberName;
	private String gosuName;
	private String phone;
	private String M_email;
	
	
	
	public Gosu_RQ_Content() {
	}
	
	
	
	
	public Gosu_RQ_Content(int num, String title, String content, Date writedate, Date hopedate, String memberName,
			String gosuName, String phone, String m_email) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.hopedate = hopedate;
		this.memberName = memberName;
		this.gosuName = gosuName;
		this.phone = phone;
		this.M_email = m_email;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public Date getHopedate() {
		return hopedate;
	}
	public void setHopedate(Date hopedate) {
		this.hopedate = hopedate;
	}
	public String getGosuName() {
		return gosuName;
	}
	public void setGosuName(String gosuName) {
		this.gosuName = gosuName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getM_email() {
		return M_email;
	}
	public void setM_email(String m_email) {
		M_email = m_email;
	}
	@Override
	public String toString() {
		return "Gosu_RQ_Content [num=" + num + ", title=" + title + ", content=" + content + ", writedate=" + writedate
				+ ", hopedate=" + hopedate + ", gosuName=" + gosuName + ", phone=" + phone + ", M_email=" + M_email + "]";
	}
	
	
	

}
/*
 * 고수가 받는 요청서 목록
1. Num
2. Title
3. Content
4. writedate
5. Hopedate
6. 고수 이름
7. 요청자 이름
8. 요청자 전화번호
9. 요청자 이메일

 */