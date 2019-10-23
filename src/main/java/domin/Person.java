package domin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Table;

@Entity( name = "Person" )
public class Person {
  private Integer id;	
	
  private String name;
  
  private String sex;
  
  public Person() {
	  
  }
  
  public Person(Integer id, String name, String sex) {
	  this.id = id;
	  this.name = name;
	  this.sex = sex;
  }
  
  @Id
  @GeneratedValue(generator="increment")
  @GenericGenerator(name="increment", strategy="increment")
  public Integer getId() {
	  return id;
  }
  
  public void setId(Integer id) {
	  this.id = id;
  }
  
  @Column(name = "name")
  public String getName() {
	  return name;
  }
 
  public void setName(String name) {
	  this.name = name;
  }
  
  @Column(name = "sex")
  public String getSex() {
	  return sex;
  }
  
  public void setSex(String sex) {
	  this.sex = sex;
  }
}
