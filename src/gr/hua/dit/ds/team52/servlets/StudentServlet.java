package gr.hua.dit.ds.team52.servlets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


//@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //String username = request.getParameter("username");
        //String password = request.getParameter("password");

        //System.out.println(username + password);
        String c = request.getParameter("list");

        System.out.println(c);

        try {

        } catch (Exception e) {
            System.out.println(e);
        }

        // create session factory
        SessionFactory factory = new Configuration().
                configure("hibernate.cfg.xml")
//                .addAnnotatedClass(Student.class)
//                .addAnnotatedClass(Petition.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {
            session.beginTransaction();
            //example of search and append
            String username = "MikeHawk313";
//            List<Student> students = session.createQuery("from Student s where s.username='"+username+"'").getResultList();
//            System.out.println("ID IS :"+ session.get(Student.class,students.get(0).getId()));
//            Student student = students.get(0);
//
//            //create petitions
//            Petition pet1 = new Petition("free the weed","petitioning for the legalization of marijuana");
//            Petition pet2 = new Petition("free hong kong","petitioning for the independence of hong kong");
//            Petition pet3 = new Petition("free the middle east","petitioning for the end of conflict in the middle east");
//
//            //add them to student
//            student.add(pet1);
//            student.add(pet2);
//            student.add(pet3);
//
//            //save to session
//            session.save(pet1);
//            session.save(pet2);
//            session.save(pet3);

            //commit changes
            session.getTransaction().commit();

            System.out.println("Done!");

        }finally {
            //clean up
            session.close();
            factory.close();
        }

        response.sendRedirect("index.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
