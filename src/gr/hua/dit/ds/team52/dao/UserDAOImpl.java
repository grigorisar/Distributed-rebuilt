package gr.hua.dit.ds.team52.dao;

import gr.hua.dit.ds.team52.actors.StudentEntity;


//import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void addStudent(String username, String password, String firstname, String lastname, String role, int failed, String dept, String year) {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();



        Query q = currentSession.createNativeQuery("INSERT INTO `user` (`username`, `password`, `enabled`) VALUES ('', '', '1');");


    }

}
