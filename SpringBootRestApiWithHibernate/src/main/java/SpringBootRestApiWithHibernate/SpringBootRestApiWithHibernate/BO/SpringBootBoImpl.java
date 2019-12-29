package SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.BO;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DAO.SpringBootDAO;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Employee;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Student;

@Component
public class SpringBootBoImpl implements SpringBootBO {
	@Autowired
	private SpringBootDAO springBootDao;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	public void saveEmployeeDetail(Employee employee) {
		logger.info("SpringBootBoImpl :-> saveEmployeeDetail()");
		springBootDao.saveEmployeeDetail(employee);
		
	}
	public List<Employee> getEmployeeDetail() {
		logger.info("SpringBootBoImpl :-> getEmpDetail()");
		List<Employee> employeeDetailList =  springBootDao.getEmployeeDetail();
		return employeeDetailList;
	}

	public List<Student> getStudentDetail() {
		logger.info("SpringBootBoImpl :-> getStudentDetail()");
		List<Student> studentDetailList =  springBootDao.getStudentDetail();
		return studentDetailList;
	}
	public void saveStudentDetail(Student student) {
		logger.info("SpringBootBoImpl :-> saveStudentDetail()");
		springBootDao.saveStudentDetail(student);
	}

}
