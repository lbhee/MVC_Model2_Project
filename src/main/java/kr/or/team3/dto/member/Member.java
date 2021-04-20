package kr.or.team3.dto.member;

public class Member {
	
	private String email; //이메일
	private String name;  //이름
	private String pwd;   //비밀번호
	private String adr;   //주소
	
	public Member() {
		
	}


	public Member(String email, String name, String pwd, String adr) {
		super();
		this.email = email;
		this.name = name;
		this.pwd = pwd;
		this.adr = adr;
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
	
	
	
}
