package doa;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import domin.Person;

public interface IMyBatisDoa {
    public boolean insertPerson(Person person);

    public boolean deletePerson(String name);
	
    public String getPersonSexByName(String name);
    
    public String setPersonName(String oldName, String newName);
    
    public String getNameById(Integer id);
}
