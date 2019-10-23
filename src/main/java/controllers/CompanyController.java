package controllers;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.type.DateType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import domin.Apartment;
import domin.Company;
import domin.Project;
import services.ICompanyService;

@RestController
public class CompanyController {
	@Autowired
	private ICompanyService cservice;
	
    @RequestMapping(value="/get-apartmentname")
    public Set<String> getApartmentsNameByCompany(@RequestParam(value="companyName") String companyName) {
    	return cservice.getApartmentsNameByCompany(companyName);
    }
    
    @RequestMapping(value="/add-company", method=RequestMethod.POST)
    public void addCompany(@RequestBody Company company) {
    	cservice.addCompany(company);
    }
    
    @RequestMapping(value="/update-companyname", method=RequestMethod.POST)
    public String updateCompanyName(@RequestBody Map<String, String> name) {
    	return cservice.updateCompanyName(name.get("oldName"), name.get("newName")) == false ? "no" : "sus";
    }
    
    @RequestMapping(value="/delete-company", method=RequestMethod.POST)
    public String deleteCompany(@RequestBody Map<String, String> companyName) {
    	return cservice.deleteCompany(companyName.get("companyName"))== false ? "no" : "sus";
    }
    
    @RequestMapping(value="/add-project", method=RequestMethod.POST)
    public void addProject(@RequestBody Map<String, Object> info) throws ParseException {
    	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date startTime = formatter.parse((String) info.get("startTime"));
    	Date endTime = formatter.parse((String) info.get("endTime"));
    	cservice.addProject(new Project((String)info.get("name"), startTime , endTime), (List<String>)info.get("apartmentsName"));
    }
    
    @RequestMapping(value="/attach-projecttoapartment", method=RequestMethod.POST)
    public void attachProjectsToApartment(@RequestBody Map<String, Object> apartment) {
    	cservice.attachProjectsToApartment((String)apartment.get("apartmentName"), (List<String>)apartment.get("projectsName"));
    }
}
