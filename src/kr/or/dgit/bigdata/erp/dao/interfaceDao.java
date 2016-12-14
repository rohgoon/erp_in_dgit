package kr.or.dgit.bigdata.erp.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface interfaceDao<T> {
	
	
	public int insertItem(T t);
	
	
	public int updateItem(T t);
	
	
	void deleteItem(int no);
	
	List<T> selectItemByAll();
	
	T selectItemByNo(int no);

	T getObject(ResultSet rs) throws SQLException;
	
	int getNextNo();
	
}













