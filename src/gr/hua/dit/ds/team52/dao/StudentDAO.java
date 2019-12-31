package gr.hua.dit.ds.team52.dao;

import gr.hua.dit.ds.team52.entity.Petition;
import gr.hua.dit.ds.team52.entity.Student;

import java.util.List;

public interface StudentDAO {

    public List<Student> getStudents();
    public List<Student> getStudent(String username);
    public Boolean saveStudent(Student student);
    public Boolean deleteStudent(String username);
    public List<Petition> getPetitions();
//    public Boolean savePetition(Petition petition,String studentUsername);
    public boolean savePetition(String title, String description, String student_username);
    public boolean updateStudent(String old_username, String username, String firstname, String lastname, String failed, String dept, String year);
    public boolean addStudent(String username, String password, String firstname, String lastname, String role, int failed, String dept, String year);

}
