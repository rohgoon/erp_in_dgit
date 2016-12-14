package kr.or.dgit.bigdata.erp.ui.list;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

@SuppressWarnings("serial")
public abstract class AbstractList extends JPanel {

	
	private JTable table;

	public AbstractList() {
		
		setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);

		table = new JTable();
		table.setBorder(new EmptyBorder(10, 10, 10, 10));
		scrollPane.setViewportView(table);
		reloadData();
	}

	public void reloadData() {///
		DefaultTableModel model = new DefaultTableModel(getRowData(), getColumnData());
		table.setModel(model);
		tableSetAlignWith();
		
		
	}

	protected abstract void tableSetAlignWith();

	protected abstract String[] getColumnData();

	protected abstract String[][] getRowData();
	
	protected void tableCellAlignment(int align, int...idx){
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(align);
		TableColumnModel model = table.getColumnModel();
		for (int i = 0; i < idx.length; i++) {
			model.getColumn(idx[i]).setCellRenderer(dtcr);
		}
	}
	
	protected void tableSetWidth(int...width){
		TableColumnModel model = table.getColumnModel();
		for (int i = 0; i < width.length; i++) {
			model.getColumn(i).setPreferredWidth(width[i]);
		}
	}

}
