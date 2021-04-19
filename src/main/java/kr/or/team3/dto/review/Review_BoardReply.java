package kr.or.team3.dto.review;

import java.sql.Date;

public class Review_BoardReply {
	private int re_num;      //댓글번호
	private String content;  //내용
	private Date writedate;  //작성일
	private int num;         //글번호(참조)
	private String email;    //이메일(참조)
	
	public Review_BoardReply() {
	}
	
	public Review_BoardReply(int re_num, String content, Date writedate, int num, String email) {
		super();
		this.re_num = re_num;
		this.content = content;
		this.writedate = writedate;
		this.num = num;
		this.email = email;
	}
	
	public int getRe_num() {
		return re_num;
	}
	public void setRe_num(int re_num) {
		this.re_num = re_num;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
