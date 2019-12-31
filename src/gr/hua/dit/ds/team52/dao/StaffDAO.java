package gr.hua.dit.ds.team52.dao;


import gr.hua.dit.ds.team52.entity.Internship;
import gr.hua.dit.ds.team52.entity.Petition;
import gr.hua.dit.ds.team52.entity.Service;
import gr.hua.dit.ds.team52.entity.Staff;

import java.util.List;

public interface StaffDAO {
    public List<Petition> getPetitions();
    public Service searchService(String title);
    public List<Staff> getStaff();
    public List<Internship> getInternships();
    public Boolean saveStaff(Staff staff);
    public Boolean deleteStaff(String username);
    public boolean updateStaff(String old_username, String username, String firstname, String lastname, String position);
    public boolean addStaff(String username, String password, String firstname, String lastname, String role, String position);
}
