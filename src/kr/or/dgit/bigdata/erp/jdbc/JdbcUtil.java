package kr.or.dgit.bigdata.erp.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil {
	public static void close(Statement stmt){
		try {
			stmt.close();
			stmt = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public static void close(ResultSet rs){
		try {
			rs.close();
			rs=null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void close(ResultSet rs, Statement stmt){
		close(rs);
		close(stmt);
		
		
	}
	
}
