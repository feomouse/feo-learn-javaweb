package doa;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import domin.Apartment;
import domin.Company;
import domin.Project;

@Repository
@Scope
public class HTableReDoa implements IHTableReDoa {
    private final StandardServiceRegistry registry;
    
    private final SessionFactory sessionFactory;
    
	public HTableReDoa() {
		registry = new StandardServiceRegistryBuilder().configure().build();
		
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }
	
	public Set<String> getApartmentsNameByCompany(String companyName) {
    	Session session = sessionFactory.openSession();
    	Set<String> res = new HashSet<String>();
    	
    	try {
        	session.beginTransaction();
        	Company c = session.createQuery("from company where name = :companyName", Company.class).setParameter("companyName", companyName).getSingleResult();

        	session.getTransaction().commit();
        	
        	for(Apartment ap : c.getApartments()) {
        		res.add(ap.getName());
        	}
    	}
    	
    	catch (Exception e) {
    		if ( session.getTransaction().getStatus() == TransactionStatus.ACTIVE
    				|| session.getTransaction().getStatus() == TransactionStatus.MARKED_ROLLBACK ) {
    			session.getTransaction().rollback();
    		}
    	}
    	
    	finally {
    		session.close();
        	return res;
    	}
	}
	
	public boolean addCompany(Company company) {
    	Session session = sessionFactory.openSession();
        Integer res = 0;
    	
    	try {
        	session.beginTransaction();
        	session.save(company);
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
        	return res != 0 ? true :false;
    	}
	}
	
	public boolean updateCompanyName(String oldName, String newName) {
    	Session session = sessionFactory.openSession();
        Integer res = 0;
    	
    	try {
        	session.beginTransaction();
        	res = session.createQuery("update company c set c.name = :newName where c.name = :oldName").
            		setParameter("newName", newName).
            		setParameter("oldName", oldName)
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
        	return res != 0 ? true :false;
    	}
	}
	public boolean deleteCompany(String companyName) {
    	Session session = sessionFactory.openSession();
        Integer res = 0;
    	
    	try {
        	session.beginTransaction();
        	res = session.createQuery("delete from company where name = :name").
            		setParameter("name", companyName)
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
        	return res != 0 ? true :false;
    	}
	}
	
	public boolean addApartmentToCompany(Apartment apartment, String companyName) {
    	Session session = sessionFactory.openSession();
        Integer res = 0;
    	
    	try {
        	session.beginTransaction();
        	Company company = session.createQuery("from company where name =:name", Company.class).setParameter("name", companyName).getSingleResult();
        	apartment.setCompany(company);
        	company.addApartment(apartment);
            res = (Integer) session.save(company);
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
        	return res != 0 ? true :false;
    	}
	}
	
	public boolean addProject(Project project, List<String> apartmentNames) {
    	Session session = sessionFactory.openSession();
        Integer res = 0;
        Set<Apartment> apartments = new HashSet<Apartment>();
        Apartment tempApartment = null;
    	
    	try {
        	session.beginTransaction();
            for (String aName : apartmentNames) {
            	Apartment apartment = session.createQuery("from apartment where name = :name", Apartment.class).
            		setParameter("name", aName).
            		getSingleResult();
            	
            	Set<Project> projects = new HashSet<Project>();

            	for (Project pro : apartment.getProjects()) {
            	    projects.add(pro);
            	}
                projects.add(project);
            	apartment.setProjects(projects);
            	apartments.add(apartment);
            	session.save(project);

            }
            project.setApartments(apartments);
            session.saveOrUpdate(project);
            
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
        	return res != 0 ? true :false;
    	}
	}
	
	public boolean attachProjectsToApartment(String apartmentName, List<String> projectsName) {
    	Session session = sessionFactory.openSession();
        Integer res = 0;
    	
    	try {
        	session.beginTransaction();
            Apartment apartment = session.createQuery("from apartment where name = :name", Apartment.class).
            	setParameter("name", apartmentName).
            	getSingleResult();
            
            for (String projectName : projectsName) {
            	Project pro = session.createQuery("from project where name = :name", Project.class).
            		setParameter("name", projectName).
            		getSingleResult();
            	
            	pro.addApartment(apartment);
            	
            	apartment.addProjects(pro);
            }
            res = (Integer) session.save(apartment);
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
        	return res != 0 ? true :false;
    	}	
	}
}
