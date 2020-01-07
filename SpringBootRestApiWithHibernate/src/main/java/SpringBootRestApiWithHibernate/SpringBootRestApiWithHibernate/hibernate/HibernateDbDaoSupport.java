package SpringBootRestApiWithHibernate.SpringBootRestApiWithHibernate.hibernate;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HibernateDbDaoSupport {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private EntityManager entityManager;
	
	public Session getSession() {
		return entityManager.unwrap(Session.class);
	}
	
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
