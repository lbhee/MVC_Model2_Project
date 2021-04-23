package kr.or.team3.dto.gosu;

public class Gosu_Detail_Join_Service {
	private int D_CODE;
	private String D_NAME;
	private int S_CODE;
	private String S_NAME;
	private String PR; // 자기소개 여기서 받음
	
	
	public Gosu_Detail_Join_Service() {
		
	}



	public Gosu_Detail_Join_Service(int d_CODE, String d_NAME, int s_CODE, String s_NAME, String pR) {
		super();
		D_CODE = d_CODE;
		D_NAME = d_NAME;
		S_CODE = s_CODE;
		S_NAME = s_NAME;
		PR = pR;
	}

	

	
	public String getPR() {
		return PR;
	}



	public void setPR(String pR) {
		PR = pR;
	}



	public int getD_CODE() {
		return D_CODE;
	}



	public void setD_CODE(int d_CODE) {
		D_CODE = d_CODE;
	}



	public String getD_NAME() {
		return D_NAME;
	}



	public void setD_NAME(String d_NAME) {
		D_NAME = d_NAME;
	}



	public int getS_CODE() {
		return S_CODE;
	}



	public void setS_CODE(int s_CODE) {
		S_CODE = s_CODE;
	}



	public String getS_NAME() {
		return S_NAME;
	}



	public void setS_NAME(String s_NAME) {
		S_NAME = s_NAME;
	}
	
	
	
	
}
