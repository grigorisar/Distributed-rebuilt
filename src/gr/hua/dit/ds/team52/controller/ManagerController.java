package gr.hua.dit.ds.team52.controller;


import com.google.gson.Gson;
import gr.hua.dit.ds.team52.dao.ServiceDAO;
import gr.hua.dit.ds.team52.dao.StaffDAO;
import gr.hua.dit.ds.team52.dao.StudentDAO;
import gr.hua.dit.ds.team52.dao.UserDAO;
import gr.hua.dit.ds.team52.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/manager")
public class ManagerController {
        // inject the customer dao

    @Autowired
    private StaffDAO staffDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private StudentDAO studentDAO;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);


    @RequestMapping("/")
    public String showMenu(Model model) {
        return "manager/startpage-manager";
    }

    @RequestMapping("/user")
    public String userManager(Model model) {
         //DISABLE ENABLE USERS ONLY
        List<User> users = userDAO.getUserList();
        model.addAttribute("users",users);
        return "manager/list-user";
    }

    @RequestMapping("/student")
    public String studentManager(Model model) {
        List<Student> students = userDAO.getStudents();
        model.addAttribute("students", students);
        return "manager/student-manager";
    }

    @RequestMapping("/staff")
    public String staffManager(Model model) {
        List<Staff> staff = userDAO.getStaff();
        model.addAttribute("staff", staff);
        return "manager/staff-manager";
    }

    @RequestMapping("/service")
    public String serviceManager(Model model) {
        // get services from dao
        List<Service> services = serviceDAO.getServices();
        List<Role> roles = serviceDAO.getRoles();

        // add the services to the model
        model.addAttribute("services", services);
        model.addAttribute("roles", roles);

        return "manager/service-manager";
    }

    @RequestMapping("/role")
    public String roleManager(Model model) {
        // get roles from dao
        List<Role> roles = serviceDAO.getRoles();
        // add the roles to the model
        model.addAttribute("roles", roles);
        return "manager/role-manager";
    }

    @ResponseBody
    @RequestMapping (value ="/create_role" , method = RequestMethod.POST)
    public String createRole(WebRequest request ) {
        Role role = new Role();
        //set title id will auto generate
        role.setTitle(request.getParameter("title").toUpperCase());

        serviceDAO.saveRole(role);
        return "Role Added.";
    }

    @ResponseBody
    @RequestMapping (value ="/update_role" , method = RequestMethod.POST)
    public String updateRole(WebRequest request ) {
        //search
        Role role = serviceDAO.getRoleByName(request.getParameter("old_title"));
        role.setTitle(request.getParameter("title").toUpperCase());
        serviceDAO.saveRole(role);
        return "Role Updated.";
    }

    @ResponseBody
    @RequestMapping (value ="/delete_role" , method = RequestMethod.POST)
    public String deleteRole(WebRequest request ) {
        //search
        Role role = serviceDAO.getRoleByName(request.getParameter("title"));
        serviceDAO.deleteRole(role);
        return "Role Deleted.";
    }



    @ResponseBody
    @RequestMapping (value ="/create_service" , method = RequestMethod.POST)
    public String createService(WebRequest request ) {

        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String[] roles = request.getParameterValues("role[]");


        Service service= new Service();
        service.setDescription(description);
        service.setTitle(title);

        for (String temp:roles) {
            service.add(serviceDAO.getRoleByName(temp));
        }
        serviceDAO.saveService(service);
        return "Service Added.";
    }

    @ResponseBody
    @RequestMapping (value ="/update_service" , method = RequestMethod.POST)
    public String updateService (WebRequest request ) {

        String old_title = request.getParameter("old_title");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String[] roles = request.getParameterValues("role[]");

        Service service = serviceDAO.getServiceByName(old_title);

        //clear list
        service.setRoles(null);
        service.setDescription(description);
        service.setTitle(title);

        //fill list with new roles
        for (String temp:roles) {
            service.add(serviceDAO.getRoleByName(temp));
        }
        serviceDAO.saveService(service);
        return "Service Updated.";
    }

    @ResponseBody
    @RequestMapping (value ="/delete_service" , method = RequestMethod.POST)
    public String deleteService (WebRequest request ) {
        Service service = serviceDAO.getServiceByName(request.getParameter("title"));
        serviceDAO.deleteService(service);
        return "Service Deleted.";
    }

    @ResponseBody
    @PostMapping(value = "/create_user_process", produces = "plain/text")
    public String createUser(WebRequest request ) {

        String role = "ROLE_"+request.getParameter("role").toUpperCase();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");

        try {
            password = encoder.encode(password);
            System.out.println(password);
        }catch (Exception e) {
            e.printStackTrace();
        }

        User user = new User(username,password,1);
        userDAO.saveUser(user);

//        Authorities authority = new Authorities();
//        authority.setAuthority(role);
//        authority.setUsername(username);
//        userDAO.saveAuthority(authority);

        if (role.contentEquals("ROLE_STUDENT" )){             //check if it's a student or staff
            Student student= new Student(firstname,lastname,username);
            student.setDept(request.getParameter("dept"));
            //cant bind null to integer
            student.setFailed(Integer.parseInt(Objects.requireNonNull(request.getParameter("failed"))));
            student.setYear(Integer.parseInt(Objects.requireNonNull(request.getParameter("year"))));
            userDAO.saveStudent(student);
        } else {
            Staff staff = new Staff(firstname,lastname,username);
            staff.setPosition(request.getParameter("position"));
            userDAO.saveStaff(staff);
        }
        return "Registration Successful";
    }

    @ResponseBody
    @PostMapping(value = "/update_user_process", produces = "plain/text")
    public String updateUser(WebRequest request ) {

        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String old_username = request.getParameter("old_username");     //delete this later
        String role ="ROLE_"+request.getParameter("role").toUpperCase();

        if (role.contentEquals("ROLE_STUDENT" ) ) {             //check if it's a student or staff
            Student student =userDAO.getStudentByUsername(old_username);

            student.setUsername(username);
            student.setFirstName(firstname);
            student.setLastName(lastname);
            student.setFailed(Integer.parseInt(request.getParameter("failed")));
            student.setDept(request.getParameter("dept"));
            student.setYear(Integer.parseInt(request.getParameter("year")));

            userDAO.saveStudent(student);
        } else {
            Staff staff = userDAO.getStaffByUsername(old_username);

            staff.setFirstName(firstname);
            staff.setLastName(lastname);
            staff.setUsername(username);
            staff.setPosition(request.getParameter("position"));

            userDAO.saveStaff(staff);
        }

        userDAO.saveAuthority(username,role); // necessary to bind role to user

        return "Staff updated";
    }

    @ResponseBody
    @PostMapping(value = "/delete_user_process", produces = "plain/text")
    public String deleteUser(WebRequest request ,HttpServletResponse response ,Model model) {

        boolean v = false;
        String username = request.getParameter("username");
//        String role = request.getParameter("role_d");
        try {
            v = userDAO.deleteUser(username);
        } catch (Exception e) {

        }
        if ( !v ) return "Deletion Failed";

        return "Deletion Successful";
    }

    @ResponseBody
    @GetMapping(value = "/get_students_json", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStudentsJson(WebRequest request ,HttpServletResponse response ,Model model) {

        List<Student> students = userDAO.getStudents();

        Gson gson = new Gson();

        return gson.toJson(students);
    }

    @ResponseBody
    @GetMapping(value = "/get_staff_json", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getStaffJson(WebRequest request ,HttpServletResponse response ,Model model) {

        List<Staff> staff = userDAO.getStaff();

        Gson gson = new Gson();

        return gson.toJson(staff);
    }


}

