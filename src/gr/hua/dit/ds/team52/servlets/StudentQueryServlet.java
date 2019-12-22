package gr.hua.dit.ds.team52.servlets;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.util.List;

//@WebServlet(name = "gr.hua.distributed.team52.resources.StudentQueryServlet")
public class StudentQueryServlet extends HttpServlet {

        public static void main(String[] args) {

            // create session factory
            SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
//                    .addAnnotatedClass(User.class)
//                    .addAnnotatedClass(Student.class)
//                    .addAnnotatedClass(Petition.class)
                    .buildSessionFactory();

            // create session
            Session session = factory.getCurrentSession();

            try {
                // start a transaction
                session.beginTransaction();

                // query students
//                List<Student> students = session.createQuery("from Student").getResultList();

                System.out.println("all students");
//                displayStudents(students);

                // query students: lastName='Τσαδήμας'
//                students = session.createQuery("from Student s where s.lname='Τσαδήμας'").getResultList();
                System.out.println("students: lastName='Τσαδήμας'");
//                displayStudents(students);

                // query students: lastName='Τσαδήμας' OR firstName='Βασίλης'
//                students = session.createQuery("from Student s where s.lname='Τσαδήμας'" + " OR s.firstName='Βασίλης'")
//                        .getResultList();
                System.out.println("students: lastName='Τσαδήμας' OR firstName='Βασίλης'");
//                displayStudents(students);

                // query students where email ends with hua.gr
                /*students = session.createQuery("from Student s where s.email LIKE '%hua.gr'").getResultList();
                System.out.println("students where email LIKE '%hua.gr'");
                displayStudents(students);
                */
                // commit transaction
                session.getTransaction().commit();

                System.out.println("Done!");

            } finally {
                factory.close();
            }

        }

//        private static void displayStudents(List<Student> students) {
            // display students
//            for (Student student : students) {
//                System.out.println(student);
//            }
//        }

}
