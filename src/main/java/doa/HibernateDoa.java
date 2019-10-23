package doa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import domin.Person;

@Repository
@Scope
public class HibernateDoa implements IHibernateDoa {
    private final StandardServiceRegistry registry;
    
    private final SessionFactory sessionFactory;
	
	public HibernateDoa() {
		registry = new StandardServiceRegistryBuilder().configure().build();
		
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
	
    public boolean insertPerson(Person person) {
    	Session session = sessionFactory.openSession();
    	try {
        	session.beginTransaction();
        	session.save( person );
        	session.getTransaction().commit();
    	}
    	
    	catch (Exception e) {
    		if ( session.getTransaction().getStatus() == TransactionStatus.ACTIVE
    				|| session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK ) {
    			session.getTransaction().rollback();
    		}
    	}
    	
    	finally {
    		session.close();
        	return true;
    	}
    }

    public boolean deletePerson(String name) {
    	Session session = sessionFactory.openSession();
    	boolean result = true;
    	
    	try {
        	session.beginTransaction();
        	session.createQuery("DELETE FROM Person WHERE name = :name")
        			.setParameter("name", name)
        			.executeUpdate();
        	
        	session.getTransaction().commit();
    	}
    	
    	catch (Exception e) {
    		if ( session.getTransaction().getStatus() == TransactionStatus.ACTIVE
    				|| session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK ) {
    			session.getTransaction().rollback();
    			result = false;
    		}
    	}
    	
    	finally {
    		session.close();
        	return result;
    	}
    }
    
    public String getPersonSexByName(String name) {
    	Session session = sessionFactory.openSession();
    	Person p = null;
    	
    	try {
        	p = session.createQuery("from Person p WHERE p.name = :name", Person.class)
					.setParameter("name", name)
        			.getSingleResult();
    	}
    	
    	catch (Exception e) {

    	}
    	
    	finally {
    		session.close();
        	return p.getSex();
    	}
    }
    
    public String getNameById(Integer id) {
    	Session session = sessionFactory.openSession();
    	Person p = null;
    	
    	try {
        	p = session.get(Person.class, id);
    	}
    	
    	catch (Exception e) {

    	}
    	
    	finally {
    		session.close();
        	return p.getName();
    	}
    }
    
    public String setPersonName(String oldName, String newName) {
    	Session session = sessionFactory.openSession();
    	int res = 0;
    	try {
    		session.beginTransaction();
    		
        	res = session.createQuery("UPDATE Person p SET p.name = :newName WHERE p.name = :oldName")
					.setParameter("newName", newName)
					.setParameter("oldName", oldName)
        			.executeUpdate();
        	
        	session.getTransaction().commit();
    	}
    	
    	catch (Exception e) {
    		if ( session.getTransaction().getStatus() == TransactionStatus.ACTIVE
    				|| session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK ) {
    			session.getTransaction().rollback();
    		}
    	}
    	
    	finally {
    		session.close();
        	return res != 0 ? newName : oldName;
    	}
    }
}
