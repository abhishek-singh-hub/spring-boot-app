package SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DAO;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Employee;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.EmployeeRepository;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Student;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.StudentRepository;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.hibernate.HibernateDbDaoSupport;

@Component
public class SpringBootDaoImpl implements SpringBootDAO {
	
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private HibernateDbDaoSupport hibernateDbDaoSupport;
	
	public List<Employee> getEmployeeDetail() {
		logger.info("SpringBootDaoImpl :--> getEmpDetail()");
		javax.persistence.Query query = hibernateDbDaoSupport.getEntityManager().createQuery("from Employee");
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

	public void saveStudentDetail(Student student) {
		logger.info("SpringBootDaoImpl :--> saveStudentDetail()");
		studentRepository.save(student);
	}

	public Student getStudentDetailById(Integer studentId) {
		logger.info("SpringBootDaoImpl :--> saveStudentDetail()");
		Query query = hibernateDbDaoSupport.getSession().createQuery("from Student where id=:id");
		query.setParameter("id", studentId);
		return (Student) query.uniqueResult();
	}
}
