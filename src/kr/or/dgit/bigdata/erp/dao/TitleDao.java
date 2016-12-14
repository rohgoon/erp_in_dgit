package kr.or.dgit.bigdata.erp.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.or.dgit.bigdata.erp.dto.Title;
import kr.or.dgit.bigdata.erp.jdbc.DbConnection;
import kr.or.dgit.bigdata.erp.jdbc.JdbcUtil;

public class TitleDao implements interfaceDao<Title>{
	
	private static final TitleDao instance =new TitleDao();

	public static TitleDao getInstance() {
		return instance;
	}
	@Override
	public int insertItem(Title t) {
		String sql = "insert into title values(?,?)";
		Connection con = DbConnection.getConnection();
		int res =-1;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, t.getNo());
			pstmt.setString(2, t.getTitleName());
			System.out.println(pstmt);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getErrorCode());
			if (e.getErrorCode()==1062) {
				System.out.println("중복입니다.");
			}
			e.printStackTrace();
			
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		return res;
		
	}

	@Override
	public int updateItem(Title t) {
		String sql = "update title set titlename =? where no =?";
		int res = -1;
		PreparedStatement pstmt = null;
		
		try {
			pstmt =DbConnection.getConnection().prepareStatement(sql);
			pstmt.setString(1, t.getTitleName());
			pstmt.setInt(2, t.getNo());
			System.out.println(pstmt);
			res=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		
		return res;
	}

	@Override
	public void deleteItem(int no) {
		String sql = "delete from title where no =?";
		PreparedStatement pstmt = null;
		
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1, no);
			System.out.println(pstmt);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(pstmt);
		}
		
	}

	@Override
	public List<Title> selectItemByAll() {
		List<Title> titleList = new ArrayList<Title>();
		String sql = "select no, titlename from title";
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt= DbConnection.getConnection().prepareStatement(sql);
			rs= pstmt.executeQuery();
			while(rs.next()){
				titleList.add(getObject(rs));
				
				
				/*int no = rs.getInt(1);
				String titleName = rs.getString("titlename");
				Title title = new Title(no, titleName);
				titleList.add(title);*/
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs,pstmt);
			
			
		}
		return titleList;
	}

	@Override
	public Title selectItemByNo(int no) {
		String sql = "select no, titlename from title where no = ?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Title title = null;
		try {
			pstmt = DbConnection.getConnection().prepareStatement(sql);
			pstmt.setInt(1,  no);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				title = getObject(rs);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs,pstmt);
			
			
		}
		
		return title;
		
	}

	@Override
	public Title getObject(ResultSet rs) throws SQLException {
		return new Title(rs.getInt(1), rs.getString("titlename"));
	}

	@Override
	public int getNextNo() {
		String sql = "select max(no)+1 from title";
		PreparedStatement pstmt = null;
		ResultSet rs =null;
		int nextNo =-1;
		
		try {
			pstmt =DbConnection.getConnection().prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
			nextNo = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JdbcUtil.close(rs,pstmt);
		}
		return nextNo;
	}
	
	
}













