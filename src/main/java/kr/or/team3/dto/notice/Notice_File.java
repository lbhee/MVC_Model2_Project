package kr.or.team3.dto.notice;

public class Notice_File {

	private String filename;   //파일명
	private int filesize;      //파일크기
	private String filepath;   //파일경로
	private int num;           //글번호(참조)
	
	public Notice_File() {
	}

	public Notice_File(String filename, int filesize, String filepath, int num) {
		super();
		this.filename = filename;
		this.filesize = filesize;
		this.filepath = filepath;
		this.num = num;
	}

	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getFilesize() {
		return filesize;
	}
	public void setFilesize(int filesize) {
		this.filesize = filesize;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
