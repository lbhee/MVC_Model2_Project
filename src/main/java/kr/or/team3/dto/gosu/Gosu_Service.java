package kr.or.team3.dto.gosu;

public class Gosu_Service {
	private int s_code;    //서비스코드
	private String s_name; //서비스이름
	
	public Gosu_Service() {

	}
	
	public Gosu_Service(int s_code, String s_name) {
		super();
		this.s_code = s_code;
		this.s_name = s_name;
	}
	
	public int getS_code() {
		return s_code;
	}
	public void setS_code(int s_code) {
		this.s_code = s_code;
	}
	public String getS_name() {
		return s_name;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	
	
}
