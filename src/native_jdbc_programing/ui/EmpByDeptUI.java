package native_jdbc_programing.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import native_jdbc_programing.dao.impl.EmployeeDaoImpl;
import native_jdbc_programing.dto.Department;
import native_jdbc_programing.dto.Employee;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import native_jdbc_programing.ui.DepartmentUI;

public class EmpByDeptUI extends JFrame {

	private JPanel contentPane;
	private JTextField tfDeptNo;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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

	/**
	 * Create the frame.
	 */
	public EmpByDeptUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 336);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		JPanel pEmp = new JPanel();
		contentPane.add(pEmp);
		pEmp.setLayout(new GridLayout(0, 2, 0, 0));
		
		JLabel lblDeptNo = new JLabel("\uBD80\uC11C \uBC88\uD638");
		lblDeptNo.setHorizontalAlignment(SwingConstants.RIGHT);
		pEmp.add(lblDeptNo);
		
		tfDeptNo = new JTextField();
		pEmp.add(tfDeptNo);
		tfDeptNo.setColumns(10);
		
		JPanel pBtn = new JPanel();
		contentPane.add(pBtn);
		
		JButton btnBefore = new JButton("검색");
		btnBefore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int no = Integer.parseInt(tfDeptNo.getText());
				Department dept = new Department(no);
				EmployeeDaoImpl.getInstance().selectEmpByDeptNo(dept);
				table.setModel(getModel());
			}
		});
		pBtn.add(btnBefore);
		
		JButton btnExit = new JButton("종료");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		pBtn.add(btnExit);
		
		JPanel pList = new JPanel();
		contentPane.add(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		pList.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		table.setModel(getModel());
		scrollPane.setViewportView(table);
	}

	public DefaultTableModel getModel() {
		return new DefaultTableModel(getData(),getColumns());
	}

	public Object[][] getData() {
		try {
		Department dept = new Department(Integer.parseInt(tfDeptNo.getText()));
		List<Employee> list = EmployeeDaoImpl.getInstance().selectEmpByDeptNo(dept);
		Object[][] arr = new Object[list.size()][];
		for(int i = 0; i < list.size(); i++) {
			Employee emp = list.get(i);
			arr[i] = new Object[] {
				emp.getEmpNo(),
				emp.getEmpName(),
				emp.getTitle().getTno(),
				emp.getManager().getEmpNo(),
				emp.getSalary(),
				emp.getDept().getDeptNo()
			};
		}
		return arr;
		}catch(NumberFormatException e){}
		return null;
	}
	public String[] getColumns() {
		return new String[] {
			"사원 번호", "사원 명", "직책", "직속 상사", "급여", "사원 번호"
		};
	}

}
