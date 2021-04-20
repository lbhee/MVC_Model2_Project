package kr.or.team3.dto.gosu;

public class Gosu_Info_Basic {
<<<<<<< HEAD
	
	private String email;	  //이메일
	private String payment;   //결재수단
	private String area;      //활동지역
	private int hire_num;     //고용횟수
	private String calltime;  //연락가능시간
	private int g_code;	      //고수고유코드
	
=======
	private String payment; 	// 결재수단
	private String area; 		// 활동지역
	private int hire_num; 		// 고용횟수
	private String calltime; 	// 연락가능시간
	private int g_code; 		// 고수고유코드(참조)
	private String email;		// 고수이메일

>>>>>>> 871714e32a06625d73bf7430fc0d0b8058fbb3ad
	public Gosu_Info_Basic() {
		
	}

<<<<<<< HEAD
	public Gosu_Info_Basic(String email, String payment, String area, int hire_num, String calltime, int g_code) {
=======
	public Gosu_Info_Basic(String payment, String area, int hire_num, String calltime, int g_code, String email) {
>>>>>>> 871714e32a06625d73bf7430fc0d0b8058fbb3ad
		super();
		this.email = email;
		this.payment = payment;
		this.area = area;
		this.hire_num = hire_num;
		this.calltime = calltime;
		this.g_code = g_code;
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public int getHire_num() {
		return hire_num;
	}

	public void setHire_num(int hire_num) {
		this.hire_num = hire_num;
	}

	public String getCalltime() {
		return calltime;
	}

	public void setCalltime(String calltime) {
		this.calltime = calltime;
	}

	public int getG_code() {
		return g_code;
	}

	public void setG_code(int g_code) {
		this.g_code = g_code;
	}
}
