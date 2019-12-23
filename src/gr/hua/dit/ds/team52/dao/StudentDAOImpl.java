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
    public List<Petition> getPetitions(){
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query
        Query<Petition> query = currentSession.createQuery("from Petition", Petition.class);

        // execute the query and get the results list
        List<Petition> Petitions = query.getResultList();
        return Petitions;
    }

}
