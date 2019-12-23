package gr.hua.dit.ds.team52.controller;


import gr.hua.dit.ds.team52.dao.StaffDAO;
import gr.hua.dit.ds.team52.entity.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

@Controller
@RequestMapping("/manager")
public class ManagerController {

        // inject the customer dao
        @Autowired
        private StaffDAO staffDAO;

        @RequestMapping("/")
        public String showMenu(Model model) {
            return "manager/index-manager";
        }

        @RequestMapping("/user")
        public String userManager(Model model) {
            // get users from dao
//            List<Customer> customers = customerDAO.getCustomers();

            // add the users to the model
            model.addAttribute("users", null);

            return null; //TODO user manager JSP
        }

        @RequestMapping("/role")
        public String roleManager(Model model) {
            // get roles from dao
//            List<Customer> customers = customerDAO.getCustomers();

            // add the roles to the model
            model.addAttribute("roles", null);
            return null; //TODO role manager JSP
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

}
