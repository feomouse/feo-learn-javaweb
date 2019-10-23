package services;

import domin.Person;

public interface IPersonService {
    public boolean insertPerson(Person person);

    public boolean deletePerson(String name);
    
    public String getPersonSexByName(String name);
    
    public String setPersonName(String oldName, String newName);
}
