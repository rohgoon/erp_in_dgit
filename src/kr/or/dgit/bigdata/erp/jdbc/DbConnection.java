package kr.or.dgit.bigdata.erp.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import kr.or.dgit.bigdata.erp.Config;

public class DbConnection {
	private static final DbConnection instance = new DbConnection();
	private static Connection con;
	
	
	
	private DbConnection(){
		try {
			Class.forName(Config.DRIVER);
			con = DriverManager.getConnection(Config.URL+Config.DB_NAME, Config.USER, Config.PASSWORD);
			
			
		} catch (ClassNotFoundException e) {
			System.err.println("jdbc Driver 등록");
		} catch (SQLException e) {
			System.err.println("주소 혹은 계정 비밀번호 확인");
		}
	}

	public static Connection getConnection() {
		if (instance == null) {
			new DbConnection();
		}
		
		return instance.con;
	}
	
	public static void closeConnection(){
		if (con != null) {
			try {
				con.close();
				con= null;
			} catch (SQLException e) {				
				e.printStackTrace();
			}
		}
	}
	
}
