package gr.hua.dit.ds.team52.dao;

import gr.hua.dit.ds.team52.entity.Role;
import gr.hua.dit.ds.team52.entity.Service;
import gr.hua.dit.ds.team52.entity.Staff;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ServiceDAOImpl implements ServiceDAO{


    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional
    public String[] getRoles(String serviceName) {
        Session currentSession = sessionFactory.getCurrentSession();
        //id 1=manager 2=petitions 3=internships 6=create petition
        // create a query
        Service service = currentSession.createQuery("from Service S WHERE S.title LIKE '"+serviceName+"'", Service.class).getSingleResult();

        List<Role> rolesOBJ =service.getRoles();

        String[] roles = new String[rolesOBJ.size()];
        int i=0;
        for (Role tempRole:rolesOBJ) {
            roles[i++] = tempRole.getTitle();
        }
        return roles;
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
    public void addRoleToService(Service service, Role role) {
        Session currentSession = sessionFactory.getCurrentSession();

        service.add(role);
        currentSession.save(service);

    }

    @Override
    public void deleteRoleFromService(Service service,Role role) {
        Session currentSession = sessionFactory.getCurrentSession();

        service.getRoles().remove(role);
        currentSession.save(service);
    }
}
