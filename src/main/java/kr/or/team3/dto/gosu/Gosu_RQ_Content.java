package kr.or.team3.dto.gosu;

import java.sql.Date;

public class Gosu_RQ_Content {
	private int num;
	private String title;
	private String conent;
	private Date writedate;
	private Date hopedate;
	private String gosuName;
	private String phon;
	private String M_email;
	
	
	public Gosu_RQ_Content(int num, String title, String conent, Date writedate, Date hopedate, String gosuName,
			String phon, String m_email) {
		this.num = num;
		this.title = title;
		this.conent = conent;
		this.writedate = writedate;
		this.hopedate = hopedate;
		this.gosuName = gosuName;
		this.phon = phon;
		this.M_email = m_email;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getConent() {
		return conent;
	}
	public void setConent(String conent) {
		this.conent = conent;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public Date getHopedate() {
		return hopedate;
	}
	public void setHopedate(Date hopedate) {
		this.hopedate = hopedate;
	}
	public String getGosuName() {
		return gosuName;
	}
	public void setGosuName(String gosuName) {
		this.gosuName = gosuName;
	}
	public String getPhon() {
		return phon;
	}
	public void setPhon(String phon) {
		this.phon = phon;
	}
	public String getM_email() {
		return M_email;
	}
	public void setM_email(String m_email) {
		M_email = m_email;
	}
	@Override
	public String toString() {
		return "Gosu_RQ_Content [num=" + num + ", title=" + title + ", conent=" + conent + ", writedate=" + writedate
				+ ", hopedate=" + hopedate + ", gosuName=" + gosuName + ", phon=" + phon + ", M_email=" + M_email + "]";
	}
	
	
	

}
/*
 * 고수가 받는 요청서 목록
1. Num
2. Title
3. Content
4. writedate
5. Hopedate
6. 고수 이름
7. 요청자 이름
8. 요청자 전화번호
9. 요청자 이메일

 */