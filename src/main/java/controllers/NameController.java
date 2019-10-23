package controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domin.Person;
import services.IPersonService;

@RestController
public class NameController {
	@Autowired
	private IPersonService pservice;
	
    @RequestMapping(value="/get-sex")
    public String getSex(@RequestParam(value="name") String name) {
    	return pservice.getPersonSexByName(name);
    }
    
    @RequestMapping(value="/insert-person", method=RequestMethod.POST)
    public void insertPerson(@RequestBody Person person) {
    	pservice.insertPerson(person);
    }
    
    @RequestMapping(value="/delete-person", method=RequestMethod.POST)
    public String deletePerson(@RequestBody Map<String, String> name) {
    	return pservice.deletePerson(name.get("name")) == false ? "no" : "sus";
    }
    
    @RequestMapping(value="/update-name", method=RequestMethod.POST)
    public String updatePersonName(@RequestBody List<Map<String, String>> nameInfo) {
    	return pservice.setPersonName(nameInfo.get(0).get("oldName"), nameInfo.get(1).get("newName"));
    }
}
