package native_jdbc_programing.dao;

import java.util.List;

import native_jdbc_programing.dto.Department;
import native_jdbc_programing.dto.Employee;

public interface EmployeeDao {
	List<Employee> selectEmpByAll();
	List<Employee> selectEmpByAllJoin();
	
	List<Employee> selectEmpByDeptNo(Department dept);
	Employee selectEmpByNo(Employee employee);
	int insertEmployee(Employee employee);
	int updateEmployee(Employee employee);
	int deleteEmployee(int empNo);
}
