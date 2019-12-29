package SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * SpringBoot with Hibernate Test
 *
 */

@SpringBootApplication
public class SpringBootApp
{
    public static void main( String[] args )
    {
    	SpringApplication.run(SpringBootApp.class, args);
    }
}
