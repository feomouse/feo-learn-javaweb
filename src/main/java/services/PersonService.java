package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import doa.IHibernateDoa;
import doa.IMyBatisDoa;
import domin.Person;

@Service
public class PersonService implements IPersonService {
	//@Autowired
	//private IHibernateDoa doaApi;
	
	@Autowired
	private IMyBatisDoa doaApi;
	
	public PersonService() {
		
	}
	
    public boolean insertPerson(Person person) {
    	return doaApi.insertPerson(person);
    }

    public boolean deletePerson(String name) {
    	return doaApi.deletePerson(name);
    }
    
    public String getPersonSexByName(String name) {
    	return doaApi.getPersonSexByName(name);
    }
    
    public String setPersonName(String oldName, String newName) {
    	return doaApi.setPersonName(oldName, newName);
    }
}
