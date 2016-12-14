package kr.or.dgit.bigdata.erp.dto;

public class Department {
	private int no;
	private String deptName;
	private int deptFloor;
	
	public Department() {
		
	}
	public Department(int no) {
		super();
		this.no = no;
	}
	
	public Department(int no, String departmentName) {
		this.no = no;
		this.deptName = departmentName;
	}
	
	public Department(int no, String deptName, int deptFloor) {
		super();
		this.no = no;
		this.deptName = deptName;
		this.deptFloor = deptFloor;
	}
	
	public int getDeptFloor() {
		return deptFloor;
		
		
	}
	public void setDeptFloor(int deptFloor) {
		this.deptFloor = deptFloor;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String departmentName) {
		this.deptName = departmentName;
	}
	
	@Override
	public String toString() {
		return String.format("%s(%s), %s층", deptName, no, deptFloor);
	}
	
	public String[] toArray(){
		return new String[]{no+"", deptName, deptFloor+""};
		
	}
}
