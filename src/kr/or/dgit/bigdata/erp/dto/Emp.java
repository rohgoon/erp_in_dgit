package kr.or.dgit.bigdata.erp.dto;

public class Emp {
	private int no;
	private String empName;
	private Title title;
	private Emp manager;
	private int salary;
	private Department dno;
	
	
	public Emp() {
		
	}

	
	public Emp(int no) {
		super();
		this.no = no;
	}


	public Emp(int no, String empName, Title title, int salary) {
		super();
		this.no = no;
		this.empName = empName;
		this.title = title;
		this.salary = salary;
	}
	

	public Emp(int no, String empName, Title title, Emp manager, int salary, Department dno) {
		super();
		this.no = no;
		this.empName = empName;
		this.title = title;
		this.manager = manager;
		this.salary = salary;
		this.dno = dno;
	}



	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getEmpName() {
		return empName;
	}


	public void setEmpName(String empName) {
		this.empName = empName;
	}


	public Title getTitle() {
		return title;
	}


	public void setTitle(Title title) {
		this.title = title;
	}


	public Emp getManager() {
		return manager;
	}


	public void setManager(Emp manager) {
		this.manager = manager;
	}


	public int getSalary() {
		return salary;
	}


	public void setSalary(int salary) {
		this.salary = salary;
	}


	public Department getDno() {
		return dno;
	}


	public void setDno(Department dno) {
		this.dno = dno;
	}
	

	@Override
	public String toString() {
		return String.format("%s(%s)", empName, title.getTitleName());
	}
	public String[] toArray(){
		return new String[]{no+"", empName, title.getTitleName(), manager==null?"":manager.getEmpName(),salary+"",dno.getDeptName()};
		
	}


	/*@Override
	public boolean equals(Object obj) {
		Title t = (Title) obj;
		if (t=) {
			
		} else {

		}
		return super.equals(obj);
	}*/
	
}
