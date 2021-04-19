package kr.or.team3.dto.member;

import java.sql.Date;

public class RQ_Form {
	
	private int num;         //요청번호
	private String title;    //요청제목
	private String content;  //요청내용
	private Date writedate;  //요청한날짜
	private Date hopedate;   //수업희망날짜
	private int done;		 //요청완료 유무 default = 0 >> 요청미완료, 1 >> 요청완료
	private String phone;    //핸드폰번호
	private String email;    //이메일(참조)
	private int g_code;      //고수고유코드(참조)
	
	public RQ_Form() {
	}

	public RQ_Form(int num, String title, String content, Date writedate, Date hopedate, int done, String phone, String email,
			int g_code) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.hopedate = hopedate;
		this.done = done;
		this.phone = phone;
		this.email = email;
		this.g_code = g_code;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getG_code() {
		return g_code;
	}
	public void setG_code(int g_code) {
		this.g_code = g_code;
	}

	public int getDone() {
		return done;
	}

	public void setDone(int done) {
		this.done = done;
	}
}
