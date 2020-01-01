package gr.hua.dit.ds.team52.controller;

import gr.hua.dit.ds.team52.dao.StaffDAO;
import gr.hua.dit.ds.team52.entity.Internship;
import gr.hua.dit.ds.team52.entity.Petition;
import gr.hua.dit.ds.team52.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

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
        List<Internship> internshipsP = staffDAO.getInternshipsPending();
        List<Internship> internshipsA = staffDAO.getInternshipsAccepted();

        // add the customers to the model
        model.addAttribute("internshipsPending", internshipsP);
        model.addAttribute("internshipsAccepted", internshipsA);

        return "staff/list-internships";
    }

    @ResponseBody
    @PostMapping(value = "/accept_internship_process", produces = "plain/text")
    public String createUser(WebRequest request ) {

        String title = request.getParameter("title");

        boolean v = staffDAO.acceptInternship(title);

        if ( v ) return "Internship successfully accepted";

        return "Internship with given title is already accepted or it doesn't exist";
    }

}
