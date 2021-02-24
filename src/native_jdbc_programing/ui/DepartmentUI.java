package native_jdbc_programing.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import native_jdbc_programing.dao.DepartmentDao;
import native_jdbc_programing.dao.impl.DepartmentDaoImpl;
import native_jdbc_programing.dto.Department;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

import native_jdbc_programing.ui.EmpByDeptUI;

public class DepartmentUI extends JFrame {
	
	private JPanel contentPane;
	private JPanel pDept;
	private JPanel pBtn;
	private JPanel pList;
	private JLabel lblDeptNo;
	private JTextField tfDeptNo;
	private JLabel lblDeptName;
	private JTextField tfDeptName;
	private JLabel lblFloor;
	private JTextField tfFloor;
	private JButton btnAdd;
	private JButton btnCancel;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnModi;
	private JButton btnExit;
	private JButton btnByDno;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentUI frame = new DepartmentUI();
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
	public DepartmentUI() {
		setTitle("부서정보");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pDept = new JPanel();
		contentPane.add(pDept);
		pDept.setLayout(new GridLayout(0, 2, 10, 0));
		
		lblDeptNo = new JLabel("부서 번호");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblDeptNo);
		
		tfDeptNo = new JTextField();
		pDept.add(tfDeptNo);
		tfDeptNo.setColumns(10);
		
		lblDeptName = new JLabel("부서명");
		lblDeptName.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblDeptName);
		
		tfDeptName = new JTextField();
		pDept.add(tfDeptName);
		tfDeptName.setColumns(10);
		
		lblFloor = new JLabel("위치");
		lblFloor.setHorizontalAlignment(SwingConstants.RIGHT);
		pDept.add(lblFloor);
		
		tfFloor = new JTextField();
		tfFloor.setColumns(10);
		pDept.add(tfFloor);
		
		pBtn = new JPanel();
		contentPane.add(pBtn);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				Department newdept = getDepartment();					
				DepartmentDaoImpl.getInstance().insertDepartment(newdept);
				table.setModel(getModel());
			}
		});
		
		pBtn.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DepartmentDaoImpl.getInstance().deleteDepartment(Integer.parseInt(tfDeptNo.getText()));
				table.setModel(getModel());
			}
		});
		
		pBtn.add(btnCancel);
		
		btnModi = new JButton("수정");
		btnModi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Department upDept = getDepartment();
				DepartmentDaoImpl.getInstance().updateDepartment(upDept);
				table.setModel(getModel());
			}
		});
		pBtn.add(btnModi);
		
		btnExit = new JButton("종료");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		pBtn.add(btnExit);
		
		btnByDno = new JButton("부서별 검색");
		btnByDno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							EmpByDeptUI frame = new EmpByDeptUI();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		pBtn.add(btnByDno);
		
		pList = new JPanel();
		contentPane.add(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		pList.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(getData(),getColumn()));
		scrollPane.setViewportView(table);
	}

	protected Department getDepartment() {
		int deptNo = Integer.parseInt(tfDeptNo.getText());
		String deptName = tfDeptName.getText();
		int floor = Integer.parseInt(tfFloor.getText());
		return new Department(deptNo, deptName, floor);
	}

	private TableModel getModel() {
		return new DefaultTableModel(getData(),getColumn());
	}

	public String[] getColumn() {
		return new String[] {
			"부서 번호", "부서명", "위치"
		};
	}

	public Object[][] getData() {
		List<Department> list =  DepartmentDaoImpl.getInstance().selectDeptByAll();
		Object[][] arr = new Object[list.size()][];
		for(int i = 0; i < list.size();i++) {
			Department dept = list.get(i);
			arr[i] = new Object[] {
					dept.getDeptNo(),
					dept.getDeptName(),
					dept.getFloor()
			};
		}
		return arr;
	}

}
