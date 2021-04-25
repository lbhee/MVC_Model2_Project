package kr.or.team3.dto.QnABoard;

public class QnA_Board {
	private int num;        //글번호
	private String title;   //글제목
	private String content; //글내용
	private String wirtedate; //작성일
	private String g_email;   //고수이메일
	private int g_code;       //고수고유코드
	
	public QnA_Board() {
	}

	public QnA_Board(String content) {
		this.content = content;
	}
	
	
	
	public QnA_Board(String title, String content, String wirtedate, String g_email) {
		super();
		this.title = title;
		this.content = content;
		this.wirtedate = wirtedate;
		this.g_email = g_email;
	}

	public QnA_Board(int num, String title, String content, String wirtedate, String g_email, int g_code) {
		
		this.num = num;
		this.title = title;
		this.content = content;
		this.wirtedate = wirtedate;
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
	public String getWirtedate() {
		return wirtedate;
	}
	public void setWirtedate(String wirtedate) {
		this.wirtedate = wirtedate;
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

	@Override
	public String toString() {
		return content ;
	}
	
}
