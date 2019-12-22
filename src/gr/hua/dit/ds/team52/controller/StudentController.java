package gr.hua.dit.ds.team52.controller;

import gr.hua.dit.ds.team52.actors.StudentEntity;
import gr.hua.dit.ds.team52.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    //Inject DAO its the same for all objects
    @Autowired
    private UserDAO userDAO;

    @RequestMapping("/list")
    public String listStudents(Model model){

        // get customers from dao
        List<StudentEntity> students = userDAO.getStudentList();

        // add the customers to the model
        model.addAttribute("students", students);

        return "list-students";
    }



}
