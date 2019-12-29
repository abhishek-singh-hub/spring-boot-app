package SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.BO;

import java.util.List;

import org.json.JSONObject;

import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Employee;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Student;

public interface SpringBootBO {
	void saveEmployeeDetail(Employee employee);
	List<Student> getStudentDetail();
	List<Employee> getEmployeeDetail();
	void saveStudentDetail(Student student);
}
