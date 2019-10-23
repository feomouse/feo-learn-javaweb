package domin;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;

@Entity(name="company")
public class Company {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy="increment")
	private Integer id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="type")
	private String type;
	
	@Column(name="location")
	private String location;
	
	@OneToMany(mappedBy="company", cascade = {CascadeType.ALL})
	private Set<Apartment> apartments = new HashSet<Apartment>();
	
	public void setId(Integer id) {
	    this.id = id;	
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public void setApartments(Set<Apartment> apartments) {
		this.apartments = apartments;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	public String getLocation() {
		return location;
	}
	
	public Set<Apartment> getApartments() {
		return apartments;
	}
	
	public void addApartment(Apartment apartment) {
		this.apartments.add(apartment);
	}
}
