package native_jdbc_programing.dto;

public class Title {
	private int tNo;
	private String tName;

	public Title() {
		// TODO Auto-generated constructor stub
	}

	public Title(int tNo, String tName) {
		this.tNo = tNo;
		this.tName = tName;
	}

	public Title(int tno) {
		this.tNo = tno;
	}

	public int getTno() {
		return tNo;
	}

	public void setTno(int tno) {
		this.tNo = tno;
	}

	public String getTname() {
		return tName;
	}

	public void setTname(String tname) {
		this.tName = tname;
	}

	@Override
	public String toString() {
		return String.format("%4s %3s  ", tNo, tName);
	}

}
