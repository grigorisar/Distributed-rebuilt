package gr.hua.dit.ds.team52.controller;


import gr.hua.dit.ds.team52.dao.StaffDAO;
import gr.hua.dit.ds.team52.dao.StudentDAO;
import gr.hua.dit.ds.team52.dao.UserDAO;
import gr.hua.dit.ds.team52.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {
        // inject the customer dao
        @Autowired
        private StaffDAO staffDAO;

        @Autowired
        private UserDAO userDAO;

        @Autowired
        private StudentDAO studentDAO;

        private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

        @RequestMapping("/")
        public String showMenu(Model model) {
            return "manager/startpage-manager";
        }

        @RequestMapping("/user")
        public String userManager(Model model) {
            // get users from dao
            List<User> users = userDAO.getUserList();

            // add the users to the model
            model.addAttribute("users", users);

            return "manager/list-user"; //TODO user manager JSP
        }

        @RequestMapping("/studentmanager")
        public String StudentManager(Model model) {
            // get student from dao
            List<Student> students = studentDAO.getStudents();

            // add the customers to the model
            model.addAttribute("students", students);

            return "manager/student-manager";
        }

        @RequestMapping("/staffmanager")
        public String StaffManager(Model model) {
            // get student from dao
            List<Staff> staff = staffDAO.getStaff();

            // add the customers to the model
            model.addAttribute("staff", staff);

            return "manager/staff-manager";
        }


        @RequestMapping("/user/create")
        public String createUser(HttpServletRequest request,Model model){
            User user = new User();

            user.setUsername(request.getParameter("username"));
            user.setPassword(request.getParameter("password"));
            user.setEnabled(1);

            //Call userDAO and insert student
            userDAO.addUser(user);

            return "student/create-petition";
        }


        @RequestMapping("/role")
        public String roleManager(Model model) {
            // get roles from dao
//            List<Customer> customers = customerDAO.getCustomers();

            // add the roles to the model
            model.addAttribute("roles", null);
            return "redirect:/manager/";
        }


        @RequestMapping("/service")
        public String serviceManager(Model model) {
            // get services from dao
            List<Service> services = staffDAO.getServices();

            // add the services to the model
            model.addAttribute("services", services);

            return "manager/list-services";
        }

        @RequestMapping("/service/index")
        public String serviceManagerIndex(WebRequest request,Model model) {
            // get services from dao
            String title = request.getParameter("selectedService");
            Service service = staffDAO.searchService(title);
            model.addAttribute("service", service);
            return "manager/service-details";
        }

        // ================================================================================ \\

        @ResponseBody
        @PostMapping(value = "/create_user_process", produces = "plain/text")
        public String createUser(WebRequest request ) {

            String role = null,  username = null,  password = null, firstname = null, lastname = null, dept = null, year = null, position = null;

//            String type = request.getParameter("type");
            password = request.getParameter("password");

            int failed = 0;
            boolean v = true;

            try {
                password = encoder.encode(password);
                System.out.println(password);
            }catch (Exception e) {
            }

            role = request.getParameter("role");
            username = request.getParameter("username");
            firstname = request.getParameter("firstname");
            lastname = request.getParameter("lastname");

    //            User user = new User();
    //            user.setPassword(password);
    //            user.setEnabled(1);
    //            user.setUsername(username);

    //            v = userDAO.addUser(user);


            if (v) {                                                        // do not continue the process if inserting into the user table failed

                role = "ROLE_" + role.toUpperCase();

    //                Authorities authority = new Authorities();
    //                authority.setAuthority(role);
    //                authority.setUsername(username);
    //                userDAO.addAuthority(authority);

                if (role.contentEquals("ROLE_STUDENT" ) ) {             //check if it's a student or staff

                    failed = Integer.parseInt(request.getParameter("failed"));
                    dept = request.getParameter("dept");
                    year = request.getParameter("year");

//                    Student student = new Student(firstname, lastname, username, dept, year, failed);

//                    v = studentDAO.saveStudent( student);
                    v = studentDAO.addStudent(username, password, firstname, lastname, role, failed, dept, year);

                } else {

                    position = request.getParameter("position");
                    Staff staff = new Staff(firstname, lastname, position, username);

                    v = staffDAO.addStaff(username, password, firstname, lastname, role, position);

                }
            } else {

                return "Cannot Add User";
            }


        return "Registration Successful";
    }


        @ResponseBody
        @PostMapping(value = "/update_user_process", produces = "plain/text")
        public String updateUser(WebRequest request ) {

            String role = null,  username = null,  password = null, firstname = null, lastname = null, dept = null, position = null;

            String type = request.getParameter("type");

            String failed;
            boolean v = true;

            role = request.getParameter("role_u");
            username = request.getParameter("username_u");
            firstname = request.getParameter("firstname_u");
            lastname = request.getParameter("lastname_u");
            String old_username = request.getParameter("old_username");     //delete this later

            User user = new User();                                     //insert the data into the user table first
            user.setUsername(username);

    //        v = userDAO.updateUser(user);         //If you find a way change it

                if (v) {                                                        // do not continue the process if inserting into the user table failed

                    role = "ROLE_" + role.toUpperCase();

    //                Authorities authority = new Authorities();
    //                authority.setAuthority(role);
    //                authority.setUsername(username);
    //                userDAO.updateAuthority(authority);

                    if (role.contentEquals("ROLE_STUDENT" ) ) {             //check if it's a student or staff

                        failed = request.getParameter("failed_u");
                        dept = request.getParameter("dept_u");
                        String year = request.getParameter("year_u");

    //                    Student student = new Student(firstname, lastname, username, dept, year, failed);

    //                    studentDAO.saveStudent(student);
                        studentDAO.updateStudent(old_username, username, firstname, lastname, failed, dept, year);

                    } else {

                        position = request.getParameter("position_u");

                        Staff staff = new Staff(firstname, lastname, position, username);

                        staffDAO.updateStaff(old_username, username, firstname, lastname, position);

    //                    v = staffDAO.saveStaff(staff);

                    }
                } else {

                    return "Cannot Update User";
                }


                return "Update Successful";
            }

        @ResponseBody
        @PostMapping(value = "/delete_user_process", produces = "plain/text")
        public String deleteStudent(WebRequest request ,HttpServletResponse response ,Model model) {

            boolean v = false;
            String username = request.getParameter("username_d");
            try {

                v = userDAO.deleteUser(username);

            } catch (Exception e) {

            }
            if ( !v ) return "Deletion Failed";

            return "Deletion Successful";
        }




}

