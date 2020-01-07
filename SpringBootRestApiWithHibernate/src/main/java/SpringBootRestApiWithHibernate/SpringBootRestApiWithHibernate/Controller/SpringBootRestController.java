package SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.Controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.BO.SpringBootBO;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Employee;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Student;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/restapi")
public class SpringBootRestController {
	@Autowired
	private SpringBootBO springBootBo;
	
	@RequestMapping("/getservername")
	public String getServerName() {
		return "Welcome to Spring Rest Server";
	}
	@RequestMapping("/getEmpDetail")
	public String getEmployeeDetail() {
		Gson gson = new Gson();
		List<Employee> empDetailList = springBootBo.getEmployeeDetail();
		return gson.toJson(empDetailList);
	}
	
	@RequestMapping("/saveEmpDetail")
	public String saveEmployeeDetail(@RequestBody Employee employee) {
		System.out.println("AKS");
		System.out.println(employee.getId());
		springBootBo.saveEmployeeDetail(employee);
		return null;
	}
	
	@RequestMapping("/getStudentDetail")
	public List<Student> getStudentDetail() {
		List<Student> studentDetail = springBootBo.getStudentDetail();
		return studentDetail;
	}
	
	@RequestMapping("/saveStudentDetail")
	public String saveStudentDetail(@RequestBody Student student) {
		springBootBo.saveStudentDetail(student);
		return null;
	}
	@RequestMapping("/getstudentdetailbyId/{studentId}")
	public Student getDetailById(@PathVariable(value = "studentId") Integer studentId) {
		return springBootBo.getStudentDetailById(studentId);
	}
}
