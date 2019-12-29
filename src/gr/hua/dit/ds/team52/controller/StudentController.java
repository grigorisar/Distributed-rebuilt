package gr.hua.dit.ds.team52.controller;

import gr.hua.dit.ds.team52.actors.StudentEntity;
import gr.hua.dit.ds.team52.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
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

    @RequestMapping("/create_petition")
    public String petitionForm() {
        return "create-petition";
    }

    @ResponseBody
    @PostMapping(value = "/create_petition_process", produces = "plain/text")
    public String createPetition(WebRequest request , HttpServletResponse response , Model model) {
        String title = request.getParameter("title");

        String description = request.getParameter("description");

        String student_username = request.getParameter("student_username");

        int v = userDAO.addPetition( title, description, student_username );

        if ( v == 0 ) return "Petition with the same title already exists";

        return "Petition successfully added";
    }


}
