package SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.MongoDb;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;

import SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.common.ApplicationProperties;

public class MongoConnectionClass {
	private MongoClient mongo = null;
	private static MongoConnectionClass instance = new MongoConnectionClass();
	final static String host = ApplicationProperties.getValue("app.nosql.host");
	final static String port = ApplicationProperties.getValue("app.nosql.port");
	final static String database = ApplicationProperties.getValue("app.nosql.database");
	final static String username = ApplicationProperties.getValue("app.nosql.username");
	final static String password = ApplicationProperties.getValue("app.nosql.password");
	public void createCollection() {
		instance.getMongoDb().createCollection("javaCol");
		System.out.println("Collection Created Successfully");
	}
	public void saveDataInMongoDb(List<Document> documentList) {
		instance.getMongoDb().getCollection("javaCol").insertMany(documentList);
	}
	
	public MongoDatabase getMongoDb() {
		if(mongo == null) {
			// Creating a Mongo client 
			mongo = new MongoClient(host, Integer.parseInt(port));
			// Creating Credentials
			MongoCredential mongoCredential = MongoCredential.createCredential(username, database, password.toCharArray());
			System.out.println("Connected to the "+ database +" database successfully");
			
		}
		// Accessing the database 
		return mongo.getDatabase(database);
	}
	
	public void close() {
		System.out.println("Closing MongoDB connection");
		if (mongo != null) {
			try {
				mongo.close();
				mongo = null;
			} catch (Exception e) {
				System.err.println("An error occurred when closing the MongoDB connection\n" + e.getMessage());
			}
		} else {
			System.err.println("Mongo connection is already closed");
		}
	}

}
