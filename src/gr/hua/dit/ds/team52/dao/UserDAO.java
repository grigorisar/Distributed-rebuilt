package gr.hua.dit.ds.team52.dao;

import gr.hua.dit.ds.team52.entity.Authorities;
import gr.hua.dit.ds.team52.entity.Staff;
import gr.hua.dit.ds.team52.entity.Student;
import gr.hua.dit.ds.team52.entity.User;

import java.util.List;

public interface UserDAO {

    public Boolean saveAuthority(String username,String role);//TODO MAYBE DELETE THIS LATER

    public List<User> getUserList();
    public Boolean saveUser(User user);
    public Boolean deleteUser(String username);

    public List<Student> getStudents();
    public Student getStudentByUsername(String username);
    public Boolean saveStudent(Student student);

    public List<Staff> getStaff();
    public Boolean saveStaff(Staff staff);
    public Staff getStaffByUsername(String username);


}
