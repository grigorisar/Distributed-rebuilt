package gr.hua.dit.ds.team52.controller;

import gr.hua.dit.ds.team52.dao.StudentDAO;
import gr.hua.dit.ds.team52.entity.Petition;
import gr.hua.dit.ds.team52.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private StudentDAO studentDAO;

    @RequestMapping("/")
    public String showOptions(Model model){
        return "student/startpage-student";
    }

    @RequestMapping("/list")
    public String listStudents(Model model){

        // get customers from dao
        List<Student> students = studentDAO.getStudents();

        // add the customers to the model
        model.addAttribute("students", students);

        return "student/list-students";
    }

    @RequestMapping("/new_petition")
    public String createPetitionJSP(Model model){
        return "student/create-petition";
    }

    @RequestMapping("/create_petition")
    public String petitionForm() {
        return "student/create-petition";
    }

    @ResponseBody
    @PostMapping(value = "/create_petition_process", produces = "plain/text")
    public String createPetition(WebRequest request , HttpServletResponse response , Model model) {
        String title = request.getParameter("title");

        String description = request.getParameter("description");

        String currentUserName = null;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {            //not 100% sure what this if is about  TODO check this condition
            currentUserName = authentication.getName();                             //get the logged in user's username
        }

        List<Student> list = studentDAO.getStudent(currentUserName);    //find the rest of the logged-in student's credentials

        int year = list.get(0).getYear();   //usernames are unique we can safely the first one from the list

        int failedClasses = list.get(0).getFailed();

        if ( (year != 3 && year != 4) && failedClasses > 3) {       //check if the student can petition
            return "Student cannot petition!";
        } else {

            Petition petition = new Petition(title, description, "pending");

            boolean v = studentDAO.savePetition(title, description, currentUserName);

            if ( v ) return "Petition successfully added";

            return "Petition with the same title already exists";
        }



    }


}
