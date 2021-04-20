package kr.or.team3.dto.notice;

import java.sql.Date;

public class Notice {
	
	private int num;        //글번호
	private String title;   //글제목
	private String content; //글내용
	private Date writedate; //작성일
	private int count;      //조회수
	private String g_email; //고수이메일
	private int g_code;     //고수고유코드
	
	public Notice() {
		
	}
	
	public Notice(int num, String title, String content, Date writedate, int count, String g_email, int g_code) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.count = count;
		this.g_email = g_email;
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
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getG_email() {
		return g_email;
	}
	public void setG_email(String g_email) {
		this.g_email = g_email;
	}
	public int getG_code() {
		return g_code;
	}
	public void setG_code(int g_code) {
		this.g_code = g_code;
	}
	
}
