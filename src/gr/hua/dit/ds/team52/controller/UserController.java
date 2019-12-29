package gr.hua.dit.ds.team52.controller;

import gr.hua.dit.ds.team52.actors.StudentEntity;
import gr.hua.dit.ds.team52.dao.UserDAO;
import gr.hua.dit.ds.team52.entity.Customer;
import gr.hua.dit.ds.team52.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("/user")

public class UserController {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

    @Autowired
    UserDAO userDAO;

    @RequestMapping("/")
    public String showMyPage() {
        return "index";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @RequestMapping("/create_user")
    public String userForm() {
        return "create-user";
    }

    @ResponseBody
    @PostMapping(value = "/create_user_process", produces = "plain/text")
    public String createUser(WebRequest request ,HttpServletResponse response ,Model model) {

        String role = request.getParameter("role");

        String username = request.getParameter("username");

        String password = request.getParameter("password");

        try {
            password = encoder.encode(password);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        String firstname = request.getParameter("firstname");

        String lastname = request.getParameter("lastname");


        if (role.contentEquals("Student" ) ) {
            int failed = Integer.parseInt(request.getParameter("failed"));

            String dept = request.getParameter("dept");

            String year = request.getParameter("year");

            System.out.println("here");

            role = "ROLE_" + role.toUpperCase();

            int v = userDAO.addStudent( username, password, firstname, lastname, role, failed, dept, year);

            if ( v == 0 ) return "User Already Exists";

        } else {
            String position = request.getParameter("position");

            role = "ROLE_" + role.toUpperCase();

            int v = userDAO.addStaff( username, password, firstname, lastname, role, position);

            if ( v == 0 ) return "User Already Exists";

        }

        return "Registration Successful?";
    }

    @ResponseBody
    @PostMapping(value = "/update_student_process", produces = "plain/text")
    public String updateStudent(WebRequest request ,HttpServletResponse response ,Model model) {

        String old_username = request.getParameter("old_username");

        String username = request.getParameter("username");

        String dept = request.getParameter("dept");

        String year = request.getParameter("year_u");

        String firstname = request.getParameter("firstname");

        String lastname = request.getParameter("lastname");

        int failed = Integer.parseInt(request.getParameter("failed"));
        int v =0;

            try {

                v = userDAO.updateStudent(old_username, username, firstname, lastname, failed, dept, year);

            } catch (Exception e) {

            }
            if ( v == 0 ) return "Update Aborted";

        return "Update Successful";
    }

    @ResponseBody
    @PostMapping(value = "/delete_student_process", produces = "plain/text")
    public String deleteStudent(WebRequest request ,HttpServletResponse response ,Model model) {

        int v=0;
        String username = request.getParameter("username_d");
        try {

            v = userDAO.deleteStudent(username);

        } catch (Exception e) {

        }
        if ( v == 0 ) return "Deletion Failed";

        return "Deletion Successful";
    }

    @RequestMapping("/student-manager")
    public String listCustomers(Model model) {
        // get student from dao
        List<StudentEntity> students = userDAO.getStudentList();

        // add the customers to the model
        model.addAttribute("students", students);

        return "student-manager";
    }
}
