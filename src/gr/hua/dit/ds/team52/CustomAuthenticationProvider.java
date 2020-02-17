package gr.hua.dit.ds.team52;

import gr.hua.dit.ds.team52.dao.UserDAO;
import gr.hua.dit.ds.team52.entity.Authorities;
import gr.hua.dit.ds.team52.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    DataSource myDataSource;

    @Autowired
    UserDAO userDAO;

    @Autowired
    private AuthenticationManagerBuilder auth;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

    @Override
    public Authentication authenticate(Authentication authentication )
            throws AuthenticationException {

//
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();

//        System.out.println(username + password);

        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";

        User user = userDAO.getUserByName(username);

        if ( user == null) {
            throw new BadCredentialsException("1000");
        }
        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("1000");
        }
        Authorities a = userDAO.getUserAuthority(username);

        String authority = a.getRole();
        ArrayList list = new ArrayList();
        list.add(new SimpleGrantedAuthority(authority));

        return new UsernamePasswordAuthenticationToken(username, encoder.encode(password), list);

//        if (shouldAuthenticateAgainstThirdPartySystem()) {

//        try {
//            auth.jdbcAuthentication()
//                    .dataSource(myDataSource)
//                    .passwordEncoder(passwordEncoder())
//                    .usersByUsernameQuery("select :username, :password, enabled "     //table to find the user
//                            + "from user "
//                            + "where :username = ? and enabled <> 0")
//                    .authoritiesByUsernameQuery("select :username, authority "       //table to find the role of user
//                            + "from authorities "
//                            + "where username = ?");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        // use the credentials
            // and authenticate against the third-party system
//            return new UsernamePasswordAuthenticationToken(
//                    username, password, new ArrayList<>());
//        } else {
//            return null;
//        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}