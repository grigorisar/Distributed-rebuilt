package gr.hua.dit.ds.team52.dao;

import gr.hua.dit.ds.team52.entity.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StaffDAOImpl implements StaffDAO {
        // inject the session factory

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public List<Staff> getStaff(){
        Session currentSession = sessionFactory.getCurrentSession();
        // create a query
        Query<Staff> query = currentSession.createQuery("from Staff", Staff.class);
        // execute the query and get the results list
        List<Staff> staff = query.getResultList();
        return staff;
    }

    @Override
    @Transactional
    public Boolean saveStaff(Staff staff) {
        /**
         * This function is both an UPDATE and INSERT tool.
         */
        Session currentsession=sessionFactory.getCurrentSession();

        if (staff.getId()!=0) {
            currentsession.update(staff);
        }else {
            currentsession.save(staff);
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean deleteStaff(String username) {
        /**
         * This function is both an UPDATE and INSERT tool.
         */
        Session currentsession=sessionFactory.getCurrentSession();

        try {
            currentsession.delete(username,Staff.class);
        }catch (Exception e){
            return false;           //TODO
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
        List<Petition> petitions = query.getResultList();
        return petitions;
    }

    @Override
    @Transactional
    public List<Service> getServices(){
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query
        Query<Service> query = currentSession.createQuery("from Service", Service.class);

        System.out.println(query.getFirstResult());
        // execute the query and get the results list
        List<Service> services = query.getResultList();
        return services;
    }

    @Override
    public Service searchService(String title) {
        Session currentSession = sessionFactory.getCurrentSession();
        // create a query
        Service service = currentSession.createQuery("from Service S WHERE s.title LIKE '"+title+"'" , Service.class).getSingleResult();
        return service;
    }

    @Override
        @Transactional
        public List<Internship> getInternships(){
            Session currentSession = sessionFactory.getCurrentSession();

            // create a query
            Query<Internship> query = currentSession.createQuery("from Internship", Internship.class);

            // execute the query and get the results list
            List<Internship> internships = query.getResultList();
            return internships;
        }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public boolean updateStaff(String old_username, String username, String firstname, String lastname, String position) {

        Session currentSession = sessionFactory.getCurrentSession();

        try {
            int q =0;

            if (old_username.equals(username)) {

            }   else {

                q = currentSession.createSQLQuery("UPDATE `user` SET `username` = '" + username + "'  WHERE `user`.`username` = '" + old_username + "';").executeUpdate();

            }

            q = currentSession.createSQLQuery("UPDATE `staff` SET  `first_name` = '" + firstname + "', `last_name` = '" + lastname + "'," +
                    " `position` = '" + position + "' WHERE `student`.`username` = '" + username + "'; ").executeUpdate();

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public boolean addStaff(String username, String password, String firstname, String lastname, String role, String position) {
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
            return false;
        }
        return true;

    }
}
