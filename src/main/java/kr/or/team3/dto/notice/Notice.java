package kr.or.team3.dto.notice;

public class Notice {
	private int num;        //글번호
	private String title;   //글제목
	private String content; //글내용
	private String writedate; //작성일
	private String email;   //이메일(참조)
	private int g_code;	//고수고유코드
	private String filename;
	
	public Notice() {
	}
	
	public Notice(int num) {
		this.num = num;
	}

	public Notice(String title) {
		this.title = title;
	}
	
	public Notice(String title, int num) {
		this.title = title;
		this.num = num;
	}
	public Notice(String title, int num, String writedate) {
		this.title = title;
		this.num = num;
		this.writedate = writedate;
	}

	
	public Notice(int num, String title, String content, String filename) {
		this.num = num;
		this.title = title;
		this.content = content;
		this.filename = filename;
	}



	public Notice(int num, String title, String content, String writedate, String email, String filename) {
		super();
		this.num = num;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.email = email;
		this.filename = filename;
	}



	public Notice(int num, String title, String content, String writedate, String email, int g_code, String filename) {
		this.num = num;
		this.title = title;
		this.content = content;
		this.writedate = writedate;
		this.email = email;
		this.g_code = g_code;
		this.filename = filename;
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

	public String getWritedate() {
		return writedate.substring(0, 10);
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}



	@Override
	public String toString() {
		return "Notice [filename=" + filename + "]";
	}

	
}
