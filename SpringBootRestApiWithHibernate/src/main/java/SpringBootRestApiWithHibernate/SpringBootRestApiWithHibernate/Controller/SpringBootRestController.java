package SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.Controller;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.mongodb.client.MongoCollection;

import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.BO.SpringBootBO;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Employee;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Student;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.common.ApplicationProperties;

@RestController
//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/restapi")
public class SpringBootRestController {
	@Autowired
	private SpringBootBO springBootBo;
	
	@RequestMapping("/getservername")
	public String getServerName() {
		return ApplicationProperties.getValue("app.welcome.msg");
	}
	
	@RequestMapping(value = "/getEmpDetail", method = {RequestMethod.GET}, produces = "application/json")
	public ResponseEntity getEmployeeDetail() {
		try {
			List<Employee> empDetailList = springBootBo.getEmployeeDetail();
			if (empDetailList.size() == 0) {
				return new ResponseEntity<String>(ApplicationProperties.getValue("app.no.record"), HttpStatus.OK);
			}
			return new ResponseEntity<List<Employee>>(empDetailList, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/saveEmpDetail", method = {RequestMethod.POST}, consumes = "application/json")
	public ResponseEntity saveEmployeeDetail(@RequestBody Employee employee) {
		try {
			springBootBo.saveEmployeeDetail(employee);
			return new ResponseEntity<String>(ApplicationProperties.getValue("app.save.success"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value  = "/getStudentDetail", method = {RequestMethod.GET} , produces = "application/json")
	public ResponseEntity getStudentDetail() {
		try {
			List<Student> studentDetail = springBootBo.getStudentDetail();
			if(studentDetail.size()==0) {
				return new ResponseEntity<String>(ApplicationProperties.getValue("app.no.record"), HttpStatus.OK);
			}
			return new ResponseEntity<List<Student>>(studentDetail, HttpStatus.OK);
		} catch (Exception e){
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/saveStudentDetail", method = {RequestMethod.POST}, consumes = "application/json")
	public String saveStudentDetail(@RequestBody Student student) {
		try {
			springBootBo.saveStudentDetail(student);
			return ApplicationProperties.getValue("app.save.success");
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
	}
	@RequestMapping(value = "/getstudentdetailbyId/{studentId}", method = {RequestMethod.GET}, produces = "application/json")
	public ResponseEntity getDetailById(@PathVariable(value = "studentId") Integer studentId) {
		try {
			Student studentDetail = springBootBo.getStudentDetailById(studentId);
			if(studentDetail == null) {
				return new ResponseEntity<String>(ApplicationProperties.getValue("app.no.record"), HttpStatus.OK);
			} 
			return new ResponseEntity<Student>(studentDetail, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/getEmpActivityHistory", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity getEmployeeActivityHistory() {
		try {
			List<Map<String, Object>> empActivityHistory = springBootBo.getEmployeeActivityHistory();
			return new ResponseEntity<List>(empActivityHistory,  HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();//aks
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
		
	}
	@RequestMapping(value = "/saveEmpActivityHistory", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity saveEmployeeActivityHistory(@RequestBody String empActivityArray) {
		try {
			JSONObject jsonObject = new JSONObject(empActivityArray);
			JSONArray jsonArr = (JSONArray) jsonObject.get("history");
			springBootBo.saveEmployeeActivity(jsonArr);
			return new ResponseEntity<String>(ApplicationProperties.getValue("app.save.success"), HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.OK);
		}
	}
}
