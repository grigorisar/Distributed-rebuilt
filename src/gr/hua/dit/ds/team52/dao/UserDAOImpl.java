package gr.hua.dit.ds.team52.dao;

import gr.hua.dit.ds.team52.actors.StudentEntity;


import org.hibernate.query.NativeQuery;
import gr.hua.dit.ds.team52.actors.UserEntity;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    // inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<StudentEntity> getStudentList() {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query
        Query<StudentEntity> query = currentSession.createQuery("from StudentEntity", StudentEntity.class);

        // execute the query and get the results list
        List<StudentEntity> students = query.getResultList();

        // return the results
        return students;
    }

    @Override
    @Transactional
    public int addStudent(String username, String password, String firstname, String lastname, String role, int failed, String dept, String year) {
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
            return 0;
        }
        return 1;

    }

    @Override
    @Transactional
    public int addStaff(String username, String password, String firstname, String lastname, String role, String position) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

//        UserEntity e = new UserEntity();
//        StudentEntity s =new StudentEntity();

        try {

            int q = currentSession.createSQLQuery("INSERT INTO `user` (`username`, `password`, `enabled`) VALUES ( '" + username + "' , '" + password + "' ,  '1')").executeUpdate();

            q = currentSession.createSQLQuery("INSERT INTO `staff` (`id`, `first_name`, `last_name`, `username`, `position`) VALUES ( NULL, '" + firstname + "', '" +
                    lastname + "', '" + username + "', '" + position + "');").executeUpdate();

            q = currentSession.createSQLQuery("INSERT INTO authorities (username, authority) VALUES ('" + username +"', '" + role + "') ").executeUpdate();
        } catch (Exception e) {
            return 0;
        }
        return 1;

    }

    @Override
    @Transactional
    public int addPetition(String title, String description, String student_username) {
        Session currentSession = sessionFactory.getCurrentSession();

        String currentUserName = "current username not found";


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            currentUserName = authentication.getName();
        }

        System.out.println(currentUserName);

        try {

            int q = currentSession.createSQLQuery("INSERT INTO `petition` (`id`, `title`, `description`, `status`, `student_username`) VALUES ( NULL, '" + title + "', '" +
                    description + "', '" + "pending" + "', '" + currentUserName + "');").executeUpdate();

              //TODO change the student_username variable please

        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    @Transactional
    public int updateStudent(String old_username, String username, String firstname, String lastname, int failed, String dept, String year) {

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
            return 0;
        }
        return 1;
    }

    @Override
    @Transactional
    public int deleteStudent(String username) {

        Session currentSession = sessionFactory.getCurrentSession();

        try {

            int q = currentSession.createSQLQuery(" DELETE FROM `user` WHERE `username`IN ('" + username + "');").executeUpdate();

        } catch (Exception e) {
            return 0;
        }
        return 1;
    }




}
