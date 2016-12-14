package kr.or.dgit.bigdata.erp.ui.list;

import java.util.List;

import javax.swing.SwingConstants;

import kr.or.dgit.bigdata.erp.dao.EmpDao;
import kr.or.dgit.bigdata.erp.dto.Emp;

@SuppressWarnings("serial")
public class EmpList extends AbstractList {
	public EmpList() {

	}

	@Override
	protected void tableSetAlignWith() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2, 3, 5);
		tableCellAlignment(SwingConstants.RIGHT, 4);
		tableSetWidth(100, 200, 100, 200, 250, 100);

	}

	@Override
	protected String[] getColumnData() {
		return new String[] { "사번", "성명", "직책", "직속상사", "연봉", "부서번호" };
	}

	@Override
	protected String[][] getRowData() {
		List<Emp> list = EmpDao.getInstance().selectItemByAll();
		String[][] rowDatas = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			rowDatas[i] = list.get(i).toArray();
		}
		return rowDatas;
	}

}
