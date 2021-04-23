package kr.or.team3.dto.gosu;

public class Gosu_Detail {
	
	private int d_code;    //상세서비스코드
	private String d_name; //상세서비스이름
	private int s_code;    //서비스코드(참조)
	
	public Gosu_Detail() {
	}
	
	public Gosu_Detail(int d_code, String d_name, int s_code) {
		super();
		this.d_code = d_code;
		this.d_name = d_name;
		this.s_code = s_code;
	}
	
	public int getD_code() {
		return d_code;
	}
	public void setD_code(int d_code) {
		this.d_code = d_code;
	}
	public String getD_name() {
		return d_name;
	}
	public void setD_name(String d_name) {
		this.d_name = d_name;
	}
	public int getS_code() {
		return s_code;
	}
	public void setS_code(int s_code) {
		this.s_code = s_code;
	}

	@Override
	public String toString() {
		return "Gosu_Detail [d_code=" + d_code + ", d_name=" + d_name + ", s_code=" + s_code + "]";
	}
	
	
}
