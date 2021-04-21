package kr.or.team3.dto.gosu;

public class Gosu_Register {
	
	private String email; //이메일(참조)
	private int g_code;   //고수확인코드
	private String pr;    //자기소개
	private int d_code;   //상세서비스코드(참조)
	
	
	
	public Gosu_Register() {
		
	}

	
	
	public Gosu_Register(String email, String pr, int d_code) {
		super();
		this.email = email;
		this.pr = pr;
		this.d_code = d_code;
	}



	public Gosu_Register(String email, int g_code, String pr, int d_code) {
		super();
		this.email = email;
		this.g_code = g_code;
		this.pr = pr;
		this.d_code = d_code;
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
	public String getPr() {
		return pr;
	}
	public void setPr(String pr) {
		this.pr = pr;
	}
	
	
	public int getD_code() {
		return d_code;
	}
	public void setD_code(int d_code) {
		this.d_code = d_code;
	}
	
	
}
