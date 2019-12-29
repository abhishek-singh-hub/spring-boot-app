package SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DAO;

import java.util.List;

import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Employee;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Student;

public interface SpringBootDAO {

	List<Employee> getEmployeeDetail();
	List<Student> getStudentDetail();
	void saveEmployeeDetail(Employee employee);

}
