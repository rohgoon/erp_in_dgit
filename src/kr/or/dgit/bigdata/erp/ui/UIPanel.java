package kr.or.dgit.bigdata.erp.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.bigdata.erp.dao.TitleDao;
import kr.or.dgit.bigdata.erp.dto.Title;

public class UIPanel extends JPanel implements ActionListener {
	private JTextField tfNo;
	private JTextField tfName;
	private JButton btnOK;
	private JButton btnCancel;

	/**
	 * Create the panel.
	 */
	public UIPanel() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pMain = new JPanel();
		pMain.setBorder(new EmptyBorder(10, 10, 10, 0));
		add(pMain, BorderLayout.CENTER);
		pMain.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNo = new JLabel("번호");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblNo);
		
		tfNo = new JTextField();
		tfNo.setEditable(false);
		tfNo.setText(getNextNo());
		pMain.add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblName = new JLabel("직책명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblName);
		
		tfName = new JTextField();
		pMain.add(tfName);
		tfName.setColumns(10);
		
		JPanel pBtn = new JPanel();
		add(pBtn, BorderLayout.SOUTH);
		
		btnOK = new JButton("추가");
		btnOK.addActionListener(this);
		pBtn.add(btnOK);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);

	}

	private String getNextNo() {
		String no = TitleDao.getInstance().getNextNo()+"";
		return no;
	}

	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnCancel) {
			actionPerformedBtnCancel(arg0);
		}
		if (arg0.getSource() == btnOK) {
			actionPerformedBtnOK(arg0);
		}
	}
	protected void actionPerformedBtnOK(ActionEvent arg0) {
		Title inTitle = getObject();
		if(btnOK.getText().equals("추가")){
			
			TitleDao.getInstance().insertItem(inTitle);
			JOptionPane.showMessageDialog(null, "추가");
			
		}else{
			
			TitleDao.getInstance().updateItem(inTitle);
			JOptionPane.showMessageDialog(null, "수정");
			
			btnOK.setText("추가");
			
		}
		clearTf();
	}
	private void clearTf() {
		tfNo.setText(getNextNo());
		tfName.setText("");
		
	}
	public void setObject(Title title){
		tfNo.setText(title.getNo()+"");
		tfName.setText(title.getTitleName());
		btnOK.setText("수정");
		
	}
	
	private Title getObject() {
		int no = Integer.parseInt(tfNo.getText().trim());
		String titleName = tfName.getText().trim();
		return new Title(no, titleName);
	}

	protected void actionPerformedBtnCancel(ActionEvent arg0) {
		clearTf();
		
	}
}
