package gr.hua.dit.ds.team52.dao;

import gr.hua.dit.ds.team52.entity.Authorities;
import gr.hua.dit.ds.team52.entity.User;

import java.util.List;

public interface UserDAO {

    public List<User> getUserList();
    public Boolean addUser(User user);
    public Boolean updateUser(User user);
    public Boolean addAuthority(Authorities authority);
    public Boolean updateAuthority(Authorities authority);
    public boolean deleteUser(String username);


}
