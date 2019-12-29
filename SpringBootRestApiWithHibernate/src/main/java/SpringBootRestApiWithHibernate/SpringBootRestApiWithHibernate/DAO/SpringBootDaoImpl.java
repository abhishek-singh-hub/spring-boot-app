package SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Employee;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.EmployeeRepository;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Student;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.StudentRepository;

@Component
public class SpringBootDaoImpl implements SpringBootDAO {
	
	@Autowired
	private EntityManager entityManager;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Employee> getEmployeeDetail() {
		logger.info("SpringBootDaoImpl :--> getEmpDetail()");
		Query query = entityManager.createQuery("from Employee");
		return query.getResultList();
	}
	
	public List<Student> getStudentDetail() {
		logger.info("SpringBootDaoImpl :--> getEmpDetail()");
		return studentRepository.findAll();
	}

	public void saveEmployeeDetail(Employee employee) {
		logger.info("SpringBootDaoImpl :--> saveEmployeeDetail()");
		employeeRepository.save(employee);
	}
}
