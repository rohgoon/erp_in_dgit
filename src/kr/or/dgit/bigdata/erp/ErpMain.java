package kr.or.dgit.bigdata.erp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kr.or.dgit.bigdata.erp.dao.DeptDao;
import kr.or.dgit.bigdata.erp.dao.EmpDao;
import kr.or.dgit.bigdata.erp.dao.TitleDao;
import kr.or.dgit.bigdata.erp.dto.Department;
import kr.or.dgit.bigdata.erp.dto.Emp;
import kr.or.dgit.bigdata.erp.dto.Title;
import kr.or.dgit.bigdata.erp.ui.DeptUI;
import kr.or.dgit.bigdata.erp.ui.EmpPanel;
import kr.or.dgit.bigdata.erp.ui.EmpUI;
import kr.or.dgit.bigdata.erp.ui.UIPanel;
import kr.or.dgit.bigdata.erp.ui.list.DeptList;
import kr.or.dgit.bigdata.erp.ui.list.EmpList;
import kr.or.dgit.bigdata.erp.ui.list.TitleList;

public class ErpMain extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JMenuItem mnTitleList;
	private JMenuItem mnTitleAdd;
	private JMenuItem mnTitleUpdate;
	private JMenuItem mnTitleDelete;
	private TitleList titleList;
	private JMenu mnDept;
	private JMenuItem mnDeptAdd;
	private JMenuItem mnDeptUpdate;
	private JMenuItem mnDeptDel;
	private JMenuItem mnDeptList;
	private DeptList deptList;
	private JMenu mnEmp;
	private JMenuItem mnEmpAdd;
	private JMenuItem mnEmpUpdate;
	private JMenuItem mnEmpDel;
	private JMenuItem mnEmpList;
	private EmpList empList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErpMain frame = new ErpMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ErpMain() {
		setTitle("erp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnTitle = new JMenu("직책관리");
		menuBar.add(mnTitle);

		mnTitleAdd = new JMenuItem("직책추가");
		mnTitleAdd.addActionListener(this);
		mnTitle.add(mnTitleAdd);

		mnTitleUpdate = new JMenuItem("직책수정");
		mnTitleUpdate.addActionListener(this);
		mnTitle.add(mnTitleUpdate);

		mnTitleDelete = new JMenuItem("직책삭제");
		mnTitleDelete.addActionListener(this);
		mnTitle.add(mnTitleDelete);

		mnTitleList = new JMenuItem("직책목록");
		mnTitleList.addActionListener(this);
		mnTitle.add(mnTitleList);
		
		mnDept = new JMenu("부서관리");
		menuBar.add(mnDept);
		
		mnDeptAdd = new JMenuItem("부서추가");
		mnDeptAdd.addActionListener(this);
		mnDept.add(mnDeptAdd);
		
		mnDeptUpdate = new JMenuItem("부서수정");
		mnDeptUpdate.addActionListener(this);
		mnDept.add(mnDeptUpdate);
		
		mnDeptDel = new JMenuItem("부서삭제");
		mnDeptDel.addActionListener(this);
		mnDept.add(mnDeptDel);
		
		mnDeptList = new JMenuItem("부서목록");
		mnDeptList.addActionListener(this);
		mnDept.add(mnDeptList);
		
		mnEmp = new JMenu("사원관리");
		menuBar.add(mnEmp);
		
		mnEmpAdd = new JMenuItem("사원추가");
		mnEmpAdd.addActionListener(this);
		mnEmp.add(mnEmpAdd);
		
		mnEmpUpdate = new JMenuItem("사원수정");
		mnEmpUpdate.addActionListener(this);
		mnEmp.add(mnEmpUpdate);
		
		mnEmpDel = new JMenuItem("사원삭제");
		mnEmpDel.addActionListener(this);
		mnEmp.add(mnEmpDel);
		
		mnEmpList = new JMenuItem("사원목록");
		mnEmpList.addActionListener(this);
		mnEmp.add(mnEmpList);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mnEmpList) {
			actionPerformedMnEmpList(e);
		}
		if (e.getSource() == mnEmpDel) {
			actionPerformedMnEmpDel(e);
		}
		if (e.getSource() == mnEmpUpdate) {
			actionPerformedMnEmpUpdate(e);
		}
		if (e.getSource() == mnEmpAdd) {
			actionPerformedMnEmpAdd(e);
		}
		if (e.getSource() == mnDeptList) {
			actionPerformedMnDeptList(e);
		}
		if (e.getSource() == mnDeptDel) {
			actionPerformedMnDeptDel(e);
		}
		if (e.getSource() == mnDeptUpdate) {
			actionPerformedMnDeptUpdate(e);
		}
		if (e.getSource() == mnDeptAdd) {
			actionPerformedMnDeptAdd(e);
		}
		if (e.getSource() == mnTitleDelete) {
			actionPerformedMnTitleDelete(e);
		}
		if (e.getSource() == mnTitleUpdate) {
			actionPerformedMnTitleUpdate(e);
		}
		if (e.getSource() == mnTitleAdd) {
			actionPerformedMnTitleAdd(e);
		}
		if (e.getSource() == mnTitleList) {
			actionPerformedMnTitleList(e);
		}
	}

	protected void actionPerformedMnTitleList(ActionEvent e) {
		titleList = new TitleList("직책");
		setContentPane(titleList);
		revalidate();
	}

	protected void actionPerformedMnTitleAdd(ActionEvent e) {
		UIPanel titleUI = new UIPanel();
		setContentPane(titleUI);
		revalidate();
	}

	protected void actionPerformedMnTitleUpdate(ActionEvent e) {
		List<Title> list = TitleDao.getInstance().selectItemByAll();

		Title selectedTitle = (Title) JOptionPane.showInputDialog(null, "수정할 직책을 선택하시오.", "직책 수정",
				JOptionPane.INFORMATION_MESSAGE, null, list.toArray(), list.get(3));

		UIPanel updatePanel = new UIPanel();
		updatePanel.setObject(selectedTitle);
		setContentPane(updatePanel);
		revalidate();

		System.out.println(selectedTitle);
	}
	protected void actionPerformedMnTitleDelete(ActionEvent e) {
		List<Title> list = TitleDao.getInstance().selectItemByAll();

		Title selectedTitle = (Title) JOptionPane.showInputDialog(null, "삭제할 직책을 선택하시오.", "직책 삭제",
				JOptionPane.INFORMATION_MESSAGE, null, list.toArray(), list.get(0));
		TitleDao.getInstance().deleteItem(selectedTitle.getNo());
		JOptionPane.showMessageDialog(null, selectedTitle.toString()+" 삭제 완료");
		
		if(titleList != null){
			titleList.reloadData();
		}
	}
	
	//부서 관련 action
	protected void actionPerformedMnDeptAdd(ActionEvent e) {
		//부서 추가
		DeptUI deptUI = new DeptUI();
		setContentPane(deptUI);
		revalidate();
	}
	protected void actionPerformedMnDeptUpdate(ActionEvent e) {
		//부서 수정
		List<Department> list =DeptDao.getInstance().selectItemByAll();

		Department selectedDept = (Department) JOptionPane.showInputDialog(null, "수정할 부서를 선택하시오.", "부서 수정",
				JOptionPane.INFORMATION_MESSAGE, null, list.toArray(), list.get(3));

		DeptUI updatePanel = new DeptUI();
		updatePanel.setObject(selectedDept);
		setContentPane(updatePanel);
		revalidate();

		System.out.println(selectedDept);		
		
	}
	protected void actionPerformedMnDeptDel(ActionEvent e) {
		// 부서 삭제
		List<Department> list = DeptDao.getInstance().selectItemByAll();

		Department selectedDept = (Department) JOptionPane.showInputDialog(null, "삭제할 부서를 선택하시오.", "부서 삭제",
				JOptionPane.INFORMATION_MESSAGE, null, list.toArray(), list.get(0));
		DeptDao.getInstance().deleteItem(selectedDept.getNo());
		JOptionPane.showMessageDialog(null, selectedDept.toString()+" 삭제 완료");
		
		if(deptList != null){
			deptList.reloadData();
		}
		
	}
	
	protected void actionPerformedMnDeptList(ActionEvent e) {
		//부서 목록
		
		DeptList d = new DeptList();
		setContentPane(d);
		revalidate();
		
	}
	
	
	protected void actionPerformedMnEmpAdd(ActionEvent e) {
		// 사원 추가 
		/*EmpUI empUI = new EmpUI();
		setContentPane(empUI);
		revalidate();*/
		EmpPanel empUI = new EmpPanel();
		setContentPane(empUI);
		revalidate();
	}
	
	protected void actionPerformedMnEmpUpdate(ActionEvent e) {
		// 사원 수정
		List<Emp> list =EmpDao.getInstance().selectItemByAll();

		Emp selectedEmp = (Emp) JOptionPane.showInputDialog(null, "수정할 사원을 선택하시오.", "사원 수정",
				JOptionPane.INFORMATION_MESSAGE, null, list.toArray(), list.get(3));
			// 확인 요망
		EmpPanel updatePanel = new EmpPanel();		
		updatePanel.setObject(selectedEmp);
		setContentPane(updatePanel);
		revalidate();
/*
		System.out.println(selectedEmp);	*/	
	}
	protected void actionPerformedMnEmpDel(ActionEvent e) {
		//사원 삭제
		List<Emp> list = EmpDao.getInstance().selectItemByAll();

		Emp selectedEmp = (Emp) JOptionPane.showInputDialog(null, "삭제할 부서를 선택하시오.", "부서 삭제",
				JOptionPane.INFORMATION_MESSAGE, null, list.toArray(), list.get(0));
		EmpDao.getInstance().deleteItem(selectedEmp.getNo());
		JOptionPane.showMessageDialog(null, selectedEmp.toString()+" 삭제 완료");
		
		if(empList != null){
			empList.reloadData();
		}
		
	}
	protected void actionPerformedMnEmpList(ActionEvent e) {
		EmpList d = new EmpList();
		setContentPane(d);
		revalidate();
		
	}
}