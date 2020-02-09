package gr.hua.dit.ds.team52.dao;

import gr.hua.dit.ds.team52.entity.Company;
import gr.hua.dit.ds.team52.entity.Internship;

import java.util.List;

public interface CompanyDAO {
    public Company company = new Company("admin","admin");

    public void saveCompany(Company company);
    public void deleteCompany(Company company);
    public List<Company> getCompanies();
    public Company getCompanyByUsername(String username);
    public Internship getInternshipByName(String name);
    public Internship getInternshipByID(int id);


}
