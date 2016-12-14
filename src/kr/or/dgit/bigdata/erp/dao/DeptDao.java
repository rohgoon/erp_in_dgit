package kr.or.dgit.bigdata.erp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.jdbc.DbConnection;
import kr.or.dgit.bigdata.erp.jdbc.JdbcUtil;

public class DeptDao implements interfaceDao<Department>{
	
	private static final DeptDao instance = new DeptDao();

	public static DeptDao getInstance() {
		return instance;
	}
	@Override
	public int insertItem(Department t) {
		String sql = "insert into department values(?,?,?)";
		Connection con = DbConnection.getConnection();
		int res = -1;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, t.getNo());
			pstmt.setString(2, t.getDeptName());
			pstmt.setInt(3, t.getDeptFloor());
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
	public int updateItem(Department t) {
		String sql = "update department set deptname =?, floor=? where deptno =?";
		int res = -1;
		PreparedStatement pstmt = null;

		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setString(1, t.getDeptName());
			pstmt.setInt(2, t.getDeptFloor());
			pstmt.setInt(3, t.getNo());
			
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
		String sql = "delete from department where deptno =?";
		PreparedStatement pstmt = null;

		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);
			System.out.println(pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.close(pstmt);
		}
		
	}

	@Override
	public List<Department> selectItemByAll() {
		List<Department> deptList = new ArrayList<Department>();
		String sql = "select deptno, deptname, floor from department"; 
		
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			
			
			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				deptList.add(getObject(rs));

				
			}

		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs,pstmt);

		}
		return deptList;
	}

	@Override
	public Department selectItemByNo(int no) {
		String sql = "select deptno, deptname, floor  from department where deptno = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Department dept = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				dept = getObject(rs);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs,pstmt);

		}

		return dept;
	}

	@Override
	public Department getObject(ResultSet rs) throws SQLException {
		return new Department(rs.getInt(1), rs.getString("deptname"), rs.getInt(3));
	}

	@Override
	public int getNextNo() {
		String sql = "select max(deptno)+1 from department";
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
			JdbcUtil.close(rs,pstmt);
		}
		return nextNo;

	}
	

}
