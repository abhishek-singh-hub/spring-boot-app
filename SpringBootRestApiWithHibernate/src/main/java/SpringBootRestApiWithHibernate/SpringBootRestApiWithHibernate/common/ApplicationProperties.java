package SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.common;

import java.io.InputStream;
import java.util.MissingResourceException;
import java.util.Properties;

public class ApplicationProperties {
	
	private static Properties applicationProperties;
	static {
		try {
			InputStream is  = Thread.currentThread().getContextClassLoader().getResourceAsStream("app.properties");
			applicationProperties = new Properties();
			applicationProperties.load(is);
		} catch (Exception e) {
			throw new RuntimeException("Coudnt read application properties from class path", e);
		}
	}
	
	 private ApplicationProperties() {}
	 public static String getValue(String key) {
        try {
           return applicationProperties.getProperty(key);
        } catch (MissingResourceException e) {
        	e.printStackTrace();
           return '@' + key + '@';
        }
    }
}
