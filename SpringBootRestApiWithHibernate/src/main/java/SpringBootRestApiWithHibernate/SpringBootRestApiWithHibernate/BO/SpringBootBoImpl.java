package SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.BO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mongodb.client.FindIterable;

import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DAO.SpringBootDAO;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Employee;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.DTO.Student;
import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.MongoDb.MongoConnectionClass;

@Component
public class SpringBootBoImpl implements SpringBootBO {
	@Autowired
	private SpringBootDAO springBootDao;
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Transactional
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
	
	@Transactional
	public void saveStudentDetail(Student student) {
		logger.info("SpringBootBoImpl :-> saveStudentDetail()");
		springBootDao.saveStudentDetail(student);
	}
	
	public Student getStudentDetailById(Integer studentId) {
		logger.info("SpringBootBoImpl :-> getStudentDetailById()");
		return springBootDao.getStudentDetailById(studentId);
	}

	@Override
	public List<Map<String, Object>> getEmployeeActivityHistory() {
		logger.info("SpringBootBoImpl :-> getEmployeeActivityHistory()");
		List<Map<String, Object>> empActivityHistoryList = new ArrayList<>();
		MongoConnectionClass mongoConnection = new MongoConnectionClass();
		FindIterable<Document> collection =  mongoConnection.getMongoDb().getCollection("empActivityHistory").find();
		Iterator<Document> iterator = collection.iterator();
		while(iterator.hasNext()) {
			Document document = iterator.next();
			Map<String, Object> map = new HashMap<String, Object>(document);
			empActivityHistoryList.add(map);
		}
		return empActivityHistoryList;
	}

	@Override
	public void saveEmployeeActivity(JSONArray empActivityArray) throws JSONException {
		logger.info("SpringBootBoImpl :-> saveEmployeeActivity()");
		Gson gson = new Gson();
		ArrayList<Document> documentList = new ArrayList<>();
		MongoConnectionClass mongoConnection = new MongoConnectionClass();
		for(int i=0; i<empActivityArray.length(); i++) {
			JSONObject jsonObject = (JSONObject) empActivityArray.get(i);
			documentList.add(Document.parse(jsonObject.toString()));
		}
		mongoConnection.getMongoDb().getCollection("empActivityHistory").insertMany(documentList);
	}

}
