package com.navikas.finalyear.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    @Autowired
    public SecurityConfig(DataSource datasource){
        this.dataSource = datasource;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Select statement to have only 1 where idea taken from https://stackoverflow.com/questions/3156908/using-a-single-common-where-condition-for-union-in-sql/3156941
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT * FROM (\n" +
                        "  SELECT a.email, a.password, 'TRUE' as enabled FROM customer_user a  \n" +
                        "  UNION  \n" +
                        "  SELECT a.email, a.password, 'TRUE' as enabled FROM restaurant_user a  \n" +
                        ") A\n" +
                        "WHERE email =? ")
                .authoritiesByUsernameQuery("SELECT * FROM (\n" +
                        "  SELECT a.email, 'ROLE_USER' as authority FROM customer_user a  \n" +
                        "  UNION  \n" +
                        "  SELECT a.email, 'ROLE_USER' FROM restaurant_user a  \n" +
                        ") A\n" +
                        "WHERE email =? ")
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //.antMatchers("/*").permitAll()
                //.antMatchers("/css/*").permitAll()
                .antMatchers("/register").permitAll()
                .and()
                .formLogin()
                    .defaultSuccessUrl("/success.html", true);


    }
}
