package kr.or.dgit.bigdata.erp.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.bigdata.erp.dao.DeptDao;
import kr.or.dgit.bigdata.erp.dao.EmpDao;
import kr.or.dgit.bigdata.erp.dao.TitleDao;
import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Emp;
import kr.or.dgit.bigdata.erp.dto.Title;

public class EmpPanel extends JPanel implements ActionListener{
	private JTextField tfNo;
	private JTextField tfName;
	private JTextField tfSalary;
	
	private JButton btnOK;
	private JButton btnCancel;
	private JComboBox<Title> cbTitle;
	private JComboBox<Emp> cbManager;
	private JComboBox<Department> cbDno;
	/**
	 * Create the panel.
	 */
	public EmpPanel() {
		setBorder(new EmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout(0, 0));
		
		JPanel pMain = new JPanel();
		pMain.setBorder(new EmptyBorder(10, 10, 10, 0));
		add(pMain, BorderLayout.CENTER);
		pMain.setLayout(new GridLayout(0, 2, 10, 10));
		
		JLabel lblNo = new JLabel("사번");
		lblNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblNo);
		
		tfNo = new JTextField();
		pMain.add(tfNo);
		tfNo.setColumns(10);
		
		JLabel lblName = new JLabel("성명");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblName);
		
		tfName = new JTextField();
		pMain.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblTitle);
		
		cbTitle = new JComboBox();
		List<Title> listTitle = TitleDao.getInstance().selectItemByAll();
		for (Title title : listTitle) {
			cbTitle.addItem(title);
		}
		pMain.add(cbTitle);
		
		JLabel lblManager = new JLabel("직속상사");
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblManager);
		
		cbManager = new JComboBox();
		List<Emp> listEmp = EmpDao.getInstance().selectItemByAll();
		for (Emp e : listEmp) {
			cbManager.addItem(e);
		}
		pMain.add(cbManager);
		
		JLabel lblSalary = new JLabel("연봉");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblSalary);
		
		tfSalary = new JTextField();
		pMain.add(tfSalary); 
		tfSalary.setColumns(10);
		
		JLabel lblDno = new JLabel("부서번호");
		lblDno.setHorizontalAlignment(SwingConstants.RIGHT);
		pMain.add(lblDno);
		
		cbDno = new JComboBox();
		List<Department> listDept = DeptDao.getInstance().selectItemByAll();
		for (Department e : listDept) {
			cbDno.addItem(e);
		}
		pMain.add(cbDno);
		
		JPanel pBtn = new JPanel();
		add(pBtn, BorderLayout.SOUTH);
		
		btnOK = new JButton("추가");
		btnOK.addActionListener(this);
		pBtn.add(btnOK);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtn.add(btnCancel);
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
		Emp inEmp = getObject();//
		if(btnOK.getText().equals("추가")){
			
			EmpDao.getInstance().insertItem(inEmp);
			JOptionPane.showMessageDialog(null, "추가 완료");
			
		}else{
			
			EmpDao.getInstance().updateItem(inEmp);
			JOptionPane.showMessageDialog(null, "수정 완료");
			
			btnOK.setText("추가");
			
		}
		clearTf();
	}
	private void clearTf() {
		tfNo.setText("");
		tfName.setText("");	
		cbTitle.setSelectedIndex(0);
		cbManager.setSelectedIndex(0);
		tfSalary.setText("");
		cbDno.setSelectedIndex(0);
		
	}
	public void setObject(Emp emp){
		tfNo.setText(emp.getNo()+"");
		tfName.setText(emp.getEmpName());
		cbTitle.setSelectedItem(emp.getTitle());
		cbManager.setSelectedItem(emp.getManager());
		tfSalary.setText(emp.getSalary()+"");
		cbDno.setSelectedItem(emp.getDno());
		btnOK.setText("수정");
		
	}
	
	private Emp getObject() {
		int no = Integer.parseInt(tfNo.getText().trim());
		String empName = tfName.getText().trim();
		Title title = (Title) cbTitle.getSelectedItem();
		Emp manager =(Emp) cbManager.getSelectedItem();
		int salary = Integer.parseInt(tfSalary.getText().trim());
		Department dno = (Department) cbDno.getSelectedItem();
		return new Emp(no, empName, title, manager, salary, dno);
	}

	protected void actionPerformedBtnCancel(ActionEvent arg0) {
		
		clearTf();
		
	}
}
