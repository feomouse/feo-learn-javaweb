package doa;

import java.util.List;
import java.util.Set;

import domin.Apartment;
import domin.Company;
import domin.Project;

public interface IHTableReDoa {
	public Set<String> getApartmentsNameByCompany(String companyName);
	
	public boolean addCompany(Company company);
	
	public boolean updateCompanyName(String oldName, String newName);
	
	public boolean deleteCompany(String companyName);
	
	public boolean addApartmentToCompany(Apartment apartment, String companyName);
	
	public boolean addProject(Project project, List<String> apartmentNames);
	
	public boolean attachProjectsToApartment(String apartmentName, List<String> projectsName);
}
