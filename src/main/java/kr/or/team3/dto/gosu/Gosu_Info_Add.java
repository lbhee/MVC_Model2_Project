package kr.or.team3.dto.gosu;

public class Gosu_Info_Add {
	
	private String email;	 //고수이메일
	private int g_code;      //고수고유코드(참조)
	private String turn;        //순번
	private String career;   //경력
	private String license;  //자격증
	
	public Gosu_Info_Add() {
		
	}

	public Gosu_Info_Add(String email, int g_code, String turn, String career, String license) {
		super();
		this.email = email;
		this.g_code = g_code;
		this.turn = turn;
		this.career = career;
		this.license = license;
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
	public String getTurn() {
		return turn;
	}
	public void setTurn(String turn) {
		this.turn = turn;
	}
	public String getCareer() {
		return career;
	}
	public void setCareer(String career) {
		this.career = career;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
	}
}
