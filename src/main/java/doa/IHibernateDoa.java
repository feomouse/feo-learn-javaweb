package doa;

import domin.Person;

public interface IHibernateDoa {
    public boolean insertPerson(Person person);

    public boolean deletePerson(String name);
    
    public String getPersonSexByName(String name);
    
    public String setPersonName(String oldName, String newName);
    
    public String getNameById(Integer id);
}
