package controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import domin.Person;
import services.IPersonService;

@Controller
@Scope("prototype")
@ParentPackage("json-default")
public class NameAction extends ActionSupport {
	@Autowired
	private IPersonService pservice;
	
	private String name;
	private Integer id;
	private String sex;
	private String oldName;
	private String newName;
	
	public void setOldName(String oldName) {
		this.oldName = oldName;
	}
	
	public void setNewName(String newName) {
		this.newName = newName;
	}
	
	public String getOldName() {
		return oldName;
	}
	
	public String getNewName() {
		return newName;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setSex(String sex) {
		this.sex = sex;
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getSex() {
		return this.sex;
	}
	
	@Action(value="/", results= {@Result(name="getIndex", location="index.jsp")})
    public String getIndex() {
        return "getIndex";
    }

	@Action(value="/get-sex", results= {@Result(name="getPersonSex", location="getsex.jsp")})
    public String getPersonSex() {
		ActionContext context = ActionContext.getContext();
		String res = pservice.getPersonSexByName(this.name);
		Map<String,Object> request = (Map) context.get("request");		
		request.put("res", res);
    	return "getPersonSex";
    }
	
	@Action(value="/insert-person", 
			results= {@Result(name="insertPerson", location="insertperson.jsp")},
	 		interceptorRefs= {@InterceptorRef("json")})
	public String insertPerson() {
		ActionContext context = ActionContext.getContext();
    	boolean res = pservice.insertPerson(new Person(this.id, this.name, this.sex));
    	context.put("res", res);
       	return "insertPerson";
    }
	
	@Action(value="/delete-person", 
			results= {@Result(name="deletePerson", location="deleteperson.jsp")},
	 		interceptorRefs= {@InterceptorRef("json")})
    public String deletePerson() {
		ActionContext context = ActionContext.getContext();
    	String res = pservice.deletePerson(this.name) == false ? "no" : "sus";
    	context.put("res", res);
       	return "deletePerson";
	}
	
	@Action(value="/update-name", 
			results= {@Result(name="updatePersonName", location="updatepersonname.jsp")},
	 		interceptorRefs= {@InterceptorRef("json")})
    public String updatePersonName() {
		ActionContext context = ActionContext.getContext();
    	String res = pservice.setPersonName(this.oldName, this.newName);
       	context.put("res", res);
    	return "updatePersonName";    
	}
}
