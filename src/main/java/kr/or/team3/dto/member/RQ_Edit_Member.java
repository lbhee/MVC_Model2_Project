package kr.or.team3.dto.member;

import java.sql.Date;

public class RQ_Edit_Member {
	private int num;
	private String title;
	private String content;
	private Date hopedate;
	private String phone;
	
	public RQ_Edit_Member() {
		
	}

	public RQ_Edit_Member(int num, String title, String content, Date hopedate, String phone) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.hopedate = hopedate;
		this.phone = phone;
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

	public Date getHopedate() {
		return hopedate;
	}

	public void setHopedate(Date hopedate) {
		this.hopedate = hopedate;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "RQ_Edit_Member [num=" + num + ", title=" + title + ", content=" + content + ", hopedate=" + hopedate
				+ ", phone=" + phone + "]";
	}
	
	
	
}
