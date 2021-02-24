package native_jdbc_programing.dao;

import java.util.List;

import native_jdbc_programing.dto.Department;
import native_jdbc_programing.dto.Employee;

public interface DepartmentDao {
	List<Department> selectDeptByAll();
	Department selectDeptByNo(Department dept);
	int insertDepartment(Department dept);
	int updateDepartment(Department dept);
	int deleteDepartment(int deptNo);
}
