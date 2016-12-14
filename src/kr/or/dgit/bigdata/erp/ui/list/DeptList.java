package kr.or.dgit.bigdata.erp.ui.list;

import java.util.List;

import javax.swing.SwingConstants;

import kr.or.dgit.bigdata.erp.dao.DeptDao;
import kr.or.dgit.bigdata.erp.dto.Department;

@SuppressWarnings("serial")
public class DeptList extends AbstractList {

	public DeptList() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void tableSetAlignWith() {
		tableCellAlignment(SwingConstants.CENTER, 0, 1, 2);

		tableSetWidth(100, 200);

	}

	@Override
	protected String[] getColumnData() {
		return new String[] { "번호", "부서명", "위치" };
	}

	@Override
	protected String[][] getRowData() {
		List<Department> list = DeptDao.getInstance().selectItemByAll();
		String[][] rowDatas = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			rowDatas[i] = list.get(i).toArray();
		}
		return rowDatas;
	}
}
