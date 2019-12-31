package gr.hua.dit.ds.team52.dao;

import gr.hua.dit.ds.team52.entity.Role;
import gr.hua.dit.ds.team52.entity.Service;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.util.List;

public interface ServiceDAO {
    public String[] getRoles(String serviceName);
    public List<Service> getServices();
    public void addRoleToService(Service service,Role role);
    public void deleteRoleFromService(Service service,Role role);

}
