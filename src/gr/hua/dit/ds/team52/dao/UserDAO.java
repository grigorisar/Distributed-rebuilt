package gr.hua.dit.ds.team52.dao;

import gr.hua.dit.ds.team52.actors.StudentEntity;


import java.util.List;

public interface UserDAO {

    public List<StudentEntity> getStudentList();

    public int addStudent(String username, String password, String firstname, String lastname, String role, int failed, String dept, String year);

    public int addStaff(String username, String password, String firstname, String lastname, String role, String position) ;

    public int addPetition (String title, String description, String student_username);

    public int updateStudent(String old_username, String username, String firstname, String lastname, int failed, String dept, String year);

    public int deleteStudent(String username);

    }
