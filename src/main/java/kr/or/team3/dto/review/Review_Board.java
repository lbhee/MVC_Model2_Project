package kr.or.team3.dto.review;

import java.sql.Date;

public class Review_Board {
	
	private int num;         //글번호
    private String title;    //글제목
    private String content;  //글내용
    private Date writedate;  //작성일
    private int grade;       //평점
    private int count;       //조회수
    private int refer;
    private int depth;
    private int step; 
    private String m_email;    //요청자이메일
    private String g_email;    //고수이메일
    private int g_code;   	   //고수고유코드
   
	public Review_Board() {
	}
	
	public Review_Board(int num, String title, String content, Date writedate, int grade, int count, int refer, int depth,
			int step, String m_email, String g_email, int g_code) {
		
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.grade = grade;
		this.count = count;
		this.refer = refer;
		this.depth = depth;
		this.step = step;
		this.m_email = m_email;
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
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
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
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
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