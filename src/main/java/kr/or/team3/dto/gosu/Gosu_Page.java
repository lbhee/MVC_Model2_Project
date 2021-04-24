package kr.or.team3.dto.gosu;

public class Gosu_Page {
	private String email;
	private String name;
	private String pr;
	private String area;
	private String calltime;
	private int hire_num;
	private String payment;
	private String photo;
	private String license;
	private String career;
	private String d_name;
	private String s_name;
	
	public Gosu_Page() {
	}

	public Gosu_Page(String name, String pr, String area, String calltime, int hire_num, String payment,
			String photo, String license, String career, String d_name, String s_name) {
		this.name = name;
		this.pr = pr;
		this.area = area;
		this.calltime = calltime;
		this.hire_num = hire_num;
		this.payment = payment;
		this.photo = photo;
		this.license = license;
		this.career = career;
		this.d_name = d_name;
		this.s_name = s_name;
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

	public String getPr() {
		return pr;
	}

	public void setPr(String pr) {
		this.pr = pr;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCalltime() {
		return calltime;
	}

	public void setCalltime(String calltime) {
		this.calltime = calltime;
	}

	public int getHire_num() {
		return hire_num;
	}

	public void setHire_num(int hire_num) {
		this.hire_num = hire_num;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getCareer() {
		return career;
	}

	public void setCareer(String career) {
		this.career = career;
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

	@Override
	public String toString() {
		return "Gosu_Page [email=" + email + ", name=" + name + ", pr=" + pr + ", area=" + area + ", calltime="
				+ calltime + ", hire_num=" + hire_num + ", payment=" + payment + ", photo=" + photo + ", license="
				+ license + ", career=" + career + "]";
	}
	
	
	
}
