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
		try {
			Gson gson = new Gson();
			List<Employee> empDetailList = springBootBo.getEmployeeDetail();
			return gson.toJson(empDetailList);
		} catch(Exception e) {
			e.printStackTrace();
			return "Exception occured while getting the student Detail!"+e.getMessage();
		}
	}
	
	@RequestMapping("/saveEmpDetail")
	public String saveEmployeeDetail(@RequestBody Employee employee) {
		try {
			springBootBo.saveEmployeeDetail(employee);
			return "Detail Saved Successfully";
		} catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	
	@RequestMapping("/getStudentDetail")
	public List<Student> getStudentDetail() {
		try {
			List<Student> studentDetail = springBootBo.getStudentDetail();
			return studentDetail;
		} catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	@RequestMapping("/saveStudentDetail")
	public String saveStudentDetail(@RequestBody Student student) {
		try {
			springBootBo.saveStudentDetail(student);
			return "Detail Saved Successfully";
		} catch(Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	@RequestMapping("/getstudentdetailbyId/{studentId}")
	public Student getDetailById(@PathVariable(value = "studentId") Integer studentId) {
		try {
			return springBootBo.getStudentDetailById(studentId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
