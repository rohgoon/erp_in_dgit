package kr.or.dgit.bigdata.erp;

import java.util.List;

import javax.swing.JOptionPane;

import kr.or.dgit.bigdata.erp.dao.TitleDao;
import kr.or.dgit.bigdata.erp.dto.Title;


public class TestMain {
	public static void main(String[] args) {
		/*Connection con = DbConnection.getConnection();
		System.out.println(con);
		db연결 테스트*/
		
		Title title = new Title(6, "인턴");
		/*int res = TitleDao.getInstance().insertTitle(title);
		if(res==1) 
			JOptionPane.showMessageDialog(null, "삽입 성공");
		else
			JOptionPane.showMessageDialog(null, "삽입 실패");
		*/
		
		/*title.setNo(6);
		title.setTitleName("막내");
		TitleDao.getInstance().updateTitle(title);*/
		
		/*TitleDao.getInstance().deleteTitle(title.getNo());*/
		
		List<Title> list = TitleDao.getInstance().selectItemByAll();
		
		for(Title t : list){
			System.out.println(t);			
		}
		Title searchTitle = new Title(3);
		Title res = TitleDao.getInstance().selectItemByNo(searchTitle.getNo());
		System.out.println(res);
		
		
		// 과제 department dto, dao, testmain
		
	}
}
