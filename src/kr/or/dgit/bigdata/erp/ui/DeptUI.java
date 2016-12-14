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

import kr.or.dgit.bigdata.erp.dao.DeptDao;

import kr.or.dgit.bigdata.erp.dto.Department;


public class DeptUI extends JPanel implements ActionListener {
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfFloor;
	private JButton btnOK;
	private JButton btnCancel;

	public DeptUI() {
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
		
		JLabel lblName = new JLabel("부서명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblName);
		
		tfName = new JTextField();
		pMain.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblFloor);
		
		tfFloor = new JTextField();
		pMain.add(tfFloor); 
		tfFloor.setColumns(10);
		
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
		String no = DeptDao.getInstance().getNextNo()+"";
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
		Department inDept = getObject();//
		if(btnOK.getText().equals("추가")){
			
			DeptDao.getInstance().insertItem(inDept);
			JOptionPane.showMessageDialog(null, "추가 완료");
			
		}else{
			
			DeptDao.getInstance().updateItem(inDept);
			JOptionPane.showMessageDialog(null, "수정 완료");
			
			btnOK.setText("추가");
			
		}
		clearTf();
	}
	private void clearTf() {
		tfNo.setText(getNextNo());
		tfName.setText("");
		tfFloor.setText("");
		
	}
	public void setObject(Department dept){
		tfNo.setText(dept.getNo()+"");
		tfName.setText(dept.getDeptName());
		tfFloor.setText(dept.getDeptFloor()+"");
		btnOK.setText("수정");
		
	}
	
	private Department getObject() {
		int no = Integer.parseInt(tfNo.getText().trim());
		String deptName = tfName.getText().trim();
		int deptFloor = Integer.parseInt(tfFloor.getText().trim());
		return new Department(no, deptName, deptFloor);
	}

	protected void actionPerformedBtnCancel(ActionEvent arg0) {
		clearTf();
		
	}

}
