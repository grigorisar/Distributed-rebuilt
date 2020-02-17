package gr.hua.dit.ds.team52.security;

import gr.hua.dit.ds.team52.CustomAuthenticationProvider;
import gr.hua.dit.ds.team52.dao.ServiceDAO;
import gr.hua.dit.ds.team52.entity.Role;
import gr.hua.dit.ds.team52.entity.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //SERVICES AND THEIR ROLES
    private String[] CREATE_PETITION_ROLES={"STUDENT","ADMIN"};
    private String[] EXAMINE_PETITION_ROLES={"ADMIN","STAFF"};
    private String[] EXAMINE_INTERNSHIP_ROLES={"ADMIN","STAFF"};
    private String[] MANAGE_APPLICATION_ROLES={"ADMIN"};



    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    DataSource myDataSource;

    @Autowired
    private CustomAuthenticationProvider authProvider;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // add some dummy users for in memory authentication
//        auth.inMemoryAuthentication().withUser("john").password("{noop}test123").roles("STUDENT");        // "ROLE_" is automatically added
//        auth.inMemoryAuthentication().withUser("mary").password("{noop}test123").roles("STAFF");
//        auth.inMemoryAuthentication().withUser("susan").password("{noop}test123").roles("ADMIN");

//        auth.authenticationProvider(authProvider);

        auth.jdbcAuthentication()
                .dataSource(myDataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username, password, enabled "     //table to find the user
                        + "from user "
                        + "where username = ? and enabled <> 0")
                .authoritiesByUsernameQuery("select username, authority "       //table to find the role of user
                        + "from authorities "
                        + "where username = ?");
    }

    private PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/internship/**").permitAll()
                .antMatchers("/access_denied").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/create").permitAll()                         //for the create user page
                .antMatchers("/create_user_process").permitAll()            //and permit the user creation process
                .antMatchers("/manager/**").hasAnyRole(fetchServiceRoles("Manage Application"))
                .antMatchers("/student/**").hasAnyRole(fetchServiceRoles("Create Petition"))
                .antMatchers("/staff/petition_list/**").hasAnyRole(fetchServiceRoles("Examine Petitions"))
                .antMatchers("/staff/internship_list/**").hasAnyRole(fetchServiceRoles("Examine Internships"))
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");
//                .and().httpBasic();

        //.loginProcessingUrl("/authUser") //TODO custom controller page
    }

    public String[] fetchServiceRoles(String name){
        int i=0;
        Service service = serviceDAO.getServiceByName(name);
        String[] roles = new String[service.getRoles().size()];
        for (Role tempRole:service.getRoles()) {
            roles[i++] = tempRole.getTitle();
        }
        return roles;
    }
}
