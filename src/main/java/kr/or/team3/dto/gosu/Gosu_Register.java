package kr.or.team3.dto.gosu;

public class Gosu_Register {
	private String email; //이메일(참조)
	private int g_code;   //고수고유코드(참조)
	
	public Gosu_Register() {
	}
	
	public Gosu_Register(String email, int g_code) {
		super();
		this.email = email;
		this.g_code = g_code;
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
	
	
}
