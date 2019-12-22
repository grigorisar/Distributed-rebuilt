package gr.hua.dit.ds.team52.dao;

import gr.hua.dit.ds.team52.actors.StudentEntity;


import java.util.List;

public interface UserDAO {

    public List<StudentEntity> getStudentList();

    public void addStudent(String username, String password, String firstname, String lastname, String role, int failed, String dept, String year);

}
