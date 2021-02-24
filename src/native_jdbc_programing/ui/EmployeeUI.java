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

public class EmployeeUI extends JFrame {

	private JPanel contentPane;
	private JTextField tfEmpNo;
	private JTextField tfEmpName;
	private JTextField tfTitle;
	private JTextField tfManager;
	private JTextField tfSalary;
	private JTextField tfDept;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmployeeUI frame = new EmployeeUI();
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
	public EmployeeUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 548);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel pEmp = new JPanel();
		contentPane.add(pEmp);
		pEmp.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblEmpNo = new JLabel("사원 번호");
		lblEmpNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblEmpNo);
		
		tfEmpNo = new JTextField();
		pEmp.add(tfEmpNo);
		tfEmpNo.setColumns(10);
		
		JLabel lblEmpName = new JLabel("사원 명");
		lblEmpName.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblEmpName);
		
		tfEmpName = new JTextField();
		tfEmpName.setColumns(10);
		pEmp.add(tfEmpName);
		
		JLabel lblTitle = new JLabel("직책");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblTitle);
		
		tfTitle = new JTextField();
		tfTitle.setColumns(10);
		pEmp.add(tfTitle);
		
		JLabel lblManager = new JLabel("직속상사");
		lblManager.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblManager);
		
		tfManager = new JTextField();
		tfManager.setColumns(10);
		pEmp.add(tfManager);
		
		JLabel lblSalary = new JLabel("급여");
		lblSalary.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblSalary);
		
		tfSalary = new JTextField();
		tfSalary.setColumns(10);
		pEmp.add(tfSalary);
		
		JLabel lblDept = new JLabel("사원번호");
		lblDept.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblDept);
		
		tfDept = new JTextField();
		tfDept.setColumns(10);
		pEmp.add(tfDept);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		JButton btnAdd = new JButton("추가");
		pBtn.add(btnAdd);
		
		JButton btnCancel = new JButton("취소");
		pBtn.add(btnCancel);
		
		JButton btnUpdate = new JButton("수정");
		pBtn.add(btnUpdate);
		
		JButton btnExit = new JButton("종료");
		pBtn.add(btnExit);
		
		JPanel pList = new JPanel();
		contentPane.add(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pList.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(getData(),getColumns()));
		scrollPane.setViewportView(table);
	}

	public Object[][] getData() {
		return new Object[][] {
		};
	}

	public String[] getColumns() {
		return new String[] {
			"사원 번호", "사원 명", "직책", "직속 상사", "수정", "종료"
		};
	}

}
