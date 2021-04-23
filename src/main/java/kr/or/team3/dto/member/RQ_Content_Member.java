package kr.or.team3.dto.member;

import java.sql.Date;

public class RQ_Content_Member {
	private int num;
	private String title;
	private String content;
	private Date writedate;
	private Date hopedate;
	private String MemberName;
	private String GosuName;
	private String g_email;
	private String adr;
	private String subject;
	/*
	1. 요청번호
	2. Title
	3. Content
	4. Write date
	5. hopedate
	6. 요청자 이름
	7. 고수 이름
	8. 고수 이메일
	9. 고수 활동지역
	10. 배우는 과목
	 */

	public RQ_Content_Member() {
		
	}
	

	public RQ_Content_Member(int num, String title, String content, Date writedate, Date hopedate, String memberName,
			String gosuName, String g_email, String adr, String subject) {
		this.num = num;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.hopedate = hopedate;
		this.MemberName = memberName;
		this.GosuName = gosuName;
		this.g_email = g_email;
		this.adr = adr;
		this.subject = subject;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
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

	public String getMemberName() {
		return MemberName;
	}

	public void setMemberName(String memberName) {
		MemberName = memberName;
	}

	public String getGosuName() {
		return GosuName;
	}

	public void setGosuName(String gosuName) {
		GosuName = gosuName;
	}

	public String getG_email() {
		return g_email;
	}

	public void setG_email(String g_email) {
		this.g_email = g_email;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}


	@Override
	public String toString() {
		return "RQ_Content_Member [num=" + num + ", title=" + title + ", content=" + content + ", writedate="
				+ writedate + ", hopedate=" + hopedate + ", MemberName=" + MemberName + ", GosuName=" + GosuName
				+ ", g_email=" + g_email + ", adr=" + adr + ", subject=" + subject + "]";
	}

	
	
	

}
