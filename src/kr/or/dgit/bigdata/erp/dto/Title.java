package kr.or.dgit.bigdata.erp.dto;

public class Title {
	private int no;
	private String titleName;
	public Title() {
		
	}
	public Title(int no) {
		super();
		this.no = no;
	}
	
	public Title(int no, String titleName) {
		this.no = no;
		this.titleName = titleName;
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s)", titleName, no);
	}
	
	public String[] toArray(){
		return new String[]{no+"",titleName};
		
	}
	
	
}
