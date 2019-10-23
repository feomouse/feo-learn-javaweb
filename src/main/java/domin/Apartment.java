package domin;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="apartment")
public class Apartment {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="location")
	private String location;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="company_id")
	private Company company;
	
	@ManyToMany
	@JoinTable(name="a_p_relation",
			joinColumns=@JoinColumn(name="apartmentid", referencedColumnName="id"),
			inverseJoinColumns=@JoinColumn(name="projectid", referencedColumnName="id"))
	private Set<Project> projects;
	
	public Apartment() {
		
	}
	
	public Apartment(String name, String location) {
		this.name = name;
		this.location = location;
	}
	
	public Apartment(Integer id, String name, String location, Company company, Set<Project> projects) {
		this.id = id;
		this.name = name;
		this.location = location;
		this.company = company;
		this.projects = projects;
	}
	
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
	public void addProjects(Project project) {
		this.projects.add(project);
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setCompany(Company company) {
		this.company = company;
	}
	
	public Set<Project> getProjects() {
		return projects;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public Company getCompany() {
		return company;
	}
}
