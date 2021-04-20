package kr.or.team3.dto.notice;

import java.sql.Date;

public class Notice {
	private int num;        //글번호
	private String title;   //글제목
	private String content; //글내용
	private Date writedate; //작성일
	private int count;      //조회수
	private String email;   //이메일(참조)
	private int g_number;	//고수고유코드
	
	public Notice() {
	}
	
	public Notice(int num, String title, String content, Date writedate, int count, String email, int g_number) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.count = count;
		this.email = email;
		this.g_number = g_number;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getG_number() {
		return g_number;
	}
	public void setG_number(int g_number) {
		this.g_number = g_number;
	}
	
}
