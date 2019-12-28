package gr.hua.dit.ds.team52.dao;

import gr.hua.dit.ds.team52.entity.Authorities;
import gr.hua.dit.ds.team52.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

//import org.hibernate.query.NativeQuery;

@Repository
public class UserDAOImpl implements UserDAO {

    // inject the session factory
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<User> getUserList() {
        // get current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        // create a query
        Query<User> query = currentSession.createQuery("from User", User.class);

        // execute the query and get the results list
        List<User> users = query.getResultList();

        // return the results
        return users;
    }

    @Override
    @Transactional
    public Boolean addUser(User user) {

        try {
            Session currentsession=sessionFactory.getCurrentSession();

            currentsession.save(user);

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }

        return true;
    }

    @Override
    @Transactional
    public Boolean updateUser(User user) {

        try {
            Session currentsession=sessionFactory.getCurrentSession();

            currentsession.update(user);

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }

        return true;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public boolean deleteUser(String username) {

        Session currentSession = sessionFactory.getCurrentSession();

        try {

            int q = currentSession.createSQLQuery(" DELETE FROM `user` WHERE `username`IN ('" + username + "');").executeUpdate();

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public Boolean addAuthority(Authorities authority) {
        try {
            Session currentsession=sessionFactory.getCurrentSession();

            currentsession.save(authority);

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }

        return true;
    }

    @Override
    @Transactional
    public Boolean updateAuthority(Authorities authority) {
        try {
            Session currentsession=sessionFactory.getCurrentSession();

            currentsession.update(authority);

        } catch (Exception e) {
            // TODO: handle exception
            return false;
        }

        return true;
    }


}
