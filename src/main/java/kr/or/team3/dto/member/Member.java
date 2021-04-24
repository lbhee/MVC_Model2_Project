package kr.or.team3.dto.member;

public class Member {

	private String email;  //이메일
	private String name;   //이름
	private String pwd;    //비밀번호
	private String adr;    //주소
	private String d_name; //상세 서비스
	private String s_name; //서비스
	
	public Member() {
	}

	public Member(String email, String name, String pwd, String adr) {
		this.email = email;
		this.name = name;
		this.pwd = pwd;
		this.adr = adr;
	}
	
	public Member(String email, String adr, String name, String s_name, String d_name) {
		this.email = email;
		this.adr = adr;
		this.name = name;
		this.s_name = s_name;
		this.d_name = d_name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getAdr() {
		return adr;
	}
	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getD_name() {
		return d_name;
	}

	public void setD_name(String d_name) {
		this.d_name = d_name;
	}

	public String getS_name() {
		return s_name;
	}

	public void setS_name(String s_name) {
		this.s_name = s_name;
	}

}
