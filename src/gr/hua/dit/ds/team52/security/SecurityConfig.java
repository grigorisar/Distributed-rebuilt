package gr.hua.dit.ds.team52.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource myDataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // add some dummy users for in memory authentication
//        auth.inMemoryAuthentication().withUser("john").password("{noop}test123").roles("STUDENT");        // "ROLE_" is automatically added
//        auth.inMemoryAuthentication().withUser("mary").password("{noop}test123").roles("STAFF");
//        auth.inMemoryAuthentication().withUser("susan").password("{noop}test123").roles("ADMIN");

        auth.jdbcAuthentication()
                .dataSource(myDataSource)
                .usersByUsernameQuery("select username, password, enabled "
                        + "from user "
                        + "where username = ?")
                .authoritiesByUsernameQuery("select username, authority "
                        + "from authorities "
                        + "where username = ?");


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/**/").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")               //should not start with _ROLE
                .antMatchers("/student/**").hasRole("STUDENT")
                .antMatchers("/staff/**").hasRole("STAFF")
                .anyRequest().authenticated()
                .and()
                //.loginProcessingUrl("/authUser") for custom login page
                .formLogin()
                .and()
                .csrf().disable();;
    }

    //public static PasswordEncoder encoder() {
    //    return new BCryptPasswordEncoder();
    //}
}


