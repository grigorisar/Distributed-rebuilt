package gr.hua.dit.ds.team52.dao;

import gr.hua.dit.ds.team52.entity.Petition;
import gr.hua.dit.ds.team52.entity.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // inject the session factory
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public List<Student> getStudents(){
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query
        Query<Student> query = currentSession.createQuery("from Student", Student.class);

        // execute the query and get the results list
        List<Student> students = query.getResultList();
        return students;
    }

    @Override
    @Transactional
    public Boolean saveStudent(Student student) {
        /**
         * This function is both an UPDATE and INSERT tool.
         */
        Session currentsession=sessionFactory.getCurrentSession();

        if (student.getId()!=0) {
            currentsession.update(student);
        }else {
            currentsession.save(student);

        }
        return true;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public boolean updateStudent(String old_username, String username, String firstname, String lastname, String failed, String dept, String year) {

        Session currentSession = sessionFactory.getCurrentSession();

        try {
            int q =0;

            if (old_username.equals(username)) {

            }   else {

                q = currentSession.createSQLQuery("UPDATE `user` SET `username` = '" + username + "'  WHERE `user`.`username` = '" + old_username + "';").executeUpdate();

            }

            q = currentSession.createSQLQuery("UPDATE `student` SET  `first_name` = '" + firstname + "', `last_name` = '" + lastname + "'," +
                    " `dept` = '" + dept + "', `year` = '" + year + "', `failed` = '" + failed + "' WHERE `student`.`username` = '" + username + "'; ").executeUpdate();

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteStudent(String username) {

        Session currentsession=sessionFactory.getCurrentSession();

        try {
            currentsession.delete(username, Student.class);
        }catch (Exception e){
            return false;           //TODO
        }
        return true;

    }

    @Override
    @Transactional
    public boolean addStudent(String username, String password, String firstname, String lastname, String role, int failed, String dept, String year) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

//        UserEntity e = new UserEntity();
//        StudentEntity s =new StudentEntity();

        try {

            int q = currentSession.createSQLQuery("INSERT INTO `user` (`username`, `password`, `enabled`) VALUES ( '" + username + "' , '" + password + "' ,  '1')").executeUpdate();

            System.out.println(q);

            q = currentSession.createSQLQuery("INSERT INTO `student` (`id`, `first_name`, `last_name`, `username`, `dept`, `year`, `failed`) VALUES ( NULL, '" + firstname + "', '" +
                    lastname + "', '" + username + "', '" + dept + "', '" + year + "', '" + year + "');").executeUpdate();

            System.out.println(q);

            q = currentSession.createSQLQuery("INSERT INTO authorities (username, authority) VALUES ('" + username +"', '" + role + "') ").executeUpdate();

            System.out.println(q);

        } catch (Exception e) {
            return false;
        }
        return true;

    }

    @Override
    @Transactional
    public List<Petition> getPetitions(){
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query
        Query<Petition> query = currentSession.createQuery("from Petition", Petition.class);

        // execute the query and get the results list
        List<Petition> Petitions = query.getResultList();
        return Petitions;
    }

    @Override
    @Transactional
    public Boolean savePetition(Petition petition,String studentID) {
        /**
         * This function is an INSERT tool ONLY.
         */
        try {
            Session currentsession=sessionFactory.getCurrentSession();
            Student student = currentsession.get(Student.class,studentID);
            petition.setStudent(student);

            currentsession.save(petition);
        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }

        return true;
    }
}
