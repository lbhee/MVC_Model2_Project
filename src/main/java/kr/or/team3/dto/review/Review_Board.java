package kr.or.team3.dto.review;



public class Review_Board {
	private String name; //이름
	private int num;         //글번호
    private String content;  //글내용
    private String writedate;  //작성일
    private String m_email;    //요청자이메일
    private String g_email;    //고수이메일
    private int g_code;     //고수고유코드
    private String grade;
    
	public Review_Board() {
	}

	
	
	public Review_Board(String name, String content, String writedate) {
		super();
		this.name = name;
		this.content = content;
		this.writedate = writedate;
	}



	public Review_Board(String name, String m_email) {
		super();
		this.name = name;
		this.m_email = m_email;
	}

	public Review_Board(String g_email ,String m_email, String content, String writedate ) {
		this.g_email = g_email;
		this.m_email = m_email;
		this.content = content;
		this.writedate = writedate;
	}

	public Review_Board(String name, int num, String content, String writedate, String m_email, String g_email, int g_code, String grade) {
		super();
		this.name = name;
		this.num = num;
		this.content = content;
		this.writedate = writedate;
		this.m_email = m_email;
		this.g_email = g_email;
		this.g_code = g_code;
		this.grade = grade;
	}
	
	

	public String getGrade() {
		return grade;
	}



	public void setGrade(String grade) {
		this.grade = grade;
	}



	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWritedate() {
		return writedate;
	}

	public void setWritedate(String writedate) {
		this.writedate = writedate;
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



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
	
  
}