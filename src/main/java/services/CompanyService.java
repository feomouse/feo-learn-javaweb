package services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import doa.IHTableReDoa;
import domin.Apartment;
import domin.Company;
import domin.Project;

@Service
public class CompanyService implements ICompanyService {
	@Autowired
	private IHTableReDoa doa;
	
	public Set<String> getApartmentsNameByCompany(String companyName) {
		return doa.getApartmentsNameByCompany(companyName);
	}
	
	public boolean addCompany(Company company) {
		return doa.addCompany(company);
	}
	
	public boolean updateCompanyName(String oldName, String newName) {
		return doa.updateCompanyName(oldName, newName);
	}
	
	public boolean deleteCompany(String companyName) {
		return doa.deleteCompany(companyName);
	}
	
	public boolean addApartmentToCompany(Apartment apartment, String companyName) {
		return doa.addApartmentToCompany(apartment, companyName);
	}
	
	public boolean addProject(Project project, List<String> apartmentNames) {
		return doa.addProject(project, apartmentNames);
	}
	
	public boolean attachProjectsToApartment(String apartmentName, List<String> projectsName) {
		return doa.attachProjectsToApartment(apartmentName, projectsName);
	}
}
