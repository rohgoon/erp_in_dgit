package kr.or.dgit.bigdata.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Emp;
import kr.or.dgit.bigdata.erp.dto.Title;
import kr.or.dgit.bigdata.erp.jdbc.DbConnection;
import kr.or.dgit.bigdata.erp.jdbc.JdbcUtil;

public class EmpDao implements interfaceDao<Emp> {
	private static final EmpDao instance = new EmpDao();

	public static EmpDao getInstance() {
		return instance;
	}

	@Override
	public int insertItem(Emp t) {
		String sql = "insert into employee values(?,?,?,?,?,?)";
		Connection con = DbConnection.getConnection();
		int res = -1;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, t.getNo());
			pstmt.setString(2, t.getEmpName());
			pstmt.setInt(3, t.getTitle().getNo());
			pstmt.setInt(4, t.getManager().getNo());
			pstmt.setInt(5, t.getSalary());
			pstmt.setInt(6, t.getDno().getNo());
			System.out.println(pstmt);

			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			if (e.getErrorCode() == 1062) {
				System.out.println("중복입니다.");
			}
			e.printStackTrace();

		} finally {
			JdbcUtil.close(pstmt);
		}

		return res;
	}

	@Override
	public int updateItem(Emp t) {
		String sql = "update employee set empname =?, title=?, manager=?, salary=?,dno=? where empno =?";
		int res = -1;
		PreparedStatement pstmt = null;

		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, t.getNo());
			pstmt.setString(2, t.getEmpName());
			pstmt.setInt(3, t.getTitle().getNo());
			pstmt.setInt(4, t.getManager().getNo());
			pstmt.setInt(5, t.getSalary());
			pstmt.setInt(6, t.getDno().getNo());

			System.out.println(pstmt);
			res = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}

		return res;
	}

	@Override
	public void deleteItem(int no) {
		String sql = "delete from employee where empno =?";
		

		try (PreparedStatement pstmt = DbConnection.getConnection().prepareStatement(sql);){
			// ㄴ 새로나온 형식으로 finally에 안넣어도 자동 close()가 호출된다
			pstmt.setInt(1, no);			
			pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public List<Emp> selectItemByAll() {
		List<Emp> empList = new ArrayList<Emp>();
		String sql = "select empno, empname, title, manager, salary,dno from employee";
		// 확인 요망

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				empList.add(getObject(rs));

			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);

		}
		return empList;
	}
	
	@Override
	public Emp selectItemByNo(int no) {
		String sql = "select empno, empname, title, manager, salary,dno from employee where empno = ?";
		// 확인 요망
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Emp emp = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				emp = getObject(rs);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);

		}

		return emp;
	}

	@Override
	public Emp getObject(ResultSet rs) throws SQLException {
		
		int no = rs.getInt("empno");
		String empName = rs.getString("empName");
		Title title = TitleDao.getInstance().selectItemByNo(rs.getInt("title"));
		Emp manager = EmpDao.getInstance().selectItemByNo(rs.getInt("manager"));
		int salary = rs.getInt("salary");
		Department dno = DeptDao.getInstance().selectItemByNo(rs.getInt("dno"));
		return new Emp(no, empName, title, manager, salary, dno);
	}

	@Override
	public int getNextNo() {
		String sql = "select max(empno)+1 from employee";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int nextNo = -1;

		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				nextNo = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, pstmt);
		}
		return nextNo;
	}

	
}