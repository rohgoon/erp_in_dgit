package kr.or.dgit.bigdata.erp.ui.list;

import java.util.List;

import javax.swing.SwingConstants;

import kr.or.dgit.bigdata.erp.dao.TitleDao;
import kr.or.dgit.bigdata.erp.dto.Title;

@SuppressWarnings("serial")
public class TitleList extends AbstractList {
	
	public TitleList(String s) {
		
	}

	@Override
	protected void tableSetAlignWith() {
		tableCellAlignment(SwingConstants.CENTER, 0,1);
		
		tableSetWidth(100,200);
		
	}

	@Override
	protected String[] getColumnData() {
		return new String[] { "번호", "직책명" };
	}

	@Override
	protected String[][] getRowData() {
		List<Title> list = TitleDao.getInstance().selectItemByAll();
		String[][] rowDatas = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			rowDatas[i] = list.get(i).toArray();

		}
		return rowDatas;
	}
}
