package gr.hua.dit.ds.team52.dao;

import gr.hua.dit.ds.team52.entity.Customer;
import gr.hua.dit.ds.team52.entity.Petition;
import gr.hua.dit.ds.team52.entity.Student;

import java.util.List;

public interface CustomerDAO {

    public List<Customer> getCustomers();
    public List<Student> getStudents();
    public List<Petition> getPetitions();

}
