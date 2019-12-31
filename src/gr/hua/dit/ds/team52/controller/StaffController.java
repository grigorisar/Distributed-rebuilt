package gr.hua.dit.ds.team52.controller;

import gr.hua.dit.ds.team52.dao.StaffDAO;
import gr.hua.dit.ds.team52.entity.Internship;
import gr.hua.dit.ds.team52.entity.Petition;
import gr.hua.dit.ds.team52.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private StaffDAO staffDAO;

    @RequestMapping("/petition_list")
    public String listPetitions(Model model){

        // get customers from dao
        List<Petition> petitions = staffDAO.getPetitions();

        // add the customers to the model
        model.addAttribute("petitions", petitions);

        return "staff/list-petitions";
    }

    @RequestMapping("/internship_list")
    public String listInternships(Model model){

        // get customers from dao
        List<Internship> internships = staffDAO.getInternships();

        // add the customers to the model
        model.addAttribute("internships", internships);

        return "staff/list-internships";
    }

}
