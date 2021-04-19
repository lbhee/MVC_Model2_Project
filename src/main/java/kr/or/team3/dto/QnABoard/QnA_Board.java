package kr.or.team3.dto.QnABoard;

import java.sql.Date;

public class QnA_Board {
	private int num;        //글번호
	private String title;   //글제목
	private String content; //글내용
	private Date wirtedate; //작성일
	private int count;      //조회수
	private int refer;      
	private int depth;
	private int step;
	private String email;   //이메일(참조)
	
	public QnA_Board() {
	}

	public QnA_Board(int num, String title, String content, Date wirtedate, int count, int refer, int depth, int step,
			String email) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.wirtedate = wirtedate;
		this.count = count;
		this.refer = refer;
		this.depth = depth;
		this.step = step;
		this.email = email;
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
	public Date getWirtedate() {
		return wirtedate;
	}
	public void setWirtedate(Date wirtedate) {
		this.wirtedate = wirtedate;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getRefer() {
		return refer;
	}
	public void setRefer(int refer) {
		this.refer = refer;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
